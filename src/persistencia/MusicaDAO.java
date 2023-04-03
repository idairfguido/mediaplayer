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
import modelo.Musica;

/**
 *
 * @author Idair F. Guido
 */
public class MusicaDAO {

    public static boolean inserir(Musica musica) {
        try {
            DaoDb4o.conexao.store(musica);
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean alterar(Musica musica) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.constrain(musica);
        ObjectSet lista = consulta.execute();
        Musica temp = null;
        if (!lista.isEmpty()) {
//            temp = (Musica) lista.get(0);
//            temp.setAlbum(musica.getAlbum());
//            temp.setArtista(musica.getArtista());
//            temp.setCaminho(musica.getCaminho());
//            temp.setClassificacao(musica.getClassificacao());
//            temp.setFormato(musica.getFormato());
//            temp.setNome(musica.getNome());
//            temp.setQualidade(musica.getQualidade());
//            temp.setTamanhoArquivo(musica.getTamanhoArquivo());
            DaoDb4o.conexao.store(temp);
            return true;
        } else {

            return false;
        }
    }

    public static boolean excluir(Musica musica) {
        try {
            DaoDb4o.conexao.delete(musica);
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException | Db4oIOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Musica> localizar(String caracteristica, String conteudo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend(caracteristica).constrain(conteudo).like();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<Musica> temp = lista;
            return temp;
        } else {
            return null;
        }
    }

    public static boolean existe(Musica musica){
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend("titulo").constrain(musica.getTitulo()).like();
        Query consulta2 = DaoDb4o.conexao.query();
        consulta2.constrain(Musica.class);
        consulta2.descend("album").constrain(musica.getAlbum()).like();
        Query consulta3 = DaoDb4o.conexao.query();
        consulta3.constrain(Musica.class);
        consulta3.descend("nome").constrain(musica.getNome()).like();
        ObjectSet lista = consulta.execute();
        ObjectSet lista2 = consulta2.execute();
        ObjectSet lista3 = consulta3.execute();
        int porAlbum = lista2.size();
        int porTitulo = lista.size();
        int porNome = lista3.size();
        return !((porTitulo == 0) || (porAlbum == 0) || (porNome == 0));
    }

    public static Musica localizar(int codigo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend("codigo").constrain(codigo).like();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            Musica musica = (Musica) lista.get(0);
            return musica;
        } else {
            return null;
        }
    }

    public static List<Musica> listaTodos() {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend("codigo").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<Musica> todos = lista;
            return todos;
        } else {
            return null;
        }
    }

    public static boolean servidorVazio(){
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend("codigo").orderAscending();
        ObjectSet lista = consulta.execute();
        return lista.isEmpty();
    }

    public static List<Musica> listaTodosPorTitulo() {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend("titulo").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<Musica> todos = lista;
            return todos;
        } else {
            return null;
        }
    }

    public static List<Musica> listaTodosPorAlbum() {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend("album").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<Musica> todos = lista;
            return todos;
        } else {
            return null;
        }
    }

    public static List<Musica> listaTodosPorArtista() {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Musica.class);
        consulta.descend("artista").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            List<Musica> todos = lista;
            return todos;
        } else {
            return null;
        }
    }

     public static int codigoMusicaMaisMais(){
        Query consuta = DaoDb4o.conexao.query();
        consuta.constrain(Musica.class);
        consuta.descend("codigo").orderDescending();//ordena a resposta de traz para frente
        ObjectSet respostaBanco = consuta.execute();

        if (respostaBanco.isEmpty()) {
            //se nao tiver ninguem no banco é pq vc esta cadastrando o
            //primeiro registro logo o codigo dele sera um.
            return 1;
        } else {
            Musica localizado = (Musica) respostaBanco.get(0);
            return ((localizado.getCodigo()) + 1);
        }

    }

}
