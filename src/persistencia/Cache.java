/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.LinkedList;
import java.util.List;
import modelo.Album;
import modelo.Musica;
import modelo.PlayList;
import util.Configuracoes;

/**
 *
 * @author idair
 */
public class Cache {

    private List<Musica> musicas;
    private List<Album> albums;
    private List<PlayList> playLists;
    private List<Configuracoes> configuracoes;

    private static Cache instance = null;

    private Cache() {
        this.musicas = new LinkedList<>();
        this.albums = new LinkedList<>();
        this.playLists = new LinkedList<>();
        this.configuracoes = new LinkedList<>();
    }

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public boolean insert(Object o) {
        boolean isMusica = o instanceof Musica;
        boolean isAlbum = o instanceof Album;
        boolean isPlayList = o instanceof PlayList;

        if (isMusica) {
            int nextMusicCodigo = 0;
            for (Musica m : musicas) {
                nextMusicCodigo = (m.getCodigo() > nextMusicCodigo) ? m.getCodigo() : nextMusicCodigo;
            }
            nextMusicCodigo = nextMusicCodigo + 1;
            Musica musica = (Musica) o;
            musica.setCodigo(nextMusicCodigo);
            this.musicas.add(musica);
            return true;
        }
        if (isAlbum) {
            int nextAlbumCodigo = 0;
            for (Album a : albums) {
                nextAlbumCodigo = (a.getCodigo() > nextAlbumCodigo) ? a.getCodigo() : nextAlbumCodigo;
            }
            nextAlbumCodigo = nextAlbumCodigo + 1;
            Album album = (Album) o;
            album.setCodigo(nextAlbumCodigo);
            this.albums.add(album);
            return true;
        }
        if (isPlayList) {
            int nextPlayListCodigo = 0;
            for (PlayList pl : playLists) {
                nextPlayListCodigo = (pl.getCodigo() > nextPlayListCodigo) ? pl.getCodigo() : nextPlayListCodigo;
            }
            nextPlayListCodigo = nextPlayListCodigo + 1;
            PlayList playList = (PlayList) o;
            playList.setCodigo(nextPlayListCodigo);
            this.playLists.add(playList);
            return true;
        }

        return false;
    }

    public Object update(Object o) {
        boolean isMusica = o instanceof Musica;
        boolean isAlbum = o instanceof Album;
        boolean isPlayList = o instanceof PlayList;
        return null;
    }

    public boolean delete(Object o) {
        boolean isMusica = o instanceof Musica;
        boolean isAlbum = o instanceof Album;
        boolean isPlayList = o instanceof PlayList;
        return true;
    }

    public List<Object> select(Object o) {
        boolean isMusica = o instanceof Musica;
        boolean isAlbum = o instanceof Album;
        boolean isPlayList = o instanceof PlayList;
        List<Object> objects = new LinkedList<>();
        if(isMusica){
            for (Musica musica : musicas) {
                objects.add(musica);
            }
        }
        if(isAlbum){
            for (Album album : albums) {
                objects.add(album);
            }
        }
        if(isPlayList){
            for (PlayList playList : playLists) {
                objects.add(playList);
            }
        }
        return objects;
    }

}
