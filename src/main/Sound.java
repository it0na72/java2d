package main;
import javax.sound.sampled.*;
import java.net.URL;

public class Sound
{
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

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
        soundURL[9] = getClass().getResource("res/sound/burning.wav");
        soundURL[10] = getClass().getResource("res/sound/cuttree.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch(Exception e) {
        }
    }

    public void play() {
            clip.start();  // cant play music for some reason...//
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop () {
            clip.stop();
    }
    public void checkVolume() {

        switch (volumeScale) {
            case 0: volume = -80F; break;
            case 1: volume = -20F; break;
            case 2: volume = -12F; break;
            case 3: volume = -5F; break;
            case 4: volume = 1F; break;
            case 5: volume = 6F; break;
        }
        fc.setValue(volume);
    }
}



