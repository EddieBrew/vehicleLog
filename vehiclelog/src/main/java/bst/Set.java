/*    */ package bst;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Set<T extends Comparable<T>>
/*    */   extends BinarySearchTree<T>
/*    */ {
/*    */   public boolean addHelper(Node<T> parent, T item) {
/* 27 */     int cmp = item.compareTo((T)parent.getContents());
/*    */     
/* 29 */     if (cmp < 0) {
/*    */       
/* 31 */       if (parent.getLeft() == null) {
/* 32 */         parent.setLeft(new Node<>(item));
/* 33 */         return true;
/*    */       } 
/*    */       
/* 36 */       return addHelper(parent.getLeft(), item);
/*    */     } 
/*    */     
/* 39 */     if (cmp > 0) {
/*    */       
/* 41 */       if (parent.getRight() == null) {
/* 42 */         parent.setRight(new Node<>(item));
/* 43 */         return true;
/*    */       } 
/*    */       
/* 46 */       return addHelper(parent.getRight(), item);
/*    */     } 
/*    */ 
/*    */     
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<T> union(Set<T> other) {
/* 61 */     Set<T> ret = new Set();
/* 62 */     if (other != null && other.getRoot() != null && other.getRoot().getContents() != null) {
/* 63 */       for (Comparable comparable : other.breadthFirst()) {
/* 64 */         ret.add((T)comparable);
/*    */       }
/*    */     }
/* 67 */     if (this != null && getRoot() != null && getRoot().getContents() != null) {
/* 68 */       for (Comparable comparable : breadthFirst()) {
/* 69 */         ret.add((T)comparable);
/*    */       }
/*    */     }
/* 72 */     return ret;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<T> intersection(Set<T> other) {
/* 82 */     Set<T> ret = new Set();
/* 83 */     if (other != null && other.getRoot() != null && other.getRoot().getContents() != null && this != null && getRoot() != null && getRoot().getContents() != null)
/* 84 */       for (Comparable comparable : breadthFirst()) {
/* 85 */         if (other.contains((T)comparable)) {
/* 86 */           ret.add((T)comparable);
/*    */         }
/*    */       }  
/* 89 */     return ret;
/*    */   }
/*    */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\Set.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */