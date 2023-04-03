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
import controle.DaoDb4o;
import java.util.List;
import modelo.PlayList;

/**
 *
 * @author Idair F. Guido
 */
public class ListaDAO {

    public static boolean inserir(PlayList playList) {
        try {
            DaoDb4o.conexao.store(playList);
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean alterar(PlayList playList) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(PlayList.class);
        consulta.constrain(playList);
        ObjectSet lista = consulta.execute();
        PlayList temp = null;
        if (!lista.isEmpty()) {
            temp = (PlayList) lista.get(0);
            
            DaoDb4o.conexao.store(temp);
            return true;
        } else {

            return false;
        }
    }

    public static boolean addMusica(int codigoMusica, int codigoPlayList) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(PlayList.class);
        consulta.descend("codigo").constrain(codigoPlayList).like();
        ObjectSet lista = consulta.execute();
        PlayList playList;
        if (!lista.isEmpty()) {
            playList = (PlayList) lista.get(0);
            return true;
        } else {
            return false;
        }
    }

    public static PlayList buscaPorNome(String nome){
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(PlayList.class);
        consulta.descend("titulo").constrain(nome).like();
        ObjectSet result = consulta.execute();
        if(!result.isEmpty()){
            PlayList temp = (PlayList) result.get(0);
            return temp;
        }else{
            return null;
        }
    }

    public static boolean existe(String nome){
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(PlayList.class);
        consulta.descend("titulo").constrain(nome).like();
        ObjectSet result = consulta.execute();
        if(!result.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean excluir(PlayList playList) {
        try {
            DaoDb4o.conexao.delete(playList);
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException | Db4oIOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<PlayList> localizar(String caracteristica, String conteudo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(PlayList.class);
        consulta.descend(caracteristica).constrain(conteudo).like();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<PlayList> temp = lista;
            return temp;
        } else {
            return null;
        }
    }

    public static PlayList localizar(int codigo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(PlayList.class);
        consulta.descend("codigo").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            PlayList playList = (PlayList) lista.get(0);
            return playList;
        } else {
            return null;
        }
    }

    public static List<PlayList> listaTodos() {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(PlayList.class);
        consulta.descend("titulo").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<PlayList> todos = lista;
            return todos;
        } else {
            return null;
        }
    }

     public static int codigoPlayListMaisMais(){
        Query consuta = DaoDb4o.conexao.query();
        consuta.constrain(PlayList.class);
        consuta.descend("codigo").orderDescending();//ordena a resposta de traz para frente
        ObjectSet respostaBanco = consuta.execute();

        if (respostaBanco.isEmpty()) {
            //se nao tiver ninguem no banco é pq vc esta cadastrando o
            //primeiro registro logo o codigo dele sera um.
            return 1;
        } else {
            PlayList localizado = (PlayList) respostaBanco.get(0);
            return ((localizado.getCodigo()) + 1);
        }

    }

}
