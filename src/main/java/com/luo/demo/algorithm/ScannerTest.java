package com.luo.demo.algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午6:42
 */
public class ScannerTest {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "/Users/luohui/conf2/gatewayServer";
        try (Scanner scanner = new Scanner(new FileInputStream(path))){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }
    }
}
