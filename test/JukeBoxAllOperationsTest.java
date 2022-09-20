import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class JukeBoxAllOperationsTest {
    JukeBoxAllOperations jukeBoxAllOperations = null;

    @Before
    public void setUp() throws Exception {
        jukeBoxAllOperations = new JukeBoxAllOperations();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void CheckHowManySongsPresentInList() {

        assertEquals(11, jukeBoxAllOperations.getAllSongs().size(), 0);

    }

    @Test

    public void CheckDurationOfIndex() {

        assertEquals(6.1, jukeBoxAllOperations.getAllSongs().get(0).getDuration(), 0);
        assertEquals(4.47, jukeBoxAllOperations.getAllSongs().get(1).getDuration(), 0);
        assertEquals(4.44, jukeBoxAllOperations.getAllSongs().get(3).getDuration(), 0);

    }

    @Test
    public void CheckGenreOfSong() {

        assertEquals("Test Failed", jukeBoxAllOperations.getAllSongs().get(1).getGenre(), "Fantasy");
        assertEquals("Test Failed", jukeBoxAllOperations.getAllSongs().get(3).getGenre(), "Romantic");
        assertEquals("Test Failed", jukeBoxAllOperations.getAllSongs().get(7).getGenre(), "Rock");
    }

    @Test
    public void CheckSongPresentInColumn() {
        assertEquals("Test Failed", jukeBoxAllOperations.getAllSongs().get(0).getSongName(), "Bekhayali");
        assertEquals("Test Failed", jukeBoxAllOperations.getAllSongs().get(2).getSongName(), "Humraah");
        assertEquals("Test Failed", jukeBoxAllOperations.getAllSongs().get(7).getSongName(), "Sterio_Hearts");
    }
   /* @Test
    public void ShowCreatedPlaylist(){

        try {
            assertEquals(1,  jukeBoxAllOperations.showCreatedPlaylist(),0);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }*/
   // }
}
    /*@Test
    public void createNewPlaylist() throws SQLException, ClassNotFoundException {
        //assertEquals(4, jukeBoxAllOperations.CreateNewPlaylist());
    }*/

   // @Test
    /*public void add_Song_To_Collection() throws SQLException, ClassNotFoundException {
        assertEquals(1, jukeBoxAllOperations.Add_Song_To_Collection(), 0);
    }*/

   // @Test
   /* public void show_Search_Song() {
    }*/
