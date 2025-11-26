/*    */ package main_screen;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.EventQueue;
/*    */ import java.awt.Font;
/*    */ import java.awt.LayoutManager;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class About
/*    */ {
/*    */   private JFrame frame;
/*    */   
/*    */   public static void main(String[] args) {
/* 26 */     EventQueue.invokeLater(new Runnable() {
/*    */           public void run() {
/*    */             try {
/* 29 */               About window = new About();
/* 30 */               window.frame.setVisible(true);
/* 31 */             } catch (Exception e) {
/* 32 */               e.printStackTrace();
/*    */             } 
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public About() {
/* 42 */     initialize();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void initialize() {
/* 50 */     this.frame = new JFrame();
/* 51 */     this.frame.getContentPane().setFont(new Font("Tahoma", 0, 10));
/* 52 */     this.frame.getContentPane().setBackground(Color.PINK);
/* 53 */     this.frame.getContentPane().setForeground(Color.WHITE);
/* 54 */     this.frame.setBounds(100, 100, 517, 367);
/* 55 */     this.frame.setDefaultCloseOperation(2);
/* 56 */     this.frame.setTitle("About");
/* 57 */     this.frame.getContentPane().setLayout((LayoutManager)null);
/*    */ 
/*    */     
/* 60 */     JLabel lblPageName = new JLabel("About");
/* 61 */     lblPageName.setFont(new Font("Tahoma", 1, 22));
/* 62 */     lblPageName.setBounds(10, 11, 157, 30);
/* 63 */     this.frame.getContentPane().add(lblPageName);
/*    */     
/* 65 */     JLabel lblTitle = new JLabel("<html>Vehicle Log <br>\r\n@ Aug 2024 Created with Eclipse <br>\r\nRev 1.0 <br>\r\n\r\n\r\n\r\n");
/* 66 */     lblTitle.setFont(new Font("Tahoma", 1, 12));
/* 67 */     lblTitle.setVerticalAlignment(1);
/* 68 */     lblTitle.setBounds(10, 52, 481, 97);
/* 69 */     this.frame.getContentPane().add(lblTitle);
/*    */     
/* 71 */     JLabel lblDescription = new JLabel("<html>\r\nVehicle Log is an application that keep tracks of maintenance  repairs on the 2004<br> \r\nNissan Frontier  abd 2017 Nissan Rogue The app stores all information in  a MYSQL <br>\r\ndatabase. The apps also allows uploads of all file receipts pertaining to the repairs.<br>\r\nFile locations information are used to open and view receipt records in file formats<br>\r\njpg, png and pdfs. Vehicle reports van be queried  using the dates, vehicle type and <br>\r\nkeywords <br>\r\n\r\n");
/* 72 */     lblDescription.setVerticalAlignment(1);
/* 73 */     lblDescription.setHorizontalAlignment(2);
/* 74 */     lblDescription.setBackground(new Color(240, 240, 240));
/* 75 */     lblDescription.setFont(new Font("Tahoma", 0, 12));
/* 76 */     lblDescription.setBounds(10, 187, 481, 102);
/* 77 */     this.frame.getContentPane().add(lblDescription);
/*    */     
/* 79 */     JLabel lblVerionDate = new JLabel("Version Date : Aug 2024");
/* 80 */     lblVerionDate.setFont(new Font("Tahoma", 1, 11));
/* 81 */     lblVerionDate.setBounds(10, 288, 189, 24);
/* 82 */     this.frame.getContentPane().add(lblVerionDate);
/*    */     
/* 84 */     this.frame.setVisible(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\About.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */