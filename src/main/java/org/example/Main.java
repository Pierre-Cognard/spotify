package org.example;

public class Main {

    public static void main(String[] args) {
        MusicServerApp server = new MusicServerApp();
        int status = server.main("MusicServerApp", args);
        System.exit(status);
    }
}
