import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.*;

public class SoundPlayer {

    public static void playSound(String fileName) {
        try {
            // Correct the file path to the audio folder inside resources
            InputStream soundStream = SoundPlayer.class.getClassLoader().getResourceAsStream(fileName);

            if (soundStream == null) {
                System.out.println("Error: Sound file not found: " + fileName);
                return;
            }

            // Wrap the InputStream with a BufferedInputStream to support mark/reset
            BufferedInputStream bufferedStream = new BufferedInputStream(soundStream);

            // Set up the audio input stream and clip for playback
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedStream);  // Use bufferedStream here
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();  // Play the sound
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("SOUND ERROR");
            e.printStackTrace();
        }
    }
}
