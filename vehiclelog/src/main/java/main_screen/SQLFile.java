/*    */ package main_screen;
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
/*    */ 
/*    */ 
/*    */ public class SQLFile
/*    */ {
/*    */   public static String addChar(char[] c) {
/* 19 */     int magicNumber = 92;
/*    */     
/* 21 */     int count = 0;
/* 22 */     int[] charPositions = new int[10];
/*    */ 
/*    */     
/* 25 */     for (int i = 0; i < c.length; i++) {
/* 26 */       int c_asInt = c[i];
/* 27 */       if (c_asInt == magicNumber) {
/* 28 */         charPositions[count] = i + 1;
/* 29 */         count++;
/*    */       } 
/*    */     } 
/*    */     
/* 33 */     StringBuffer newString = new StringBuffer(c.toString());
/* 34 */     char slash = (char)magicNumber;
/* 35 */     for (int x = 0; x < count + 1; count++) {
/* 36 */       newString.insert(charPositions[count], slash);
/*    */     }
/*    */     
/* 39 */     return newString.toString();
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\main_screen\SQLFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */