/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Digital Informática
 */
public class GerenciadorArquivos {
    
    public static final String PATH_LOG = "logs";
    public static final String ARQUIVO_LOG = "mediaplayer.log";

    public static void limparLogs() {
        File pathLog = new File(PATH_LOG);
        File[] arqs = pathLog.listFiles();
        LocalDate dataLimite = LocalDate.now().minusDays(15);
        Date dataDelecao = Formatar.convertToDate(dataLimite);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DGLogger.print(GerenciadorArquivos.class, DGLogger.TIPO_INFO, new Exception("Verificando arquivos anteriores a " + sdf.format(dataDelecao)));
        for (File f : arqs) {
            Date lastModified = new Date(f.lastModified());
            if (lastModified.before(dataDelecao)) {
                f.delete();
                DGLogger.print(GerenciadorArquivos.class, DGLogger.TIPO_INFO, new Exception("Deletando: " + f.getName()));
            }
        }
    }
    
    public static File getLogFile(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateForName = simpleDateFormat.format(new Date());      
        return new File(PATH_LOG+ File.separator + dateForName + "_" + ARQUIVO_LOG);
    }
}
