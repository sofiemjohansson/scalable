package com.example.ScaleableServer.LegoCSV;

import com.example.ScaleableServer.Entity.Lego;
import com.example.ScaleableServer.Service.ServiceLego;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetInfoFromCSVFile {

    public GetInfoFromCSVFile() {
    }

    private static List<Lego> legoList = new ArrayList<>();

    public static void loadFromFile() {
        try {
            File file = new File("lego.csv");
            Scanner sc = new Scanner(file);
            String headline = sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lineParts = line.split("");
                if (lineParts.length == 2) {
                    lineParts = ifIndexOutOfBounds(lineParts);
                }
                legoList.add(new Lego(Integer.parseInt(lineParts[0]) ,lineParts[1], Integer.parseInt(lineParts[2])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String[] ifIndexOutOfBounds(String[] arr) {

        String[] newArr = new String[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[2] = "0";
        return newArr;
    }

    public static List<Lego> getLegoList() {
        return legoList;
    }
}
