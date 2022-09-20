
    public class SongCollection {

        private int SongId;
        private String Album;
        private double Duration;
        private String SongName;
        private String Artist;
        private String Genre;
        private String SongPath;

        public String getSongPath() {
            return SongPath;
        }

        public void setSongPath(String songPath) {
            SongPath = songPath;
        }

        public int getSongId() {
            return SongId;
        }

        public void setSongId(int songId) {
            SongId = songId;
        }

        public String getAlbum() {
            return Album;
        }

        public void setAlbum(String album) {
            Album = album;
        }

        public double getDuration() {
            return Duration;
        }

        public void setDuration(double duration) {
            Duration = duration;
        }
        public String getSongName() {
            return SongName;
        }

        public void setSongName(String songName) {
            SongName = songName;
        }

        public String getArtist() {
            return Artist;
        }

        public void setArtist(String artist) {
            this.Artist = artist;
        }

        public String getGenre() {
            return Genre;
        }

        public void setGenre(String genre) {
            this.Genre = genre;
        }


        public SongCollection(int SongId,String Album, double Duration, String SongName, String Artist, String Genre,String SongPath) {
            this.SongId=SongId;
            this.Album = Album;
            this.Duration = Duration;
            this.SongName = SongName;
            this.Artist = Artist;
            this.Genre = Genre;
            this.SongPath=SongPath;
        }

        @Override
        public String toString() {
            return "SongCollection{" +
                    "SongId=" + SongId +
                    ", Album='" + Album + '\'' +
                    ", Duration=" + Duration +
                    ", SongName='" + SongName + '\'' +
                    ", artist='" + Artist + '\'' +
                    ", genre='" + Genre + '\'' +
                    ", SongPath='" + SongPath + '\'' +
                    '}';
        }
    }


