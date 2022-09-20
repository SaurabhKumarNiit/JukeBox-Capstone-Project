

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class JukeBoxImplementation {
    List<SongCollection> SongsList;
    int songIndex;
    public void PlaySong(List<SongCollection> SongsList) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

            Scanner scanner = new Scanner(System.in);
            this.SongsList = SongsList;
            for (int i = 0; i < SongsList.size(); i++) {
                songIndex = i;
                PlaySong(SongsList.get(i).getSongPath());
            }
    }
    public void PlaySong(String SongPath)  {


        Scanner scanner = new Scanner(System.in);
       try {
            String path = SongPath ;
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            int response=0;

              //  while (response!=6){
           boolean loop=true;
                    while (loop){
                System.out.println("1 = Play Song From Current PlayList");
                System.out.println("2 = Pause, 3 =Stop, 4 =Loop, 5 = Reset,6 = NextSong,7 = previousSong");
                    System.out.println("----- Press : 8 : For Main Menu ");
                System.out.print("Enter your choice: ");

                response = scanner.nextInt();
                //response = response.toUpperCase();


                switch (response) {
                    case 1: {
                        clip.start();
                       /* long clip_position = */clip.getMicrosecondPosition();
                        //  System.out.println("Songs in queue: ");
                        break;
                    }
                    case 2: {
                        clip.stop();
                       /* long clip_position =*/ clip.getMicrosecondPosition();
                        break;
                    }
                    case 3: {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case 4: {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        System.out.println("Song is Now In Loop");
                    }
                    break;
                    case 5:
                        clip.setMicrosecondPosition(0);
                        break;

                    case 6:
                        songIndex += 1;
                        clip.close();
                        PlaySong(SongsList.get(songIndex).getSongPath());
                        break;
                    case 7:
                        songIndex -= 1;
                        clip.close();
                        PlaySong(SongsList.get(songIndex).getSongPath());
                        break;
                    case 8:
                        clip.close();
                      String[] arg=new String[0];
                        JukeBox.main(arg);
                        break;

                    default:
                        System.err.println("PLEASE SELECT THE CORRECT OPTION");
                        response = scanner.nextInt();
                        break;
                        //response = response.toUpperCase();
                }

            }
          // scanner.close();
        } catch (Exception e) {

          e.printStackTrace();
        }

    }

}