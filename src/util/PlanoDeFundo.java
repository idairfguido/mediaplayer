/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JPanel;

/**
 *
 * @author Idair F. Guido
 */
public class PlanoDeFundo extends JPanel {

    private Image img = null;
    private String path;

    /**
     * Creates new class PlanoDeFundo
     *
     * @param pathImage
     */
    public PlanoDeFundo(String pathImage) {
        path = pathImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            URL myurl = this.getClass().getResource(path);
            Toolkit tk = this.getToolkit();
            img = tk.getImage(myurl);
            if (img != null) {
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        } catch (Exception e) {
        }
    }
}
