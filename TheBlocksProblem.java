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
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=37
 */
public class TheBlocksProblem {
    
    //variáveis usadas em todos os métodos
    static int length;
    static int aI, aJ, bI, bJ;
    static String [][] blocksWorld;
    
    public static void main(String[] args) {
        
        //declaração de variáveis de entrada
        Scanner scanner = new Scanner(System.in);
        String inputLine;
        ArrayList<String> input = new ArrayList<>();
        
        //ler a primeira linha (tamanho do vetor)
        inputLine = scanner.nextLine();
        length = Integer.parseInt(inputLine);
        //assignment do blocksWorld de acordo com a o tamanho escolhido pelo usuário
        blocksWorld = new String [length][length];
        //preeenchimento do blocks world
        for (int i = 0; i < length; i++) {
            blocksWorld[i][0] = i+"";
            for (int j = 1; j < length; j++) {
                blocksWorld[i][j] = "";
            }
        }
        
        //ler os comandos
        do{
            inputLine = scanner.nextLine();
            input.add(inputLine);
        }while(!inputLine.equals("quit"));
        
        //remover a última linha
        input.remove("quit");
        
        //executar os comandos
        for (String string : input) {
            //testar se o input foi preenchido corretamente
            //System.out.println(string.split(" ")[1] + " " + string.split(" ")[3]);
            
            //quebrar cada comando em comando 1, comando 2, variável 'a' e variável 'b'
            String command1 = string.split(" ")[0];
            String a = string.split(" ")[1];
            String command2 = string.split(" ")[2];
            String b = string.split(" ")[3];
            
            //testar se 'a' e 'b' são iguais ou estão empilhadas
            if (!(a.equals(b))&&!(findLine(a) == findLine(b))){
            
                //triagem dos comandos
                if (command1.equals("move")) {
                    if (command2.equals("onto")) {
                        moveOnto(a, b);
                    }else
                    if (command2.equals("over")) {
                        moveOver(a, b);
                    }
                }else
                if (command1.equals("pile")) {
                    if (command2.equals("onto")) {
                        pileOnto(a, b);
                    }else
                    if (command2.equals("over")) {
                        pileOver(a, b);
                    }
                }
                
            }
            
        }
        
        //saída
        for (int i = 0; i < length; i++) {
            System.out.print(i+":");
            for (int j = 0; j < length; j++) {
                if (!(blocksWorld[i][j].equals(""))){
                    System.out.print(" "+blocksWorld[i][j]);
                }else
                    break;
            }
            System.out.println("");
        }
    }
    
    static void moveOnto (String a, String b){
        
        //chamar os métodos findLine e findColumn apenas uma vez para 'a' e 'b'
        aI = findLine(a);
        aJ = findColumn(a);
        bI = findLine(b);
        bJ = findColumn(b);
        
        //voltar para posição inicial todos os blocos acima de 'a'
        for (int j = aJ+1; j < length; j++) {
            if (!(blocksWorld[aI][j].equals(""))) {
                initialPosition(blocksWorld[aI][j]);
                blocksWorld[aI][j] = "";
            }else{
                break;
            }
        }
        
        //voltar para posição inicial todos os blocos acima de 'b'
        for (int j = bJ+1; j < length; j++) {
            if (!(blocksWorld[bI][j].equals(""))) {
                initialPosition(blocksWorld[bI][j]);
                blocksWorld[bI][j] = "";
            }else{
                break;
            }
        }
        
        
        //colocar 'a' acima de 'b'
        blocksWorld[bI][bJ+1] = blocksWorld[aI][aJ];
        blocksWorld[aI][aJ] = "";
        
    }
    
    static void moveOver(String a, String b){
        
        //chamar os métodos findLine e findColumn apenas uma vez para 'a' e 'b'
        aI = findLine(a);
        aJ = findColumn(a);
        bI = findLine(b);
        bJ = findColumn(b);
        
        //colocar voltar para posição inicial todos os blocos acima de 'a'
        for (int j = aJ+1; j < length; j++) {
            if (!(blocksWorld[aI][j].equals(""))) {
                initialPosition(blocksWorld[aI][j]);
                blocksWorld[aI][j] = "";
            }else{
                break;
            }
        }
        
        //colocar 'a' acima de todos os blocos empilhados sobre 'b'
        for (int j = bJ+1; j < length; j++) {
            if (blocksWorld[bI][j].equals("")) {
                blocksWorld[bI][j] = blocksWorld[aI][aJ];
                blocksWorld[aI][aJ] = "";
                break;
            }
        }
        
    }
    
    static void pileOnto (String a, String b){
        
        //chamar os métodos findLine e findColumn apenas uma vez para 'a' e 'b'
        aI = findLine(a);
        aJ = findColumn(a);
        bI = findLine(b);
        bJ = findColumn(b);
        
        //voltar para posição inicial todos os blocos acima de 'b'
        for (int j = bJ+1; j < length; j++) {
            if (!(blocksWorld[bI][j].equals(""))) {
                initialPosition(blocksWorld[bI][j]);
                blocksWorld[bI][j] = "";
            }else{
                break;
            }
        }
        
        //move 'a' e os blocos acima dele para cima de 'b'
        int posicaoRelativa = 0;
        for (int j = aJ; j < length; j++) {
            if (!(blocksWorld[aI][j].equals(""))) {
                blocksWorld[bI][bJ+1+posicaoRelativa] = blocksWorld[aI][j];
                blocksWorld[aI][j] = "";
                posicaoRelativa++;
            }else{
                break;
            }
        }
        
    }
    
    static void pileOver (String a, String b){
        
        //chamar os métodos findLine e findColumn apenas uma vez para 'a' e 'b'
        aI = findLine(a);
        aJ = findColumn(a);
        bI = findLine(b);
        bJ = findColumn(b);
        
        //colocar 'a', e todos os blocos empilhados sobre ele, acima de todos os blocos empilhados sobre 'b'
        for (int j1 = bJ+1; j1 < length; j1++) {
            if (blocksWorld[bI][j1].equals("")) {
                int posicaoRelativa = 0;
                for (int j2 = aJ; j2 < length; j2++) {
                    if (!(blocksWorld[aI][j2].equals(""))) {
                        blocksWorld[bI][j1+posicaoRelativa] = blocksWorld[aI][j2];
                        blocksWorld[aI][j2] = "";
                        posicaoRelativa++;
                    }else{
                        break;
                    }
                }
                break;
            }
        }
        
    }
    
    //encontrar a linha do bloco desejado
    static int findLine(String block){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (blocksWorld[i][j].equals(block)){
                    return i;
                }
            }
        }
        return 0;
    }
    
    //encontrar a coluna do bloco desejado
    static int findColumn(String block){
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (blocksWorld[i][j].equals(block)){
                    return j;
                }
            }
        }
        return 0;
    }
    
    /*
    *voltar um bloco para sua posição inicial. Não zera a posição atual do bloco
    *porque isso iria necessitar chamar o findLine e findColumn ou mandar eles
    *como argumento
    */
    static void initialPosition (String block){
        blocksWorld[Integer.parseInt(block)][0] = block+"";
    }
}