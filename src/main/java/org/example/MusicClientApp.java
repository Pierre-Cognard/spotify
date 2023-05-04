package org.example;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Exception;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServerPrx;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

import java.util.ArrayList;
import java.util.Scanner;


public class MusicClientApp {

    private static boolean playing;

    public static void main(String[] args) {
        int status = 0;
        playing = false;
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


            while (!playing) {

                Scanner scanner = new Scanner(System.in);
                int choix;
                System.out.println("\n\nMenu:");
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

                        String titre, artiste, path;

                        System.out.print("Titre : ");
                        titre = scanner.next();

                        System.out.print("Artiste : ");
                        artiste = scanner.next();

                        System.out.print("Path : ");
                        path = scanner.next();

                        boolean added = musicServer.uploadMusic(titre, artiste, path);

                        if (added) {
                            System.out.println("Musique ajoutée avec succès !");
                        } else {
                            System.out.println("Erreur lors de l'ajout de la musique");
                        }
                    }
                    case 2 -> {
                        System.out.println("Supprimer une musique :");
                        System.out.println("Liste des musiques :");
                        int id;
                        Music[] musics = musicServer.getMusics();

                        for (int i = 0; i < musics.length; i++) {
                            System.out.println(i + 1 + ". " + musics[i].getTitle() + " - " + musics[i].getArtist());
                        }
                        System.out.print("\nVeuillez choisir un numéro : ");
                        id = scanner.nextInt();

                        boolean removed = musicServer.removeMusic(musics[id - 1].getTitle(), musics[id - 1].getArtist());
                        if (removed) {
                            System.out.println("Musique supprimée avec succès !");
                        } else {
                            System.out.println("Erreur lors de la suppression de la musique");
                        }
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
                                System.out.print("Titre : ");
                                recherche = scanner.next();
                                musicSearch = musicServer.searchMusic(recherche, "");
                            }
                            case 2 -> {
                                System.out.print("Auteur : ");
                                recherche = scanner.next();
                                musicSearch = musicServer.searchMusic("", recherche);
                            }
                        }
                        if (musicSearch.getArtist().length() != 0) {
                            System.out.println("Musique trouvée : " + musicSearch);
                            String url = musicServer.getMusicStream(musicSearch);
                            System.out.println("url : " + url);
                            playing = true;
                            controller(playRtspStream(url));
                        } else {
                            System.out.println("Aucune musique trouvée !");
                        }
                    }
                    case 5 -> {
                        scanner.close();
                        System.exit(0);
                    }
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
                    playing = false;
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
                    playing = false;
                    break;
                }
            }
        }
    }
}
