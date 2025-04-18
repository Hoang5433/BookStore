/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author admin
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ProgressBarJPanel extends JPanel {
    float percent = 0;
    String bgColor = "#ffffff";
    String OuterColor = "#f8cf61";
    String textColor = "#0000000";
    String InnerColor = "#ebb9b9";
    public ProgressBarJPanel(){
    super();
    }
    public ProgressBarJPanel(String bgcolor,String OuterColor,String InnerColor){
        super();
        this.bgColor= bgcolor;
        this.OuterColor = OuterColor;
        this.InnerColor = InnerColor;
    }
    public void UpdateProgress(float percent){
        this.percent = percent;
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.translate(this.getWidth()/2,this.getHeight()/2);
        g2.rotate(Math.toRadians(270));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Arc2D.Float arc =  new Arc2D.Float(Arc2D.PIE);      
        Arc2D.Float background =  new Arc2D.Float(Arc2D.PIE);
        Ellipse2D.Float circle = new Ellipse2D.Float(0,0,160,160);
        
        circle.setFrameFromCenter(new Point(0,0),new Point(41,41));
        arc.setFrameFromCenter(new Point(0,0),new Point(50,50));
        background.setFrameFromCenter(new Point(0,0),new Point(50,50));
        background.setAngleStart(0);
        background.setAngleExtent(-100*3.6);
        arc.setAngleStart(0);
        arc.setAngleExtent(-percent*3.6);
        g2.setColor(Color.decode(InnerColor));
        g2.draw(background);
        g2.fill(background);
        g2.setColor(Color.decode(OuterColor));
        g2.draw(arc);
        g2.fill(arc);
        
        g2.setColor(Color.decode(bgColor));
        g2.draw(circle);
        g2.fill(circle);
        g2.setColor(Color.decode(textColor));
        g2.rotate(Math.toRadians(90));
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D rec2D = fm.getStringBounds(percent + "%", g);
        g2.drawString(percent+"%",-(int)rec2D.getWidth()/2,-(int)rec2D.getHeight()/2+fm.getAscent());
    }
}
