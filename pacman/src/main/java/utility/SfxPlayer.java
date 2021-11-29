package utility;

import javax.sound.sampled.*;
import java.io.IOException;

public class SfxPlayer {

    Clip clip;
    AudioInputStream audioStream;
    String path;

    public SfxPlayer(String path) throws UnsupportedAudioFileException, IOException {
        try {
            this.path = path;
            this.audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("couldn t find sfx file");
        }

    }

    public void play() {
        clip.start();


    }
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
//        clip.stop();
//        clip.close();
//        audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
//        clip.open(audioStream);
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void restartOnLoop() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
//        clip.stop();
//        clip.close();
//        audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
//        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void playOnLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();

    }

    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {

        clip.stop();
//        clip.close();



    }
}