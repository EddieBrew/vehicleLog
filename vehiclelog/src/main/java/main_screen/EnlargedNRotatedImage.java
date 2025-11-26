/*    */ package main_screen;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.image.ImageObserver;
/*    */ import javax.swing.Icon;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnlargedNRotatedImage
/*    */   extends JFrame
/*    */ {
/*    */   private static JFrame mainFrame;
/*    */   private static JLabel lblReceiptImage;
/*    */   private static JPanel contentPane;
/*    */   private String filepath;
/* 26 */   private double rot = 0.0D;
/*    */ 
/*    */   
/*    */   private JScrollPane scrollPaneImage;
/*    */ 
/*    */ 
/*    */   
/*    */   EnlargedNRotatedImage(VehicleData data) {
/* 34 */     final ImageIcon icon = new ImageIcon(data.getReceiptFilename());
/* 35 */     lblReceiptImage = new JLabel("Hello", icon, 0)
/*    */       {
/*    */         private static final long serialVersionUID = 1L;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         protected void paintComponent(Graphics g) {
/* 44 */           Graphics2D g2 = (Graphics2D)g;
/* 45 */           g2.rotate(EnlargedNRotatedImage.this.rot, (icon.getIconWidth() / 2), (icon.getIconHeight() / 2));
/* 46 */           g2.drawImage(icon.getImage(), 0, 0, (ImageObserver)null);
/*    */         }
/*    */       };
/*    */     
/* 50 */     lblReceiptImage.setFont(lblReceiptImage.getFont().deriveFont(70.0F));
/* 51 */     lblReceiptImage.setBackground(Color.CYAN);
/* 52 */     lblReceiptImage.setForeground(new Color(156, 156, 156));
/*    */     
/* 54 */     lblReceiptImage.addMouseListener(new MouseAdapter()
/*    */         {
/*    */           public void mouseClicked(MouseEvent arg0) {
/* 57 */             EnlargedNRotatedImage.this.rot = EnlargedNRotatedImage.this.rot + 0.7853981633974483D;
/* 58 */             EnlargedNRotatedImage.lblReceiptImage.repaint();
/*    */           }
/*    */         });
/*    */     
/* 62 */     this.scrollPaneImage = new JScrollPane();
/* 63 */     this.scrollPaneImage.setBounds(10, 200, 500, 500);
/* 64 */     this.scrollPaneImage.setViewportView(lblReceiptImage);
/*    */     
/* 66 */     getContentPane().add(this.scrollPaneImage);
/* 67 */     setPreferredSize(new Dimension(600, 600));
/* 68 */     String title = String.valueOf(data.getDate()) + " for " + data.getVehicle() + "Receipt where cost = " + data.getCost().toString();
/* 69 */     setTitle(title);
/* 70 */     pack();
/* 71 */     setVisible(true);
/* 72 */     setResizable(false);
/* 73 */     setDefaultCloseOperation(2);
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\EnlargedNRotatedImage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */