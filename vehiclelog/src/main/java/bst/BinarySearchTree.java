/*     */ package bst;

/*     */ public class BinarySearchTree<T extends Comparable<T>>
/*     */   extends BinaryTree<T>
/*     */ {
/*     */   public BinarySearchTree() {}
/*     */   
/*     */   public BinarySearchTree(Node<T> root) {
/*  13 */     super(root);
/*     */   }

/*     */   
/*     */   public boolean add(T item) {
/*  27 */     if (item == null) {
/*  28 */       throw new NullPointerException("param item cannot be null!");
/*     */     }
/*     */     
/*  31 */     if (getRoot() == null) {
/*  32 */       setRoot(new Node<>(item));
/*  33 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  37 */     Node<T> parent = getRoot();
/*  38 */     return addHelper(parent, item);
/*     */   }
/*     */   
/*     */   public boolean addHelper(Node<T> parent, T item) {
/*  42 */     int cmp = item.compareTo((T)parent.getContents());
/*     */     
/*  44 */     if (cmp < 0) {
/*     */       
/*  46 */       if (parent.getLeft() == null) {
/*  47 */         parent.setLeft(new Node<>(item));
/*  48 */         return true;
/*     */       } 
/*     */       
/*  51 */       return addHelper(parent.getLeft(), item);
/*     */     } 
/*     */     
/*  54 */     if (cmp > 0) {
/*     */       
/*  56 */       if (parent.getRight() == null) {
/*  57 */         parent.setRight(new Node<>(item));
/*  58 */         return true;
/*     */       } 
/*     */       
/*  61 */       return addHelper(parent.getRight(), item);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  66 */     if (parent.getLeft() == null) {
/*  67 */       parent.setLeft(new Node<>(item));
/*  68 */       return true;
/*     */     } 
/*     */     
/*  71 */     return addHelper(parent.getLeft(), item);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeHelper(Node<T> removed) {
/*     */     Node<T> swapped;
/*  83 */     if (removed.getRight() == null || removed.getLeft() == null) {
/*  84 */       if (removed.getLeft() != null)
/*  85 */       { swapped = removed.getLeft(); }
/*     */       
/*  87 */       else if (removed.getRight() != null) { swapped = removed.getRight(); }
/*     */       else
/*  89 */       { swapped = null; }
/*  90 */        if (removed.getParent() == null) setRoot(swapped);
/*     */     
/*     */     } else {
/*     */       
/*  94 */       swapped = removed.getRight();
/*  95 */       swapped.setLeft(removed.getLeft());
/*  96 */       swapped.setRight(removed.getRight().getRight());
/*  97 */       Node<T> rightIter = new Node<>(swapped.getRight());
/*  98 */       while (rightIter.getLeft() != null) {
/*  99 */         rightIter = rightIter.getLeft();
/*     */       }
/* 101 */       rightIter.getParent().setLeft(removed.getRight().getLeft());
/*     */     } 
/*     */     
/* 104 */     if (removed.getParent() == null)
/* 105 */     { setRoot(swapped); }
/*     */     
/* 107 */     else if (removed.getParent().getLeft() == removed)
/* 108 */     { if (swapped != null) { removed.getParent().setLeft(swapped); }
/* 109 */       else { removed.getParent().setLeft(new Node()); }
/*     */       
/*     */        }
/*     */     
/* 113 */     else if (swapped != null) { removed.getParent().setRight(swapped); }
/* 114 */     else { removed.getParent().setRight(new Node()); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(T item) {
/* 120 */     if (getRoot().getContents() == null) return false; 
/* 121 */     Node<T> cur = getRoot();
/* 122 */     while (cur.getContents() != null) {
/* 123 */       if (((Comparable)cur.getContents()).equals(item))
/* 124 */         return true; 
/* 125 */       if (((Comparable<T>)cur.getContents()).compareTo(item) < 0 && cur.getRight() != null) {
/* 126 */         cur = cur.getRight(); continue;
/* 127 */       }  if (cur.getLeft() != null) {
/* 128 */         cur = cur.getLeft(); continue;
/* 129 */       }  return false;
/*     */     } 
/* 131 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\BinarySearchTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */