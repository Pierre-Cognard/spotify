
module MusicManager {


    struct Music {
        string title;
        string artist;
        string path;
    };

    sequence<byte> filecontent;
    sequence<Music> arrayMusic;

    interface MusicServer {
        Music searchMusic(string title, string artist);
        bool addMusic(Music music);
        bool removeMusic(string title, string artist);
        bool modifyMusic(Music music);
        string getMusicStream(Music music);
        void getMusicList();
        bool uploadMusic(string title, string artist, string path);
        arrayMusic getMusics();
    };
};