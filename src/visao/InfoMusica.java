/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InfoMusica.java
 *
 * Created on 11/02/2010, 02:57:40
 */

package visao;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import modelo.Musica;
import util.PlanoDeFundo;

/**
 *
 * @author Idair F. Guido
 */
public class InfoMusica extends javax.swing.JFrame {
    private Musica musica;

    /** Creates new form InfoMusica */
    public InfoMusica(Musica musica) {
        setaIcon();
        initComponents();
        this.musica = musica;
        setInfo();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jlTitulo = new javax.swing.JLabel();
        jlArtista = new javax.swing.JLabel();
        jlAlbum = new javax.swing.JLabel();
        jlNome = new javax.swing.JLabel();
        jlQualidade = new javax.swing.JLabel();
        jlFormato = new javax.swing.JLabel();
        jlCaminho = new javax.swing.JLabel();
        jlDuracao = new javax.swing.JLabel();
        jtfTitulo = new javax.swing.JTextField();
        jtfArtista = new javax.swing.JTextField();
        jtfAlbum = new javax.swing.JTextField();
        jtfNome = new javax.swing.JTextField();
        jtfQualidade = new javax.swing.JTextField();
        jtfFormato = new javax.swing.JTextField();
        jtfDuracao = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaCaminho = new javax.swing.JTextArea();
        jbOk = new javax.swing.JButton();
        jlTituloInfoMusica = new javax.swing.JLabel();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Propriedades");
        setResizable(false);

        jlTitulo.setText("Titulo:");

        jlArtista.setText("Artista:");

        jlAlbum.setText("Album:");

        jlNome.setText("Nome:");

        jlQualidade.setText("Qualidade:");

        jlFormato.setText("Formato:");

        jlCaminho.setText("Caminho:");

        jlDuracao.setText("Duração:");

        jtaCaminho.setColumns(20);
        jtaCaminho.setRows(1);
        jScrollPane1.setViewportView(jtaCaminho);

        jbOk.setText("Ok");
        jbOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOkActionPerformed(evt);
            }
        });

        jlTituloInfoMusica.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTituloInfoMusica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTituloInfoMusica.setText("Informacões sobre a Midia em execução.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jlTituloInfoMusica, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(jlArtista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlAlbum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlDuracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlCaminho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlQualidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlFormato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jtfArtista, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jtfTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jtfAlbum, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jtfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jtfQualidade, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jtfFormato, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jtfDuracao, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jbOk, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jlTituloInfoMusica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTitulo)
                    .addComponent(jtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlArtista)
                    .addComponent(jtfArtista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAlbum)
                    .addComponent(jtfAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNome)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlQualidade)
                    .addComponent(jtfQualidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFormato)
                    .addComponent(jtfFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDuracao)
                    .addComponent(jtfDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlCaminho)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbOk))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-350)/2, (screenSize.height-406)/2, 350, 406);
    }// </editor-fold>//GEN-END:initComponents

    private void jbOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOkActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbOkActionPerformed

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new InfoMusica().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JButton jbOk;
    private javax.swing.JLabel jlAlbum;
    private javax.swing.JLabel jlArtista;
    private javax.swing.JLabel jlCaminho;
    private javax.swing.JLabel jlDuracao;
    private javax.swing.JLabel jlFormato;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlQualidade;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JLabel jlTituloInfoMusica;
    private javax.swing.JTextArea jtaCaminho;
    private javax.swing.JTextField jtfAlbum;
    private javax.swing.JTextField jtfArtista;
    private javax.swing.JTextField jtfDuracao;
    private javax.swing.JTextField jtfFormato;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfQualidade;
    private javax.swing.JTextField jtfTitulo;
    // End of variables declaration//GEN-END:variables

    private void setInfo(){
        jtfAlbum.setText(musica.getAlbum());
        jtfArtista.setText(musica.getArtista());
        jtfDuracao.setText(musica.getDuração());
        jtfFormato.setText(musica.getFormato());
        jtfNome.setText(musica.getNome());
        jtfQualidade.setText(musica.getQualidade());
        jtfTitulo.setText(musica.getTitulo());
        jtaCaminho.setText(musica.getCaminho().toString());
    }

    public void setaIcon(){
        Image img = new ImageIcon(getClass().getResource("/visao/icones/picon.png")).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT);
        this.setIconImage(img);
        JPanel bg = new PlanoDeFundo("/visao/imagens/fundo_propriedades.jpg");
        this.setContentPane(bg);
    }

}
