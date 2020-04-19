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
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=38
 */
public class EcologicalBinPacking {
    
    //vetor caixas, vai ser usado em vários métodos
    static int [][] caixas;
    
    public static void main(String[] args) {
        
        //input
        ArrayList <String> input = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String string;
        
        do{
            string = scanner.nextLine();
            input.add(string);
        }while(!string.isEmpty());
        
        input.remove("");
        
        caixas = new int [input.size()][9];
        
        //preenchimento do array
        for (int i = 0; i < input.size(); i++){
            caixas[i][0] = Integer.parseInt(input.get(i).split("\\s+")[0]);
            caixas[i][1] = Integer.parseInt(input.get(i).split("\\s+")[1]);
            caixas[i][2] = Integer.parseInt(input.get(i).split("\\s+")[2]);
            caixas[i][3] = Integer.parseInt(input.get(i).split("\\s+")[3]);
            caixas[i][4] = Integer.parseInt(input.get(i).split("\\s+")[4]);
            caixas[i][5] = Integer.parseInt(input.get(i).split("\\s+")[5]);
            caixas[i][6] = Integer.parseInt(input.get(i).split("\\s+")[6]);
            caixas[i][7] = Integer.parseInt(input.get(i).split("\\s+")[7]);
            caixas[i][8] = Integer.parseInt(input.get(i).split("\\s+")[8]);
        }
        
        //saída
        for (int i = 0; i < caixas.length; i++) {
            System.out.println(movimentar(i));
        }
        
    }
    
    static String movimentar (int linha){
        
        int [] movimentos = new int [6];
        
        int bcg = 0;
        //marrom > 1ª
        bcg += moveTo1(linha, 0);
        //transparentes > 2ª
        bcg += moveTo2(linha, 5);
        //verde > 3ª
        bcg += moveTo3(linha, 7);
        movimentos[0] = bcg;
        
        int bgc = 0;
        //marrom > 1ª
        bgc += moveTo1(linha, 0);
        //verde > 2ª
        bgc += moveTo2(linha, 4);
        //transparentes > 3ª
        bgc += moveTo3(linha, 8);
        movimentos[1] = bgc;
        
        int cbg = 0;
        //transparentes > 1ª
        cbg += moveTo1(linha, 2);
        //marrom > 2ª
        cbg += moveTo2(linha, 3);
        //verde > 3ª
        cbg += moveTo3(linha, 7);
        movimentos[2] = cbg;
        
        int cgb = 0;
        //transparentes > 1ª
        cgb += moveTo1(linha, 2);
        //verde > 2ª
        cgb += moveTo2(linha, 4);
        //marron > 3ª
        cgb += moveTo3(linha, 6);
        movimentos[3] = cgb;
        
        int gbc = 0;
        //verde > 1ª
        gbc += moveTo1(linha, 1);
        //marrom > 2ª
        gbc += moveTo2(linha, 3);
        //transparentes > 3ª
        gbc += moveTo3(linha, 8);
        movimentos[4] = gbc;
        
        int gcb = 0;
        //verde > 1ª
        gcb += moveTo1(linha, 1);
        //transparentes > 2ª
        gcb += moveTo2(linha, 5);
        //marrom > 3ª
        gcb += moveTo3(linha, 6);
        movimentos[5] = gcb;
        
        //encontrar o maior valor, para depois encontrar o menor sem usar Integer.MAX_VALUE
        int maior = 0;
        for (int i = 0; i < 6 ; i++) {
            if (movimentos[i] > maior) {
                maior = movimentos[i];
            }
        }
        
        //encontrar o menor valor
        int menor = maior;
        int ordem = 6;
        for (int i = 5; i >= 0; i--) {
            if (movimentos[i] <= menor) {
                menor = movimentos[i];
                ordem = i;
            }
        }
        
        //retornar a ordem das caixas junto com a menor quantidade de movimentos
        switch (ordem){
            case 0: return "bcg ".toUpperCase() + menor;
            case 1: return "bgc ".toUpperCase() + menor;
            case 2: return "cbg ".toUpperCase() + menor;
            case 3: return "cgb ".toUpperCase() + menor;
            case 4: return "gbc ".toUpperCase() + menor;
            case 5: return "gcb ".toUpperCase() + menor;
        }
        
        return "";
    }
    
    static int moveTo1(int linha, int cor){
        return caixas[linha][cor+3] + caixas[linha][cor+6];
    }
    
    static int moveTo2(int linha, int cor){
        return caixas[linha][cor-3] + caixas[linha][cor+3];    
    }
    
    static int moveTo3(int linha, int cor){
        return caixas[linha][cor-6] + caixas[linha][cor-3];
    }
}