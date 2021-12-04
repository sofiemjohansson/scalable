import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InitializerProxy extends ChannelInitializer<SocketChannel> {

    private final Proxy proxy;
    private final List<Integer> nodes;

    public InitializerProxy(Proxy proxy) {
        this.proxy = proxy;
        nodes = new ArrayList<>();

        try {
            portFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        Bootstrap bootstrap = new Bootstrap();
        Channel channel = bootstrap.group(proxy.getWorkerGroup())
                .channel(NioSocketChannel.class)
                .handler(new ServerInitializer(socketChannel))
                .connect("localhost", roundRobin()).sync().channel();

        socketChannel.pipeline()
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new SimpleChannelInboundHandler<String>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                        channel.writeAndFlush(msg);

                        System.out.println("The number of server is " + nodeIndex + " number of servers is: " + nodes.size());
                    }
                });
    }

    private int nodeIndex = 0;

    public int roundRobin() {
        int port = nodes.get(nodeIndex++);

        System.out.println(port);

        if (nodeIndex >= nodes.size()) {
            nodeIndex = 0;
        }
        return port;
    }

    public void portFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("port.txt"));
        while(scanner.hasNext()){
            nodes.add(scanner.nextInt());
        }
        scanner.close();
    }
}
