/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 *
 * @author Idair F. Guido
 */
public class Tempo {

    public static String getTempo(File caminho) throws UnsupportedAudioFileException, IOException{
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(caminho);
        Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat).properties();
        ArrayList x = new ArrayList();
        x.add(propriedade.get("duration"));
        String tempo = x.get(0).toString();
        float duracao = Float.parseFloat(tempo+"")/1000/1000/60;
        int minutos = (int)duracao;
        float s = (duracao - minutos)*60;
        String decimalSegundos = "";
        String decimalMinutos = "";
        String horas = "";
        int segundos = (int) s;
        int h = 0;
        while(minutos > 59){
            h = h + 1;
            minutos = minutos - 60;
        }
        if(minutos < 10){
            decimalMinutos = "0";
        }
        if(segundos < 10){
            decimalSegundos = "0";
        }
        if(h == 0){
            horas = "";
        }else{
            horas = h+":";
        }
        return horas+decimalMinutos+minutos+":"+decimalSegundos+segundos;
    }

    public static String transfomaTempo(Long tempo){
        float duracao = Float.parseFloat(tempo+"")/1000/1000/60;
        int minutos = (int)duracao;
        float s = (duracao - minutos)*60;
        String decimalSegundos = "";
        String decimalMinutos = "";
        String horas = "";
        int segundos = (int) s;
        int h = 0;
        while(minutos > 59){
            h = h + 1;
            minutos = minutos - 60;
        }
        if(minutos < 10){
            decimalMinutos = "0";
        }
        if(segundos < 10){
            decimalSegundos = "0";
        }
        if(h == 0){
            horas = "";
        }else{
            horas = h+":";
        }
        return horas+decimalMinutos+minutos+":"+decimalSegundos+segundos;
    }

    public static Long getTempoReal(File caminho) throws UnsupportedAudioFileException, IOException{
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(caminho);
        Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat).properties();
        ArrayList x = new ArrayList();
        x.add(propriedade.get("duration"));
        return Long.parseLong(x.get(0).toString());
    }

}
