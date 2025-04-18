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
public class setIconForCell extends DefaultTableCellRenderer {
  String value;
  public setIconForCell(JTable table,int row,int column){
      value = table.getValueAt(row,column).toString();
  }    
    
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

    //Cells are by default rendered as a JLabel.
    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    if(value.equals("")){
    l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/product/tuoitredanggiabaonhieu.png")));
    }
    else if(value.equals("Thêm sản phẩm")){
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-product.png")));
    }
    else if(value.equals("Xóa sản phẩm đã thêm")){
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove-product.png")));
    }    
    else if(value.equals("Sửa")){
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png")));
    }
    else if(value.equals("Xóa")){
        l.setText("");
        l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png")));
    }
    setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

  //Return the JLabel which renders the cell.
  return l;

}
}

