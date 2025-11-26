/*     */ package main_screen;
/*     */ 
/*     */ import java.util.Objects;
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
/*     */ public class VehicleData
/*     */   implements Comparable<VehicleData>
/*     */ {
/*     */   private String vehicle;
/*     */   private String date;
/*     */   private Double mileage;
/*     */   private Double cost;
/*     */   private String info;
/*     */   private String receiptFilename;
/*     */   
/*     */   public VehicleData(String vehicle, String date, Double mileage, Double cost, String receiptFilename, String info) {
/*  27 */     this.vehicle = vehicle;
/*  28 */     this.date = date;
/*  29 */     this.mileage = mileage;
/*  30 */     this.cost = cost;
/*  31 */     this.info = info;
/*  32 */     this.receiptFilename = receiptFilename;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VehicleData(String input, int parsingMethodSelect) {
/*  39 */     if (parsingMethodSelect == 1) {
/*  40 */       parseIntoVariable1(input);
/*     */     }
/*  42 */     if (parsingMethodSelect == 2) {
/*  43 */       parseIntoVariable2(input);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseIntoVariable1(String input) {
/*  49 */     String DELIMITER = ",";
/*  50 */     String[] databaseInput = input.split(",");
/*     */     
/*  52 */     for (int i = 0; i < databaseInput.length; i++) {
/*     */       
/*  54 */       switch (i) {
/*     */         case 0:
/*  56 */           this.date = databaseInput[i].trim();
/*     */           break;
/*     */         case 1:
/*  59 */           this.vehicle = databaseInput[i];
/*     */           break;
/*     */         case 2:
/*  62 */           this.mileage = Double.valueOf(Double.parseDouble(databaseInput[i]));
/*     */           break;
/*     */         case 3:
/*  65 */           this.cost = Double.valueOf(Double.parseDouble(databaseInput[i]));
/*     */           break;
/*     */         case 4:
/*  68 */           this.info = databaseInput[i];
/*     */           break;
/*     */         case 5:
/*  71 */           this.receiptFilename = databaseInput[i].trim();
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseIntoVariable2(String input) {
/*  79 */     String DELIMITER = "# ";
/*  80 */     String[] databaseInput = input.split("# ");
/*     */     
/*  82 */     for (int i = 0; i < databaseInput.length; i++) {
/*     */       
/*  84 */       switch (i) {
/*     */         case 0:
/*  86 */           this.date = databaseInput[i].trim();
/*     */           break;
/*     */         case 1:
/*  89 */           this.vehicle = databaseInput[i];
/*     */           break;
/*     */         case 2:
/*  92 */           this.mileage = Double.valueOf(Double.parseDouble(databaseInput[i]));
/*     */           break;
/*     */         case 3:
/*  95 */           this.cost = Double.valueOf(Double.parseDouble(databaseInput[i]));
/*     */           break;
/*     */         case 4:
/*  98 */           this.info = databaseInput[i];
/*     */           break;
/*     */         
/*     */         case 5:
/* 102 */           this.receiptFilename = databaseInput[i].trim();
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int convertDateStringToInt(String date) {
/* 111 */     String delimStr = "-";
/*     */     
/* 113 */     String[] words = date.split(delimStr);
/*     */ 
/*     */     
/* 116 */     return Integer.parseInt(words[1]) * 100 + Integer.parseInt(words[2]) + 
/* 117 */       Integer.parseInt(words[0]) * 10000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 125 */     return "VehicleData [vehicle = " + this.vehicle + "\ndate =" + this.date + "\n cost = " + this.cost + " \n receiptFilenamecost = " + this.receiptFilename + 
/* 126 */       "\n info = " + this.info + "]";
/*     */   }
/*     */   
/*     */   public String getVehicle() {
/* 130 */     return this.vehicle;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDate() {
/* 135 */     return this.date;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Double getCost() {
/* 142 */     return this.cost;
/*     */   }
/*     */   
/*     */   public Double getMileage() {
/* 146 */     return this.mileage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReceiptFilename() {
/* 152 */     return this.receiptFilename;
/*     */   }
/*     */   
/*     */   public String getInfo() {
/* 156 */     return this.info;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDate(String date) {
/* 161 */     this.date = date;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 167 */     return Objects.hash(new Object[] { this.vehicle, this.cost, this.date, this.info, this.receiptFilename });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 172 */     if (this == obj)
/* 173 */       return true; 
/* 174 */     if (obj == null)
/* 175 */       return false; 
/* 176 */     if (getClass() != obj.getClass())
/* 177 */       return false; 
/* 178 */     VehicleData other = (VehicleData)obj;
/* 179 */     return (Objects.equals(this.date, other.date) && Objects.equals(this.cost, other.cost) && Objects.equals(this.vehicle, other.vehicle) && 
/* 180 */       Objects.equals(this.info, other.info) && Objects.equals(this.receiptFilename, other.receiptFilename));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(VehicleData obj) {
/* 189 */     return convertDateStringToInt(this.date) - convertDateStringToInt(obj.date);
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\VehicleData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */