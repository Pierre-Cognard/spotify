package org.example;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Exception;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServerPrx;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import java.util.Scanner;

public class MusicClientApp {
    public static void main(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            // Initialiser la communication avec le serveur Ice
            communicator = Util.initialize(args);

            // Récupérer le proxy pour le serveur de musique
            ObjectPrx obj = communicator.stringToProxy("MusicServer:default -p 1000");
            MusicServerPrx musicServer = MusicServerPrx.checkedCast(obj);

            if (musicServer == null) {
                throw new RuntimeException("Invalid proxy");
            }

            //playRtspStream(url2);
            //String list = musicServer.getMusicList();
            //System.out.println("list = "+list);

            Scanner scanner = new Scanner(System.in);
            int choix;
            System.out.println("Menu:");
            System.out.println("1. Ajouter une musique");
            System.out.println("2. Supprimer une musique");
            System.out.println("3. Modifier une musique");
            System.out.println("4. Rechercher une musique");
            System.out.println("5. Quitter");
            System.out.print("\nVeuillez choisir une option : ");


            choix = scanner.nextInt();
            switch (choix) {
                case 1 -> {
                    System.out.println("Ajouter une musique :");

                    Music musicToAdd = new Music("New Music", "New Artist", "/path/to/music");
                    boolean added = musicServer.addMusic(musicToAdd);
                    System.out.println("Added new music: " + added);
                }
                case 2 -> {
                    System.out.println("Supprimer une musique :");

                    boolean removed = musicServer.removeMusic("Title", "Artist");
                    System.out.println("Removed music: " + removed);
                }
                case 3 -> {
                    System.out.println("Modifier une musique :");

                    Music musicToUpdate = new Music("Updated Music", "Updated Artist", "/path/to/music");
                    boolean updated = musicServer.modifyMusic(musicToUpdate);
                    System.out.println("Updated music: " + updated);
                }
                case 4 -> {
                    System.out.println("Rechercher une musique :");
                    System.out.println("1. Par titre");
                    System.out.println("2. Par auteur");
                    System.out.print("\nVeuillez choisir une option : ");

                    Music musicSearch = null;
                    String recherche;
                    choix = scanner.nextInt();

                    switch (choix) {
                        case 1 -> {
                            System.out.println("Par titre :");
                            recherche = scanner.next();
                            musicSearch = musicServer.searchMusic(recherche, "");
                        }
                        case 2 -> {
                            System.out.println("Par auteur :");
                            recherche = scanner.next();
                            musicSearch = musicServer.searchMusic("", recherche);
                        }
                    }
                    if (musicSearch.getArtist().length() != 0){
                        System.out.println("Musique trouvée : " + musicSearch );
                        String url = musicServer.getMusicStream(musicSearch);
                        System.out.println("url : " + url );

                        controller(playRtspStream(url));
                    }
                    else{
                        System.out.println("Aucune musique trouvée !");
                    }
                }
                case 5 -> {
                    scanner.close();
                    System.exit(0);
                }
            }

        } catch (Exception ex) {
            System.err.println(ex);
            status = 1;
        } finally {
            if (communicator != null) {
                communicator.destroy();
            }
        }
        System.exit(status);
    }

    public static MediaPlayer playRtspStream(String rtspUrl) {
        // Assurez-vous que l'emplacement de votre installation VLC est correct.
        // Vous pouvez également définir la variable d'environnement VLC_PLUGIN_PATH pour éviter cette étape.
        String vlcPath = "C:\\Program Files\\VideoLAN\\VLC";
        System.setProperty("jna.library.path", vlcPath);

        // Créez un MediaPlayerFactory et un MediaPlayer.
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

        // Lire le flux RTSP
        mediaPlayer.media().play(rtspUrl);

        return mediaPlayer;
    }

    public static void controller(MediaPlayer mediaPlayer){
        System.out.println("Controleur :");
        int choix;
        Scanner scanner = new Scanner(System.in);

        while (mediaPlayer.status().isPlayable()){

            if (mediaPlayer.status().isPlaying()){
                System.out.println("1. Reprendre la lecture");
                System.out.println("2. Arreter la lecture");
                choix = scanner.nextInt();

                if (choix == 1){
                    mediaPlayer.controls().pause();
                } else if (choix == 2) {
                    mediaPlayer.release();
                    break;
                }

            }
            else{
                System.out.println("1. Mettre en pause la lecture");
                System.out.println("2. Arreter la lecture");
                choix = scanner.nextInt();
                if (choix == 1){
                    mediaPlayer.controls().pause();
                } else if (choix == 2) {
                    mediaPlayer.release();
                    break;
                }
            }
        }
    }
}
