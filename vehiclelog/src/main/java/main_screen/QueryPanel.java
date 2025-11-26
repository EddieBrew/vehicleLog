/*     */ package main_screen;
/*     */ 
/*     */ import bst.BinarySearchTree;
/*     */ import com.toedter.calendar.JDateChooser;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QueryPanel
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private JFrame qFrame;
/*     */   private JTextField textFieldVariables;
/*     */   private JButton btnResult;
/*     */   private JDateChooser dateChooserB;
/*     */   private JDateChooser dateChooserE;
/*     */   private String username;
/*     */   private MySQLConnect myDatabase;
/*  46 */   private final String VEHICLE_REPAIR_DATABASE = "vehicle_repairs";
/*     */   
/*     */   private JTextField textField;
/*     */   
/*     */   private JComboBox<Object> comboBoxVehicle;
/*     */ 
/*     */   
/*     */   public QueryPanel(MySQLConnect mySQLDatabase) {
/*  54 */     setPreferredSize(new Dimension(500, 193));
/*  55 */     this.username = this.username;
/*  56 */     this.myDatabase = mySQLDatabase;
/*  57 */     initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize() {
/*  63 */     setLayout((LayoutManager)null);
/*  64 */     setBackground(Color.PINK);
/*  65 */     setBounds(10, 10, 500, 200);
/*  66 */     setSize(500, 189);
/*     */     
/*  68 */     JLabel lblQueryPanel = new JLabel("QUERY  PANEL");
/*  69 */     lblQueryPanel.setHorizontalAlignment(0);
/*  70 */     lblQueryPanel.setFont(new Font("Tahoma", 1, 20));
/*  71 */     lblQueryPanel.setBounds(97, 11, 219, 32);
/*  72 */     add(lblQueryPanel);
/*     */ 
/*     */     
/*  75 */     this.dateChooserB = new JDateChooser();
/*  76 */     this.dateChooserB.setDateFormatString("yyyy-MM-dd");
/*  77 */     this.dateChooserB.setBounds(10, 54, 138, 30);
/*  78 */     add((Component)this.dateChooserB);
/*     */     
/*  80 */     this.dateChooserE = new JDateChooser();
/*  81 */     this.dateChooserE.setDateFormatString("yyyy-MM-dd");
/*  82 */     this.dateChooserE.setBounds(158, 54, 138, 30);
/*  83 */     add((Component)this.dateChooserE);
/*     */     
/*  85 */     Date date = new Date();
/*  86 */     this.dateChooserB.setDate(date);
/*  87 */     this.dateChooserE.setDate(date);
/*  88 */     this.btnResult = new JButton("RESULTS");
/*  89 */     this.btnResult.setFont(new Font("Tahoma", 1, 11));
/*  90 */     this.btnResult.setBounds(191, 153, 138, 29);
/*  91 */     add(this.btnResult);
/*     */ 
/*     */     
/*  94 */     JLabel lblQueryValues = new JLabel();
/*  95 */     lblQueryValues.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
/*  96 */     lblQueryValues.setText("Enter query values, separated by commas(i.e. brakes, engine, oil, fender, etc)");
/*     */     
/*  98 */     lblQueryValues.setFont(new Font("Tahoma", 0, 12));
/*  99 */     lblQueryValues.setBounds(10, 95, 480, 20);
/* 100 */     add(lblQueryValues);
/*     */     
/* 102 */     this.textFieldVariables = new JTextField();
/* 103 */     this.textFieldVariables.setFont(new Font("Tahoma", 0, 12));
/* 104 */     this.textFieldVariables.setHorizontalAlignment(2);
/* 105 */     this.textFieldVariables.setBounds(10, 114, 461, 30);
/* 106 */     add(this.textFieldVariables);
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
/* 117 */     this.comboBoxVehicle = new JComboBox();
/* 118 */     this.comboBoxVehicle.setFont(new Font("Tahoma", 0, 12));
/* 119 */     this.comboBoxVehicle.setModel(new DefaultComboBoxModel<>(new String[] { "2004 Nissan Frontier", "2017 Nissan Rogue" }));
/* 120 */     this.comboBoxVehicle.setEditable(false);
/* 121 */     this.comboBoxVehicle.setBounds(316, 54, 155, 30);
/* 122 */     add(this.comboBoxVehicle);
/*     */ 
/*     */     
/* 125 */     this.btnResult.addActionListener(new ActionListener()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*     */             try {
/* 133 */               QueryPanel.this.getDateRangeFromDatabase();
/* 134 */             } catch (Exception e1) {
/* 135 */               e1.printStackTrace();
/*     */               
/* 137 */               JOptionPane.showMessageDialog(null, e.toString());
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void testArray(String[] data, String[] variables) {
/* 145 */     Arrays.sort((Object[])data);
/* 146 */     int count = 0;
/*     */     
/* 148 */     for (int j = 0; j < variables.length; j++) {
/* 149 */       int position = binarySearch(data, variables[j]);
/* 150 */       if (position > 0)
/*     */       {
/* 152 */         count++;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isVariableFound(String[] data, String variables) {
/* 161 */     BinarySearchTree<String> dataTree = new BinarySearchTree();
/* 162 */     for (int i = 0; i < data.length; i++) {
/* 163 */       dataTree.add(data[i]);
/*     */     }
/*     */     
/* 166 */     if (dataTree.contains(variables)) {
/* 167 */       return Boolean.valueOf(true);
/*     */     }
/*     */     
/* 170 */     return Boolean.valueOf(false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getDateRangeFromDatabase() {
/* 203 */     String pattern = "yyyy-MM-dd";
/* 204 */     DateFormat formatter = new SimpleDateFormat(pattern);
/*     */     
/* 206 */     Date bDate = this.dateChooserB.getDate();
/* 207 */     String dateStringB = formatter.format(bDate);
/* 208 */     Date eDate = this.dateChooserE.getDate();
/* 209 */     String dateStringE = formatter.format(eDate);
/*     */     
/* 211 */     String result = "SELECT * FROM vehicle_repairs WHERE  DATE >= '" + dateStringB + "' AND DATE <= '" + dateStringE + "' AND VEHICLE  = '" + this.comboBoxVehicle.getSelectedItem().toString() + "'";
/* 212 */     this.myDatabase.getDateRangeResults(result);
/*     */     
/* 214 */     List<VehicleData> dateRangeList = this.myDatabase.getList();
/*     */ 
/*     */     
/* 217 */     if (dateRangeList.size() == 0) {
/* 218 */       JOptionPane.showMessageDialog(null, "No Records Matched Your Query ");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 223 */     String[] queryVariables = this.textFieldVariables.getText().split(", ");
/* 224 */     Arrays.sort((Object[])queryVariables);
/* 225 */     List<VehicleData> finalList = null;
/* 226 */     int count = 20;
/*     */ 
/*     */     
/* 229 */     if (this.textFieldVariables.getText().isEmpty()) {
/* 230 */       finalList = new ArrayList<>(dateRangeList);
/* 231 */       for (int x = 0; x < finalList.size(); x++)
/*     */       {
/* 233 */         count += 20;
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 239 */     finalList = new ArrayList<>();
/*     */     
/* 241 */     for (int i = 0; i < dateRangeList.size(); i++) {
/* 242 */       String[] dataString = ((VehicleData)dateRangeList.get(i)).getInfo().split("[ ,.]");
/* 243 */       Arrays.sort((Object[])dataString);
/* 244 */       for (int j = 0; j < queryVariables.length; j++) {
/* 245 */         if (binarySearch(dataString, queryVariables[j]) >= 0) {
/* 246 */           finalList.add(dateRangeList.get(i));
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 253 */     if (finalList.size() == 0) {
/* 254 */       JOptionPane.showMessageDialog(null, "No Records Matched Your Variable Field ");
/*     */     } else {
/* 256 */       for (int x = 0; x < finalList.size(); x++)
/*     */       {
/* 258 */         count += 20;
/*     */       }
/*     */     } 
/* 261 */     this.myDatabase.clearList();
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
/*     */   public static class SortHomeDataInAscendingOrderByDate
/*     */     implements Comparator<VehicleData>
/*     */   {
/*     */     public int compare(VehicleData a, VehicleData b) {
/* 276 */       int dateSelect = 1;
/*     */       
/* 278 */       return QueryPanel.convertDateStringToInt(a.getDate(), dateSelect) - QueryPanel.convertDateStringToInt(b.getDate(), dateSelect);
/*     */     }
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
/*     */   public static class SortHomeDataInDescendingOrderByDate
/*     */     implements Comparator<VehicleData>
/*     */   {
/*     */     public int compare(VehicleData a, VehicleData b) {
/* 294 */       int dateSelect = 1;
/* 295 */       return QueryPanel.convertDateStringToInt(b.getDate(), dateSelect) - QueryPanel.convertDateStringToInt(a.getDate(), dateSelect);
/*     */     }
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
/*     */   
/*     */   public static int convertDateStringToInt(String date, int dateSelect) {
/* 312 */     String delimStr = "-";
/*     */     
/* 314 */     String[] words = date.split(delimStr);
/* 315 */     int intDate = 0;
/* 316 */     switch (dateSelect) {
/*     */       
/*     */       case 1:
/* 319 */         intDate = Integer.parseInt(words[1]) * 100 + Integer.parseInt(words[2]) + 
/* 320 */           Integer.parseInt(words[0]) * 10000;
/*     */         break;
/*     */       case 2:
/* 323 */         intDate = Integer.parseInt(words[1]);
/*     */         break;
/*     */       case 3:
/* 326 */         intDate = Integer.parseInt(words[0]);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 331 */     return intDate;
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
/*     */   static int binarySearch(String[] arr, String x) {
/* 344 */     int l = 0, r = arr.length - 1;
/*     */ 
/*     */     
/* 347 */     while (l <= r) {
/*     */       
/* 349 */       int m = l + (r - l) / 2;
/*     */ 
/*     */ 
/*     */       
/* 353 */       int res = x.trim().compareToIgnoreCase(arr[m].trim());
/*     */ 
/*     */       
/* 356 */       if (res == 0) {
/* 357 */         return m;
/*     */       }
/*     */       
/* 360 */       if (res > 0) {
/* 361 */         l = m + 1;
/*     */         
/*     */         continue;
/*     */       } 
/* 365 */       r = m - 1;
/*     */     } 
/*     */     
/* 368 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\QueryPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */