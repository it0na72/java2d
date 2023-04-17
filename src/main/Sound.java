package main;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.net.URL;

public class Sound
{
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("res/sound/MainTitle.wav");
        soundURL[1] = getClass().getResource("res/sound/coin.wav");
        soundURL[2] = getClass().getResource("res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("res/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("res/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("res/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("res/sound/levelup.wav");
        soundURL[8] = getClass().getResource("res/sound/cursor.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e) {
        }
    }
    public void play() {
        if(clip != null) {
            clip.start();  // cant play music for some reason...//
        }
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop () {
        if(clip != null) {
            clip.stop();
        }
    }
}


