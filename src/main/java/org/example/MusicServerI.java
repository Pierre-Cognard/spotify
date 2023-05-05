package org.example;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Exception;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServer;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MusicServerI implements MusicServer {

    ArrayList<Music> musicsList = new ArrayList<Music>();
    // Créez un MediaPlayerFactory et un MediaPlayer.
    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

    @Override
    public Music searchMusic(String title, String artist, Current current) {
        System.out.println("Client connected: " + current.con.toString());

        musicsList = new ArrayList<Music>();
        this.getMusicList(current); // update musics

        Music music = null;

        if (title.length() != 0){
            for (Music m : musicsList){
                if (m.getTitle().toLowerCase().contains(title.toLowerCase())){
                    //System.out.println("musique :"+ m);
                    music = m;
                }
            }
        }
        else if (artist.length() != 0){
            for (Music m : musicsList){
                if (m.getArtist().toLowerCase().contains(artist.toLowerCase())){
                    //System.out.println("musique :"+ m);
                    music = m;
                }
            }
        }
        else{
            return music;
        }
        return music;
    }

    @Override
    public boolean addMusic(Music music, Current current) {
        System.out.println("Client connected: " + current.con.toString());
        return false;
    }

    @Override
    public boolean removeMusic(String title, String artist, Current current) {

        File musicDirectory = new File("./Musiques");
        File[] musicFiles = musicDirectory.listFiles();
        File fileToDelete = null;

        assert musicFiles != null;
        for (File file : musicFiles){
            if (file.getName().contains(title) && file.getName().contains(artist)){
                fileToDelete = file;
            }
        }

        assert fileToDelete != null;
        if (fileToDelete.delete()) {
            System.out.println("Fichier supprimé : " + fileToDelete.getName());
            return true;
        } else {
            System.out.println("Impossible de supprimer le fichier : " + fileToDelete.getName());
            return false;
        }
    }

    @Override
    public boolean modifyMusic(Music music, String title, String artist, Current current) {
        System.out.println("Client connected: " + current.con.toString());

        File oldFile = new File(music.getPath());

        // Créez un objet File pour le nouveau nom de fichier
        File newFile = new File("./Musiques/"+title+" - "+artist+".mp3");

        // Renommez le fichier
        if (oldFile.renameTo(newFile)) {
            System.out.println("Renaming successful");
            return true;
        } else {
            System.out.println("Renaming failed");
            return false;
        }
    }

    @Override
    public String getMusicStream(Music music, Current current) {
        String vlcPath = "C:\\Program Files\\VideoLAN\\VLC";
        System.setProperty("jna.library.path", vlcPath);

        // Générer une URL de streaming RTSP unique
        String rtspUrl = "rtsp://localhost:8554/" + music.getTitle();
        String musicFilePath = music.getPath(); // Assurez-vous que votre chemin de fichier est correct.

        // Préparer le streaming avec l'URL RTSP
        String[] mediaOptions = {
                ":sout=#rtp{sdp=rtsp://:8554/" + music.getTitle() + "}",
                ":sout-all",
                ":sout-keep",
                ":no-sout-rtp-sap",
                ":no-sout-standard-sap"
        };

        // Ajoutez un écouteur pour détecter quand le média a fini de jouer
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                System.out.println("FIN DE LA MUSIQUE");
                //mediaPlayer.release();
                //mediaPlayerFactory.release();
            }

            @Override
            public void error(MediaPlayer mediaPlayer) {
                System.err.println("Error while streaming media: " + "test");
                mediaPlayer.release();
                mediaPlayerFactory.release();
            }
        });

        // Démarrer la diffusion en streaming
        System.out.println("Stream de la music : " + music);
        mediaPlayer.media().start(musicFilePath, mediaOptions);

        // Retourner l'URL RTSP pour que le client puisse lire le flux
        return rtspUrl;
    }

    @Override
    public void getMusicList(Current current) {
        File musicDirectory = new File("./Musiques");
        File[] musicFiles = musicDirectory.listFiles();

        assert musicFiles != null;
        for (File file : musicFiles) {
            //System.out.println(file.getName());
            String titre = file.getName().replaceFirst("[.][^.]+$", "").split(" - ")[0];
            String artiste = file.getName().replaceFirst("[.][^.]+$", "").split(" - ")[1];
            Music m = new Music(titre,artiste,file.getPath());
            musicsList.add(m);
        }
        //System.out.println("musicsList :"+musicsList);
    }


    @Override
    public boolean uploadMusic(String title, String artist, String path, Current current) {
        try {
            // Ouvrir le fichier sur le disque dur du client
            FileInputStream fileInputStream = new FileInputStream(path);

            // Lire le contenu du fichier dans un tableau de bytes
            byte[] fileContent = fileInputStream.readAllBytes();

            try {
                String filename = "Musiques/"+title + " - " + artist + ".mp3";
                File file = new File(filename);
                boolean created = file.createNewFile();
                if (!created) {
                    System.err.println("Failed to create file " + filename);
                    return false;
                }

                FileOutputStream out = new FileOutputStream(file);
                out.write(fileContent);
                out.close();
                System.out.println("Fichier crée: " + filename);
                return true;
            } catch (IOException ex) {
                System.err.println("Failed to create file : " + ex.getMessage());
                return false;
            }
        } catch (IOException e) {
            System.err.println("Error uploading music: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Music[] getMusics(Current current) {
        File musicDirectory = new File("./Musiques");
        File[] musicFiles = musicDirectory.listFiles();

        Music[] musics = new Music[musicFiles.length];

        for (int i = 0; i < musics.length; i++) {
            String titre = musicFiles[i].getName().replaceFirst("[.][^.]+$", "").split(" - ")[0];
            String artiste = musicFiles[i].getName().replaceFirst("[.][^.]+$", "").split(" - ")[1];
            musics[i] = new Music(titre,artiste,musicFiles[i].getPath());;
        }

        return musics;
    }


    public int run(String musicServerApp, String[] args) {

        Communicator communicator = null;
        try {
            communicator = Util.initialize(args);

            // Créer un objet servant pour le serveur de musique
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MusicServer", "default -p 1000");
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
