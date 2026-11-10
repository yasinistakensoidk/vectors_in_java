package Function;

import java.util.ArrayList;

public class C_Stack<String> extends ArrayList<String> {
    protected ArrayList stack;
    
    public C_Stack(){
        stack = new ArrayList<String>();
    }

    protected String pop(){
        String temp = this.get(this.size());
        this.remove(this.size());
        return temp;
    }
    
    protected void Push(String term){
        this.add(term);
    }

    protected String peek(){
        String temp = this.get(this.size());
        return temp;
    }
}
