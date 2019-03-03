package retoExam.history;

import static retoExam.Screen.Screen.*;
import retoExam.entities.Parent;

public class History {
    
    public void prompt(Parent parent){
        showHistory(chooseChild(parent));
    }
}
