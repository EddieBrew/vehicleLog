/*      */ package main_screen;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.time.LocalDate;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Date;
/*      */ import java.util.List;

/*      */ import javax.sound.sampled.AudioInputStream;
/*      */ import javax.sound.sampled.AudioSystem;
/*      */ import javax.sound.sampled.Clip;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.SwingUtilities;

       import com.fasterxml.jackson.databind.ObjectMapper;

/*      */ 
/*      */ public class HomeMainGui
/*      */ {
	/*      */   private static final long serialVersionUID = -8044048618173986424L;
	/*      */   private static String username;
	/*      */   private JFrame frame;
	/*      */   private JMenuBar menuBar;
	/*      */   private JMenu myMenu;
	/*      */   private JMenuItem queryWork;
	/*      */   private JMenuItem logout;
	/*      */   private JMenuItem download;
	/*      */   private JMenuItem upload;
	/*      */   private JMenuItem about;
	/*      */   private JMenuItem reformat;
	/*      */   private JMenuItem currentMonth;
	/*      */   private MySQLConnect mySQLDatabase;
	/*   75 */   private final String VEHICLE_REPAIR_DATABASE = "vehicle_repairs";
	/*      */   
	/*      */   private static final String inputFile = "vehicleRepairData.csv";
	/*      */   
	/*      */   public static final String credentialsFilename = "mysignonstuff.txt";
	/*      */   
	/*      */   public static final int ROWS = 6;
	/*      */   
	/*      */   public static final int COLS = 12;
	/*      */   
	/*      */   private MyVechicleInputPanel myVechicleInputPanel;
	/*      */   
	/*      */   private QueryPanel queryPanel;
	/*      */ 
	/*      */   
	/*      */   public HomeMainGui(String username, char[] password) {
		/*   91 */     HomeMainGui.username = username;
		/*   92 */     signonCredentCorrect();
		/*   93 */     initialize();
	/*      */   }
	/*      */ 
	/*      */   
	/*      */   class MyMouseListener
	/*      */     extends MouseAdapter
	/*      */   {
		/*      */     public void mouseClicked(MouseEvent evt) {
			/*  101 */       if (evt.getClickCount() == 3) {
				/*  102 */         HomeMainGui.this.reformat.setVisible(false);
			/*  103 */       } else if (evt.getClickCount() == 2) {
				/*  104 */         HomeMainGui.this.reformat.setVisible(true);
			/*      */       } 
		/*      */     }
	/*      */   }
	/*      */ 
	/*      */ 
	/*      */ 
	/*      */   
	/*      */   private void initialize() {
		/*  113 */     this.menuBar = new JMenuBar();
		/*  114 */     this.menuBar.setFont(new Font("Segoe UI", 1, 12));
		/*  115 */     this.menuBar.setBackground(Color.WHITE);
		/*      */ 
		/*      */     
		/*  118 */     this.myMenu = new JMenu("MENU");
		/*  119 */     this.myMenu.setMnemonic(0);
		/*  120 */     this.myMenu.getAccessibleContext().setAccessibleDescription("Get My Shit");
		/*  121 */     this.myMenu.setBackground(new Color(50, 205, 50));
		/*  122 */     this.menuBar.add(this.myMenu);
		/*      */     
		/*  124 */     this.download = new JMenuItem("Download From Server");
		/*  125 */     this.queryWork = new JMenuItem("Perform Queries");
		/*  126 */     this.currentMonth = new JMenuItem("Current Month's Expenses");
		/*  127 */     this.upload = new JMenuItem("Upload File To Database");
		/*  128 */     this.logout = new JMenuItem("Logout");
		/*  129 */     this.about = new JMenuItem("About");
		/*  130 */     this.reformat = new JMenuItem("Reformat CSV File");
		/*  131 */     this.reformat.setVisible(false);
		/*      */ 
		/*      */     
		/*  134 */     this.myMenu.add(this.reformat);
		/*  135 */     this.myMenu.add(this.currentMonth);
		/*  136 */     this.myMenu.add(this.download);
		/*  137 */     this.myMenu.add(this.queryWork);
		/*  138 */     this.myMenu.add(this.upload);
		/*  139 */     this.myMenu.add(this.about);
		/*  140 */     this.myMenu.add(this.logout);
		/*      */ 
		/*      */     
		/*  143 */     this.myMenu.addMouseListener(new MyMouseListener());
  
		/*  155 */     this.about.addActionListener(new ActionListener()
				/*      */         {
			/*      */           public void actionPerformed(ActionEvent e) {}
		/*      */         });

		/*      */     
		/*  164 */     this.currentMonth.addActionListener(new ActionListener()
				/*      */         {
			/*      */           
			/*      */           public void actionPerformed(ActionEvent e)
			/*      */           {
				/*  169 */             HomeMainGui.this.getMonthlyExpenses();
			/*      */           }
		/*      */         });
		/*      */     
		/*  173 */     this.download.addActionListener(new ActionListener()
				/*      */         {
			/*      */           public void actionPerformed(ActionEvent e)
			/*      */           {
				/*  177 */             HomeMainGui.this.downloadFromServerToFile();
			/*      */           }
		/*      */         });
		/*      */ 
		/*      */     
		/*  182 */     this.logout.addActionListener(new ActionListener()
				/*      */         {
			/*      */           public void actionPerformed(ActionEvent e)
			/*      */           {
				/*  186 */             JFrame frame1 = new JFrame();
				/*  187 */             String theMessage = " Do You Want To Quit The Application?";
				/*  188 */             int result = JOptionPane.showConfirmDialog(frame1, theMessage, "alert", 0);
				/*  189 */             if (result == 0) {
					/*  190 */               HomeMainGui.this.downloadFromServerToFile();
					/*  191 */               HomeMainGui.this.copyFileToGoogleDrive();
					/*  192 */               System.exit(0);
				/*      */             } 
			/*      */           }
		/*      */         });
		/*      */     
		/*  197 */     this.queryWork.addActionListener(new ActionListener()
				/*      */         {
			/*      */           public void actionPerformed(ActionEvent e) {}
		/*      */         });
		/*      */ 
		/*      */ 
		/*      */ 
		/*      */ 
		/*      */     
		/*  206 */     this.upload.addActionListener(new ActionListener()
				/*      */         {
			/*      */ 
			/*      */           
			/*      */           public void actionPerformed(ActionEvent e)
			/*      */           {
				/*  212 */             Thread databaseThread = new Thread(new Runnable()
						/*      */                 {
					/*      */ 
					/*      */                   
					/*      */                   public void run()
					/*      */                   {
						/*  218 */                     if (refreshDatabase()) {
							/*  219 */                       JOptionPane.showMessageDialog(null, "MYSQL database updated");
						/*      */                     } else {
							/*  221 */                       JOptionPane.showMessageDialog(null, "ERROR:::MYSQL database was not updated");
						/*      */                     } 
					/*      */                   }
				/*  224 */                 }, "Database Thread");
				/*      */ 
				/*      */             
				/*  227 */             databaseThread.start();
			/*      */           }
		/*      */         });
		/*      */ 
		/*      */ 
		/*      */     
		/*  233 */     this.reformat.addActionListener(new ActionListener()
				/*      */         {
			/*      */           
			/*      */           public void actionPerformed(ActionEvent e)
			/*      */           {
				/*  238 */             List<VehicleData> list = HomeMainGui.getDataFromFile();
				/*  239 */             HomeMainGui.replaceDataInFile(list, "vehicleRepairData.csv");
				/*  240 */             JOptionPane.showMessageDialog(null, "Data reformatted in  vehicleRepairData.csv file");
			/*      */           }
		/*      */         });
		/*      */ 
		/*      */ 
		/*      */     
		/*  246 */     this.frame = new JFrame();
		/*  247 */     this.frame.getContentPane().setLayout((LayoutManager)null);
		/*      */ 
		/*      */     
		/*  250 */     this.frame.setMinimumSize(new Dimension(530, 750));
		/*      */     
		/*  252 */     this.frame.setTitle("Vehicle Maintenance Logs");
		/*  253 */     this.frame.setJMenuBar(this.menuBar);
		/*      */ 
		/*      */     
		/*  256 */     this.myVechicleInputPanel = new MyVechicleInputPanel(this.mySQLDatabase);
		/*  257 */     this.myVechicleInputPanel.setBounds(10, 11, 500, 487);
		/*  258 */     this.frame.getContentPane().add(this.myVechicleInputPanel);
		/*  259 */     this.queryPanel = new QueryPanel(this.mySQLDatabase);
		/*  260 */     this.queryPanel.setBounds(10, 497, 500, 189);
		/*  261 */     this.frame.getContentPane().add(this.queryPanel);
		/*      */ 
		/*      */     
		/*  264 */     this.frame.pack();
		/*  265 */     this.frame.setVisible(true);
		/*  266 */     this.frame.setResizable(false);
	/*      */   }
	/*      */ 
  private void copyFileToGoogleDrive() {
		/*  276 */     InputStream in = null;
		/*  277 */     OutputStream out = null;
		/*      */     
		/*  279 */     File source = new File("vehicleRepairData.csv");
		/*  280 */     File dest = new File("M:\\My Drive\\Marlin Info\\vehicleRepairData.csv");
		/*      */     
		/*      */     try {
			/*  283 */       in = new FileInputStream(source);
			/*  284 */       out = new FileOutputStream(dest);
			/*  285 */       byte[] buffer = new byte[1024];
			/*      */       int length;
			/*  287 */       while ((length = in.read(buffer)) > 0) {
				/*  288 */         out.write(buffer, 0, length);
			/*      */       }
		/*  290 */     } catch (Exception e1) {
			/*      */       
			/*  292 */       JOptionPane.showMessageDialog(null, "ERROR: " + e1.toString());
		/*      */     } finally {
			/*      */       
			/*      */       try {
				/*  296 */         in.close();
				/*  297 */         if (out == null) {
					/*  298 */           JOptionPane.showMessageDialog(null, "ERROR: Data Not copied to outfile");
				/*      */         } else {
					/*  300 */           out.close();
				/*      */         }
				/*      */       
			/*  303 */       } catch (IOException e2) {
				/*      */ 
				/*      */         
				/*  306 */         JOptionPane.showMessageDialog(null, "ERROR: File Copy Not Closed ");
			/*      */       } 
		/*      */     } 
	/*      */   }
 
	/*      */   protected boolean refreshDatabase() {
		/*  314 */     List<VehicleData> myList = getDataFromFile();
		/*      */     
		/*  316 */     if (myList == null) {
			/*  317 */       return false;
		/*      */     }
		/*  319 */     if (this.mySQLDatabase.refreshDatabase(myList)) {
			/*  320 */       return true;
		/*      */     }
		/*  322 */     return false;
	/*      */   }
 
	/*      */   public void uploadFileToGoogleDrive() {
		/*  329 */     JFrame frame = new JFrame();
		/*  330 */     String theMessage = " UpLoading vehicleRepairData.csv File to Google Drive. Continue with upload?";
		/*  331 */     int result = JOptionPane.showConfirmDialog(frame, theMessage, "alert", 2);
		/*      */     
		/*  333 */     if (result == 0) {
			/*      */       
			/*  335 */       JFileChooser fileChooser = new JFileChooser();
			/*  336 */       fileChooser.setFileSelectionMode(2);
			/*  337 */       int option = fileChooser.showOpenDialog(frame);
			/*  338 */       if (option == 0) {
				/*      */ 
				/*      */ 
				/*      */         
				/*  342 */         frame.setDefaultCloseOperation(3);
				/*  343 */         JOptionPane.showMessageDialog(null, "SUCCESS: File Uploaded to Google Drive ");
			/*      */       } 
		/*      */     } 
	/*      */   }
	
	/*      */   public void downloadFromServerToFile() {
		/*  353 */     JFrame frame = new JFrame();
		/*  354 */     String theMessage = " Download From Server? File Data Will Be Overwritten. Continue?";
		/*  355 */     int result = JOptionPane.showConfirmDialog(frame, theMessage, "alert", 0);
		/*  356 */     if (result == 0) {
			/*      */       
			/*  358 */       String query = "SELECT * FROM vehicle_repairs";
			/*  359 */       this.mySQLDatabase.getQuery(query);
			/*  360 */       List<VehicleData> myList = this.mySQLDatabase.getList();
			/*  361 */       Collections.sort(myList, new SortHomeDataInDescendingOrderByDate());
			/*  362 */       if (replaceDataInFile(myList, "vehicleRepairData.csv").booleanValue()) {
				/*  363 */         JOptionPane.showMessageDialog(null, "SUCCESS: Data Downloaded to File ");
				/*      */ 
				/*      */         
				/*  366 */         if (createJSONData(myList)) {
					/*  367 */           JOptionPane.showMessageDialog(null, "SUCCESS: JSON File created ");
				/*      */         } else {
					/*  369 */           JOptionPane.showMessageDialog(null, "ERROR: Downloading JSON Data ");
				/*      */         }
				/*      */       
			/*      */       } else {
				/*      */         
				/*  374 */         JOptionPane.showMessageDialog(null, "ERROR: Downloading Data ");
			/*      */       } 
			/*  376 */       this.mySQLDatabase.clearList();
		/*      */     } 
		/*      */ 
		/*      */     
		/*  380 */     if (result == 0) {
			/*      */       
			/*  382 */       (new Thread(new Runnable()
					/*      */           {
				/*      */             public void run()
				/*      */             {
					/*  386 */               String query = "SELECT * FROM vehicle_repairs";
					/*  387 */               HomeMainGui.this.mySQLDatabase.getQuery(query);
					/*  388 */               List<VehicleData> myList = HomeMainGui.this.mySQLDatabase.getList();
					/*  389 */               Collections.sort(myList, new HomeMainGui.SortHomeDataInDescendingOrderByDate());
					/*  390 */               if (HomeMainGui.replaceDataInFile(myList, "vehicleRepairData.csv.csv").booleanValue()) {
						/*  391 */                 JOptionPane.showMessageDialog(null, "SUCCESS: Data Downloaded to File ");
					/*      */               } else {
						/*  393 */                 JOptionPane.showMessageDialog(null, "ERROR: Downloading Data ");
					/*      */               } 
				/*      */             }
			/*  396 */           })).start();
			/*  397 */       this.mySQLDatabase.clearList();
		/*      */     } 
	/*      */   }
	/*      */ 
	/*      */ 
	/*      */   
	/*      */   public boolean createJSONData(List<VehicleData> data) {
		/*  404 */     if (data.size() == 0) {
			/*  405 */       return false;
		/*      */     }
		/*      */     
		/*      */     //JSONArray homeDataListList = new JSONArray();

		try (FileWriter file = new FileWriter("homeData.json")) {
			for(int i = 0; i < data.size(); i++) {
				ObjectMapper Obj = new ObjectMapper();
				String jsonStr = Obj.writeValueAsString(data.get(i));
				file.write(jsonStr);
			}
		}
	 catch (IOException e) {
          e.printStackTrace();  
          } 
 return true;
	/*      */   }

	/*      */   public void getMonthlyExpenses() {
		/*  435 */     Date date = new Date();
		/*      */     try {
			/*  437 */       int OFFSET = 20;
			/*      */ 
			/*      */       
			/*  440 */       String result = "SELECT * FROM vehicle_repairs WHERE  DATE >= '" + getFirstDayOfMonth(date) + 
					/*  441 */         "' AND DATE <= '" + getLastDayOfMonth(date) + "'";
			/*  442 */       this.mySQLDatabase.getDateRangeResults(result);
			/*      */       
			/*  444 */       List<VehicleData> dateRangeList = this.mySQLDatabase.getList();
			/*  445 */       Collections.sort(dateRangeList, new SortHomeDataInDescendingOrderByDate());
			/*      */       
			/*  447 */       for (int i = 0; i < dateRangeList.size(); i++)
			/*      */       {
				/*      */         
				/*  450 */         OFFSET += 20;
			/*      */       }
		/*      */     }
		/*  453 */     catch (Exception e) {
			/*      */       
			/*  455 */       JOptionPane.showMessageDialog(null, "ERROR: Unable To obtain current months repairs. Check database connection ");
		/*      */     } 
		/*      */ 
		/*      */     
		/*  459 */     this.mySQLDatabase.clearList();
	/*      */   }
	 
	/*      */   public static void playMaxLimitSound(double currentBalance, double maxLimit) {
		/*      */     try {
			/*  480 */       File musicpath = new File("cash_register.wav");
			/*  481 */       if (musicpath.exists()) {
				/*  482 */         AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);
				/*  483 */         Clip clip = AudioSystem.getClip();
				/*  484 */         clip.open(audioInput);
				/*  485 */         clip.start();
				/*      */         
				/*  487 */         String theMessage = "Your monthly expenditures have exceed your set $" + String.format("%.2f", new Object[] { Double.valueOf(maxLimit)
				/*  488 */             }) + "\n by $" + String.format("%.2f", new Object[] { Double.valueOf(currentBalance - maxLimit) });
				/*  489 */         JOptionPane.showMessageDialog(null, theMessage, "ALERT", 0);
			/*      */       } else {
				/*      */         
				/*  492 */         String theMessage1 = "Audio File Not Found";
				/*  493 */         JOptionPane.showMessageDialog(null, theMessage1, "alert", 0);
			/*      */       }
			/*      */     
		/*  496 */     } catch (Exception exception) {}
	/*      */   }
 
	/*      */   public static String getFirstDayOfMonth(Date d) {
		/*  511 */     Calendar calendar = Calendar.getInstance();
		/*  512 */     calendar.setTime(d);
		/*  513 */     calendar.set(5, 1);
		/*  514 */     Date dddd = calendar.getTime();
		/*  515 */     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		/*  516 */     return sdf1.format(dddd);
	/*      */   }

	/*      */   public static String getLastDayOfMonth(Date d) {
		/*  529 */     Calendar calendar = Calendar.getInstance();
		/*  530 */     calendar.setTime(d);
		/*  531 */     calendar.set(5, calendar.getActualMaximum(5));
		/*  532 */     Date dddd = calendar.getTime();
		/*  533 */     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		/*  534 */     return sdf1.format(dddd);
	/*      */   }
	 
	/*      */   public static String getMonthOfDate() {
		/*  547 */     LocalDate date = LocalDate.now();
		/*  548 */     return date.getMonth().toString();
	/*      */   }
	  
	/*      */   public static void getMonthlyTotalForTheYear(double[][] monthlyTotals, List<VehicleData> list, int row) {
		/*  606 */     int dateSelect = 2;
		/*      */     
		/*  608 */     for (int i = 0; i < list.size(); i++) {
			/*  609 */       switch (convertDateStringToInt(((VehicleData)list.get(i)).getDate(), dateSelect)) {
			/*      */         case 1:
				/*  611 */           monthlyTotals[row][0] = monthlyTotals[row][0] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 2:
				/*  614 */           monthlyTotals[row][1] = monthlyTotals[row][1] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 3:
				/*  617 */           monthlyTotals[row][2] = monthlyTotals[row][2] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 4:
				/*  620 */           monthlyTotals[row][3] = monthlyTotals[row][3] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 5:
				/*  623 */           monthlyTotals[row][4] = monthlyTotals[row][4] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 6:
				/*  626 */           monthlyTotals[row][5] = monthlyTotals[row][5] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 7:
				/*  629 */           monthlyTotals[row][6] = monthlyTotals[row][6] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 8:
				/*  632 */           monthlyTotals[row][7] = monthlyTotals[row][7] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 9:
				/*  635 */           monthlyTotals[row][8] = monthlyTotals[row][8] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 10:
				/*  638 */           monthlyTotals[row][9] = monthlyTotals[row][9] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 11:
				/*  641 */           monthlyTotals[row][10] = monthlyTotals[row][10] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */         case 12:
				/*  644 */           monthlyTotals[row][11] = monthlyTotals[row][11] + ((VehicleData)list.get(i)).getCost().doubleValue();
				/*      */           break;
			/*      */       } 
		/*      */     } 
	/*      */   }
	
	/*      */   public static int convertDateStringToInt(String date, int dateSelect) {
		/*  667 */     String delimStr = "-";
		/*      */     
		/*  669 */     String[] words = date.split(delimStr);
		/*  670 */     int intDate = 0;
		/*  671 */     switch (dateSelect) {
		/*      */       
		/*      */       case 1:
			/*  674 */         intDate = Integer.parseInt(words[1]) * 100 + Integer.parseInt(words[2]) + 
			/*  675 */           Integer.parseInt(words[0]) * 10000;
			/*      */         break;
		/*      */       case 2:
			/*  678 */         intDate = Integer.parseInt(words[1]);
			/*      */         break;
		/*      */       case 3:
			/*  681 */         intDate = Integer.parseInt(words[0]);
			/*      */         break;
		/*      */     } 
		/*      */ 
		/*      */     
		/*  686 */     return intDate;
	/*      */   }
 
	/*      */   private void signonCredentCorrect() {
		/*  692 */     String credentialsFilename = "mysqlsignonstuff.txt";
		/*  693 */     String DELIMITER = "%";
		/*  694 */     String[] myDatastuff = getCredentialsFromFile("mysqlsignonstuff.txt").split("%");
		/*      */ 
		/*      */     
		/*  697 */     this.mySQLDatabase = new MySQLConnect(myDatastuff[0], username, myDatastuff[2]);
		/*      */ 
		/*      */     
		/*  700 */     if (this.mySQLDatabase.isConnected().booleanValue()) {
			/*  701 */       JOptionPane.showMessageDialog(null, "Database Connected");
		/*      */     } else {
			/*  703 */       JOptionPane.showMessageDialog(null, "Trouble Connecting to Database");
		/*      */     } 
	/*      */   }

	/*      */   
	/*      */   private String getCredentialsFromFile(String inputFile) {
		/*  720 */     int myMagicNumber = 36;
		/*  721 */     String allData = null;
		/*  722 */     int count = 1;
		/*  723 */     BufferedReader bufferedReader = null;
		/*      */     
		/*      */     try {
			/*  726 */       bufferedReader = new BufferedReader(new FileReader(inputFile));
			/*      */       try {
				/*      */         String data;
				/*  729 */         while ((data = bufferedReader.readLine()) != null) {
					/*  730 */           if (count == 36) {
						/*  731 */             allData = data;
					/*      */           }
					/*  733 */           count++;
				/*      */         } 
				/*  735 */         if (count < 36) {
					/*  736 */           JOptionPane.showMessageDialog(null, "Credentials can not be found.");
				/*      */         }
			/*      */       }
			/*  739 */       catch (IOException e) {
				/*      */         
				/*  741 */         e.printStackTrace();
				/*  742 */         JOptionPane.showMessageDialog(null, "Can not read  from file ");
			/*      */       } 
		/*  744 */     } catch (FileNotFoundException e) {
			/*      */       String data;
			/*  746 */       JOptionPane.showMessageDialog(null, "Can not find credential file ");
			/*  747 */       e.printStackTrace();
		/*      */     } finally {
			/*      */       
			/*      */       try {
				/*  751 */         bufferedReader.close();
			/*      */       }
			/*  753 */       catch (IOException e) {
				/*      */         
				/*  755 */         JOptionPane.showMessageDialog(null, "Error Closing The File" + e);
				/*  756 */         e.printStackTrace();
			/*      */       } 
		/*      */     } 
		/*      */     
		/*  760 */     return allData;
	/*      */   }
	
	/*      */   public static class SortHomeDataInAscendingOrderByDate
	/*      */     implements Comparator<VehicleData>
	/*      */   {
		/*      */     public int compare(VehicleData a, VehicleData b) {
			/*  801 */       int dateSelect = 1;
			/*      */       
			/*  803 */       return HomeMainGui.convertDateStringToInt(a.getDate(), dateSelect) - HomeMainGui.convertDateStringToInt(b.getDate(), dateSelect);
		/*      */     }
	/*      */   }

	/*      */   public static class SortHomeDataInDescendingOrderByDate
	/*      */     implements Comparator<VehicleData>
	/*      */   {
		/*      */     public int compare(VehicleData a, VehicleData b) {
			/*  819 */       int dateSelect = 1;
			/*  820 */       return HomeMainGui.convertDateStringToInt(b.getDate(), dateSelect) - HomeMainGui.convertDateStringToInt(a.getDate(), dateSelect);
		/*      */     }
	/*      */   }
	 
	/*      */   public static List<VehicleData> getDataFromFile() {
		/*  839 */     BufferedReader fileReader = null;
		/*  840 */     String str = "";
		/*  841 */     List<VehicleData> myList = new ArrayList<>();
		/*  842 */     int parseSelect = 1;
		/*      */ 
		/*      */     
		/*      */     try {
			/*  846 */       fileReader = new BufferedReader(new FileReader("vehicleRepairData.csv"));
			/*      */ 
			/*      */       
			/*  849 */       str = fileReader.readLine();
			/*  850 */       while ((str = fileReader.readLine()) != null) {
				/*  851 */         VehicleData data = new VehicleData(str, 1);
				/*      */ 
				/*      */ 
				/*      */         
				/*  855 */         myList.add(data);
			/*      */       } 
		/*  857 */     } catch (Exception e) {
			/*      */       
			/*  859 */       e.printStackTrace();
			/*  860 */       JOptionPane.showMessageDialog(null, "Error:" + e.toString());
		/*      */     } finally {
			/*      */       try {
				/*  863 */         fileReader.close();
			/*  864 */       } catch (IOException e) {
				/*  865 */         JOptionPane.showMessageDialog(null, "Error Closing The File" + e);
			/*      */       } 
		/*      */     } 
		/*      */ 
		/*      */ 
		/*      */ 
		/*      */ 
		/*      */ 
		/*      */ 
		/*      */     
		/*  875 */     if (myList.size() > 0) {
			/*      */       
			/*  877 */       Collections.sort(myList, new SortHomeDataInDescendingOrderByDate());
			/*      */       
			/*  879 */       return myList;
		/*      */     } 
		/*  881 */     return null;
	/*      */   }

	/*      */   public static Boolean placeInFile(VehicleData item) {
		/*  896 */     Boolean isWrittenToFile = Boolean.valueOf(true);
		/*  897 */     BufferedWriter bw = null;
		/*  898 */     Boolean createFileHeaders = Boolean.valueOf(true);
		/*  899 */     String COMMA_DELIMITER = ",";
		/*  900 */     String NEW_LINE_SEPARATOR = "\n";
		/*      */     try {
			/*  902 */       File file = new File("vehicleRepairData.csv");
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */       
			/*  908 */       if (!file.exists()) {
				/*  909 */         file.createNewFile();
				/*  910 */         createFileHeaders = Boolean.valueOf(false);
			/*      */       } 
			/*  912 */       FileWriter fw = new FileWriter(file, true);
			/*  913 */       bw = new BufferedWriter(fw);
			/*      */       
			/*  915 */       if (!createFileHeaders.booleanValue()) {
				/*  916 */         bw.write("DATE");
				/*  917 */         bw.write(",");
				/*  918 */         bw.write("VEHICLE");
				/*  919 */         bw.write(",");
				/*  920 */         bw.write("MILEAGE");
				/*  921 */         bw.write(",");
				/*  922 */         bw.write("COST");
				/*  923 */         bw.write(",");
				/*  924 */         bw.write("INFO");
				/*  925 */         bw.write(",");
				/*  926 */         bw.write("RECEIPT");
				/*  927 */         bw.write("\n");
			/*      */       } 
			/*      */       
			/*  930 */       bw.write(item.getDate());
			/*  931 */       bw.write(",");
			/*  932 */       bw.write(item.getVehicle());
			/*  933 */       bw.write(",");
			/*  934 */       bw.write(item.getMileage().toString());
			/*  935 */       bw.write(",");
			/*  936 */       bw.write(item.getCost().toString());
			/*  937 */       bw.write(",");
			/*  938 */       bw.write(item.getInfo());
			/*  939 */       bw.write(",");
			/*  940 */       bw.write(item.getReceiptFilename());
			/*  941 */       bw.write("\n");
		/*      */     }
		/*  943 */     catch (IOException ioe) {
			/*  944 */       isWrittenToFile = Boolean.valueOf(false);
			/*  945 */       JOptionPane.showMessageDialog(null, "Error Opening The File ");
		/*      */     } finally {
			/*      */       try {
				/*  948 */         if (bw != null) {
					/*  949 */           bw.close();
				/*      */         }
			/*  951 */       } catch (Exception e) {
				/*  952 */         JOptionPane.showMessageDialog(null, "Error Closing The File ");
			/*      */       } 
		/*      */     } 
		/*  955 */     return isWrittenToFile;
	/*      */   }
   
	/*      */   public static Boolean replaceDataInFile(List<VehicleData> item, String filename) {
		/*  967 */     Boolean isWriteSuccess = Boolean.valueOf(false);
		/*  968 */     BufferedWriter bw = null;
		/*      */     
		/*  970 */     String COMMA_DELIMITER = ",";
		/*  971 */     String NEW_LINE_SEPARATOR = "\n";
		/*      */     try {
			/*  973 */       File file = new File(filename);
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */ 
			/*      */       
			/*  979 */       if (!file.exists()) {
				/*  980 */         file.createNewFile();
			/*      */       }
			/*      */       
			/*  983 */       if (filename.equalsIgnoreCase("vehicleRepairData.csv")) {
				/*  984 */         FileWriter fw = new FileWriter(file, false);
				/*  985 */         bw = new BufferedWriter(fw);
				/*  986 */         bw.write("DATE");
				/*  987 */         bw.write(",");
				/*  988 */         bw.write("VEHICLE");
				/*  989 */         bw.write(",");
				/*  990 */         bw.write("MILEAGE");
				/*  991 */         bw.write(",");
				/*  992 */         bw.write("COST");
				/*  993 */         bw.write(",");
				/*  994 */         bw.write("INFO");
				/*  995 */         bw.write(",");
				/*  996 */         bw.write("RECEIPT");
				/*  997 */         bw.write("\n");
			/*      */       }
			/*      */       else {
				/*      */         
				/* 1001 */         FileWriter fw = new FileWriter(file, true);
				/* 1002 */         bw = new BufferedWriter(fw);
			/*      */       } 
			/*      */       
			/* 1005 */       for (int i = 0; i < item.size(); i++) {
				/* 1006 */         bw.write(((VehicleData)item.get(i)).getDate());
				/* 1007 */         bw.write(",");
				/* 1008 */         bw.write(((VehicleData)item.get(i)).getVehicle());
				/* 1009 */         bw.write(",");
				/* 1010 */         bw.write(((VehicleData)item.get(i)).getMileage().toString());
				/* 1011 */         bw.write(",");
				/* 1012 */         bw.write(((VehicleData)item.get(i)).getCost().toString());
				/* 1013 */         bw.write(",");
				/* 1014 */         bw.write(((VehicleData)item.get(i)).getInfo());
				/* 1015 */         bw.write(",");
				/* 1016 */         bw.write(((VehicleData)item.get(i)).getReceiptFilename());
				/* 1017 */         bw.write("\n");
			/*      */       } 
			/* 1019 */       isWriteSuccess = Boolean.valueOf(true);
		/*      */     }
		/* 1021 */     catch (IOException ioe) {
			/* 1022 */       JOptionPane.showMessageDialog(null, "Data was not written to file. \nVerify the vehicleRepairData.csv file is closed");
		/*      */     } finally {
			/*      */       try {
				/* 1025 */         if (bw != null) {
					/* 1026 */           bw.close();
				/*      */         }
			/* 1028 */       } catch (Exception e) {
				/* 1029 */         JOptionPane.showMessageDialog(null, "Error Closing The File " + e);
			/*      */       } 
		/*      */     } 
		/* 1032 */     return isWriteSuccess;
	/*      */   }
	 
	/*      */   public static void main(String[] args) {
		/* 1068 */     SwingUtilities.invokeLater(new Runnable()
				/*      */         {
			/*      */           public void run() {
				/* 1071 */             char[] p = { 'l', 'i', 'i', 's', 't', '9', 'm', '1', '6', '5', '5' };
			/*      */           }
		/*      */         });
	/*      */   }
/*      */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\HomeMainGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */