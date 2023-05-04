package org.example;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Exception;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServerPrx;

public class MusicClientApp {
    public static void main(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            // Initialiser la communication avec le serveur Ice
            communicator = Util.initialize(args);

            // Récupérer le proxy pour le serveur de musique
            ObjectPrx obj = communicator.stringToProxy("MusicServer:default -p 10000");
            MusicServerPrx musicServer = MusicServerPrx.checkedCast(obj);

            if (musicServer == null) {
                throw new RuntimeException("Invalid proxy");
            }












            // Tester la recherche de musique
            Music musicList = musicServer.searchMusic("Title", "Artist");
            System.out.println("Found " + musicList.artist + " results:");


            // Tester l'ajout de musique
            Music musicToAdd = new Music("New Music", "New Artist", "New Album", 2021, "Pop", "/path/to/music");
            boolean added = musicServer.addMusic(musicToAdd);
            System.out.println("Added new music: " + added);

            // Tester la suppression de musique
            boolean removed = musicServer.removeMusic("Title", "Artist");
            System.out.println("Removed music: " + removed);

            // Tester la modification de musique
            Music musicToUpdate = new Music("Updated Music", "Updated Artist", "Updated Album", 2022, "Rock", "/path/to/music");
            boolean updated = musicServer.modifyMusic(musicToUpdate);
            System.out.println("Updated music: " + updated);

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
