/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import GUI.SanPhamJPanel;
import GUI.DoanhThuJPanel;
import GUI.ThongKeJPanel;
import GUI.TongQuanJPanel;
import GUI.DonHangJPanel;
import GUI.KhachHangJPanel;
import GUI.QLNVJPanel;
import GUI.ThemTaiKhoanJPanel;
import GUI.PhieuNhapHangJPanel;
import GUI.KhuyenMaiJPanel;
import GUI.QuanLyDanhMucJPanel;
import GUI.RoundedJPanel;
import GUI.CaiDatJPanel;
import bean.DanhMucBean;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author admin 
 */
public class changeScreenController {
    
    private JPanel pnlRoot;
    private String selectedScreen = "";
    private List<DanhMucBean> listItem = null; 
    public changeScreenController(JPanel pnlRoot) {
        this.pnlRoot = pnlRoot;
    }
    
public void setScreen(String selectedScreen,RoundedJPanel pnlItem,JLabel lblItem){
        
        this.selectedScreen = selectedScreen;
        
        lblItem.setForeground(Color.white);
        pnlItem.changeColor(Color.decode("#0092f2"));
        lblItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/"+selectedScreen+".png")));
        pnlItem.setBorder(BorderFactory.createMatteBorder(
                    0, 3, 0, 0, Color.decode("#d2302c")));         
        pnlRoot.removeAll();
        pnlRoot.setLayout(new BorderLayout());
        try {
            pnlRoot.add((Component) Class.forName("GUI."+this.selectedScreen+"JPanel").newInstance());
        } catch (Exception ex) {
            Logger.getLogger(changeScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pnlRoot.validate();
        pnlRoot.repaint();
       
        
    }
    
    public void setEvent(List<DanhMucBean> listItem){
        this.listItem = listItem;
        for(DanhMucBean item : listItem){
            item.getPnlName().addMouseListener(new changeScreen(item.getKindOfScreen(),item.getPnlName(),item.getLblName()));
        }
        
    } 
    class changeScreen implements MouseListener {
        private JPanel current;
        private String kindOfScreen = "";
        private RoundedJPanel pnlName;
        private JLabel lblName;
        
        
        changeScreen(String kindOfScreen,RoundedJPanel pnlName,JLabel lblName){
            this.kindOfScreen = kindOfScreen;
            this.pnlName = pnlName;
            this.lblName = lblName;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
            switch(kindOfScreen){
                case "TongQuan":
                    
                    current = new TongQuanJPanel();
                    break;
                case "DonHang":
                    current = new DonHangJPanel();
                    break;
                case "SanPham":
                    current = new SanPhamJPanel();
                    break;
                case "ThemTaiKhoan":
                    current = new ThemTaiKhoanJPanel();
                    break;
                case "QLNV":
                    current = new QLNVJPanel();
                    break;
                case "DoanhThu":
                    current = new DoanhThuJPanel();
                    break;
                case "ThongKe":
                    current = new ThongKeJPanel();
                    break;
                case "PhieuNhapHang":
                    current = new PhieuNhapHangJPanel();
                    break;  
                case "KhuyenMai":
                    current = new KhuyenMaiJPanel();
                    break; 
                case "KhachHang":
                    current = new KhachHangJPanel();
                    break;
                case "QLDM":
                    current = new QuanLyDanhMucJPanel();
                    break;
                case "CaiDat":
                    current = new CaiDatJPanel();
                    break;
            }
           
            current.setBackground(Color.decode("#EEF3F7"));//SET BG COLOR PANEL VIEW
            pnlRoot.removeAll();
            pnlRoot.setLayout(new BorderLayout());
            pnlRoot.add(current);
            pnlRoot.validate();
            pnlRoot.repaint();
            
            setBackgroundColor(kindOfScreen);
            
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            selectedScreen = kindOfScreen;
            lblName.setForeground(Color.white);
            lblName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/"+kindOfScreen+".png")));
            lblName.setBackground(Color.decode("#0092f2"));
            pnlName.changeColor(Color.decode("#0092f2"));
            pnlName.repaint();
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
                lblName.setForeground(Color.white);
                pnlName.changeColor(Color.decode("#0092f2"));
                lblName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/"+kindOfScreen+".png")));
                pnlName.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!selectedScreen.equalsIgnoreCase(kindOfScreen)){
                lblName.setForeground(Color.decode("#181818"));
                lblName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/"+kindOfScreen+"Color.png")));
                pnlName.changeColor(Color.white);
                pnlName.repaint();
            }
        }
        public void setBackgroundColor(String kind){
            for(DanhMucBean item : listItem){
                if(item.getKindOfScreen().equalsIgnoreCase(kind)){
                            item.getLblName().setForeground(Color.decode("#ffffff"));
                            item.getLblName().setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/"+kind+".png")));
                            item.getPnlName().changeColor(Color.decode("#0092f2"));
                            item.getPnlName().repaint();
                            //Hover
                }
                else{
                            item.getLblName().setForeground(Color.decode("#181818"));
                            item.getPnlName().changeColor(Color.white);
                            item.getLblName().setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/navicon/"+item.getKindOfScreen()+"Color.png")));
                            item.getPnlName().repaint();
                            
                }
            }
        }
        
    }
    
}
