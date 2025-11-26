/*     */ package bst;
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
/*     */ public class Heap<T extends Comparable<T>>
/*     */ {
/*     */   private T contents;
/*     */   private Heap<T> left;
/*     */   private Heap<T> right;
/*     */   
/*     */   public Heap() {
/*  22 */     this.contents = null;
/*  23 */     this.left = null;
/*  24 */     this.right = null;
/*     */   }
/*     */   
/*     */   public Heap(T item) {
/*  28 */     this.contents = item;
/*  29 */     this.left = null;
/*  30 */     this.right = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Heap(Heap<T> rootHeap) {
/*  39 */     this.contents = rootHeap.contents;
/*  40 */     this.left = rootHeap.left;
/*  41 */     this.right = rootHeap.right;
/*     */   }
/*     */   
/*     */   public Heap<T> getLeft() {
/*  45 */     return this.left;
/*     */   }
/*     */   
/*     */   public Heap<T> getRight() {
/*  49 */     return this.right;
/*     */   }
/*     */   
/*     */   public T getContents() {
/*  53 */     return this.contents;
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
/*     */   public boolean add(T item) {
/*  67 */     if (item == null) {
/*  68 */       throw new NullPointerException("param item cannot be null!");
/*     */     }
/*     */     
/*  71 */     if (this.contents == null) {
/*  72 */       this.contents = item;
/*  73 */       return true;
/*     */     } 
/*     */     
/*  76 */     if (item.compareTo(this.contents) > 0) {
/*  77 */       this.right = new Heap(this);
/*  78 */       this.left = null;
/*  79 */       this.contents = item;
/*  80 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     return addHelper(this, item);
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
/*     */   public boolean addHelper(Heap<T> parent, T item) {
/*  98 */     if (parent.left == null && parent.right == null) {
/*  99 */       parent.right = new Heap(item);
/* 100 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     if (parent.right == null) {
/*     */ 
/*     */       
/* 107 */       if (item.compareTo(parent.left.contents) < 0) {
/* 108 */         parent.right = new Heap(parent.left);
/* 109 */         parent.left = new Heap(item);
/* 110 */         return true;
/*     */       } 
/* 112 */       parent.right = new Heap(item);
/* 113 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 117 */     if (parent.left == null) {
/*     */       
/* 119 */       if (item.compareTo(parent.right.contents) > 0) {
/* 120 */         parent.left = new Heap(parent.right);
/* 121 */         parent.right = new Heap(item);
/* 122 */         return true;
/*     */       } 
/* 124 */       parent.left = new Heap(item);
/* 125 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 130 */     if (item.compareTo(parent.left.contents) < 0) {
/* 131 */       return addHelper(parent.left, item);
/*     */     }
/*     */ 
/*     */     
/* 135 */     if (item.compareTo(parent.right.contents) < 0) {
/*     */       
/* 137 */       Heap<T> newMid = new Heap(item);
/*     */       
/* 139 */       if (parent.left.right != null) newMid.left = parent.left.right; 
/* 140 */       if (parent.left.left != null) newMid.left.right = parent.left.left;
/*     */       
/* 142 */       newMid.right = new Heap(parent.left.contents);
/* 143 */       parent.left = newMid;
/* 144 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 148 */     if (item.compareTo(parent.right.contents) > 0) {
/* 149 */       Heap<T> newMid = new Heap(item);
/* 150 */       newMid.left = parent.left;
/* 151 */       newMid.right = parent.right;
/*     */       
/* 153 */       parent.right = newMid;
/* 154 */       parent.left = null;
/* 155 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 159 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T extractMax() {
/* 170 */     T tempCon = this.contents;
/* 171 */     if (this.left == null || this.right == null) {
/* 172 */       if (this.right != null) { setRoot(this.right); }
/*     */       else
/* 174 */       { setRoot(this.left); }
/*     */     
/*     */     } else {
/*     */       
/* 178 */       this.contents = this.right.contents;
/* 179 */       this.right = new Heap(this.right.extractMax());
/*     */     } 
/* 181 */     return tempCon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoot(Heap<T> newRoot) {
/* 189 */     if (newRoot != null) {
/* 190 */       this.contents = newRoot.contents;
/* 191 */       this.left = newRoot.left;
/* 192 */       this.right = newRoot.right;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 200 */     this.contents = null;
/* 201 */     this.right = null;
/* 202 */     this.left = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Queue<T> iterate() {
/* 211 */     Stack<Heap<T>> nodes = new Stack<>(this);
/* 212 */     Queue<T> depth = new Queue<>(this.contents);
/* 213 */     if (((Heap)nodes.peek()).getRight() != null) {
/* 214 */       depth.addQueue(((Heap<T>)nodes.peek()).getRight().iterate());
/*     */     }
/* 216 */     if (((Heap)nodes.peek()).getLeft() != null) {
/* 217 */       depth.addQueue(((Heap<T>)nodes.peek()).getLeft().iterate());
/*     */     }
/* 219 */     nodes.pop();
/* 220 */     return depth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T max() {
/* 228 */     return this.contents;
/*     */   }
/*     */   public boolean empty() {
/* 231 */     return (this.contents == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(T item) {
/* 239 */     int cmp = item.compareTo(this.contents);
/* 240 */     if (cmp == 0) return true; 
/* 241 */     if (cmp > 0) {
/* 242 */       return false;
/*     */     }
/* 244 */     if (this.left != null && 
/* 245 */       this.left.contains(item)) return true;
/*     */     
/* 247 */     if (this.right != null && 
/* 248 */       this.right.contains(item)) return true;
/*     */     
/* 250 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 259 */     int count = 0;
/*     */     
/* 261 */     if (this.contents != null) count++;
/*     */     
/* 263 */     if (this.right != null) {
/* 264 */       count += this.right.size();
/*     */     }
/*     */     
/* 267 */     if (this.left != null) {
/* 268 */       count += this.left.size();
/*     */     }
/*     */     
/* 271 */     return count;
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\Heap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */