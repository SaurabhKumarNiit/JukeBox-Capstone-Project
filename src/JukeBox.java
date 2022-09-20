
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class JukeBox {
    static Scanner scanner = new Scanner(System.in);
    //static int playSong;
    static int repeat;

    public JukeBox() {

        // printList(songList);
    }
    public static void main(String[] args) {
        JukeBoxAllOperations jukeBox = new JukeBoxAllOperations();
        JukeBoxImplementation jukeBoxImplementation = new JukeBoxImplementation();

        try{
        List<SongCollection> songList = new JukeBoxAllOperations().getAllSongs();

        System.out.println("===============================================: Welcome To JukeBox :===================================================================");
        System.out.println(" ");
        //private static void printList(List<SongCollection> songList) {
        do {
            System.out.format("%-20s %-30s %-20s %-30s %-20s\n", "SongId", "Album Name", "Duration", "Song Name", "Artist Name", "Genre");
            for (SongCollection songCollection : songList) {
                System.out.format("%-20s %-30s %-20s %-30s %-20s\n", songCollection.getSongId(), songCollection.getAlbum(), songCollection.getDuration(),
                        songCollection.getSongName(), songCollection.getArtist(), songCollection.getGenre());
            }
            // create AudioInputStream object
            System.out.println(" ");
            System.out.println("=========================================================================================================================================");
            System.out.println("");
            System.out.println("Press 1 to Play Songs :-");
            System.out.println("Press 2 to Search Song then play song");
            System.out.println("Press 3 to Create a New Playlist ");
            System.out.println("Press 4 to Add New Song in Collection");
            System.out.println("Press 5 to Add song to playlist");
            System.out.println("Press 6 to Show Created Playlist");
            System.out.println("Press 7 to Exit  :");

            int option = scanner.nextInt();
            switch (option) {
                case 2:
                    List<SongCollection> SearchSongCollections = jukeBox.Show_Search_Song();

                    System.out.format("%-20s %-20s %-20s %-30s %-20s\n", "SongId", "Album Name", "Duration", "Song Name", "Artist Name", "Genre");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                    for (SongCollection songCollection : SearchSongCollections) {
                        System.out.format("%-20s %-20s %-20s %-30s %-20s\n", songCollection.getSongId(), songCollection.getAlbum(), songCollection.getDuration(),
                                songCollection.getSongName(), songCollection.getArtist(), songCollection.getGenre());
                    }

                    jukeBoxImplementation.PlaySong(SearchSongCollections);

                    break;
                case 1:
                    System.out.println(" ");
                    System.out.println("Choose option to play song :-");
                    jukeBoxImplementation.PlaySong(songList);

                    break;

                case 3:

                    try {
                        jukeBox.CreateNewPlaylist();

                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.exit(0);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        System.exit(0);
                    }

                    break;

                case 4:
                    int songId = 0;
                    try {
                        songId = jukeBox.Add_Song_To_Collection();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println(" ");
                    System.out.println("Song Added Successfully :-");
                    System.out.println("Total Song in Playlist  :-" + songId);
                    break;

                case 5:
                    jukeBox.showCreatedPlaylist();
                    System.out.println(" ");
                    jukeBox.addSongToPlaylist();
                    break;

                case 6:

                    List<SongCollection> PlaylistSongCollections = jukeBox.ExistingPlaylist();
                    System.out.format("%-20s %-30s %-20s %-30s %-20s\n", "SongId", "Album Name", "Duration", "Song Name", "Artist Name", "Genre");
                    System.out.println("=========================================================================================================================================");
                    for (SongCollection songCollection : PlaylistSongCollections) {
                        System.out.format("%-20s %-30s %-20s %-30s %-20s\n", songCollection.getSongId(), songCollection.getAlbum(), songCollection.getDuration(),
                                songCollection.getSongName(), songCollection.getArtist(), songCollection.getGenre());
                    }
                    System.out.println("=========================================================================================================================================");
                    jukeBoxImplementation.PlaySong(PlaylistSongCollections);

                    break;

                case 7:
                    System.exit(0);
                    break;


            }
            System.out.println("Press Any Numeric Key To Show menu");
            System.out.println("Press -1 for Exit");
            repeat = scanner.nextInt();
        } while (repeat != -1);
        System.out.println(" ");
        System.out.println("Thanks For Using My JukeBox :-");
        System.out.println("I Hope You Enjoying It.............");

    }catch (SQLException | ClassNotFoundException | UnsupportedAudioFileException | LineUnavailableException | IOException e){

        e.printStackTrace();
    }
    }
}
