/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteProblemas;

import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author thomas
 * problema https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=36
 */
public class TreeNPlusOneProblem {
    public static void main(String[] args) {
        
        //declaração de variáveis para input
        Scanner scanner = new Scanner(System.in);
        ArrayList<Par> inputs = new ArrayList<>();
        String inputString;
        String [] inputStringArray = new String [2];
        
        do{
            //digitação dos valores
            //System.out.println("digite 2 inteiros separados por espaço");
            inputString = scanner.nextLine();

            //separação da string e conversão para inteiro
            
            //otimizar com o metodo next()
            try{
                if (inputString.split(" ").length == 2) {
                    inputStringArray = inputString.split(" ");
                    Par par = new Par();
                    par.firstNumber = Integer.parseInt(inputStringArray[0]);
                    par.secondNumber = Integer.parseInt(inputStringArray[1]);
                    inputs.add(par);
                }else{
                    //System.out.println("você não digitou 2 números");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        
        }while(!inputString.equals("sair"));
        
        for (Par temp : inputs){

            //valores que são usados no for
            int minValue;
            int maxValue;   
            
            //colocar em ordem crescente
            if (temp.firstNumber < temp.secondNumber) {
                minValue = temp.firstNumber;
                maxValue = temp.secondNumber;
            }else{
                minValue = temp.secondNumber;
                maxValue = temp.firstNumber;
            }

            //tamanho da sequência
            int cycleLength = 0;
            int maxCycleLength = 0;

            for (int k = minValue; k <= maxValue; k++) {
                cycleLength = calc(k);
                if (cycleLength > maxCycleLength){
                    maxCycleLength = cycleLength;
                }
            }
            System.out.println(temp.firstNumber+" "+temp.secondNumber+" "+maxCycleLength);
        }
        
    }
    
    static int calc(int num){
        
        int length = 1;

        while (num > 1){
            if (num % 2 == 1) {
                num = 3*num + 1;
            }else{
                num = num / 2;
            }
            length++;
        }
        
        return length;
        
    }
  
}

class Par{
    int firstNumber;
    int secondNumber;
}  