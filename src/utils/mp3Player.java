package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class mp3Player {
    String moveSound = "src/sounds/move.wav";
    String captureSound = "src/sounds/capture.wav";
    AudioInputStream moveAudio;
    AudioInputStream captureAudio;
    Clip moveClip;
    Clip captureClip;

    public mp3Player(){
        try{
            moveAudio = AudioSystem.getAudioInputStream(new File(this.moveSound).getAbsoluteFile());
            moveClip = AudioSystem.getClip();
            moveClip.open(moveAudio);

            captureAudio = AudioSystem.getAudioInputStream(new File(this.captureSound).getAbsoluteFile());
            captureClip = AudioSystem.getClip();
            captureClip.open(captureAudio);
        }
        catch (Exception e){
            System.out.println(e);
        }
    };

    public void playMoveSound(){
        try {
            moveClip.setFramePosition(0);
            moveClip.start();
        }
            catch (Exception e){
                System.out.println(e);
        }
    }

    public void playCaptureSound(){
        try {
            captureClip.setFramePosition(0);
            captureClip.start();
        }
            catch (Exception e){
                System.out.println(e);
        }
    }

}
