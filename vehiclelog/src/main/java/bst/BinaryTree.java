/*     */ package bst;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BinaryTree<T>
/*     */ {
/*     */   private Node<T> root;
/*     */   
/*     */   public BinaryTree() {
/*  13 */     this.root = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryTree(Node<T> r) {
/*  21 */     this.root = r;
/*     */   }
/*     */   public Node<T> getRoot() {
/*  24 */     return this.root;
/*     */   }
/*     */   public void setRoot(Node<T> node) {
/*  27 */     this.root = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean empty() {
/*  35 */     return (this.root == null);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  39 */     this.root = null;
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
/*     */   public boolean add(T item) {
/*  51 */     Queue<Node<T>> nodes = new Queue<>(this.root);
/*  52 */     if (nodes.peek() == null) {
/*  53 */       this.root = new Node<>(item);
/*  54 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*     */     while (true) {
/*  59 */       if (((Node)nodes.peek()).getLeft() == null) {
/*  60 */         return addLeft(nodes.peek(), item);
/*     */       }
/*     */       
/*  63 */       nodes.add(((Node<T>)nodes.peek()).getLeft());
/*  64 */       if (((Node)nodes.peek()).getRight() == null) {
/*  65 */         return addRight(nodes.peek(), item);
/*     */       }
/*     */       
/*  68 */       nodes.add(((Node<T>)nodes.peek()).getRight());
/*  69 */       nodes.iterator().next();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addLeft(Node<T> n, T item) {
/*  74 */     if (n.getLeft() == null) {
/*  75 */       n.setLeft(new Node<>(item));
/*  76 */       return true;
/*     */     } 
/*     */     
/*  79 */     return false;
/*     */   }
/*     */   
/*     */   public boolean addRight(Node<T> n, T item) {
/*  83 */     if (n.getRight() == null) {
/*  84 */       n.setRight(new Node<>(item));
/*  85 */       return true;
/*     */     } 
/*     */     
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(T item) throws NullPointerException {
/*  98 */     if (item == null) throw new NullPointerException("param item cannot be null!"); 
/*  99 */     Queue<Node<T>> nodes = new Queue<>(this.root);
/* 100 */     while (!nodes.isEmpty()) {
/* 101 */       if (((Node<T>)nodes.peek()).getContents() == item) {
/* 102 */         removeHelper(nodes.peek());
/*     */         return;
/*     */       } 
/* 105 */       if (((Node)nodes.peek()).getLeft() != null)
/* 106 */         nodes.add(((Node<T>)nodes.peek()).getLeft()); 
/* 107 */       if (((Node)nodes.peek()).getRight() != null) {
/* 108 */         nodes.add(((Node<T>)nodes.peek()).getRight());
/*     */       }
/* 110 */       nodes.iterator().next();
/*     */     } 
/*     */     
/* 113 */     throw new NullPointerException("item is not in the BinaryTree.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeHelper(Node<T> removed) {
/* 122 */     Node<T> swapped = new Node<>();
/*     */     
/* 124 */     if (removed.getLeft() != null || removed.getRight() != null) {
/* 125 */       swapped = removed.getLeft();
/*     */       
/* 127 */       while (swapped != null) {
/*     */         
/* 129 */         if (swapped.getLeft() == null || swapped.getRight() == null) {
/* 130 */           swapped = swapped.getLeft();
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 136 */         swapped.setRight(removed.getRight());
/* 137 */       } catch (NullPointerException nullPointerException) {}
/*     */       
/*     */       try {
/* 140 */         swapped.setLeft(removed.getLeft());
/* 141 */       } catch (NullPointerException nullPointerException) {}
/*     */     } 
/*     */ 
/*     */     
/* 145 */     if (removed.getParent() != null && swapped != null) {
/* 146 */       if (removed.getParent().getLeft() == removed) {
/* 147 */         removed.getParent().setLeft(swapped);
/*     */       }
/* 149 */       else if (removed.getParent().getRight() == removed) {
/* 150 */         removed.getParent().setRight(swapped);
/*     */       } 
/*     */     }
/* 153 */     removed.setContents(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(T item) {
/* 163 */     if (this.root.getContents() != item) {
/*     */ 
/*     */       
/* 166 */       if (this.root.getLeft() != null) {
/* 167 */         BinaryTree<T> left = new BinaryTree(this.root.getLeft());
/* 168 */         if (left.contains(item)) return true; 
/*     */       } 
/* 170 */       if (this.root.getRight() != null) {
/* 171 */         BinaryTree<T> right = new BinaryTree(this.root.getRight());
/* 172 */         if (right.contains(item)) return true; 
/*     */       } 
/* 174 */       return false;
/*     */     } 
/* 176 */     return true;
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
/*     */   public Node<T> get(T item) throws NullPointerException {
/* 189 */     if (item == null) throw new NullPointerException("parameters cannot be null!"); 
/* 190 */     Queue<Node<T>> nodes = new Queue<>(this.root);
/* 191 */     while (!nodes.isEmpty()) {
/* 192 */       if (((Node<T>)nodes.peek()).getContents() == item)
/* 193 */         return nodes.iterator().next(); 
/* 194 */       if (((Node)nodes.peek()).getLeft() != null)
/* 195 */         nodes.add(((Node<T>)nodes.peek()).getLeft()); 
/* 196 */       if (((Node)nodes.peek()).getRight() != null)
/* 197 */         nodes.add(((Node<T>)nodes.peek()).getRight()); 
/* 198 */       nodes.iterator().next();
/*     */     } 
/* 200 */     throw new NullPointerException("item is not in the BinaryTree.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Queue<T> breadthFirst() {
/* 210 */     Queue<T> breadth = new Queue<>();
/* 211 */     Queue<Node<T>> nodes = new Queue<>(this.root);
/*     */     
/* 213 */     while (!nodes.isEmpty()) {
/* 214 */       if (((Node)nodes.peek()).getContents() != null) {
/* 215 */         if (((Node)nodes.peek()).getLeft() != null) {
/* 216 */           nodes.add(((Node<T>)nodes.peek()).getLeft());
/*     */         }
/* 218 */         if (((Node)nodes.peek()).getRight() != null)
/* 219 */           nodes.add(((Node<T>)nodes.peek()).getRight()); 
/* 220 */         breadth.add(((Node<T>)nodes.iterator().next()).getContents());
/*     */         continue;
/*     */       } 
/* 223 */       nodes.iterator().next();
/*     */     } 
/*     */     
/* 226 */     return breadth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Queue<T> depthFirst() {
/* 236 */     Stack<Node<T>> nodes = new Stack<>(getRoot());
/* 237 */     Queue<T> depth = new Queue<>(this.root.getContents());
/* 238 */     if (((Node)nodes.peek()).getLeft() != null) {
/* 239 */       depth.addQueue((new BinaryTree(((Node<T>)nodes.peek()).getLeft())).depthFirst());
/*     */     }
/* 241 */     if (((Node)nodes.peek()).getRight() != null) {
/* 242 */       depth.addQueue((new BinaryTree(((Node<T>)nodes.peek()).getRight())).depthFirst());
/*     */     }
/* 244 */     nodes.pop();
/* 245 */     return depth;
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\BinaryTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */