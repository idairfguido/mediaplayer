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
import modelo.PlayList;

/**
 *
 * @author Idair F. Guido
 */
public class ListaDAO {

    private static final int USE_CACHE = 0;
    private static final int USE_DB4O = 1;
    private static final int USE = USE_CACHE;

    public static boolean inserir(PlayList playList) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(playList);
        } else {
            try {
                Banco.conexao().store(playList);
                Banco.disconnect();
                return true;
            } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public static boolean alterar(PlayList playList) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(playList);
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(PlayList.class);
            consulta.constrain(playList);
            ObjectSet lista = consulta.execute();
            PlayList temp = null;
            if (!lista.isEmpty()) {
                temp = (PlayList) lista.get(0);

                Banco.conexao().store(temp);
                Banco.disconnect();
                return true;
            } else {
                Banco.disconnect();
                return false;
            }
        }
    }

    public static boolean addMusica(int codigoMusica, int codigoPlayList) {
        if (USE == USE_CACHE) {
            return true;
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(PlayList.class);
            consulta.descend("codigo").constrain(codigoPlayList).like();
            ObjectSet lista = consulta.execute();
            PlayList playList;
            if (!lista.isEmpty()) {
                playList = (PlayList) lista.get(0);
                Banco.disconnect();
                return true;
            } else {
                Banco.disconnect();
                return false;
            }
        }
    }

    public static PlayList buscaPorNome(String nome) {
        if (USE == USE_CACHE) {
            return new PlayList();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(PlayList.class);
            consulta.descend("titulo").constrain(nome).like();
            ObjectSet result = consulta.execute();
            if (!result.isEmpty()) {
                PlayList temp = (PlayList) result.get(0);
                Banco.disconnect();
                return temp;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static boolean existe(String nome) {
        if (USE == USE_CACHE) {
            return true;
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(PlayList.class);
            consulta.descend("titulo").constrain(nome).like();
            ObjectSet result = consulta.execute();
            if (!result.isEmpty()) {
                Banco.disconnect();
                return true;
            } else {
                Banco.disconnect();
                return false;
            }
        }
    }

    public static boolean excluir(PlayList playList) {
        if (USE == USE_CACHE) {
            return Cache.getInstance().insert(playList);
        } else {
            try {
                Banco.conexao().delete(playList);
                Banco.disconnect();
                return true;
            } catch (DatabaseClosedException | DatabaseReadOnlyException | Db4oIOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public static List<PlayList> localizar(String caracteristica, String conteudo) {
        if (USE == USE_CACHE) {
            return new LinkedList<>();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(PlayList.class);
            consulta.descend(caracteristica).constrain(conteudo).like();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<PlayList> temp = lista;
                Banco.disconnect();
                return temp;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static PlayList localizar(int codigo) {
        if (USE == USE_CACHE) {
            return new PlayList();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(PlayList.class);
            consulta.descend("codigo").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                PlayList playList = (PlayList) lista.get(0);
                Banco.disconnect();
                return playList;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static List<PlayList> listaTodos() {
        if (USE == USE_CACHE) {
            return new LinkedList<>();
        } else {
            Query consulta = Banco.conexao().query();
            consulta.constrain(PlayList.class);
            consulta.descend("titulo").orderAscending();
            ObjectSet lista = consulta.execute();
            if (!lista.isEmpty()) {
                List<PlayList> todos = lista;
                Banco.disconnect();
                return todos;
            } else {
                Banco.disconnect();
                return null;
            }
        }
    }

    public static int codigoPlayListMaisMais() {
        if (USE == USE_CACHE) {
            return 0;
        } else {
//        Query consuta = Banco.conexao().query();
//        consuta.constrain(PlayList.class);
//        consuta.descend("codigo").orderDescending();//ordena a resposta de traz para frente
            ObjectSet respostaBanco = Banco.conexao().queryByExample(new PlayList());

            int retorno = 0;
            if (respostaBanco.isEmpty()) {
                //se nao tiver ninguem no banco é pq vc esta cadastrando o
                //primeiro registro logo o codigo dele sera um.
                retorno = 1;
            } else {
                PlayList localizado = (PlayList) respostaBanco.get(0);
                retorno = ((localizado.getCodigo()) + 1);
            }
            Banco.disconnect();
            return retorno;
        }

    }

}
