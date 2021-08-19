/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.LinkedList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Idair F. Guido
 */
public class GListModel implements ListModel{

    private List<String> listaObjetos = new LinkedList<String>();
    private List<ListDataListener> listaPlayList = new LinkedList<ListDataListener>();

    public int getSize() {
        return listaObjetos.size();
    }

    public Object getElementAt(int index) {
        return listaObjetos.get(index);
    }

    public void addListDataListener(ListDataListener l) {
        listaPlayList.add(l);
    }

    public void removeListDataListener(ListDataListener l) {
        listaPlayList.remove(l);
    }

    public void addElement(String obj){
        listaObjetos.add(obj);
    }

    public void removeAllElements(){
        listaObjetos = new LinkedList<String>();
    }

}
