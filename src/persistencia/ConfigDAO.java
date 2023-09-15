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
import util.Configuracoes;

/**
 *
 * @author Idair F. Guido
 */
public class ConfigDAO {

    public static boolean inserir(Configuracoes config) {
        try {
            Banco.conexao().store(config);
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean alterar(Configuracoes config) {
        Query consulta = Banco.conexao().query();
        consulta.constrain(Configuracoes.class);
        consulta.constrain(config);
        ObjectSet lista = consulta.execute();
        Configuracoes temp = null;
        if (!lista.isEmpty()) {
            temp = (Configuracoes) lista.get(0);

//            temp.setAleatorio(config.isAleatorio());
//            temp.setNumero(config.getNumero());
//            temp.setRepetir(config.isRepetir());
//            temp.setSequencia(config.isSequencia());
//            temp.setUltimaPlayListExecutada(config.getUltimaPlayListExecutada());
            
            Banco.conexao().store(temp);
            return true;
        } else {

            return false;
        }
    }

    public static boolean excluir(Configuracoes config) {
        try {
            Banco.conexao().delete(config);
            return true;
        } catch (DatabaseClosedException | DatabaseReadOnlyException | Db4oIOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Configuracoes localizar(int codigo) {
        Query consulta = Banco.conexao().query();
        consulta.constrain(Configuracoes.class);
        consulta.descend("numero").orderAscending();
        ObjectSet lista = consulta.execute();
        if (!lista.isEmpty()) {
            Configuracoes config = (Configuracoes) lista.get(0);
            return config;
        } else {
            return null;
        }
    }

}
