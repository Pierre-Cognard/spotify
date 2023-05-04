module MusicManager {
    struct Music {
        string title;
        string artist;
        string album;
        int year;
        string genre;
        string path;
    };

    interface MusicServer {
        Music searchMusic(string title, string artist);
        bool addMusic(Music music);
        bool removeMusic(string title, string artist);
        bool modifyMusic(Music music);
    };
};
