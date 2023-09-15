/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Digital Informática
 */
public class DGLogger {

    public static final int TIPO_INFO = 0;
    public static final int TIPO_WARN = 1;
    public static final int TIPO_ERROR = 2;
    public static final int TIPO_FATAL_ERROR = 3;

    public static final boolean IS_FILENAME = true;
    public static final boolean NO_FILENAME = false;

    public static String getGravidade(int tipo) {
        switch (tipo) {
            case TIPO_INFO: {
                return "INFO";
            }
            case TIPO_WARN: {
                return "WARN";
            }
            case TIPO_ERROR: {
                return "ERROR";
            }
            case TIPO_FATAL_ERROR: {
                return "FATAL_ERROR";
            }
            default: {
                return "UNDEFINED";
            }
        }
    }

    public static void print(Class c, int tipo, Exception exception) {
        String gravidade = "";
        switch (tipo) {
            case TIPO_INFO: {
                gravidade = "INFO";
                break;
            }
            case TIPO_WARN: {
                gravidade = "WARN";
                break;
            }
            case TIPO_ERROR: {
                gravidade = "ERROR";
                break;
            }
            case TIPO_FATAL_ERROR: {
                gravidade = "FATAL_ERROR";
                break;
            }
            default: {
                gravidade = "UNDEFINED";
            }
        }

        String linha = dateLog(NO_FILENAME) + " - " + gravidade + " - " + c.getSimpleName() + " - " + exception.getLocalizedMessage();
        if (gravaLog(linha)) {
            System.out.println(linha);
        }

    }

    private static String dateLog(boolean filename) {
        String pattern = (filename) ? "dd-MM-yyyy" : "EEE dd MMM yyyy HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    private static boolean gravaLog(String linha) {
        try {
            criaArquivo();

            boolean anexar = false;
            File f = GerenciadorArquivos.getLogFile();
            if (f.length() != 0L) {
                anexar = true;
            }
            FileWriter writer = new FileWriter(f, anexar);
            try ( PrintWriter printWriter = new PrintWriter(writer)) {
                printWriter.println(linha);
                printWriter.flush();
            }

            return true;
        } catch (IOException ex) {
            Logger.getLogger(DGLogger.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private static void criaArquivo() {
        File path = new File(GerenciadorArquivos.PATH_LOG);
        if (!path.exists()) {
            path.mkdirs();
        }
        File f = GerenciadorArquivos.getLogFile();
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DGLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
