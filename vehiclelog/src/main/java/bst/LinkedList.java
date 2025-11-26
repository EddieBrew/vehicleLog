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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LinkedList<T>
/*     */ {
/*     */   private T contents;
/*     */   private LinkedList<T> next;
/*     */   
/*     */   public LinkedList(T object) {
/*  24 */     this.contents = object;
/*  25 */     this.next = null;
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
/*     */ 
/*     */   
/*     */   public boolean add(T o) {
/*  44 */     if (getContents() == null && getNext() == null) {
/*  45 */       setContents(o);
/*  46 */       return true;
/*     */     } 
/*     */     
/*  49 */     LinkedList<T> nextNode = this;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     while (nextNode.getNext() != null) {
/*  56 */       nextNode = nextNode.getNext();
/*     */     }
/*  58 */     nextNode.setNext(new LinkedList(o));
/*  59 */     return true;
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
/*     */   public boolean add(int index, T o) {
/*  74 */     if (index < 0) {
/*  75 */       throw new IndexOutOfBoundsException("Cannot have negative indices!");
/*     */     }
/*     */     
/*  78 */     LinkedList<T> node = this;
/*     */     
/*  80 */     if (index == 0) {
/*     */ 
/*     */       
/*  83 */       LinkedList<T> nextLinkedList = new LinkedList(getContents());
/*  84 */       nextLinkedList.setNext(getNext());
/*  85 */       setContents(o);
/*  86 */       setNext(nextLinkedList);
/*  87 */       return true;
/*     */     } 
/*     */     
/*  90 */     for (int traversed = 1; traversed < index; traversed++) {
/*     */       
/*  92 */       if (node.getNext() == null) {
/*  93 */         throw new IndexOutOfBoundsException("The given index is outside the bounds of the LinkedList.");
/*     */       }
/*  95 */       node = node.getNext();
/*     */     } 
/*  97 */     LinkedList<T> nodeToBeInserted = new LinkedList(o);
/*  98 */     nodeToBeInserted.setNext(node.getNext());
/*  99 */     node.setNext(nodeToBeInserted);
/* 100 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T get(int index) throws IllegalArgumentException {
/* 111 */     if (index < 0) throw new IndexOutOfBoundsException("No negative indices!"); 
/* 112 */     LinkedList<T> requestedNode = this;
/* 113 */     for (int traversed = 0; traversed < index; traversed++) {
/* 114 */       if (requestedNode.getNext() == null) {
/* 115 */         throw new IndexOutOfBoundsException("The given index is outside the bounds of the LinkedList.");
/*     */       }
/* 117 */       requestedNode = requestedNode.getNext();
/*     */     } 
/* 119 */     return requestedNode.getContents();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(T object) {
/* 130 */     return (indexOf(object) != -1);
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
/*     */   public int indexOf(T object) {
/* 143 */     int nodeIndex = 0;
/* 144 */     LinkedList<T> currentNode = this;
/* 145 */     while (!nullSafeCheckEquivalence(object, currentNode.getContents())) {
/* 146 */       if (currentNode.getNext() == null) {
/* 147 */         return -1;
/*     */       }
/* 149 */       currentNode = currentNode.getNext();
/* 150 */       nodeIndex++;
/*     */     } 
/* 152 */     return nodeIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean nullSafeCheckEquivalence(Object object1, Object object2) {
/* 163 */     if (object1 == null) {
/* 164 */       return (object2 == null);
/*     */     }
/* 166 */     return object1.equals(object2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 173 */     this.next = null;
/* 174 */     this.contents = null;
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
/*     */   public T remove(int index) {
/* 187 */     if (index < 0) throw new IndexOutOfBoundsException("No negative indices!"); 
/* 188 */     if (index == 0) {
/*     */       
/* 190 */       T removed = getContents();
/* 191 */       if (getNext() != null) {
/* 192 */         setContents(getNext().getContents());
/* 193 */         setNext(getNext().getNext());
/*     */       } else {
/* 195 */         clear();
/*     */       } 
/* 197 */       return removed;
/*     */     } 
/*     */     
/* 200 */     LinkedList<T> iteratingNode = this;
/*     */     
/* 202 */     LinkedList<T> previousNode = null;
/*     */     
/* 204 */     int iteratingNodeIndex = 0;
/* 205 */     while (iteratingNodeIndex < index) {
/* 206 */       if (iteratingNode.getNext() == null) {
/* 207 */         throw new IndexOutOfBoundsException("No such index!");
/*     */       }
/*     */       
/* 210 */       previousNode = iteratingNode;
/* 211 */       iteratingNode = iteratingNode.getNext();
/* 212 */       iteratingNodeIndex++;
/*     */     } 
/* 214 */     LinkedList<T> nodeToReinsert = iteratingNode.getNext();
/* 215 */     previousNode.setNext(nodeToReinsert);
/* 216 */     return iteratingNode.getContents();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 224 */     String str = "[";
/* 225 */     LinkedList<T> currentNode = this;
/* 226 */     for (int transversed = 0; transversed < size(); transversed++) {
/* 227 */       if (currentNode.getContents() != null) {
/* 228 */         str = String.valueOf(str) + currentNode.getContents().toString();
/*     */       } else {
/* 230 */         str = String.valueOf(str) + "<empty>";
/*     */       } 
/* 232 */       if (transversed != size() - 1) str = String.valueOf(str) + ", "; 
/* 233 */       currentNode = currentNode.getNext();
/*     */     } 
/* 235 */     str = String.valueOf(str) + "]";
/* 236 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 245 */     int nodes = 1;
/* 246 */     LinkedList<T> currentNode = this;
/* 247 */     while (currentNode.getNext() != null) {
/* 248 */       nodes++;
/* 249 */       currentNode = currentNode.getNext();
/*     */     } 
/* 251 */     return nodes;
/*     */   }
/*     */   
/*     */   public T getContents() {
/* 255 */     return this.contents;
/*     */   }
/*     */   
/*     */   private void setContents(T contents) {
/* 259 */     this.contents = contents;
/*     */   }
/*     */   
/*     */   public LinkedList<T> getNext() {
/* 263 */     return this.next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNext(LinkedList<T> next) {
/* 273 */     this.next = next;
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\LinkedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */