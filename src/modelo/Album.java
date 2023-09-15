/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Idair F. Guido
 */
public class Album implements Serializable{

    private String titulo, artista;
    private int codigo;

    public Album() {
    }

    public Album(String titulo, String artista, int codigo) {
        this.titulo = titulo;
        this.artista = artista;
        this.codigo = codigo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
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

    @Override
    public String toString() {
        return "Codigo: " + codigo + " \n"
                + "Titulo: " + titulo + " \n"
                + "Artista: " + artista;
    }

}
