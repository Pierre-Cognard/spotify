package org.example;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Exception;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServerPrx;

import java.util.Scanner;

public class MusicClientApp {
    public static void main(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            // Initialiser la communication avec le serveur Ice
            communicator = Util.initialize(args);

            // Récupérer le proxy pour le serveur de musique
            ObjectPrx obj = communicator.stringToProxy("MusicServer:default -p 7000");
            MusicServerPrx musicServer = MusicServerPrx.checkedCast(obj);

            if (musicServer == null) {
                throw new RuntimeException("Invalid proxy");
            }




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

                    Music musicToAdd = new Music("New Music", "New Artist", "New Album", 2021, "Pop", "/path/to/music");
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

                    Music musicToUpdate = new Music("Updated Music", "Updated Artist", "Updated Album", 2022, "Rock", "/path/to/music");
                    boolean updated = musicServer.modifyMusic(musicToUpdate);
                    System.out.println("Updated music: " + updated);
                }
                case 4 -> {
                    System.out.println("Rechercher une musique :");
                    Music musicList = musicServer.searchMusic("Title", "Artist");
                    System.out.println("Found " + musicList.artist + " results:");
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
}
