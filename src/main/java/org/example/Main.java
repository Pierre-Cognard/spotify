package org.example;

import name.finsterwalder.fileutils.FileUtils;
import org.example.MusicManager.Music;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        File musicDirectory = new File("./Musiques");
        File[] musicFiles = musicDirectory.listFiles();

        assert musicFiles != null;
        for (File file : musicFiles) {
            System.out.println(file.getName());
        }



        MusicServerI server = new MusicServerI();


        int status = server.run("MusicServerApp", args);
        System.exit(status);
    }
}
