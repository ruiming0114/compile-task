package tokenizer;

import java.util.ArrayList;
import java.util.Scanner;


public class TokenIterator {

    private Scanner scanner;
    private ArrayList<Character> buffer = new ArrayList<>();
    private int pointer = 0;

    public TokenIterator(Scanner scanner){
        this.scanner = scanner;
        this.readAll();
    }

    public void readAll(){
        while (scanner.hasNext()){
            String res = scanner.nextLine();
            char[] temp = (res+'\n').toCharArray();
            for (char c : temp){
                buffer.add(c);
            }
        }
    }

    public char peekChar(){
        if (isEOF()){
            return 0;
        }
        return buffer.get(pointer);
    }

    public char nextChar(){
        if (isEOF()){
            return 0;
        }
        pointer++;
        return buffer.get(pointer-1);
    }

    public void unread(){
        pointer--;
    }

    public boolean isEOF(){
        return pointer == buffer.size();
    }
}
