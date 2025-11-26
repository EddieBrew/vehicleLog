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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Stack<T>
/*     */   implements Iterable<T>
/*     */ {
/*     */   private T contents;
/*     */   private Stack<T> next;
/*     */   
/*     */   public Stack() {
/*  28 */     this.contents = null;
/*  29 */     this.next = null;
/*     */   }
/*     */   
/*     */   public Stack(T item) {
/*  33 */     this.contents = item;
/*  34 */     this.next = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean empty() {
/*  43 */     return (this.contents == null && this.next == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T peek() {
/*  52 */     return this.contents;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T pop() {
/*  61 */     T popped = this.contents;
/*  62 */     if (this.next == null) {
/*  63 */       this.contents = null;
/*     */     } else {
/*  65 */       this.contents = this.next.contents;
/*  66 */       this.next = this.next.next;
/*     */     } 
/*  68 */     return popped;
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
/*     */   public void push(T item) {
/*  82 */     if (this.contents == null && this.next == null) {
/*  83 */       this.contents = item;
/*     */       
/*     */       return;
/*     */     } 
/*  87 */     Stack<T> nextItem = new Stack();
/*  88 */     nextItem.contents = this.contents;
/*  89 */     nextItem.next = this.next;
/*  90 */     this.next = nextItem;
/*  91 */     this.contents = item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int search(T o) {
/* 102 */     Stack<T> i = this;
/* 103 */     int index = 1;
/* 104 */     while (!o.equals(i.contents)) {
/* 105 */       if (i.next == null) {
/* 106 */         return -1;
/*     */       }
/*     */       
/* 109 */       index++;
/* 110 */       i = i.next;
/*     */     } 
/* 112 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 121 */     int elements = 1;
/*     */     
/* 123 */     Stack<T> traverser = this;
/* 124 */     while (traverser.next != null) {
/* 125 */       traverser = traverser.next;
/* 126 */       elements++;
/*     */     } 
/* 128 */     return elements;
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
/*     */   public Object[] toArray() {
/* 145 */     Object[] array = new Object[size()];
/* 146 */     Stack<T> traversed = this;
/* 147 */     for (int i = 0; i < array.length; i++) {
/* 148 */       array[i] = traversed.contents;
/* 149 */       traversed = traversed.next;
/*     */     } 
/* 151 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 160 */     String serialized = "[";
/* 161 */     Stack<T> element = this;
/* 162 */     while (element != null) {
/* 163 */       if (element.contents == null) {
/* 164 */         serialized = String.valueOf(serialized) + "<empty>, ";
/*     */       } else {
/* 166 */         serialized = String.valueOf(serialized) + element.contents.toString() + ", ";
/*     */       } 
/* 168 */       element = element.next;
/*     */     } 
/* 170 */     serialized = serialized.substring(0, serialized.length() - 2);
/* 171 */     serialized = String.valueOf(serialized) + ']';
/* 172 */     return serialized;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<T> iterator() {
/* 177 */     Iterator<T> it = new Iterator<T>()
/*     */       {
/*     */         public boolean hasNext()
/*     */         {
/* 181 */           return (Stack.this.next.contents != null);
/*     */         }
/*     */ 
/*     */         
/*     */         public T next() {
/* 186 */           if (Stack.this.next.contents == null) throw new NoSuchElementException("No more elements!"); 
/* 187 */           return Stack.this.next.pop();
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/* 192 */           throw new UnsupportedOperationException();
/*     */         }
/*     */       };
/* 195 */     return it;
/*     */   }
/*     */ }


/* Location:              C:\Users\rober\OneDrive\Documents\MyApplications\VenicleLog\vehiclelog.jar!\bst\Stack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */