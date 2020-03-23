package com.twilight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        CGameController gameController = CGameController.getInstance();
        gameController.gameLoop();

        try {
        }
        catch (Exception exc) {
        }
    }

    protected static void testFile(String fileName) throws IOException {
        File fout = new File("testfile.txt");
        fout.createNewFile();

        FileWriter fileWriter = new FileWriter(fout);
        fileWriter.write("Hello, ");

        BufferedWriter bufWriter = new BufferedWriter(fileWriter);
        bufWriter.write("world!");
        bufWriter.flush();
    }
}
