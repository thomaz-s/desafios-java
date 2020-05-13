package Desafios;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thomas
 * UNSOLVED
 * Link to the problem: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=41
 */
public class SkylineProblem {
    
    private static ArrayList<Dot>dots = new ArrayList<>();
    private static ArrayList<String>inputs = new ArrayList<>();
    private static int first;
    private static int last;
    
    public static void main(String[] args) {
        
        readInputs();
        createLines();
        removeDuplicate();
        showNotDuplicates();
        
    }

    private static void readInputs() {
        Scanner scanner = new Scanner(System.in);
        String input;
        do{
            input = scanner.nextLine();
            inputs.add(input);
        }while(!input.equals(""));
        inputs.remove("");
    }

    private static void createLines(){
        first = Integer.parseInt(inputs.get(0).split("\\s+")[0]);
        last = Integer.parseInt(inputs.get(inputs.size()-1).split("\\s+")[2]);
        
        for (String input : inputs){
            int l = Integer.parseInt(input.split("\\s+")[0]);
            int h = Integer.parseInt(input.split("\\s+")[1]);
            int r = Integer.parseInt(input.split("\\s+")[2]);
            
            if (l < first) {
                first = l;
            }
            
            if (r > last) {
                last = r;
            }
            
            Dot dot;
           
            //criar paredes 
            for (int y = 0; y <= h; y++){
                //esquerda
                dot = new Dot();
                dot.setPosition(l, y);
                dots.add(dot);

                //direita
                dot = new Dot();
                dot.setPosition(r, y);
                dots.add(dot);
            }

            //criar teto
            for (int x = l; x <= r; x++){
                dot = new Dot();
                dot.setPosition(x, h);
                dots.add(dot);
            }
        }
    }

    private static void removeDuplicate() {
        for (int i = 0; i < dots.size()-1; i++){
            for (int j = i+1; j < dots.size(); j++){
                if (dots.get(i).getPosition().equals(dots.get(j).getPosition())){
                    dots.get(i).duplicate = true;
                }
            }
        }
    }

    private static void showNotDuplicates() {
        for (int i = 0; i < dots.size(); i++){
            if (!dots.get(i).duplicate){
                System.out.println(i + ": " + dots.get(i).getPosition());
            }
        }
    }
    
}

class Dot{
    private int x;
    private int y;
    boolean duplicate = false;
    
    void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    String getPosition(){
        return this.x + ", " + this.y;
    }
    
    String nextUp(){
        return this.x + ", " + (this.y+1);
    }
    
    String nextDown(){
        return this.x + ", " + (this.y-1);
    }
    
    String nextLeft(){
        return (this.x-1) + ", " + (this.y);
    }
    
    String nextRight(){
        return (this.x+1) + ", " + (this.y);
    }
}