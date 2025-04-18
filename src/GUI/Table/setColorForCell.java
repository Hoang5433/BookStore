/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Table;

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
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author admin
 */
public class setColorForCell extends DefaultTableCellRenderer {
  String value;
  public setColorForCell(JTable table,int row,int column){
      value = table.getValueAt(row,column).toString();
  }    
    
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

    //Cells are by default rendered as a JLabel.
    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    if(value.equals("Đã thanh toán")|| value.equals("Hoàn thành")){
        l.setForeground(Color.decode("#20872a"));
    }
    else if(value.equals("Đã hủy") || value.equals("Chưa thanh toán") || value.equals(0)){
        l.setForeground(Color.decode("#da3434"));
    }    
    else{
        l.setForeground(Color.decode("#181818"));
    }
    setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

  //Return the JLabel which renders the cell.
  return l;

}
}

