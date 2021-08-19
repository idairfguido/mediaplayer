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
public class MusicasExecutando {

    private List<Musica> musicas = null;

    public MusicasExecutando() {
    }

    public MusicasExecutando(List<Musica> musicas) {
        this.musicas = musicas;

    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void addMusica(Musica musica){
        musicas.add(musica);
    }

}
