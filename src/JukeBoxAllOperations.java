
import java.sql.*;
import java.sql.Connection;
import Connection.DbConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JukeBoxAllOperations {
    Scanner scanner = new Scanner(System.in);

    public List<SongCollection> getAllSongs()  {
        List<SongCollection> songCollections = new ArrayList<>();
       try {
            Connection connection = DbConnection.getConnection();

            Statement statement = connection.createStatement();
            String sql = "Select * from song";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int SongId = resultSet.getInt(1);
                String Album = resultSet.getString(2);
                double Duration = resultSet.getDouble(3);
                String SongName = resultSet.getString(4);
                String Artist = resultSet.getString(4);
                String Genre = resultSet.getString(6);
                String SongPath = resultSet.getString(7);
                songCollections.add(new SongCollection(SongId, Album, Duration, SongName, Artist, Genre, SongPath));
            }
       } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return songCollections;
    }

    public void CreateNewPlaylist() throws SQLException, ClassNotFoundException {

        //boolean flag=false;
        int songId = 0;
        Connection connection = DbConnection.getConnection();
        ResultSet resultSet = null;
        int row = 0;
        Statement st = connection.createStatement();
        System.out.println("Enter Your  PlayList Name :");
        String PlayListName = scanner.next();

        row = st.executeUpdate("insert into playlist values(0,'" + PlayListName + "')");
        //flag=st.execute(sql);

        if (row == 1) {
            System.out.println("Playlist Created SuccessFully ");
        } else {
            System.out.println("Retry again");
        }
        String sql2="select playlistId from playlist where playlistName='"+PlayListName+"';";
        resultSet=st.executeQuery(sql2);
        int playListId=0;
        while (resultSet.next()){
            playListId=resultSet.getInt(1);
        }

        System.out.println("Enter Song ID to add Song to Created Playlist");
        int SongId = scanner.nextInt();

        String query1 = "select * from song where songId =" + SongId;
        String query2 = "insert into playlistcontent values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query1);
        ResultSet resultSet1 = preparedStatement.executeQuery();
        while (resultSet1.next()) {
            resultSet1.getInt(1);
        }
        PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
        preparedStatement1.setInt(1, playListId);
        preparedStatement1.setInt(2, SongId);
        int i = preparedStatement1.executeUpdate();
        if (i > 0) {
            System.out.println("Song Added to Playlist");
        } else {
            System.out.println("Failed to Add song");
        }

    }

    public void addSongToPlaylist() throws SQLException, ClassNotFoundException {

        System.out.println("Enter Playlist Id :-");
        int playListId = scanner.nextInt();
        System.out.println("Enter Song ID to add Song to Created Playlist");
        int SongId = scanner.nextInt();

        Connection connection = DbConnection.getConnection();
        String query1 = "select * from song where songId =" + SongId;
        String query2 = "insert into playlistcontent values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query1);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            resultSet.getInt(1);
        }
        PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
        preparedStatement1.setInt(1, playListId);
        preparedStatement1.setInt(2, SongId);
        int i = preparedStatement1.executeUpdate();
        if (i > 0) {
            System.out.println("Song Added to Playlist");
        } else {
            System.out.println("Failed to Add song");
        }

    }

    public void showCreatedPlaylist() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getConnection();
        Statement st = connection.createStatement();

        ResultSet resultSet = st.executeQuery("select * from playlist");

        System.out.format("%-20s %-30s", "PlayListID", "PlayListName");
        System.out.println(" ");
        System.out.println("========================================");

        while (resultSet.next()) {

            System.out.format("%-20s %-30s", resultSet.getInt(1), resultSet.getString(2));
            System.out.println();

        }
    }

    public List<SongCollection> ExistingPlaylist() throws SQLException, ClassNotFoundException {
        List<SongCollection> ExistingPlaylist = new ArrayList<>();
        List<Integer> SongIdList = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);

        ResultSet resultSet = statement.executeQuery("select * from playlist");

        System.out.format("%-20s %-30s", "PlayListID", "PlayListName");
        System.out.println(" ");
        System.out.println("========================================");

        while (resultSet.next()) {

            System.out.format("%-20s %-30s", resultSet.getInt(1), resultSet.getString(2));
            System.out.println();

        }
        System.out.println("Enter playlist id you want to open ");
        int playlistId = scanner.nextInt();

        String sql = "select songId from playlistcontent where playlistId=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, playlistId);
        ResultSet resultSet1 = preparedStatement.executeQuery();
        String sql1 = "select * from song where songId=?;";
        while (resultSet1.next()) {
            int SongId = resultSet1.getInt(1);
            SongIdList.add(resultSet1.getInt(1));
            //System.out.println(resultSet1.getInt(1));
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, SongId);
        }
        String sql2 = null;
        for (Integer element : (SongIdList)) {
            //System.out.println(element);

            sql2 = "select * from song where SongId =" + element + ";";
            ResultSet resultSet3 = statement.executeQuery(sql2);
            while (resultSet3.next()) {


                int SongId = resultSet3.getInt(1);
                String Album = resultSet3.getString(2);
                double Duration = resultSet3.getDouble(3);
                String SongName = resultSet3.getString(4);
                String Artist = resultSet3.getString(5);
                String Genre = resultSet3.getString(6);
                String SongPath = resultSet3.getString(7);
                ExistingPlaylist.add(new SongCollection(SongId, Album, Duration, SongName, Artist, Genre, SongPath));
            }

        }

        return ExistingPlaylist;
    }

    public int Add_Song_To_Collection() throws SQLException, ClassNotFoundException {
        int songId = 0;
        Connection connection = DbConnection.getConnection();

        int SongId = 0;
        System.out.println("Enter Album Name");
        String AlbumName = scanner.nextLine();

        System.out.println("Enter Song Duration");
        double Duration = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter Song Name");

        String SongName = scanner.nextLine();

        System.out.println("Enter Artist Name");
        String ArtistName = scanner.nextLine();


        System.out.println("Enter Genre of Song");
        String Genre = scanner.nextLine();
        System.out.println("Enter the path Of Song");
        String SongPath = scanner.nextLine();

        SongCollection songCollection = new SongCollection(SongId, AlbumName, Duration, SongName, ArtistName, Genre, SongPath);

        String sql = "insert into song (Album, Duration, SongName, Artist, Genre,SongPath) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, songCollection.getAlbum());
        preparedStatement.setDouble(2, songCollection.getDuration());
        preparedStatement.setString(3, songCollection.getSongName());
        preparedStatement.setString(4, songCollection.getArtist());
        preparedStatement.setString(5, songCollection.getGenre());
        preparedStatement.setString(6, songCollection.getSongPath());
        int rows = preparedStatement.executeUpdate();
        if (rows == 1) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                songId = resultSet.getInt(1);
            }
        }
        return songId;
    }

   /* public List<String> PathOfSongToPlay() throws SQLException, ClassNotFoundException {
        List<String> SongPathList = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        ResultSet resultSet = null;
        Statement st = connection.createStatement();
        resultSet = st.executeQuery("select SongPath from song");

        while (resultSet.next()) {
            SongPathList.add((resultSet.getString(1)));

            //System.out.println(resultSet.getString(1));
        }

        return SongPathList;
    }*/

    public List<SongCollection> Show_Search_Song() throws SQLException, ClassNotFoundException {
        List<SongCollection> SearchSongList = new ArrayList<>();

        Connection connection = DbConnection.getConnection();
        ResultSet resultSet = null;
        Statement st = connection.createStatement();
        int ChooseCategory = 0;
        int searchByGenre = 0;
        int repeat = 0;
        /* do {*/
        System.out.println("Choose the Specific Search Category :-");
        System.out.println("");
        System.out.println("----------- : Press 1 For Search By Song Name   : ------------");
        System.out.println("----------- : press 2 For Search By Album Name  : ------------");
        System.out.println("----------- : Press 3 For Search By Artist Name : ------------");
        System.out.println("----------- : Press 4 For Search By Genre       : ------------");
        System.out.println("----------- : Press 5 For Alphabetical Search   :-------------");
        ChooseCategory = scanner.nextInt();
        switch (ChooseCategory) {
            case 1:
                System.out.println("------------ Enter Song Name -----------");
                System.out.println("If you Don't Know Complete name the just write 1st or 2nd Character ");
                String SongName = scanner.next();
                String sql1 = "select * from song where SongName like '" + SongName + "%'";
                resultSet = st.executeQuery(sql1);
                break;
            case 2:
                System.out.println("------------ Enter Album Name -----------");
                System.out.println("If you Don't Know Complete name the just write 1st or 2nd Character ");
                String albumName = scanner.next();
                String sql2 = "select * from song where Album like '" + albumName + "%'";
                resultSet = st.executeQuery(sql2);
                break;
            case 3:
                System.out.println("------------ Enter Artist Name -----------");
                System.out.println("If you Don't Know Complete name the just write 1st or 2nd Character ");
                String artistName = scanner.next();
                String sql3 = "select * from song where Artist like '" + artistName + "%'";
                resultSet = st.executeQuery(sql3);
                break;
            case 4:
                System.out.println("Press 1 for Romantic genre :");
                System.out.println("Press 2 for Fantasy genre  :");
                System.out.println("Press 3 for PartyMix genre :");
                System.out.println("Press 4 for Rock genre     :");
                searchByGenre = scanner.nextInt();
                switch (searchByGenre) {
                    case 1:
                        resultSet = st.executeQuery("select * from song where Genre like 'Romantic%'");
                        break;
                    case 2:
                        resultSet = st.executeQuery("select * from song where Genre like 'Fantasy'");
                        break;
                    case 3:
                        resultSet = st.executeQuery("select * from song where Genre like 'PartyMix'");
                        break;
                    case 4:
                        resultSet = st.executeQuery("select * from song where Genre like 'Rock'");

                }
                break;
            case 5:
                System.out.println("------------- : Songs are Sorted Alphabetically :------------ ");
                resultSet = st.executeQuery("select * from song order by SongName asc; ");
                break;

            default:
                System.out.println("-----------: Wrong Entry Choose Option From 1 To 5 :-----------");
                System.exit(0);
        }

        System.out.println(" ");
        System.out.println(" Your Playlist :-");
        System.out.println(" ");
        while (resultSet.next()) {

            int SongId = resultSet.getInt(1);
            String Album = resultSet.getString(2);
            double Duration = resultSet.getDouble(3);
            String SongName = resultSet.getString(4);
            String Artist = resultSet.getString(4);
            String Genre = resultSet.getString(6);
            String SongPath = resultSet.getString(7);
            SearchSongList.add(new SongCollection(SongId, Album, Duration, SongName, Artist, Genre, SongPath));

        }

        return SearchSongList;
    }

}