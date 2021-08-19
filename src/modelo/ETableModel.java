package modelo;


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
public class ETableModel implements TableModel{
    private List<Musica> listaObjetos = new LinkedList<Musica>();
    List<String> listaColunas = new LinkedList<String>();
    List<TableModelListener> listaMusicas = new LinkedList<TableModelListener>();

    public ETableModel() {
        listaColunas.add("Titulo");
        listaColunas.add("Artista");
        listaColunas.add("Album");
        listaColunas.add("Duracao");
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
            return getListaObjetos().get(rowIndex).getTitulo();
        if(columnIndex == 1)
            return getListaObjetos().get(rowIndex).getArtista();
        if(columnIndex == 2)
            return getListaObjetos().get(rowIndex).getAlbum();
        if(columnIndex == 3)
            return getListaObjetos().get(rowIndex).getDuração();
        else
            return null;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String dado = aValue.toString();
        if(columnIndex == 0)
            getListaObjetos().get(rowIndex).setTitulo(dado);
        if(columnIndex == 1)
            getListaObjetos().get(rowIndex).setArtista(dado);
        if(columnIndex == 2)
            getListaObjetos().get(rowIndex).setAlbum(dado);
        if(columnIndex == 3)
            getListaObjetos().get(rowIndex).setDuração(dado);
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

    public void addObjeto(Musica musica){
        getListaObjetos().add(musica);
    }

    /**
     * @param listaObjetos the listaObjetos to set
     */
    public void setListaObjetos(List<Musica> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    /**
     * @return the listaObjetos
     */
    public List<Musica> getListaObjetos() {
        return listaObjetos;
    }

    public Musica getMusica(int posicao){
        return listaObjetos.get(posicao);
    }

    public void removeAllElements(){
        listaObjetos = new LinkedList<Musica>();
    }
}
