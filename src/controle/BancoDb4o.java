/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.db4o.Db4o;
import com.db4o.ObjectServer;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.ext.IncompatibleFileFormatException;
import com.db4o.ext.OldFormatException;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class BancoDb4o {

    private ObjectServer server; //cria objeto servidor

    public BancoDb4o() {
    }

    /**
     * Classe pa levantar o servidor
     */
    public void inicializarServidor() {
        try {
            if (!new File("./Biblioteca/").exists()) {
                if (new File("./Biblioteca/").mkdirs()) {
                    System.out.println("Biblioteca criada!");
                }
            }
            server = Db4o.openServer("./Biblioteca/dados.yap", 4488);//local onde o arquivo de dados fica e porta de conexão.
            server.grantAccess("root", "root");//damos um usuário e senha no servidor
        } catch (DatabaseFileLockedException | DatabaseReadOnlyException | Db4oIOException | IncompatibleFileFormatException | OldFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar o servidor!!");
        }

    }

    /**
     * Para o servidor
     */
    public void pararServidor() {
        try {
            server.close();//derruba o servidor
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao parar o servidor!!");
        }
    }
}
