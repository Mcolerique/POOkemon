package affichage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * La classe Musique gère la lecture de fichiers audio. Elle utilise l'API javax.sound.sampled
 */
public class Musique {

    private Clip clip;
    private boolean isLooping;

    //METHODES

    /**
     * Constructeur de la classe Musique, qui initialise le clip audio à partir du chemin spécifié.
     *
     * @param path Le chemin du fichier audio à lire.
     */
    public Musique(String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }



    /**
     * Joue le clip audio. Si la lecture en boucle est activée, le clip est lu en continu.
     */
    public void play() {
        if (clip != null) {
            if (isLooping) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }



    /**
     * Arrête la lecture du clip audio s'il est en cours de lecture.
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }



    /**
     * Active la lecture en boucle pour le clip audio.
     */
    public void loop() {
        if (clip != null) {
            isLooping = true;
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
