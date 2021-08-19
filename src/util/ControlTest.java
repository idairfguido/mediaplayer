/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

/**
 *
 * @author idair
 */

public class ControlTest {

public static void main(String[] args) throws LineUnavailableException, InterruptedException {
    Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
    for (int i = 0; i < mixerInfos.length; i++) {
        System.out.println("AudioSystem info Name:"+mixerInfos[i].toString());
        Mixer mixer = AudioSystem.getMixer(mixerInfos[i]);
        System.out.println(mixer.toString());
        Line.Info[] targetLineInfos = mixer.getTargetLineInfo();
        System.out.println("target infos : " + targetLineInfos.length);
        for (int j = 0; j < targetLineInfos.length; j++) {
            setVolume(targetLineInfos[j]);
        }
    }
}

static boolean setVolume(Line.Info lineInfo) {
    try {
        System.out.println(lineInfo);
        Line line = AudioSystem.getLine(lineInfo);
        System.out.println("open " + line.getLineInfo());
        line.open();

        BooleanControl muteControl = (BooleanControl)line.getControl(BooleanControl.Type.MUTE);
        System.out.println(muteControl.getValue());
        System.out.println("mute " + muteControl);
        muteControl.setValue(true);
        FloatControl control =(FloatControl)line.getControl(FloatControl.Type.VOLUME);
        System.out.println(control.getType().toString());
        System.out.println(control.getValue());
        control.setValue(control.getMinimum());
        System.out.println("val " + control.getValue());
        FloatControl controlp =(FloatControl)line.getControl(FloatControl.Type.MASTER_GAIN);
        System.out.println(controlp.toString());
        System.out.println(controlp.getValue());
        controlp.setValue(control.getMinimum());
        FloatControl controlp2 =(FloatControl)line.getControl(FloatControl.Type.SAMPLE_RATE);
        System.out.println(controlp2.getType().toString()+" lol");
        System.out.println(controlp2.getValue());
        controlp.setValue(control.getMinimum());
        System.out.println("val " + controlp.getValue());
        line.close();
    } catch (Exception e) {
        System.out.println(e);
        return false;
    }
        return true;
    }
}

