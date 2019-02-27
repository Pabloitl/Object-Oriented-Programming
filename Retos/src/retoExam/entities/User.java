package retoExam.entities;

import retoExam.Signup.SignUp;
import retoExam.files.FileManager;

public class User {
    
    String name;
    String password;
    String type;
    
    public User(String name){
        this.name = name;
    }
    
    public User(String name, String password){
        this(name);
        this.password = password;
    }
    
    public User(String name, String password, String type){
        this(name, password);
        this.type = type;
    }
    
    
    public static String format(String...args){
        String buffer = "";

        for (int i = 0; i < args.length; i++) {
            buffer += args[i];
            if(i < args.length - 1) buffer += ":";
        }
        
        return buffer;
    }
    
    //Setters and Getters=====================================================
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getType(){
        String[] buffer = FileManager.getLines(FileManager.SHADOW);
        for(String line: buffer){
            String[] bar = line.split(FileManager.SEPARATOR);
            if(bar[SignUp.NAME_FIELD].equals(name))
                return bar[SignUp.TYPE_FIELD];
        }
        return "";
    }
}
