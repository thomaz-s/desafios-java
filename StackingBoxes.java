/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PacoteProblemas;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author thomas
 * UNSOLVED
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=39
 */
public class StackingBoxes {
    public static void main(String[] args) {
        
        //inserção de valores
        Scanner scanner = new Scanner(System.in);
        ArrayList<String>inputs = new ArrayList<>();
        String input;
        do{
            input = scanner.nextLine();
            inputs.add(input);
        }while(!input.equals(""));
        inputs.remove("");
        
        //alocação dos valores em uma matriz
        for (int i = 0; i<inputs.size(); i++){
            String quant = inputs.get(i).split(" ")[0];
            String dimension = inputs.get(i).split(" ")[1];
            int [][] box = new int[Integer.parseInt(quant)][Integer.parseInt(dimension)+1];
            i = i+Integer.parseInt(quant);
        }
        
        //saída teste
        for (String output : inputs) {
            System.out.println(output);
        }
                
    }
    
    static void calcular(int [][] box){
        
    }
    
    static boolean testIfNests(int[] box1, int box2[]){
        
        return true;
    }
    
    static int[] rearrange(int [] box){
        
        for (int i = 0; i<box.length-1; i++){
            if (box.length == 2){
                int aux = box[box.length-1];
                box[box.length-1] = box[box.length-2];
                box[box.length-2] = aux;
            }else{
                int [] boxMinuxOne = new int[box.length-1];
                for (int j = 1; j < box.length; j++) {
                    boxMinuxOne[j-1] = box[j];
                }
                rearrange(boxMinuxOne);
            }
        }
        
        return box;
    }
}
