/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Query;
import java.util.LinkedList;
import java.util.List;
import modelo.Album;

/**
 *
 * @author Idair F. Guido
 */
public class AlbumDAO {

    private static final int USE_CACHE = 0;
    private static final int USE_DB4O = 1;
    private static final int USE = USE_CACHE;

    public static boolean inserir(Album album) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(album);
        } else {
            try {
                Banco.conexao().store(album);
                Banco.disconnect();
                return true;
            } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public static boolean alterar(Album album) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(album);
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Album.class);
            consulta.constrain(album);
            ObjectSet lista = consulta.execute();
            Album temp = null;
            if (!lista.isEmpty()) {
                temp = (Album) lista.get(0);
                Banco.conexao().store(temp);
                Banco.disconnect();
                return true;
            } else {
                Banco.disconnect();
                return false;
            }
        }
    }

    public static boolean existe(Album album) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(album);
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Album.class);
            consulta.descend("titulo").constrain(album.getTitulo()).like();
            ObjectSet lista = consulta.execute();
            if (lista.isEmpty()) {
                Banco.disconnect();
                return false;
            } else {
                Banco.disconnect();
                return true;
            }
        }

    }

    public static boolean excluir(Album album) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(album);
        } else {
            try {
                Banco.conexao().delete(album);
                Banco.disconnect();
                return true;
            } catch (DatabaseClosedException | DatabaseReadOnlyException | Db4oIOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public static List<Album> localizar(String caracteristica, String conteudo) {
        if (USE == USE_CACHE) {
            return new LinkedList<>();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Album.class);
            consulta.descend(caracteristica).constrain(conteudo).like();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<Album> temp = lista;
                Banco.disconnect();
                return temp;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static Album localizar(int codigo) {
        if (USE == USE_CACHE) {
            return new Album();
        } else {

            Query consulta = Banco.conexao().query();
            consulta.constrain(Album.class);
            consulta.descend("codigo").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                Album album = (Album) lista.get(0);
                Banco.disconnect();
                return album;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static Album localizarPorTitulo(String titulo) {
        if (USE == USE_CACHE) {
            return new Album();
        } else {

            Query consulta = Banco.conexao().query();
            consulta.constrain(Album.class);
            consulta.descend("titulo").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                Album album = (Album) lista.get(0);
                Banco.disconnect();
                return album;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static List<Album> listaTodos() {
        if (USE == USE_CACHE) {
            return new LinkedList<>();
        } else {

            Query consulta = Banco.conexao().query();
            consulta.constrain(Album.class);
            consulta.descend("nome").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<Album> todos = lista;
                Banco.disconnect();
                return todos;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static int codigoAlbumMaisMais() {
        if (USE == USE_CACHE) {
            return 0;
        } else {

            Query consuta = Banco.conexao().query();
            consuta.constrain(Album.class);
            consuta.descend("codigo").orderDescending();//ordena a resposta de traz para frente
            ObjectSet respostaBanco = consuta.execute();
            int retorno = 0;
            if (respostaBanco.isEmpty()) {
                //se nao tiver ninguem no banco é pq vc esta cadastrando o
                //primeiro registro logo o codigo dele sera um.
                retorno = 1;
            } else {
                Album localizado = (Album) respostaBanco.get(0);
                retorno = ((localizado.getCodigo()) + 1);
            }
            Banco.disconnect();
            return retorno;
        }

    }

}
