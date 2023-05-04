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

import java.io.File;
import java.util.ArrayList;

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

        Music s = null;

        if (title.length() != 0){
            for (Music m : musicsList){
                if (m.getTitle().equals(title)){
                    //System.out.println("musique :"+ m);
                    s = m;
                }
            }
        }
        else if (artist.length() != 0){
            for (Music m : musicsList){
                if (m.getArtist().equals(artist)){
                    //System.out.println("musique :"+ m);
                    s = m;
                }
            }
        }
        else{
            return s;
        }
        return s;
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
    public String getMusicStream(Music music, Current current) {
        // Assurez-vous que l'emplacement de votre installation VLC est correct.
        // Vous pouvez également définir la variable d'environnement VLC_PLUGIN_PATH pour éviter cette étape.
        String vlcPath = "C:\\Program Files\\VideoLAN\\VLC";
        System.setProperty("jna.library.path", vlcPath);

        // Générer une URL de streaming RTSP unique
        String rtspUrl = "rtsp://localhost:8554/" + music.getTitle();
        String musicFilePath = music.getPath(); // Assurez-vous que votre chemin de fichier est correct.

        // Préparer le streaming avec l'URL RTSP
        String[] mediaOptions = {
                ":sout=#rtp{sdp=rtsp://:8554/" + music.getTitle() + "}",
                ":sout-all",
                ":sout-keep"
        };

        // Ajoutez un écouteur pour détecter quand le média a fini de jouer
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                mediaPlayerFactory.release();
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

        String list = "";
        assert musicFiles != null;
        for (File file : musicFiles) {
            //System.out.println(file.getName());
            String titre = file.getName().replaceFirst("[.][^.]+$", "").split(" - ")[0];
            String artiste = file.getName().replaceFirst("[.][^.]+$", "").split(" - ")[1];
            list += file.getName().replaceFirst("[.][^.]+$", "") + ";";
            Music m = new Music(titre,artiste,file.getPath());
            musicsList.add(m);
        }
        //System.out.println("musicsList :"+musicsList);
    }

    @Override
    public void stopMusic(Current current) {
        mediaPlayer.release();
        mediaPlayerFactory.release();
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
