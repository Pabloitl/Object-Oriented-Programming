package retoExam.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    final static public String TYPE_TEXT = ".txt",
            PATH_FILES = "src/retoExam/files/",
            SHADOW_FILE = "shadow",
            USERS_FILE = "users",
            SHADOW = PATH_FILES + SHADOW_FILE + TYPE_TEXT,
            USERS = PATH_FILES + USERS_FILE + TYPE_TEXT;
    
    public static String[] getLines(String file){
        return getLines(new File(file));
    }
    
    public static String[] getLines(File file){
        ArrayList<String> buff = new ArrayList();
        
        try{
            Scanner in = new Scanner(new FileInputStream(file));
            while(in.hasNext()) buff.add(in.next());
        }catch(FileNotFoundException e){
            e.getStackTrace();
        }
        
        String[] arr = new String[buff.size()];
        return buff.toArray(arr);
    }
    
    public static void exitOnNull(String...args){
        for(String eval: args)
            if(eval == null) System.exit(0);
    }
    
    public static void append(String file, String toAppend){
        append(new File(file), toAppend);
    }
    
    public static void append(File file, String toAppend){
        try{
            FileWriter fw = new FileWriter(file, true);
            fw.write("\n" + toAppend);
            fw.close();
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}
