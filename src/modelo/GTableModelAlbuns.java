package modelo;


import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import persistencia.AlbumDAO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Idair F. Guido
 */
public class GTableModelAlbuns implements TableModel{
    private List<Album> listaObjetos = new LinkedList<Album>();
    List<String> listaColunas = new LinkedList<String>();
    List<TableModelListener> listaMusicas = new LinkedList<TableModelListener>();

    public GTableModelAlbuns() {
        listaColunas.add("Codigo");
        listaColunas.add("Titulo");
        listaColunas.add("Artista");
    }

    public int getRowCount() {
        return getListaObjetos().size();
    }

    public int getColumnCount() {
        return listaColunas.size();
    }

    public String getColumnName(int columnIndex) {
        return listaColunas.get(columnIndex);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return listaColunas.get(columnIndex).getClass();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0)
            return getListaObjetos().get(rowIndex).getCodigo();
        if(columnIndex == 1)
            return getListaObjetos().get(rowIndex).getTitulo();
        if(columnIndex == 2)
            return getListaObjetos().get(rowIndex).getArtista();
        else
            return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String dado = aValue.toString();
        if(columnIndex == 0)
            getListaObjetos().get(rowIndex).setCodigo(Integer.parseInt(dado));
        if(columnIndex == 1)
            getListaObjetos().get(rowIndex).setTitulo(dado);
        if(columnIndex == 2)
            getListaObjetos().get(rowIndex).setArtista(dado);
       
    }

    public void addTableModelListener(TableModelListener l) {
        listaMusicas.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        listaMusicas.remove(l);
    }

    public void setListaColunas(List<String> listaColunas) {
        this.listaColunas = listaColunas;
    }

    public void addObjeto(Album album){
        getListaObjetos().add(album);
    }

    /**
     * @param listaObjetos the listaObjetos to set
     */
    public void setListaObjetos(List<Album> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    /**
     * @return the listaObjetos
     */
    public List<Album> getListaObjetos() {
        return listaObjetos;
    }

    public void removeAllElements(){
        listaObjetos = new LinkedList<Album>();
    }

    public Album getObjeto(int index){
        return listaObjetos.get(index);
    }
}
