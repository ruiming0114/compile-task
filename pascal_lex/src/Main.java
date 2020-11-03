import java.io.*;

public class Main {
    public static char current;
    public static String token = "";
    public static PushbackReader reader;

    public static void Cat(char ch){
        token = token.concat(String.valueOf(ch));
    }

    public static int getChar() throws IOException {
        int now = reader.read();
        if (now != -1){
            current = (char)now;
        }
        return now;
    }

    public static int getNBC() throws IOException {
        int now = getChar();
        while(Character.isWhitespace(now)){
            now = getChar();
        }
        return now;
    }

    public static void clearToken(){
        token = "";
    }

    public static void unGetChar() throws IOException {
        reader.unread(current);
    }

    public static void main(String[] args) throws IOException {
        reader = new PushbackReader(new FileReader(new File(args[0])));
        all:
        while (getNBC() != -1){
            if (Utils.isLetter(current)){
                Cat(current);
                while (getChar() != -1){
                    if (Utils.isLetter(current) || Utils.isDigit(current)){
                        Cat(current);
                    }
                    else {
                        unGetChar();
                        break;
                    }
                }
                if (Utils.isReserve(token)){
                    System.out.println(Utils.toReserve(token));
                }
                else {
                    System.out.println("Ident("+token+")");
                }
                clearToken();
            }
            else if (Utils.isDigit(current)){
                Cat(current);
                while (getChar() != -1){
                    if (Utils.isDigit(current)){
                        Cat(current);
                    }
                    else {
                        unGetChar();
                        break;
                    }
                }
                System.out.println("Int(" + Utils.Atoi(token) +")");
                clearToken();
            }
            else {
                switch (current){
                    case '+':
                    case '*':
                    case ',':
                    case '(':
                    case ')':
                        Cat(current);
                        System.out.println(Utils.toCalculator(token));
                        clearToken();
                        break;
                    case ':':
                        Cat(current);
                        if(getChar() != -1){
                            if (current == '='){
                                Cat(current);
                            }
                            else {
                                unGetChar();
                            }
                            System.out.println(Utils.toCalculator(token));
                            clearToken();
                            break;
                        }
                        else {
                            System.out.println(Utils.toCalculator(token));
                            break all;
                        }
                    default:
                        System.out.println("Unknown");
                        break all;
                }
            }
        }
    }
}
