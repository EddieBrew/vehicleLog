/*    */ package bst;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Node<T>
/*    */ {
/*    */   private T contents;
/*    */   private Node<T> parent;
/*    */   private Node<T> left;
/*    */   private Node<T> right;
/*    */   
/*    */   public Node() {
/* 15 */     this.contents = null;
/* 16 */     this.left = null;
/* 17 */     this.right = null;
/* 18 */     this.parent = null;
/*    */   }
/*    */   
/*    */   public Node(T item) {
/* 22 */     this.contents = item;
/* 23 */     this.left = null;
/* 24 */     this.right = null;
/* 25 */     this.parent = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Node(Node<T> copiedRoot) {
/* 34 */     this.contents = copiedRoot.getContents();
/* 35 */     this.left = copiedRoot.getLeft();
/* 36 */     this.right = copiedRoot.getRight();
/* 37 */     this.parent = copiedRoot.getParent();
/*    */   }
/*    */   
/*    */   public T getContents() {
/* 41 */     return this.contents;
/*    */   }
/*    */   public void setContents(T item) {
/* 44 */     this.contents = item;
/*    */   } public void setParent(Node<T> p) {
/* 46 */     this.parent = p;
/*    */   } public Node<T> getParent() {
/* 48 */     return this.parent;
/*    */   }
/*    */   public Node<T> getLeft() {
/* 51 */     return this.left;
/*    */   }
/*    */   
/*    */   public Node<T> getRight() {
/* 55 */     return this.right;
/*    */   }
/*    */   
/*    */   public void setLeft(Node<T> item) {
/* 59 */     item.setParent(this);
/* 60 */     this.left = item;
/*    */   }
/*    */   
/*    */   public void setRight(Node<T> item) {
/* 64 */     item.setParent(this);
/* 65 */     this.right = item;
/*    */   }
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\Node.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */