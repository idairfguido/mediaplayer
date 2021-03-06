/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import com.db4o.ObjectSet;
import com.db4o.query.Query;
import controle.DaoDb4o;
import util.Configuracoes;

/**
 *
 * @author Idair F. Guido
 */
public class ConfigDAO {

    public static boolean inserir(Configuracoes config) {
        try {
            DaoDb4o.conexao.store(config);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean alterar(Configuracoes config) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Configuracoes.class);
        consulta.constrain(config);
        ObjectSet lista = consulta.execute();
        Configuracoes temp = null;
        if (lista.size() > 0) {
            temp = (Configuracoes) lista.get(0);

//            temp.setAleatorio(config.isAleatorio());
//            temp.setNumero(config.getNumero());
//            temp.setRepetir(config.isRepetir());
//            temp.setSequencia(config.isSequencia());
//            temp.setUltimaPlayListExecutada(config.getUltimaPlayListExecutada());
            
            DaoDb4o.conexao.store(temp);
            return true;
        } else {

            return false;
        }
    }

    public static boolean excluir(Configuracoes config) {
        try {
            DaoDb4o.conexao.delete(config);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Configuracoes localizar(int codigo) {
        Query consulta = DaoDb4o.conexao.query();
        consulta.constrain(Configuracoes.class);
        consulta.descend("numero").orderAscending();
        ObjectSet lista = consulta.execute();
        if (lista.size() > 0) {
            Configuracoes config = (Configuracoes) lista.get(0);
            return config;
        } else {
            return null;
        }
    }

}
