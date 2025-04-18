/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class Rounded extends JPanel{
    public int cornerRadius = 30;
    public int x =0,y=0;
    public Color backgroundColor;
    protected Color shadowColor = Color.decode("#d9dfe3");
    protected int shadowAlpha = 255;
    protected int shadowGap = 2;
    protected int shadowOffset = 4;
    protected int strokeSize = 0;       
    protected int shadow=0;
    public void changeColor(Color color){
        backgroundColor = color;
    }
    public void setShadow(int shadow){
        this.shadow = shadow;
    }
@Override
   protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            int shadowGap = this.shadowGap;
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            

            
            graphics.fillRoundRect(x,y, width, height, arcs.width, arcs.height);
            //paint background
            
            if(shadow==1){
            Color shadowColorA = new Color(shadowColor.getRed(),
            shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha); 
            
           graphics.setColor(shadowColorA);
           graphics.fillRoundRect(
                   shadowOffset,// X position
                   shadowOffset,// Y position
                   width - strokeSize - shadowOffset, // width
                   height - strokeSize - shadowOffset, // height
                   arcs.width, arcs.height);// arc Dimension
            
            
            graphics.setColor(Color.white);
            graphics.fillRoundRect(0, 0, width - shadowGap,
            height - shadowGap, arcs.width, arcs.height);
            graphics.setColor(shadowColorA);
            graphics.setStroke(new BasicStroke(strokeSize));
            graphics.drawRoundRect(0, 0, width - shadowGap,
            height - shadowGap, arcs.width, arcs.height);

            //Sets strokes to default, is better.
            graphics.setStroke(new BasicStroke()); 
            
          //graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
   }
   }
}
