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
public class Informacoes {

    public static String getExtensao(File arquivo) {
        String musicaCaminho = arquivo+"";
        StringBuilder sb = new StringBuilder();
        for (int i = (musicaCaminho.length() - 3); i < musicaCaminho.length(); i++) {
            sb.append(musicaCaminho.charAt(i));
        }
        return sb.toString().toUpperCase();
    }

    public static String getTitulo(File arquivo) throws UnsupportedAudioFileException, IOException{
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(arquivo);
        Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat).properties();
        String titulo = propriedade.get("title");
        try {
            if (titulo == null || titulo.equals("")) {
                int caracteres = arquivo.getName().length();
                String t = arquivo.getName();
                char[] a = t.toCharArray();
                for (int i = 0; i < caracteres-4; i++) {
                    titulo = titulo + a[i];
                }

                return titulo;
            }
        } catch (Exception e) {
            return titulo;
        }
        return titulo;
    }

    public static String getAlbum(File arquivo) throws UnsupportedAudioFileException, IOException{
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(arquivo);
        Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat).properties();
        String album = propriedade.get("album");
        try {
            if (album == null || album.equals("")) {
                album = "Desconhecido";
            }
        } catch (Exception e) {
            return "Desconhecido";
        }
        return album;
    }

    public static String getAutor(File arquivo) throws UnsupportedAudioFileException, IOException{
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(arquivo);
        Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat).properties();
        String autor = null;
        try {
            autor = propriedade.get("author");
            if (autor == null || autor.equals("")) {
                autor = "Desconhecido";
            }
        } catch (Exception e) {
            return "Desconhecido";
        }
        return autor;
    }

    public static String getQualidade(File arquivo) throws UnsupportedAudioFileException,IOException {
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(arquivo);
	Map<String, String> propriedade = ((TAudioFileFormat) baseFileFormat).properties();
        ArrayList x = new ArrayList();
        String a = "MP3";
        String b = "OGG";
        if(a.contentEquals(getExtensao(arquivo)) || b.contentEquals(getExtensao(arquivo))){
            String buffer = "";
            String key = getExtensao(arquivo).toLowerCase();

            key += ".bitrate.nominal.bps";
            x.add(propriedade.get(key));

            buffer = x.get(0).toString();
            int aux1 = Integer.parseInt(buffer) / 1000;
            buffer = Integer.toString(aux1) + " Kbps | ";

            key = getExtensao(arquivo).toLowerCase();
            key += ".frequency.hz";
            //key = (String) propriedade.get(key).toString();
            x.add(propriedade.get(key));
            key = x.get(1).toString();

            aux1 = Integer.parseInt(key) / 1000;
            buffer += Integer.toString(aux1) + " Khz";

            return buffer;
        }else{
            return "??? Kbps | ?? Khz";
        }
    }

}
