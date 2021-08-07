package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[] pif = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            for (int q : pif) {
                for (int w : pif) {
                    out.write((q * w + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
