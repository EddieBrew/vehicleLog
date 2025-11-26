/*     */ package main_screen;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MySQLConnect
/*     */ {
/*  26 */   private String jdbcUrl = null;
/*  27 */   private String userid = null;
/*  28 */   private String password = null;
/*     */   
/*  30 */   private final String VEHICLE_REPAIR_DATABASE = "vehicle_repairs";
/*     */ 
/*     */   
/*  33 */   static List<VehicleData> list = new ArrayList<>();
/*     */   
/*     */   public List<VehicleData> getList() {
/*  36 */     return list;
/*     */   }
/*     */   
/*     */   public void clearList() {
/*  40 */     list.clear();
/*     */   }
/*     */   
/*  43 */   public VehicleData lockerInfo = null;
/*     */   public VehicleData getLockerInfo() {
/*  45 */     return this.lockerInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MySQLConnect(String jdbcUrl, String userid, String password) {
/*  51 */     this.jdbcUrl = jdbcUrl;
/*  52 */     this.userid = userid;
/*  53 */     this.password = password;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isConnected() {
/*  60 */     Connection conn = null;
/*     */     
/*     */     try {
/*  63 */       conn = DriverManager.getConnection(this.jdbcUrl, this.userid, this.password);
/*  64 */       conn.close();
/*  65 */       return Boolean.valueOf(true);
/*  66 */     } catch (Exception e) {
/*     */       
/*  68 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  71 */         conn.close();
/*  72 */       } catch (SQLException e) {
/*     */ 
/*     */         
/*  75 */         JOptionPane.showMessageDialog(null, "ERROR: Unable to close connection to database. Try Again");
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void placeItemInPastDatabase(VehicleData item) {
/*  84 */     Connection conn = null;
/*     */     try {
/*  86 */       if (isConnected().booleanValue())
/*     */       {
/*  88 */         String query = "INSERT INTO vehicle_repairs (DATE, VEHICLE, COST, INFO, RECEIPT )VALUES(" + 
/*  89 */           item.getDate() + "," + item.getVehicle() + ", '" + item.getCost() + "'," + 
/*  90 */           item.getInfo() + "', " + "', '" + item.getReceiptFilename() + "' )";
/*     */         
/*  92 */         conn = DriverManager.getConnection(this.jdbcUrl, this.userid, this.password);
/*  93 */         Statement myStatement = conn.createStatement();
/*  94 */         myStatement = conn.createStatement();
/*  95 */         myStatement.executeUpdate(query);
/*     */       }
/*     */     
/*  98 */     } catch (SQLException e) {
/*     */       
/* 100 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 103 */         conn.close();
/* 104 */       } catch (SQLException e) {
/*     */         
/* 106 */         e.printStackTrace();
/* 107 */         JOptionPane.showMessageDialog(null, "ERROR: Unable to close connection to database. Try Again");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteDatabaseDate() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean insertData(String query) {
/* 123 */     Boolean isInsertSuccessful = Boolean.valueOf(false);
/* 124 */     Connection conn = null;
/*     */     try {
/* 126 */       if (isConnected().booleanValue())
/*     */       {
/* 128 */         conn = DriverManager.getConnection(this.jdbcUrl, this.userid, this.password);
/* 129 */         Statement myStatement = conn.createStatement();
/* 130 */         myStatement.executeUpdate(query);
/* 131 */         isInsertSuccessful = Boolean.valueOf(true);
/*     */       }
/*     */     
/*     */     }
/* 135 */     catch (SQLException e) {
/*     */       
/* 137 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 140 */         conn.close();
/* 141 */       } catch (SQLException e) {
/*     */         
/* 143 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 146 */     return isInsertSuccessful;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateDatabase(String result) {
/* 153 */     Connection conn = null;
/*     */     try {
/* 155 */       if (isConnected().booleanValue()) {
/* 156 */         String query = result;
/* 157 */         conn = DriverManager.getConnection(this.jdbcUrl, this.userid, this.password);
/* 158 */         Statement myStatement = conn.createStatement();
/* 159 */         myStatement = conn.createStatement();
/* 160 */         myStatement.executeUpdate(query);
/*     */       }
/*     */     
/* 163 */     } catch (SQLException e) {
/*     */       
/* 165 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 168 */         conn.close();
/* 169 */       } catch (SQLException e) {
/*     */         
/* 171 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean refreshDatabase(List<VehicleData> item) {
/* 179 */     Connection conn = null;
/* 180 */     boolean isDatabaseUpdated = false;
/*     */     try {
/* 182 */       if (isConnected().booleanValue()) {
/* 183 */         conn = DriverManager.getConnection(this.jdbcUrl, this.userid, this.password);
/* 184 */         Statement myStatement = conn.createStatement();
/* 185 */         String truncateQuery = "TRUNCATE TABLE vehicle_repairs";
/* 186 */         myStatement.executeUpdate(truncateQuery);
/*     */         
/* 188 */         for (int i = 0; i < item.size(); i++) {
/*     */           
/* 190 */           String filepath = ((VehicleData)item.get(i)).getReceiptFilename();
/* 191 */           String newFilePath = filepath.replaceAll("\\\\", "\\\\\\\\");
/*     */ 
/*     */           
/* 194 */           String query = "INSERT INTO vehicle_repairs (DATE, VEHICLE, MILEAGE, COST, INFO, RECEIPT )\nVALUES ('" + (
/*     */             
/* 196 */             (VehicleData)item.get(i)).getDate() + "','" + ((VehicleData)item.get(i)).getVehicle() + "'," + (
/* 197 */             (VehicleData)item.get(i)).getMileage() + "," + ((VehicleData)item.get(i)).getCost() + 
/* 198 */             ", '" + ((VehicleData)item.get(i)).getInfo() + "','" + newFilePath + "')";
/* 199 */           myStatement.execute(query);
/*     */         } 
/*     */       } 
/* 202 */       isDatabaseUpdated = true;
/* 203 */     } catch (SQLException e) {
/*     */       
/* 205 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 208 */         conn.close();
/* 209 */       } catch (SQLException e) {
/*     */         
/* 211 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 215 */     return isDatabaseUpdated;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void getQueryResultsFromDatabase(final String columnName, final String columnValueString, final int columnValueInt) {
/* 221 */     String COMMA = ", ";
/* 222 */     list.clear();
/*     */     
/* 224 */     ExecutorService executor = Executors.newCachedThreadPool();
/* 225 */     Future<VehicleData> myFuture = executor.submit(new Callable<VehicleData>()
/*     */         {
/*     */           
/*     */           public VehicleData call() throws Exception
/*     */           {
/* 230 */             int parseSelect = 2;
/* 231 */             VehicleData data = null;
/* 232 */             if (MySQLConnect.this.isConnected().booleanValue()) {
/*     */ 
/*     */               
/* 235 */               Connection conn = null;
/*     */               try {
/*     */                 ResultSet rset;
/* 238 */                 conn = DriverManager.getConnection(MySQLConnect.this.jdbcUrl, MySQLConnect.this.userid, MySQLConnect.this.password);
/* 239 */                 Statement myStatement = conn.createStatement();
/*     */ 
/*     */                 
/* 242 */                 if (columnValueString == null) {
/*     */                   
/* 244 */                   rset = myStatement.executeQuery("SELECT * FROM vehicle_repairs WHERE " + columnName + " = " + columnValueInt);
/*     */                 } else {
/*     */                   
/* 247 */                   rset = myStatement.executeQuery("SELECT * FROM vehicle_repairs WHERE " + columnName + " = " + "'" + columnValueString + "'");
/*     */                 } 
/*     */                 
/* 250 */                 while (rset.next()) {
/* 251 */                   String stringOutput = "";
/* 252 */                   stringOutput = String.valueOf(rset.getString("DATE")) + ", " + 
/* 253 */                     rset.getString("VEHICLE") + ", " + 
/* 254 */                     Double.toString(rset.getDouble("MILEAGE")) + ", " + 
/* 255 */                     Double.toString(rset.getDouble("COST")) + ", " + 
/* 256 */                     rset.getString("INFO") + ", " + 
/* 257 */                     rset.getString("RECEIPT") + ", ";
/*     */ 
/*     */                   
/* 260 */                   data = new VehicleData(stringOutput, 2);
/*     */                 } 
/* 262 */                 rset.close();
/* 263 */               } catch (SQLException e) {
/*     */                 
/* 265 */                 e.printStackTrace();
/* 266 */                 return null;
/*     */               } finally {
/* 268 */                 conn.close();
/*     */               } 
/*     */             } 
/* 271 */             return data;
/*     */           }
/*     */         });
/* 274 */     executor.shutdown();
/*     */     try {
/* 276 */       this.lockerInfo = myFuture.get();
/* 277 */     } catch (InterruptedException e) {
/*     */       
/* 279 */       e.printStackTrace();
/* 280 */     } catch (ExecutionException e) {
/*     */       
/* 282 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getDateRangeResults(final String query) {
/* 292 */     String DELIMITER = "# ";
/* 293 */     ExecutorService executor = Executors.newCachedThreadPool();
/* 294 */     Future<List<VehicleData>> myFuture = executor.submit(new Callable<List<VehicleData>>()
/*     */         {
/*     */           
/*     */           public List<VehicleData> call() throws Exception
/*     */           {
/* 299 */             int parseSelect = 2;
/* 300 */             MySQLConnect.list.clear();
/* 301 */             List<VehicleData> dataArray = new ArrayList<>();
/* 302 */             if (MySQLConnect.this.isConnected().booleanValue()) {
/*     */               
/* 304 */               Connection conn = DriverManager.getConnection(MySQLConnect.this.jdbcUrl, MySQLConnect.this.userid, MySQLConnect.this.password);
/* 305 */               Statement stmt = conn.createStatement();
/*     */ 
/*     */ 
/*     */               
/* 309 */               ResultSet rset = stmt.executeQuery(query);
/* 310 */               while (rset.next()) {
/*     */                 
/* 312 */                 String stringOutput = "";
/* 313 */                 stringOutput = String.valueOf(rset.getString("DATE")) + "# " + 
/* 314 */                   rset.getString("VEHICLE") + "# " + 
/* 315 */                   Double.toString(rset.getDouble("MILEAGE")) + "# " + 
/* 316 */                   Double.toString(rset.getDouble("COST")) + "# " + 
/* 317 */                   rset.getString("INFO") + "# " + 
/* 318 */                   rset.getString("RECEIPT");
/*     */                 
/* 320 */                 dataArray.add(new VehicleData(stringOutput, 2));
/*     */               } 
/*     */               
/* 323 */               rset.close();
/* 324 */               conn.close();
/*     */             } 
/*     */             
/* 327 */             return dataArray; }
/*     */         });
/* 329 */     executor.shutdown();
/*     */     
/*     */     try {
/* 332 */       list = myFuture.get();
/* 333 */     } catch (InterruptedException e) {
/*     */       
/* 335 */       e.printStackTrace();
/* 336 */     } catch (ExecutionException e) {
/*     */       
/* 338 */       e.printStackTrace();
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
/*     */   
/*     */   public void getQuery(final String query) {
/* 356 */     String PARSING_CHAR = "# ";
/*     */     
/* 358 */     ExecutorService executor = Executors.newCachedThreadPool();
/* 359 */     Future<List<VehicleData>> myFuture = executor.submit(new Callable<List<VehicleData>>()
/*     */         {
/*     */           
/*     */           public List<VehicleData> call() throws Exception
/*     */           {
/* 364 */             MySQLConnect.list.clear();
/* 365 */             List<VehicleData> dataArray = new ArrayList<>();
/* 366 */             int parseSelect = 2;
/*     */             
/* 368 */             if (MySQLConnect.this.isConnected().booleanValue()) {
/*     */               
/* 370 */               Connection conn = DriverManager.getConnection(MySQLConnect.this.jdbcUrl, MySQLConnect.this.userid, MySQLConnect.this.password);
/* 371 */               Statement stmt = conn.createStatement();
/* 372 */               ResultSet rset = stmt.executeQuery(query);
/* 373 */               while (rset.next()) {
/* 374 */                 String stringOutput = "";
/* 375 */                 stringOutput = String.valueOf(rset.getString("DATE")) + "# " + 
/* 376 */                   rset.getString("VEHICLE") + "# " + 
/* 377 */                   Double.toString(rset.getDouble("MILEAGE")) + "# " + 
/* 378 */                   Double.toString(rset.getDouble("COST")) + "# " + 
/* 379 */                   rset.getString("INFO") + "# " + 
/* 380 */                   rset.getString("RECEIPT") + "# ";
/* 381 */                 dataArray.add(new VehicleData(stringOutput, 2));
/*     */               } 
/*     */               
/* 384 */               rset.close();
/* 385 */               conn.close();
/*     */             } 
/* 387 */             return dataArray;
/*     */           }
/*     */         });
/*     */     
/* 391 */     executor.shutdown();
/*     */     try {
/* 393 */       list = myFuture.get();
/* 394 */     } catch (InterruptedException e) {
/*     */       
/* 396 */       e.printStackTrace();
/* 397 */     } catch (ExecutionException e) {
/*     */       
/* 399 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\MySQLConnect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */