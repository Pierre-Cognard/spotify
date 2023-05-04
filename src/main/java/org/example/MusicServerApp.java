package org.example;

import Ice.Application;
import com.zeroc.Ice.Current;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServer;

public class MusicServerApp extends Application implements MusicServer {

    @Override
    public Music searchMusic(String title, String artist, Current current) {
        return null;
    }

    @Override
    public boolean addMusic(Music music, Current current) {
        return false;
    }

    @Override
    public boolean removeMusic(String title, String artist, Current current) {
        return false;
    }

    @Override
    public boolean modifyMusic(Music music, Current current) {
        return false;
    }
}