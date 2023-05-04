
module MusicManager {

    sequence<byte> filecontent;

    struct Music {
        string title;
        string artist;
        string path;
    };

    interface MusicServer {
        Music searchMusic(string title, string artist);
        bool addMusic(Music music);
        bool removeMusic(string title, string artist);
        bool modifyMusic(Music music);
        string getMusicStream(Music music);
        void getMusicList();
        void stopMusic();
    };
};