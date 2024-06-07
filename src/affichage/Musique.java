package affichage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musique {
    private Clip clip;
    private boolean isLooping;

    public Musique(String path) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            if (isLooping) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop() {
        if (clip != null) {
            isLooping = true;
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
