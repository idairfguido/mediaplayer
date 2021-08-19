package controle;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

/**
 *
 * @author Marcos
 */

public class DaoDb4o {

    public static BancoDb4o servidor = new BancoDb4o();//servidor
    public static ObjectContainer conexao = null;//objet de conexão com o banco

    public DaoDb4o() {
    }

    /**
     * Este método trata de startar o banco e conectar.
     */
    public static boolean conectar() {
        servidor.inicializarServidor();//Levanta o servidor, coloca acessível
        try {
            conexao = Db4o.openClient("localhost", 4488, "root", "root");//conecta ao banco
            System.out.println("Banco dbf0 conectado");
            return true;
        } catch (Exception e) {
            System.out.println("Não conectado_> " + e);
            return false;
        }
    }

    public static void desconectar() {
        try {
                  conexao.close();//desliga aplicação do banco
                  System.out.println("Banco dbf0 desconectado.");
        } catch (Exception e) {
            System.out.println("Erro ao desconectar_> " + e);
        }
        
        try {
                   servidor.pararServidor();//derruba o servidor
        } catch (Exception e) {
                   System.out.println("Erro ao parar o servidor!! "+e);
        }
    }
}
