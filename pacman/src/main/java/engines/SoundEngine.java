package engines;

import utility.SfxPlayer;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * moteur son
 */
class SoundEngine {

    /**
     * les paths des sons actifs et leur SfxPlayer
     */
    private static Map<String, SfxPlayer> activeSounds = new HashMap<>();

    /**
     * joue un effet sonore depuis un fichier wav pour une seule répétition
     * si l effet sonore est déjà actif, il sera désactivé et rejoué
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
     * joue un effet sonore depuis un fichier wav en boucle
     * si l'effet sonore est déjà actif, il sera désactivé et rejoué
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
     * arrête tout les effets sonores actifs
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
     * arrête un effet sonore spécifique
     * @param path path d'effet sonore à arrêter
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



