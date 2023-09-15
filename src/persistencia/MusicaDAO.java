/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Query;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import modelo.Musica;

/**
 *
 * @author Idair F. Guido
 */
public class MusicaDAO {

    private static final int USE_CACHE = 0;
    private static final int USE_DB4O = 1;
    private static final int USE = USE_CACHE;

    public static boolean inserir(Musica musica) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(musica);
        } else {
            try {
                Banco.conexao().store(musica);
                Banco.disconnect();
                return true;
            } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public static boolean alterar(Musica musica) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(musica);
        } else {
            Query consulta = Banco.conexao().query();
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
                Banco.conexao().store(temp);
                Banco.disconnect();
                return true;
            } else {
                Banco.disconnect();
                return false;
            }
        }
    }

    public static boolean excluir(Musica musica) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(musica);
        } else {
            try {
                Banco.conexao().delete(musica);
                Banco.disconnect();
                return true;
            } catch (DatabaseClosedException | DatabaseReadOnlyException | Db4oIOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public static List<Musica> localizar(String caracteristica, String conteudo) {
        if (USE == USE_CACHE) {
            List<Object> objects = Cache.getInstance().select(new Musica());
            List<Musica> musicas = new LinkedList<>();
            for (Object object : objects) {
                Musica musica = (Musica) object;
                if(musica.getTitulo().contains(conteudo)){
                    musicas.add(musica);
                }
            }
            return musicas;
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Musica.class);
            consulta.descend(caracteristica).constrain(conteudo).like();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<Musica> temp = lista;
                Banco.disconnect();
                return temp;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static boolean existe(Musica musica) {
        if (USE == USE_CACHE) {
            return false;
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Musica.class);
            consulta.descend("titulo").constrain(musica.getTitulo()).like();
            Musica m = new Musica();
            m.setTitulo(musica.getTitulo());
            ObjectSet lista = Banco.conexao().queryByExample(m);
            int porTitulo = lista.size();
            Banco.disconnect();

            Query consulta2 = Banco.conexao().query();
            consulta2.constrain(Musica.class);
            consulta2.descend("album").constrain(musica.getAlbum()).like();
            ObjectSet lista2 = consulta2.execute();
            int porAlbum = lista2.size();
            Banco.disconnect();

            ObjectContainer conexao3 = Banco.conexao();
            Query consulta3 = conexao3.query();
            consulta3.constrain(Musica.class);
            consulta3.descend("nome").constrain(musica.getNome()).like();
            ObjectSet lista3 = consulta3.execute();
            int porNome = lista3.size();
            Banco.disconnect();

            return !((porTitulo == 0) || (porAlbum == 0) || (porNome == 0));
        }
    }

    public static Musica localizar(int codigo) {
        if (USE == USE_CACHE) {
            List<Object> objects = Cache.getInstance().select(null);
            for (Object object : objects) {
                Musica musica = (Musica) object;
                if(musica.getCodigo() == codigo){
                    return musica;
                }
            }         
            return new Musica();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Musica.class);
            consulta.descend("codigo").constrain(codigo).like();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                Musica musica = (Musica) lista.get(0);
                Banco.disconnect();
                return musica;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static List<Musica> listaTodos() {
        if (USE == USE_CACHE) {
            List<Object> objects = Cache.getInstance().select(new Musica());
            List<Musica> musicas = new LinkedList<>();
            for (Object object : objects) {
                musicas.add((Musica) object);
            }
            return musicas;
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Musica.class);
            consulta.descend("codigo").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<Musica> todos = lista;
                Banco.disconnect();
                return todos;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static boolean servidorVazio() {
        if (USE == USE_CACHE) {
            return true;
        } else {
            boolean retorno = true;
            try {
//            Query consulta = Banco.conexao().query();
//            consulta.constrain(Musica.class);
//            consulta.descend("codigo").orderAscending();
                ObjectSet lista = Banco.conexao().queryByExample(new Musica());
                retorno = lista.isEmpty();
            } catch (DatabaseClosedException e) {
                Logger.getLogger(MusicaDAO.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                Banco.disconnect();
                return retorno;
            }
        }
    }

    public static List<Musica> listaTodosPorTitulo() {
        if (USE == USE_CACHE) {
            return new LinkedList<>();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Musica.class);
            consulta.descend("titulo").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<Musica> todos = lista;
                Banco.disconnect();
                return todos;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static List<Musica> listaTodosPorAlbum() {
        if (USE == USE_CACHE) {
            return new LinkedList<>();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Musica.class);
            consulta.descend("album").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<Musica> todos = lista;
                Banco.disconnect();
                return todos;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static List<Musica> listaTodosPorArtista() {
        if (USE == USE_CACHE) {
            return new LinkedList<>();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(Musica.class);
            consulta.descend("artista").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<Musica> todos = lista;
                Banco.disconnect();
                return todos;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static int codigoMusicaMaisMais() {
        if (USE == USE_CACHE) {
            return 0;
        } else {
//        Query consuta = Banco.conexao().query();
//        consuta.constrain(Musica.class);
//        consuta.descend("codigo").orderDescending();//ordena a resposta de traz para frente
            ObjectSet respostaBanco = Banco.conexao().queryByExample(new Musica());
            if (respostaBanco.isEmpty()) {
                //se nao tiver ninguem no banco é pq vc esta cadastrando o
                //primeiro registro logo o codigo dele sera um.
                Banco.disconnect();
                return 1;
            } else {
                Musica localizado = (Musica) respostaBanco.get(0);
                Banco.disconnect();
                return ((localizado.getCodigo()) + 1);
            }
        }

    }

}
