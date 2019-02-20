package retoExam;

public class Test {
    public static void main(String[] args){
        Test.run();
    }
    
    private static void run(){
        String[] session = new Shop().answer();
        
        //Show Window depending on user
        if(session[Shop.TYPE_FIELD].equals("Student"))
            new Shop().showStudent();
        else new Shop().showParent();
    }
}
