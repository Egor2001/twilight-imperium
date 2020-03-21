package com.twilight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello,   world!");

        try {
            testFile();

        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    protected static void testFile() throws IOException {
        File fout = new File("testfile.txt");
        fout.createNewFile();

        FileWriter fileW = new FileWriter(fout);
        fileW.write("Hello, ");
        fileW.flush();

        BufferedWriter bufWriter = new BufferedWriter(fileW);
        bufWriter.write("world!");
        bufWriter.flush();
    }
}
