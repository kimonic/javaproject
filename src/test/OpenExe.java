package test;

import java.io.IOException;

public class OpenExe {
    public static void main(String[] a){
        try {
            Runtime.getRuntime().exec("D:\\Program Files (x86)\\Notepad++\\notepad++.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
