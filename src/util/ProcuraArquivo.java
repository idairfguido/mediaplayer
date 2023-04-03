/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Idair F. Guido
 */
public class ProcuraArquivo {

    private boolean multiSelecao = false;
    private File[] selecao;
    private List<File> arquivos = new ArrayList<>();
    private int opcao;

    public ProcuraArquivo() {
    }

    public List<File> procuraMusica(){
        verificaCaminho(buscar());
        return getArquivos();
    }

    private File buscar(){
        javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        jfc.setDialogTitle("File Search");
        jfc.setFileFilter(new FileNameExtensionFilter("Somente mp3 e ogg", "mp3", "ogg"));
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        opcao = jfc.showOpenDialog(jfc);
        System.out.println(opcao);
        if(jfc.getSelectedFiles().length > 1){
            selecao = jfc.getSelectedFiles();
            multiSelecao = true;
            return null;
        }else{
            if(jfc.getSelectedFile() != null){
                return jfc.getSelectedFile();
            }else{
                return null;
            }
        }
    }

    private static class FiltroArquivo implements FileFilter {
        @Override
        public boolean accept(File file) {
           return file.isDirectory() || file.getName().toLowerCase().endsWith(".mp3");
        }
    }

    private void verificaCaminho(File diretorio){
        if(diretorio == null){
            if(multiSelecao){
                for (File selecao1 : selecao) {
                    if (selecao1.isFile()) {
                        arquivos.add(selecao1);
                    }
                    if (selecao1.isDirectory()) {
                        buscaArquivos(selecao1);
                    }
                }
                multiSelecao = false;
            }
        }else{
            if(diretorio.getName().toLowerCase().endsWith(".mp3")){
                arquivos.add(diretorio);
            }else{
                if(diretorio.isDirectory()){
                    buscaArquivos(diretorio);
                }else{
                    opcao = 1;
                }
            }
        }
    }

    private void buscaArquivos(File pasta){
        File[]caminhosArquivos = pasta.listFiles(new FiltroArquivo());
        for (File caminhosArquivo : caminhosArquivos) {
            if (caminhosArquivo.isDirectory()) {
                buscaArquivos(caminhosArquivo);
            } else {
                arquivos.add(caminhosArquivo);
            }
        }
    }

    public List getArquivos() {
        if(arquivos.isEmpty()){
        }
        return arquivos;
    }
}
