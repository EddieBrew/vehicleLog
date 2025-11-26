/*    */ package main_screen;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.image.ImageObserver;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class EnlargedImagePanel extends JPanel {
/*    */   static JPanel panel;
/*    */   static JLabel label;
/* 21 */   static double rot = 0.0D;
/*    */   
/*    */   String filepath;
/*    */   
/*    */   public EnlargedImagePanel(String filepath) {
/* 26 */     this.filepath = filepath;
/* 27 */     setupPanel();
/*    */   }
/*    */   
/*    */   private void setupPanel() {
/* 31 */     JFrame frame = new JFrame("Window");
/* 32 */     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
/* 33 */     frame.setBounds(dim.width / 2 - 400, dim.height / 2 - 300, 600, 600);
/*    */     
/* 35 */     frame.setResizable(false);
/* 36 */     frame.setDefaultCloseOperation(3);
/*    */     
/* 38 */     panel = new JPanel(new FlowLayout());
/*    */ 
/*    */     
/* 41 */     final ImageIcon icon = new ImageIcon(this.filepath);
/* 42 */     label = new JLabel(null, icon, 0)
/*    */       {
/*    */         protected void paintComponent(Graphics g)
/*    */         {
/* 46 */           Graphics2D g2 = (Graphics2D)g;
/* 47 */           g2.rotate(EnlargedImagePanel.rot, (icon.getIconWidth() / 2), (icon.getIconHeight() / 2));
/* 48 */           g2.drawImage(icon.getImage(), 0, 0, (ImageObserver)null);
/*    */         }
/*    */       };
/*    */     
/* 52 */     label.setFont(label.getFont().deriveFont(70.0F));
/* 53 */     panel.setBackground(Color.CYAN);
/* 54 */     label.setForeground(new Color(156, 156, 156));
/*    */     
/* 56 */     label.addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent arg0) {
/* 59 */             EnlargedImagePanel.rot += 0.7853981633974483D;
/* 60 */             EnlargedImagePanel.label.repaint();
/*    */           }
/*    */         });
/*    */     
/* 64 */     panel.add(label);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 72 */     JFrame frame = new JFrame("Window");
/* 73 */     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
/* 74 */     frame.setBounds(dim.width / 2 - 400, dim.height / 2 - 300, 600, 600);
/* 75 */     frame.getContentPane().add(new EnlargedImagePanel("src/main/resources/honeywell380.jpg"));
/*    */     
/* 77 */     frame.setResizable(false);
/* 78 */     frame.setDefaultCloseOperation(3);
/*    */   }
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\EnlargedImagePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */