package Function;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class C_Function{
    private String function;
    private String name;
    private byte precedence;
    private String[] variables;

    static HashMap<String, C_Function> Funs = new HashMap<String, C_Function>(); // mapping of id/name to function
    static HashMap<String, Double> Constants = new HashMap<String, Double>(); // mapping of id/name to constants
    static HashMap<Byte, ArrayList<String>> ids = new HashMap<Byte, ArrayList<String>>(); // mapping of length to id/name of constants and functions

    private C_Stack stack = new C_Stack();

    public C_Function(builder b){
        this.function = b.function;
        this.name = b.name;
        this.precedence = b.precedence;
        this.variables = b.variables;

        Funs.put(b.name, this);

        if (ids.containsKey((byte) this.name.length())){
            ids.get((byte) this.name.length()).add(function);
        } else {
            ids.put((byte) this.name.length(), new ArrayList<String>());
            ids.get((byte) this.name.length()).add(function);
        }
        

        System.out.println("constructor");
        stack = RPN(b.function);
    }

    public static class builder{
        private String function;
        private String name;
        private byte precedence;
        private String[] variables;

        public builder function(String fun){
            this.function = fun;
            return this;
        }

        public builder name(String name){
            this.name = name;
            return this;
        }

        public builder precedence(byte prec){
            this.precedence = prec;
            return this;
        }

        public builder variables(String[] vars){
            this.variables = vars;
            return this;
        }

        public C_Function build(){
            return new C_Function(this);
        }
    }

    public class Token<T>{
        T value;
        E_Type type;
        public Token(T val, boolean fun){
            this.value = val ;
            if (val instanceof Byte || val instanceof Short|| val instanceof Integer || val instanceof Long || val instanceof Float || val instanceof Double){
                type = E_Type.NUMBER;
            } else if (fun){
                type = E_Type.FUNCTION;
            } else {
                type = E_Type.VARIABLE;
            }
        }

        private enum E_Type{
            NUMBER,
            FUNCTION,
            VARIABLE
        }
    }

    private enum Flag{
        nothing,
        number,
        nonnumber,
        variable,
        function
    }

    public C_Queue<Token> Tokenise(String function){
        String vars = function.split(":")[1];
        String[] variables = vars.split(",");
        C_Queue<Token> tokens = new C_Queue();
        byte[] ascii = ((String) function.split(":")[0]).getBytes();
        // split based off numbers, then functions, then variables.
        Flag f = Flag.nothing;
        String currentToken = "";

        for (Byte b:ascii){
            char current = (char) (int) b;
            if(f==Flag.nothing){
                //we were looking at a nothing
                currentToken = "";
            } else if (f==Flag.number){ // already looking for a number
                if (48<=b && b<=57 || b == 46){ // checking for 0-9 or .
                    currentToken+= Character.toString(current);
                } else{
                    tokens.add(Token<Double(Double.parseDouble(currentToken)));
                }
            } else if (f==Flag.nonnumber){ // we dont know if its a variable or a function
                if (48<=b && b<=57){ // if the next value is a number
                    // try to match current token to a variable or function
                    for (String var:variables){
                        if (var == currentToken) {
                            f = Flag.variable;
                        }
                    }
                    
                    if(!(f==Flag.variable)){
                        for (String id: C_Function.ids){
                            if ()
                        }
                    }
                
                } else{
                    //try to match to a variable or function and then proceed based off that
                }// if the current token matches to any predefined function or one of the given variable

            }

        }

        return tokens;
    }

    public C_Stack RPN(String function){
        return new C_Stack();
    }

    public double Evaluate(double[] params){
        return 0.0;
    }
    /*
    -- OLD CONSTRUCTORS --
    public C_Function(String function){
        if (ids.containsKey((byte) function.length())){
            ids.get((byte) function.length()).add(function);
        } else {
            ids.put((byte) function.length(), new ArrayList<String>());
            ids.get((byte) function.length()).add(function);
        }
        shuntingYard(function);
        Funs.put(function, this);
    }

    public C_Function(String name, String function){
        if (ids.containsKey((byte) name.length())){
            ids.get((byte) name.length()).add(name);
        } else {
            ids.put((byte) name.length(), new ArrayList<String>());           
            ids.get((byte) name.length()).add(name);
        }
        shuntingYard(function);
        Funs.put(name, this);
    }

    public C_Function(String function, int precedence){
        if (ids.containsKey((byte) function.length())){
            ids.get((byte) function.length()).add(function);
        } else {
            ids.put((byte) function.length(), new ArrayList<String>());
            ids.get((byte) function.length()).add(function);
        }
        shuntingYard(function);
        Funs.put(function, this);
    }

    public C_Function(String name, String function, int precedence){
        if (ids.containsKey((byte) name.length())){
            ids.get((byte) name.length()).add(name);
        } else {
            ids.put((byte) name.length(), new ArrayList<String>());
            ids.get((byte) name.length()).add(name);
        }
        shuntingYard(function);
        Funs.put(name, this);
    }
    */


    public C_Stack old_shuntingYard(String function){
        C_Stack st = new C_Stack();
        String[] tokens = new String[function.length()];
        int token = 0;
        byte[] temp = function.getBytes();
        Flag flag = Flag.nothing; // ill use an enum here to decide what the type it
        String tempToken = "";


        // tokenising the string
        for (int p1=0; p1<temp.length;p1++){
            System.out.println("char: " + (char) temp[p1]);
            //================================================================================
            // number check/filter
            //================================================================================
            if (48<=temp[p1] && temp[p1]<=57 && flag == Flag.nothing){ // if it is a digit and flag 
                // set flag to be a number
                flag=Flag.number;

                char c = (char) temp[p1];
                tempToken = Character.toString(c);

                if (p1 == temp.length-1){
                    tokens[token] = tempToken;
                    token++;
                }

            } else if((48<=temp[p1] && temp[p1]<=57 || temp[p1] == 46) && flag == Flag.number ){ // if we are looking at a number and we get a 0-9 or a .
                char c = (char) temp[p1];
                tempToken =tempToken+Character.toString(c);

            }else if (!(48<=temp[p1] && temp[p1]<=57 || temp[p1] == 46) && flag == Flag.number) { // if we are looking for a number but the character is not 0-9 or a . 
                // System.out.println("goofy ass");      
                switch (temp[p1]){ // check if the character is a space
                    case 32:
                        flag = Flag.space;
                }

                tokens[token] = tempToken;
                token++;
                tempToken = "";
                

                // TODO
                // add in opcheck when working
                //================================================================================
                // operator check/filter
                //================================================================================
                // lets take an array of arrays, each with an operator id, grouped by size.
                // then when a substring is found that matches an id we will consider that a token
                System.out.println("opcheck: " + (char) temp[p1]);


                Set lengths = ids.keySet();
                boolean match = false;
                int i = 0;
                            
                while (i<lengths.size() && !match){
                    //form substrings of length i
                    /* 
                    int sub = factorial(function.length()-p1)/(factorial(i)*factorial(function.length()-p1-i));
                    String[] substrings = new String[sub];
                    for (int j =p1;j<function.length()+1-i;j++){
                        substrings[j] = function.substring(j, j+i);
                    }
                    */

                    String substring = "";
                    int j = p1;
                    
                    while (j<function.length()-(p1+i) && !match){ // while iterator p1 and up is less than length-p1-i and no match
                        substring = function.substring(j, j+i);
                        System.out.println("substrings: " + j + " " + (j+i) + "  |  :" + substring);
                        
                        if (ids.containsKey((byte) i)){ // check if length id exists
                            for (String id : ids.get((byte) i)){
                               if (id == substring){
                                    System.out.println("matched");
                                    match = true;
                                    tokens[token] = id;
                                } 
                            }
                        }
                        j++;
                    }
                    i++;
                }

            }  else if (flag != Flag.number){ // was flag==-1
                //================================================================================
                // operator check/filter
                //================================================================================
                // lets take an array of arrays, each with an operator id, grouped by size.
                // then when a substring is found that matches an id we will consider that a token
                System.out.println("opcheck: " + (char) temp[p1]);


                Set lengths = ids.keySet();
                boolean match = false;
                int i = 0;
                            
                while (i<lengths.size() && !match){
                    //form substrings
                    /* 
                    int sub = factorial(function.length()-p1)/(factorial(i)*factorial(function.length()-p1-i));
                    String[] substrings = new String[sub];
                    for (int j =p1;j<function.length()+1-i;j++){
                        substrings[j] = function.substring(j, j+i);
                    }
                    */

                    String substring = "";
                    int j = p1;
                
                    while (j<function.length() && !match){
                        substring = function.substring(j, j+i);
                        System.out.println(substring);
                        
                        if (ids.containsKey((byte) i)){
                            for (String id : ids.get((byte) i)){
                               if (id == substring){
                                    match = true;
                                    tokens[token] = id;
                                } 
                            }
                        }
                        j++;
                    }
                    i++;
                }
            }
        }
        for(String t: tokens){
            System.out.print(t+" ");
        }

        System.out.println("done");
        System.out.println("================================================================================");
        return st;
    }

    public double evaluate(double[] params){
        return 0.0;
    }

}
