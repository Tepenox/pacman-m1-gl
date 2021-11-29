package engines;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundEngine {

    private static Map<String, SfxPlayer> activeSounds = new HashMap<>();

    public static void playSfx(String path) {
        SfxPlayer soundPlayer = null;
        try {
            if (activeSounds.containsKey(path)){
                activeSounds.get(path).restart();
            }else{
                soundPlayer = new SfxPlayer(path);
                soundPlayer.play();
                activeSounds.put(path,soundPlayer);

            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playSfxOnLoop(String path) {
        SfxPlayer soundPlayer = null;
        try {
            if (activeSounds.containsKey(path)){
                activeSounds.get(path).restartOnLoop();
            }else{
                soundPlayer = new SfxPlayer(path);
                soundPlayer.playOnLoop();
                activeSounds.put(path,soundPlayer);

            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopAllActiveSounds(){
        for (SfxPlayer sfxPlayer : activeSounds.values()){
            try {
                sfxPlayer.stop();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopSfx(String path){
        if (activeSounds.containsKey(path)){
            try {
                activeSounds.get(path).stop();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }




}

class SfxPlayer {

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
        clip.stop();
        clip.close();
        System.out.println(getClass().getResource(path));
        audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
        clip.open(audioStream);
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void restartOnLoop() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
        clip.open(audioStream);
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
        clip.close();



    }
}

