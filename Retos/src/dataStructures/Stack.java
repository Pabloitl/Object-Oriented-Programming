package dataStructures;

public class Stack {
   String[] arr;
   int tail;
   
   public Stack(int length){
       arr = new String[length];
   }
   
   //O(1)
   public boolean isEmpty(){
       return tail == 0;
   }
   
   //O(1)
   public boolean isFull(){
       return tail == arr.length;
   }
   
   //O(1)
   public void push(String val) throws Exception{
       if(isFull()) throw new Exception("Its full");
       arr[tail++] = val;
   }
   
   //O(1)
   public String pop() throws Exception{
       if(isEmpty()) throw new Exception("Its empty");
       String toReturn = arr[--tail];
       arr[tail] = null;
       
       return toReturn;
   }
}
