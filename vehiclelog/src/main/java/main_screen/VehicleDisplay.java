/*     */ package main_screen;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VehicleDisplay
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JPanel contentPane;
/*     */   private JTextField textFieldCost;
/*     */   private JTextField textFieldDate;
/*     */   private JFrame frame;
/*     */   private JTextField textFieldCar;
/*     */   private JLabel lblReceiptImage;
/*  50 */   private final String FILE_UPLOAD_ICON = "images/click.png";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VehicleDisplay(final VehicleData data, int offset) {
/*  65 */     this.frame = new JFrame();
/*  66 */     this.frame.setDefaultCloseOperation(3);
/*  67 */     this.frame.setBounds(100 + offset, 100 + offset, 500, 400);
/*  68 */     this.contentPane = new JPanel();
/*  69 */     this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*     */ 
/*     */     
/*  72 */     this.contentPane.setLayout((LayoutManager)null);
/*  73 */     this.contentPane.setBackground(Color.cyan);
/*     */     
/*  75 */     JScrollPane scrollPane = new JScrollPane();
/*  76 */     scrollPane.setHorizontalScrollBarPolicy(31);
/*  77 */     scrollPane.setBounds(13, 185, 449, 130);
/*     */     
/*  79 */     this.contentPane.add(scrollPane);
/*     */     
/*  81 */     JTextArea textAreaInfo = new JTextArea();
/*  82 */     scrollPane.setViewportView(textAreaInfo);
/*  83 */     textAreaInfo.setRows(5);
/*  84 */     textAreaInfo.setLineWrap(true);
/*  85 */     textAreaInfo.setWrapStyleWord(true);
/*  86 */     textAreaInfo.setText("Car Info");
/*  87 */     textAreaInfo.setText(data.getInfo());
/*     */     
/*  89 */     this.textFieldCost = new JTextField();
/*  90 */     this.textFieldCost.setBounds(50, 123, 125, 20);
/*  91 */     this.contentPane.add(this.textFieldCost);
/*  92 */     this.textFieldCost.setColumns(10);
/*     */     
/*  94 */     JLabel lblCost = new JLabel("COST");
/*  95 */     lblCost.setHorizontalAlignment(2);
/*  96 */     lblCost.setBounds(10, 123, 36, 20);
/*  97 */     this.contentPane.add(lblCost);
/*     */     
/*  99 */     JLabel lblDate = new JLabel("DATE");
/* 100 */     lblDate.setHorizontalAlignment(2);
/* 101 */     lblDate.setBounds(10, 82, 36, 20);
/* 102 */     this.contentPane.add(lblDate);
/*     */     
/* 104 */     this.textFieldDate = new JTextField();
/* 105 */     this.textFieldDate.setColumns(10);
/* 106 */     this.textFieldDate.setBounds(50, 82, 125, 20);
/* 107 */     this.contentPane.add(this.textFieldDate);
/*     */ 
/*     */     
/* 110 */     this.frame.getContentPane().add(this.contentPane);
/*     */     
/* 112 */     JLabel lblCar = new JLabel("CAR");
/* 113 */     lblCar.setHorizontalAlignment(2);
/* 114 */     lblCar.setBounds(10, 41, 36, 20);
/* 115 */     this.contentPane.add(lblCar);
/*     */     
/* 117 */     this.textFieldCar = new JTextField();
/* 118 */     this.textFieldCar.setText((String)null);
/* 119 */     this.textFieldCar.setColumns(10);
/* 120 */     this.textFieldCar.setBounds(50, 41, 125, 20);
/* 121 */     this.contentPane.add(this.textFieldCar);
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.textFieldDate.setText(data.getDate());
/* 126 */     this.textFieldCost.setText(data.getCost().toString());
/* 127 */     this.textFieldCar.setText(data.getVehicle());
/* 128 */     this.lblReceiptImage = new JLabel();
/* 129 */     this.lblReceiptImage = new JLabel();
/* 130 */     this.lblReceiptImage.setIcon(new ImageIcon("images/click.png"));
/* 131 */     this.lblReceiptImage.setLocation(201, 14);
/* 132 */     this.lblReceiptImage.setSize(new Dimension(240, 160));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     this.lblReceiptImage.addMouseListener(new MouseListener()
/*     */         {
/*     */           public void mouseClicked(MouseEvent e)
/*     */           {
/* 142 */             File pdfFile = new File(data.getReceiptFilename());
/* 143 */             if (pdfFile.exists()) {
/* 144 */               if (Desktop.isDesktopSupported()) {
/*     */                 try {
/* 146 */                   Desktop.getDesktop().open(pdfFile);
/* 147 */                 } catch (IOException e1) {
/*     */                   
/* 149 */                   e1.printStackTrace();
/* 150 */                   JOptionPane.showMessageDialog(null, "Desktop File Not Supported", "FILE ERROR", 0);
/*     */                 } 
/*     */               } else {
/* 153 */                 JOptionPane.showMessageDialog(null, "AWT Desktop Is Not Supported", "FILE ERROR", 0);
/*     */               } 
/*     */             } else {
/* 156 */               JOptionPane.showMessageDialog(null, "File Can Not Found", "FILE ERROR", 0);
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void mouseExited(MouseEvent e) {}
/*     */         });
/* 222 */     this.contentPane.add(this.lblReceiptImage);
/*     */     
/* 224 */     String title = String.valueOf(data.getDate()) + "::" + data.getVehicle() + "::Cost = " + 
/* 225 */       data.getCost().toString();
/* 226 */     this.frame.setTitle(title);
/* 227 */     this.frame.setPreferredSize(new Dimension(500, 400));
/* 228 */     this.frame.pack(); this.frame.setVisible(true);
/* 229 */     this.frame.setResizable(false);
/* 230 */     this.frame.setDefaultCloseOperation(2);
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\VehicleDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */