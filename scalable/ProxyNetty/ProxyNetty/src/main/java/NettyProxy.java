import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Proxy {

    private final int port;
    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;

    public Proxy(int port) {
        this.port = port;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
    }

    public void start(){
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ProxyInitializer(this))
                    .bind(this.port).sync().channel().closeFuture().sync();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }
}
