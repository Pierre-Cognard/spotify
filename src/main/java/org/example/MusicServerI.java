package org.example;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Exception;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.MediaPlayer;
import java.io.File;
import java.util.ArrayList;

public class MusicServerI implements MusicServer {


    @Override
    public Music searchMusic(String title, String artist, Current current) {
        System.out.println("Client connected: " + current.con.toString());
        return null;
    }

    @Override
    public boolean addMusic(Music music, Current current) {
        System.out.println("Client connected: " + current.con.toString());
        return false;
    }

    @Override
    public boolean removeMusic(String title, String artist, Current current) {
        System.out.println("Client connected: " + current.con.toString());
        return false;
    }

    @Override
    public boolean modifyMusic(Music music, Current current) {
        System.out.println("Client connected: " + current.con.toString());
        return false;
    }

    @Override
    public String getMusicStream(String title, String artist) {

        String filePath = "./Musique/California.wav";
        String options = ":sout=#rtp{dst=localhost,port=8554,mux=ts}";

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        MediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();

        String[] mediaOptions = {options};
        mediaPlayer.playMedia(filePath, mediaOptions);

        String rtspUrl = String.format("rtsp://%s:%d/%s", "localhost", 8554, "test");

        return rtspUrl;

    }

    @Override
    public ArrayList<Music> musicList() {
        System.out.println("musicList");

        ArrayList<Music> list = new ArrayList<>();

        File directory = new File("Musiques");

        // Vérifier si le chemin est un dossier
        if (!directory.isDirectory()) {
            System.err.println("Error: Musiques is not a directory");
            return list;
        }

        // Récupérer la liste des fichiers dans le dossier
        File[] files = directory.listFiles();

        for (File file : files) {
            System.out.println(file.getName());
        }
        return list;
    }

    public int run(String musicServerApp, String[] args) {

        Communicator communicator = null;
        try {
            communicator = Util.initialize(args);

            // Créer un objet servant pour le serveur de musique
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MusicServer", "default -p 7000");
            MusicServerI musicServer = new MusicServerI();
            adapter.add(musicServer, Util.stringToIdentity("MusicServer"));

            // Activer l'adaptateur avec les objets servant
            adapter.activate();

            System.out.println("MusicServer started");

            // Attendre les demandes
            communicator.waitForShutdown();
        } catch (Exception ex) {
            System.out.println("Erreur");
            System.err.println(ex);
        } finally {
            if (communicator != null) {
                communicator.shutdown();
                communicator.destroy();
            }
        }

        return 0;
    }
}
