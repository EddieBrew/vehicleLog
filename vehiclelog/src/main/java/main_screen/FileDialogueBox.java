/*    */ package main_screen;
/*    */ 
/*    */ import java.awt.FlowLayout;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.io.File;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JFileChooser;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class FileDialogueBox
/*    */ {
/*    */   JButton button;
/*    */   JLabel label;
/*    */   LayoutManager layout;
/*    */   
/*    */   private static void createWindow() {
/* 21 */     JFrame frame = new JFrame("Swing Tester");
/* 22 */     frame.setDefaultCloseOperation(3);
/* 23 */     createUI(frame);
/* 24 */     frame.setSize(200, 200);
/* 25 */     frame.setLocationRelativeTo(null);
/* 26 */     frame.setVisible(true);
/*    */   }
/*    */   
/*    */   private static void createUI(final JFrame frame) {
/* 30 */     JPanel panel = new JPanel();
/* 31 */     LayoutManager layout = new FlowLayout();
/* 32 */     panel.setLayout(layout);
/*    */     
/* 34 */     JButton button = new JButton("Save File!");
/* 35 */     final JLabel label = new JLabel();
/*    */     
/* 37 */     button.addActionListener(new ActionListener()
/*    */         {
/*    */           public void actionPerformed(ActionEvent e) {
/* 40 */             JFileChooser fileChooser = new JFileChooser();
/* 41 */             int option = fileChooser.showSaveDialog(frame);
/* 42 */             if (option == 0) {
/* 43 */               File file = fileChooser.getSelectedFile();
/* 44 */               label.setText("File Saved as: " + file.getName());
/*    */             } else {
/* 46 */               label.setText("Save command canceled");
/*    */             } 
/*    */           }
/*    */         });
/*    */     
/* 51 */     panel.add(button);
/* 52 */     panel.add(label);
/* 53 */     frame.getContentPane().add(panel, "Center");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 59 */     createWindow();
/*    */   }
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\FileDialogueBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */