import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundClip {

    private static Boolean soundon = true;

    public void startSound() {
        if (soundon == true) {
            try {
                URL url = this.getClass().getResource("audio/stone-scraping.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void noMatchClick() {
        if (soundon == true) {
            try {
                URL url = this.getClass().getResource("audio/nomatch.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void firstClick() {
        if (soundon == true)
            try {
                URL url = this.getClass().getResource("audio/singleclick.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
    }

    public void secondClick() {
        if (soundon == true)
            try {
                URL url = this.getClass().getResource("audio/doubleclick.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
    }

    public void explosion() {
        if (soundon == true)
            try {
                URL url = this.getClass().getResource("audio/explosion.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
    }


    public static void setSoundon(Boolean soundon){
        SoundClip.soundon = soundon;
    }


}