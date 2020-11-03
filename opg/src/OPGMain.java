import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class OPGMain {
    public static Map<Character,Integer> location =  new HashMap<>();
    public static int[][] matrix = {
            {1,-1,-1,1,-1,1},
            {1,1,-1,1,-1,1},
            {-1,-1,-1,0,-1,1},
            {1,1,-2,1,-2,1},
            {1,1,-2,1,-2,1},
            {-1,-1,-1,-1,-1,-3}
    };
    public static Stack<Character> stack = new Stack<>();
    public static char[] input = null;
    public static int index = 0;

    static {
        location.put('+',0);
        location.put('*',1);
        location.put('(',2);
        location.put(')',3);
        location.put('i',4);
        location.put('#',5);
    }

    public static char getNext(){
        if (index >= input.length){
            return '#';
        }
        index++;
        return input[index-1];
    }

    public static int compare(char current){
        if (location.containsKey(current)){
            return matrix[location.get(getStakeTop())][location.get(current)];
        }
        return -2;
    }

    public static char getStakeTop(){
        if (stack.peek() == 'N'){
            char temp = stack.pop();
            char res = stack.peek();
            stack.push(temp);
            return res;
        }
        return stack.peek();
    }

    public static void expect(char ch) throws Exception {
        if (stack.pop() != ch){
            throw new Exception();
        }
    }

    public static boolean doPlus(){
        try{
            expect('N');
            expect('+');
            expect('N');
            stack.push('N');
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean doMulti(){
        try{
            expect('N');
            expect('*');
            expect('N');
            stack.push('N');
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean doI(){
        try {
            expect('i');
            stack.push('N');
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean doParentheses(){
        try {
            expect(')');
            expect('N');
            expect('(');
            stack.push('N');
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(args[0]));
        input = scanner.nextLine().toCharArray();
        stack.push('#');
        all:
        while (index <= input.length){
            char current = getNext();
            int result = compare(current);
            if (result == -1 || result == 0){
                stack.push(current);
                System.out.println("I"+current);
            }
            else if (result == 1){
                while (compare(current) == 1){
                    switch (getStakeTop()){
                        case '+':
                            if (!doPlus()){
                                System.out.println("RE");
                                break all;
                            }
                            System.out.println("R");
                            break;
                        case '*':
                            if (!doMulti()){
                                System.out.println("RE");
                                break all;
                            }
                            System.out.println("R");
                            break;
                        case 'i':
                            if (!doI()){
                                System.out.println("RE");
                                break all;
                            }
                            System.out.println("R");
                            break;
                        case ')':
                            if (!doParentheses()){
                                System.out.println("RE");
                                break all;
                            }
                            System.out.println("R");
                            break;
                        default:
                            System.out.println("RE");
                            break all;
                    }
                }
                if (compare(current) == -1 || compare(current) == 0){
                    stack.push(current);
                    System.out.println("I"+current);
                }
                else if (compare(current) == -2){
                    System.out.println("E");
                    break;
                }
                else {
                    if (!(stack.size() == 2 && stack.pop() == 'N' && stack.pop() == '#')){
                        System.out.println("RE");
                    }
                    break;
                }
            }
            else if (result == -2){
                System.out.println("E");
                break;
            }
            else {
                if (!(stack.size() == 2 && stack.pop() == 'N' && stack.pop() == '#')){
                    System.out.println("RE");
                }
                break;
            }
        }
    }
}
