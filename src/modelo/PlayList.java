/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.List;

/**
 *
 * @author Idair F. Guido
 */
public class PlayList {

    private List<String> musicaIds;
    private String titulo;
    private int codigo;

    public PlayList() {
    }

    public PlayList(List<String> MusicaIds, String titulo, int codigo) {
        this.musicaIds = MusicaIds;
        this.titulo = titulo;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getMusicaIds() {
        return musicaIds;
    }

    public void setMusicaIds(List<String> musicaIds) {
        this.musicaIds = musicaIds;
    }

    public void insereMusica(int cod){
        musicaIds.add(cod+"");
    }

}
