package org.example;

import com.zeroc.Ice.Exception;
import com.zeroc.Ice.*;
import org.example.MusicManager.Music;
import org.example.MusicManager.MusicServer;

public class MusicServerApp extends Application implements MusicServer {

    @Override
    public Music searchMusic(String title, String artist, Current current) {
        return null;
    }

    @Override
    public boolean addMusic(Music music, Current current) {
        return true;
    }

    @Override
    public boolean removeMusic(String title, String artist, Current current) {
        return true;
    }

    @Override
    public boolean modifyMusic(Music music, Current current) {
        return true;
    }


    @Override
    public int run(String[] args) {

        Communicator communicator = null;
        try {
            communicator = Util.initialize(args);

            // Créer un objet servant pour le serveur de musique
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("MusicServer", "default -p 10000");
            MusicServerI musicServer = new MusicServerI();
            adapter.add(musicServer, Util.stringToIdentity("MusicServer"));

            // Activer l'adaptateur avec les objets servant
            adapter.activate();

            System.out.println("MusicServer started");

            // Attendre les demandes
            communicator.waitForShutdown();
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            if (communicator != null) {
                communicator.destroy();
            }
        }

        return 0;
    }
}