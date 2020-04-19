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
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=41
 */
public class SkylineProblem {
    public static void main(String[] args) {
                     
        //inserção de valores
        Scanner scanner = new Scanner(System.in);
        ArrayList<Ponto>pontos = new ArrayList<>();
        String input;
        Ponto ponto = new Ponto();
        input = scanner.nextLine();
        while(!input.equals("")){
            ponto.setX(Integer.parseInt(input.split("\\s+")[0]));
            ponto.setY(Integer.parseInt(input.split("\\s+")[1]));
            pontos.add(ponto);
            ponto.setX(Integer.parseInt(input.split("\\s+")[0]));
            pontos.add(ponto);
            input = scanner.nextLine();
        }
        
        for (Ponto p : pontos){
            System.out.println(p.getX()+" "+p.getY());
        }
    }
}

class Ponto{
    private int x;
    private int y;
    
    void setX(int x){
        this.x = x;
    }
    
    void setY(int y){
        this.y = y;
    }
    
    int getX(){
        return x;
    }
    
    int getY(){
        return y;
    }
}