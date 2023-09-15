/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Idair F. Guido
 */
public class Musica implements Serializable{

    private String titulo, qualidade, formato, duração, artista, album, nome;
    private Long tempo;
    private int codigo;
    private File caminho;

    public Musica() {
    }

    public Musica(String titulo, String qualidade, String formato, Long tempo, int codigo, String album, File caminho, String duração, String nome) {
        this.titulo = titulo;
        this.qualidade = qualidade;
        this.formato = formato;
        this.tempo = tempo;
        this.codigo = codigo;
        this.album = album;
        this.caminho = caminho;
        this.duração = duração;
        this.nome = nome;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    public File getCaminho() {
        return caminho;
    }

    public void setCaminho(File caminho) {
        this.caminho = caminho;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public Long getTempo() {
        return tempo;
    }

    public void setTempo(Long tempo) {
        this.tempo = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuração() {
        return duração;
    }

    public void setDuração(String duração) {
        this.duração = duração;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return  "Codigo: "+codigo+" \n" +
                "Album: "+album+" \n" +
                "Titulo: "+titulo+" \n" +
                "Qualidade: "+qualidade+" \n" +
                "Formato: "+formato+" \n" +
                "Tempo: "+tempo+" \n" +
                "Caminho: "+caminho;
    }



}
