import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer {

    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("SOUND ERROR");
            e.printStackTrace();
        }
    }
}
