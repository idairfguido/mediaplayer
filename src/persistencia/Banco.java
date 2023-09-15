/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import java.io.File;
import java.nio.file.Path;
import util.DGLogger;

/**
 *
 * @author idair
 */
public class Banco {

    public static final String DB_PATH_NAME = "Biblioteca";
    public static final String DB_FILE_NAME = "dados.yap";

    private static ObjectContainer conexao = null;

    private Banco() {
    }

    public static ObjectContainer conexao() {
        File path = new File(DB_PATH_NAME);
        if (!path.exists()) {
            path.mkdirs();
        }
        Path p = new File(DB_PATH_NAME + File.separator + DB_FILE_NAME).toPath();
        System.out.println(p.toString());
        conexao = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), p.toString());
        DGLogger.print(Banco.class, DGLogger.TIPO_INFO, new Exception("DB4O (" + conexao.ext().version() + ") conectado."));
        return conexao;
    }
    
    public static void disconnect(){
        conexao.close();
    }

}
