/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

/**
 *
 * @author Idair F. Guido
 */
public class Volume {
    
    private FloatControl volumeControl = null;


    public Volume() {
        initVolumeControl();
    }



    @SuppressWarnings("empty-statement")
    public void initVolumeControl(){
    Port lineOut;
    Mixer mixer;

    Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
    try
    {
        volumeSearch:
        for (Mixer.Info info : mixerInfo) {
            mixer = AudioSystem.getMixer(info);
            System.out.println("info.getName() = " + info.getName());
            if (mixer.isLineSupported(Port.Info.SPEAKER))
            {
                System.out.println(info.getName() + " supporta a speaker");
                lineOut = (Port) mixer.getLine(Port.Info.SPEAKER);
                lineOut.open();
                Control[] controls = lineOut.getControls();
                System.out.println("Supported controls on the speaker:");
                for (Control control : controls) {
                    // in the case of Vista, here you can get access to the volume control
                    // of the program, which appears to be equivalent to a "Wave" control in XP,
                    // except that you get it just for this app.
                    // Also, in Vista it is not a compund control, this is the end of the line (pun intended),
                    // the actual control.
                    // furthermore, in vista the Master is a compound control, but just "volume"
                    // refers to a single app. (I think).
                    if (System.getProperty("os.name").toLowerCase().contains("vista")) {
                        System.out.println("Vista");
                        if (control.getType().toString().toLowerCase().startsWith("volume")){
                            if (control instanceof FloatControl){
                                volumeControl = (FloatControl)control;
                                break volumeSearch;
                            }
                        }
                    } else {
                        System.out.println("Outro");
                        if (control.getType().toString().toUpperCase().contains("WAVE"))
                        {
                            if (control instanceof CompoundControl compoundControl)
                            {
                                System.out.println("CompoundControl");
                                CompoundControl waveControl = compoundControl;
                                Control[] wControls = waveControl.getMemberControls();
                                for (Control c : wControls)
                                {
                                    if (c.getType().toString().toUpperCase().contains("VOLUME"))
                                    {
                                        volumeControl = (FloatControl) c;
                                        break volumeSearch;
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                if (System.getProperty("os.name").toLowerCase().contains("linux")){
                    System.out.println("Linux");
                    
                }else{
                    System.out.println("Fudeu");
                }
            }
        }
    } catch (LineUnavailableException e)
    {
        System.err.println("There was an error while trying to get a volume control");
        e.printStackTrace();
    }
    }

    public float getVolumeControl() {
        return volumeControl.getValue();
    }

    public void setVolumeControl(float valor) {
        this.volumeControl.setValue(valor);
    }


}
