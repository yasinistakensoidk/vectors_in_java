package Function;

import java.util.ArrayList;

public class C_Queue<T> extends ArrayList<T> {
    
    public C_Queue(){
        super();
    }

    protected T pop(){
        try{
            T temp = this.get(0);
            this.remove(0);
            return temp;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }
    
    protected void append(T term){
        this.add(term);
    }
}
