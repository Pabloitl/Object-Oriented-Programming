package dataStructures;

public class Queue {
    private String[] arr;
    private int head, length;

    public Queue(int length){
        arr = new String[length];
        head = length-1;
    }

    //O(1)
    public boolean isFull(){
        return arr[(head+1) % arr.length] != null;
    }

    //O(1)
    public boolean isEmpty(){
        return arr[head] == null;
    }

    //O(1)
    public void enQueue(String val) throws Exception{
        if(isFull()) throw new Exception("Its full");

        arr[Math.abs((head-length))%arr.length] = val;
        length++;
    }

    //O(1)
    public String deQueue() throws Exception{
        if(isEmpty()) throw new Exception("Its empty");

        String toReturn = arr[head];
        arr[head] = null;
        head = Math.abs(head-1)%arr.length;
        length--;
        return toReturn;
    }
}
