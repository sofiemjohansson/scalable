package com.example.ScalableServer;

import com.example.ScaleableServer.CSV.GetInfoFromCSVFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScaleableServerApplication {

    public static void main(String[] args) {
        GetInfoFromCSVFile.loadFromFile();
        SpringApplication.run(ScaleableServerApplication.class, args);
    }

}
