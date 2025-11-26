/*     */ package bst;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class Queue<T>
/*     */   implements Iterable<T>
/*     */ {
/*     */   private T contents;
/*     */   private Queue<T> next;
/*     */   
/*     */   public Queue() {
/*  25 */     this.contents = null;
/*  26 */     this.next = null;
/*     */   }
/*     */   
/*     */   public Queue(T object) {
/*  30 */     this.contents = object;
/*  31 */     this.next = null;
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/*  35 */     Iterator<T> it = new Iterator<T>()
/*     */       {
/*     */         public boolean hasNext()
/*     */         {
/*  39 */           return (Queue.this.contents != null);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public T next() {
/*  49 */           if (Queue.this.contents == null) throw new NoSuchElementException("No more elements!"); 
/*  50 */           T popped = Queue.this.contents;
/*  51 */           if (Queue.this.next == null) {
/*  52 */             Queue.this.contents = null;
/*     */           } else {
/*  54 */             Queue.this.contents = Queue.this.next.contents;
/*  55 */             Queue.this.next = Queue.this.next.next;
/*     */           } 
/*  57 */           return popped;
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/*  62 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/*  65 */     return it;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  75 */     int nodes = 0;
/*  76 */     Queue<T> currentNode = this;
/*     */     
/*  78 */     while (currentNode != null && currentNode.peek() != null) {
/*     */       
/*  80 */       nodes++;
/*     */       
/*  82 */       currentNode = currentNode.getNext();
/*     */     } 
/*  84 */     return nodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  93 */     return (this.contents == null && this.next == null);
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
/*     */   public boolean add(T o) {
/* 108 */     if (this.contents == null && this.next == null) {
/* 109 */       this.contents = o;
/* 110 */       return true;
/*     */     } 
/*     */     
/* 113 */     Queue<T> nextNode = this;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     while (nextNode.getNext() != null) {
/* 120 */       nextNode = nextNode.getNext();
/*     */     }
/* 122 */     nextNode.setNext(new Queue(o));
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T peek() {
/* 133 */     return this.contents;
/*     */   }
/*     */   
/*     */   public Queue<T> getNext() {
/* 137 */     return this.next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNext(Queue<T> next) {
/* 147 */     this.next = next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addQueue(Queue<T> other) {
/* 156 */     while (other.contents != null)
/* 157 */       add(other.iterator().next()); 
/* 158 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\Queue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */