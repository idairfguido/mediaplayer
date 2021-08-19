/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import com.db4o.ObjectSet;
import com.db4o.query.Query;
import controle.DaoDb4o;
import java.util.List;
import modelo.Album;

/**
 *
 * @author Idair F. Guido
 */
public class AlbumDAO {

    public static boolean inserir(Album album) {
        try {
            DaoDb4o.conexao.store(album);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean alterar(Album album) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Album.class);
        consulta.constrain(album);
        ObjectSet lista = consulta.execute();
        Album temp = null;
        if (lista.size() > 0) {
            temp = (Album) lista.get(0);
            
            DaoDb4o.conexao.store(temp);
            return true;
        } else {

            return false;
        }
    }

    public static boolean existe(Album album){
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Album.class);
        consulta.descend("titulo").constrain(album.getTitulo()).like();
        ObjectSet lista = consulta.execute();
        if(lista.size() == 0){
            return false;
        }else{
            return true;
        }
    }

    public static boolean excluir(Album album) {
        try {
            DaoDb4o.conexao.delete(album);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Album> localizar(String caracteristica, String conteudo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Album.class);
        consulta.descend(caracteristica).constrain(conteudo).like();
        ObjectSet lista = consulta.execute();
        if (lista.size() > 0) {
            List<Album> temp = lista;
            return temp;
        } else {
            return null;
        }
    }

    public static Album localizar(int codigo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Album.class);
        consulta.descend("codigo").orderAscending();
        ObjectSet lista = consulta.execute();
        if (lista.size() > 0) {
            Album album = (Album) lista.get(0);
            return album;
        } else {
            return null;
        }
    }

    public static Album localizarPorTitulo(String titulo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Album.class);
        consulta.descend("titulo").orderAscending();
        ObjectSet lista = consulta.execute();
        if (lista.size() > 0) {
            Album album = (Album) lista.get(0);
            return album;
        } else {
            return null;
        }
    }

    public static List<Album> listaTodos() {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Album.class);
        consulta.descend("nome").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<Album> todos = lista;
            return todos;
        } else {
            return null;
        }
    }

     public static int codigoAlbumMaisMais(){
        Query consuta = DaoDb4o.conexao.query();
        consuta.constrain(Album.class);
        consuta.descend("codigo").orderDescending();//ordena a resposta de traz para frente
        ObjectSet respostaBanco = consuta.execute();

        if (respostaBanco.isEmpty()) {
            //se nao tiver ninguem no banco é pq vc esta cadastrando o
            //primeiro registro logo o codigo dele sera um.
            return 1;
        } else {
            Album localizado = (Album) respostaBanco.get(0);
            return ((localizado.getCodigo()) + 1);
        }

    }

}
