package com.vehiclelog.vehiclelog;

import java.awt.EventQueue;

import login_system.Login_Sys;

public class App {
  
	 public static void main(String[] args) {
		 /*  53 */     EventQueue.invokeLater(new Runnable() {
		 /*     */           public void run() {
		 /*     */             try {
		 /*  56 */               Login_Sys window = new Login_Sys();
		 /*  57 */               window.frame.setVisible(true);
		 /*  58 */             } catch (Exception e) {
		 /*  59 */               e.printStackTrace();
		 /*     */             } 
		 /*     */           }
		 /*     */         });
		 /*     */   }
	
	
   
  
}
