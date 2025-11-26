/*     */ package main_screen;
/*     */ 
/*     */ import com.toedter.calendar.JDateChooser;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
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
/*     */ 
/*     */ public class MyVechicleInputPanel
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -4647310868286988256L;
/*     */   private MySQLConnect myDatabase;
/*  83 */   private final String[] BACKGROUND_PIC = new String[] { "images/frontier.png", "images/rouge.png" };
/*  84 */   private final String VEHICLE_REPAIR_DATABASE = "vehicle_repairs";
/*     */   
/*     */   private JComboBox<Object> comboBoxVehicle;
/*     */   private JLabel lblAddInfo;
/*     */   private JTextField textFieldCost;
/*     */   private JTextArea textAreaInfo;
/*     */   private JLabel lblReceiptImage;
/*     */   private JLabel lblCarPic;
/*     */   private JButton btnReceipt;
/*     */   private JLabel lblImageFilePath;
/*     */   private JDateChooser dateChooserDay;
/*     */   private String filepath;
/*  96 */   private final String FILE_UPLOAD_ICON = "images/click.png";
/*     */   
/*     */   private JTextField textFieldMileage;
/*     */   
/*     */   public MyVechicleInputPanel(MySQLConnect mySQLDatabase) {
/* 101 */     setPreferredSize(new Dimension(517, 470));
/* 102 */     this.myDatabase = mySQLDatabase;
/* 103 */     setupPanel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 110 */     JFrame frame = new JFrame();
/*     */     
/* 112 */     frame.setTitle("Vehicle Maintenance Log"); frame.setMinimumSize(
/* 113 */         new Dimension(500, 500)); frame.getContentPane().add(
/* 114 */         new MyVechicleInputPanel(null)); frame.pack(); frame.setVisible(true);
/* 115 */     frame.setResizable(true);
/*     */   }
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
/*     */   private void setupPanel() {
/* 129 */     setLayout((LayoutManager)null);
/* 130 */     setBackground(Color.green);
/*     */     
/* 132 */     setBounds(10, 10, 500, 600);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     JLabel lblVehicleMaintenanceRecords = new JLabel("Vehicle Maintenance Records");
/*     */     
/* 140 */     lblVehicleMaintenanceRecords.setBounds(51, 5, 369, 27);
/* 141 */     lblVehicleMaintenanceRecords.setHorizontalAlignment(0);
/* 142 */     lblVehicleMaintenanceRecords.setFont(new Font("Tahoma", 1, 22));
/* 143 */     lblVehicleMaintenanceRecords.setForeground(Color.RED);
/* 144 */     add(lblVehicleMaintenanceRecords);
/*     */     
/* 146 */     JLabel lblCost = new JLabel("COST");
/* 147 */     lblCost.setBounds(10, 150, 44, 20);
/*     */     
/* 149 */     lblCost.setFont(new Font("Tahoma", 1, 16));
/* 150 */     lblCost.setForeground(Color.BLUE);
/* 151 */     add(lblCost);
/*     */     
/* 153 */     this.comboBoxVehicle = new JComboBox();
/* 154 */     this.comboBoxVehicle.setFont(new Font("Tahoma", 0, 12));
/* 155 */     this.comboBoxVehicle.setBounds(10, 43, 183, 27);
/* 156 */     this.comboBoxVehicle.setModel(new DefaultComboBoxModel<>(new String[] { "2004 Nissan Frontier", "2017 Nissan Rogue" }));
/* 157 */     this.comboBoxVehicle.setEditable(false);
/* 158 */     add(this.comboBoxVehicle);
/*     */     
/* 160 */     this.lblCarPic = new JLabel();
/* 161 */     this.lblCarPic.setIcon(new ImageIcon(this.BACKGROUND_PIC[0]));
/* 162 */     this.lblCarPic.setBounds(224, 43, 250, 120);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     add(this.lblCarPic);
/* 168 */     this.textFieldCost = new JTextField();
/* 169 */     this.textFieldCost.setText("0.0");
/* 170 */     this.textFieldCost.setBounds(90, 149, 103, 27);
/*     */     
/* 172 */     add(this.textFieldCost);
/*     */ 
/*     */ 
/*     */     
/* 176 */     this.lblAddInfo = new JLabel("Frontier Maintence Info");
/* 177 */     this.lblAddInfo.setBounds(10, 211, 217, 20);
/*     */     
/* 179 */     this.lblAddInfo.setFont(new Font("Tahoma", 1, 16));
/* 180 */     this.lblAddInfo.setForeground(Color.BLUE);
/* 181 */     add(this.lblAddInfo);
/*     */     
/* 183 */     this.dateChooserDay = new JDateChooser();
/* 184 */     this.dateChooserDay.setBounds(90, 119, 100, 20);
/*     */     
/* 186 */     this.dateChooserDay.setDateFormatString("yyyy-MM-dd");
/* 187 */     this.dateChooserDay.setBackground(Color.RED);
/* 188 */     add((Component)this.dateChooserDay);
/*     */     
/* 190 */     JLabel lblDate = new JLabel("DATE");
/* 191 */     lblDate.setBounds(10, 119, 45, 20);
/*     */     
/* 193 */     lblDate.setForeground(Color.BLUE);
/* 194 */     lblDate.setFont(new Font("Tahoma", 1, 16));
/* 195 */     add(lblDate);
/*     */     
/* 197 */     this.textAreaInfo = new JTextArea();
/* 198 */     this.textAreaInfo.setRows(5);
/* 199 */     this.textAreaInfo.setLineWrap(true);
/* 200 */     this.textAreaInfo.setWrapStyleWord(true);
/* 201 */     this.textAreaInfo.setBounds(10, 184, 385, 61);
/*     */ 
/*     */     
/* 204 */     JScrollPane scrollPane = new JScrollPane(this.textAreaInfo);
/* 205 */     scrollPane.setBounds(10, 235, 217, 190);
/*     */     
/* 207 */     add(scrollPane);
/*     */     
/* 209 */     this.btnReceipt = new JButton("RECEIPT");
/* 210 */     this.btnReceipt.setBounds(10, 179, 90, 23);
/* 211 */     add(this.btnReceipt);
/*     */     
/* 213 */     this.lblReceiptImage = new JLabel("");
/* 214 */     this.lblReceiptImage.setIcon(new ImageIcon("/resources/pdf.png"));
/* 215 */     this.lblReceiptImage.setIgnoreRepaint(true);
/* 216 */     this.lblReceiptImage.setBackground(new Color(255, 255, 255));
/* 217 */     this.lblReceiptImage.setVerticalAlignment(1);
/* 218 */     this.lblReceiptImage.setBounds(237, 235, 237, 190);
/* 219 */     add(this.lblReceiptImage);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     JButton btnAccept = new JButton("ACCEPT");
/* 225 */     btnAccept.setBounds(110, 179, 90, 23);
/* 226 */     add(btnAccept);
/*     */     
/* 228 */     this.lblImageFilePath = new JLabel("");
/* 229 */     this.lblImageFilePath.setHorizontalAlignment(0);
/* 230 */     this.lblImageFilePath.setFont(new Font("Arial", 0, 13));
/* 231 */     this.lblImageFilePath.setToolTipText("Filename");
/* 232 */     this.lblImageFilePath.setBounds(237, 427, 270, 20);
/* 233 */     add(this.lblImageFilePath);
/*     */     
/* 235 */     Date date = new Date();
/* 236 */     this.dateChooserDay.setDate(date);
/*     */     
/* 238 */     JButton btnClear = new JButton("CLEAR");
/* 239 */     btnClear.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 242 */             JFrame frame1 = new JFrame();
/* 243 */             String theMessage = " Do You Want To Clear Data From Fields?";
/* 244 */             int result = JOptionPane.showConfirmDialog(frame1, theMessage, "alert", 0);
/* 245 */             if (result == 0) {
/* 246 */               MyVechicleInputPanel.this.clearAllFields();
/*     */             }
/*     */           }
/*     */         });
/* 250 */     btnClear.setBounds(210, 179, 89, 23);
/* 251 */     add(btnClear);
/*     */     
/* 253 */     JLabel lblMileage = new JLabel("MILEAGE");
/* 254 */     lblMileage.setForeground(Color.BLUE);
/* 255 */     lblMileage.setFont(new Font("Tahoma", 1, 16));
/* 256 */     lblMileage.setBounds(10, 88, 77, 20);
/* 257 */     add(lblMileage);
/*     */     
/* 259 */     this.textFieldMileage = new JTextField();
/* 260 */     this.textFieldMileage.setText("0");
/* 261 */     this.textFieldMileage.setBounds(90, 81, 103, 27);
/* 262 */     add(this.textFieldMileage);
/*     */ 
/*     */     
/* 265 */     final JFrame frame = new JFrame();
/*     */     
/* 267 */     this.btnReceipt.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e) {
/* 270 */             File file = null;
/* 271 */             JFileChooser fileChooser = new JFileChooser();
/* 272 */             fileChooser.setFileSelectionMode(2);
/* 273 */             int option = fileChooser.showOpenDialog(frame);
/* 274 */             if (option == 0) {
/* 275 */               file = fileChooser.getSelectedFile();
/*     */ 
/*     */               
/* 278 */               MyVechicleInputPanel.this.filepath = file.getPath();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 284 */               MyVechicleInputPanel.this.lblImageFilePath.setText(MyVechicleInputPanel.this.filepath);
/* 285 */               MyVechicleInputPanel.this.lblReceiptImage.setIcon(new ImageIcon("images/click.png"));
/* 286 */               MyVechicleInputPanel.this.lblReceiptImage.setSize(200, 200);
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 292 */     this.lblReceiptImage.addMouseListener(new MouseListener()
/*     */         {
/*     */           
/*     */           public void mouseClicked(MouseEvent e)
/*     */           {
/* 297 */             File pdfFile = new File(MyVechicleInputPanel.this.filepath);
/* 298 */             if (pdfFile.exists()) {
/* 299 */               if (Desktop.isDesktopSupported()) {
/*     */                 try {
/* 301 */                   Desktop.getDesktop().open(pdfFile);
/* 302 */                 } catch (IOException e1) {
/*     */                   
/* 304 */                   e1.printStackTrace();
/* 305 */                   JOptionPane.showMessageDialog(null, "Desktop File Not Supported", "FILE ERROR", 0);
/*     */                 } 
/*     */               } else {
/* 308 */                 JOptionPane.showMessageDialog(null, "AWT Desktop Is Not Supported", "FILE ERROR", 0);
/*     */               } 
/*     */             } else {
/* 311 */               JOptionPane.showMessageDialog(null, "File Can Not Found", "FILE ERROR", 0);
/*     */             } 
/*     */           }
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
/*     */           public void mouseReleased(MouseEvent e) {}
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
/*     */           public void mouseExited(MouseEvent e) {}
/*     */         });
/* 344 */     this.comboBoxVehicle.addItemListener(new ItemListener()
/*     */         {
/*     */ 
/*     */           
/*     */           public void itemStateChanged(ItemEvent e)
/*     */           {
/* 350 */             if (MyVechicleInputPanel.this.comboBoxVehicle.getSelectedIndex() == 0) {
/* 351 */               MyVechicleInputPanel.this.lblCarPic.setIcon(new ImageIcon(MyVechicleInputPanel.this.BACKGROUND_PIC[0]));
/* 352 */               MyVechicleInputPanel.this.textFieldCost.setText(" ");
/* 353 */               MyVechicleInputPanel.this.lblAddInfo.setText("Frontier Maintence Info");
/* 354 */               MyVechicleInputPanel.this.textAreaInfo.setText(" ");
/*     */             } else {
/* 356 */               MyVechicleInputPanel.this.lblCarPic.setIcon(new ImageIcon(MyVechicleInputPanel.this.BACKGROUND_PIC[1]));
/* 357 */               MyVechicleInputPanel.this.textFieldCost.setText(" ");
/* 358 */               MyVechicleInputPanel.this.lblAddInfo.setText("Rogue Maintence Info");
/* 359 */               MyVechicleInputPanel.this.textAreaInfo.setText(" ");
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 364 */     btnAccept.addActionListener(new ActionListener()
/*     */         {
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 370 */             Date selectedDate = MyVechicleInputPanel.this.dateChooserDay.getDate();
/* 371 */             String pattern = "yyyy-MM-dd";
/* 372 */             DateFormat formatter = new SimpleDateFormat(pattern);
/* 373 */             String date = formatter.format(selectedDate);
/*     */             
/* 375 */             JFrame frame1 = new JFrame();
/* 376 */             String theMessage = " Add Data To Database?";
/* 377 */             int result = JOptionPane.showConfirmDialog(frame1, theMessage, "alert", 0);
/* 378 */             if (result == 0) {
/*     */               
/* 380 */               String newFilename = MyVechicleInputPanel.this.lblImageFilePath.getText().replaceAll("\\\\", "\\\\\\\\");
/* 381 */               String query = "INSERT INTO vehicle_repairs (DATE, VEHICLE, MILEAGE, COST, RECEIPT, INFO )\nVALUES ('" + 
/*     */                 
/* 383 */                 date + "','" + MyVechicleInputPanel.this.comboBoxVehicle.getSelectedItem().toString() + "'," + 
/* 384 */                 MyVechicleInputPanel.this.textFieldMileage.getText() + "," + MyVechicleInputPanel.this.textFieldCost.getText() + 
/* 385 */                 ", '" + newFilename + "','" + MyVechicleInputPanel.this.textAreaInfo.getText() + "')";
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 390 */               if (MyVechicleInputPanel.this.myDatabase.insertData(query).booleanValue()) {
/* 391 */                 JOptionPane.showMessageDialog(null, "Data Sent To Server");
/* 392 */                 MyVechicleInputPanel.this.clearAllFields();
/*     */               } else {
/*     */                 
/* 395 */                 String message = "Data was not written to server. \n Check for the following: \n1} Internet Connection is present. \n2) All fields are populated with data. \n";
/*     */ 
/*     */                 
/* 398 */                 JOptionPane.showMessageDialog(null, message, "Input Error", 0);
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
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
/*     */   public static String redoFilePath(String oldFilePath) {
/* 417 */     String DELIMITER = "\\";
/* 418 */     String[] filename = oldFilePath.split(DELIMITER);
/* 419 */     StringBuilder newFilePath = new StringBuilder();
/* 420 */     for (int i = 0; i < filename.length; i++) {
/* 421 */       if (i < filename.length - 1) {
/* 422 */         newFilePath.append(String.valueOf(filename[i]) + "--");
/*     */       } else {
/* 424 */         newFilePath.append(filename[i]);
/*     */       } 
/*     */     } 
/*     */     
/* 428 */     return newFilePath.toString();
/*     */   }
/*     */   
/*     */   public void clearAllFields() {
/* 432 */     this.comboBoxVehicle.setSelectedIndex(0);
/* 433 */     this.lblCarPic.setIcon(new ImageIcon(this.BACKGROUND_PIC[0]));
/* 434 */     Date newDate = new Date();
/* 435 */     this.dateChooserDay.setDate(newDate);
/* 436 */     this.textFieldCost.setText("0.0");
/* 437 */     this.textAreaInfo.setText((String)null);
/* 438 */     this.lblReceiptImage.setIcon((Icon)null);
/* 439 */     this.lblImageFilePath.setText((String)null);
/* 440 */     this.filepath = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String correctFileNamePath(String oldString) {
/* 447 */     char[] c = oldString.toCharArray();
/* 448 */     int magicNumber = 92;
/*     */     
/* 450 */     int count = 0;
/* 451 */     int[] charPositions = new int[10];
/*     */     
/* 453 */     for (int i = 0; i < c.length; i++) {
/* 454 */       int c_asInt = c[i];
/* 455 */       if (c_asInt == magicNumber) {
/* 456 */         charPositions[count] = i + 1;
/* 457 */         count++;
/*     */       } 
/*     */     } 
/*     */     
/* 461 */     char slash = (char)magicNumber;
/* 462 */     String finalString = oldString;
/*     */ 
/*     */     
/* 465 */     for (int x = 0; x < count; count++)
/*     */     {
/* 467 */       finalString = insertChar(finalString, slash, charPositions[x], x);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 472 */     return finalString;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String insertChar(String word, char letter, int position, int numOfInsertions) {
/* 478 */     char[] chars = word.toCharArray();
/* 479 */     char[] newchars = new char[word.length() + 1];
/*     */     
/* 481 */     for (int i = 0; i < word.length(); i++) {
/* 482 */       if (i < position) {
/* 483 */         newchars[i] = chars[i];
/*     */       } else {
/* 485 */         newchars[i + 1] = chars[i];
/*     */       } 
/* 487 */     }  newchars[position] = letter;
/*     */ 
/*     */     
/* 490 */     return new String(newchars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ImageIcon createImageIcon(String path) {
/* 498 */     URL imgURL = MyVechicleInputPanel.class.getResource(path);
/* 499 */     if (imgURL != null) {
/* 500 */       return new ImageIcon(imgURL);
/*     */     }
/* 502 */     System.err.println("Couldn't find file: " + path);
/* 503 */     return null;
/*     */   }
/*     */   
/*     */   static void SaveImageToFolfer(String path) {}
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\MyVechicleInputPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */