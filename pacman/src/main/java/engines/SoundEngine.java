package engines;

import utility.SfxPlayer;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class SoundEngine {

    private static Map<String, SfxPlayer> activeSounds = new HashMap<>();

    /**
     * jouer un effet sonore depuis d'un fichier wav pour une seul repition
     * si l effet sonore est deja active, il sera desactivé et rejoué
     * @param path path de fichier
     */
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
    /**
     * jouer un effet sonore depuis d'un fichier wav en boucle
     * si l effet sonore est deja active, il sera desactivé et rejoué
     * @param path path de fichier
     */

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

    /**
     * arreter tous les effets sonore0 active
     */

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
        activeSounds = new HashMap<>();
    }
    /**
     * arreter un effet sonore specifique
     */
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



