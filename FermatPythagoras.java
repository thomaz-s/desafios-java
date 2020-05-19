/**
 * @author thomas
 * problem: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=42
 */
import java.util.Scanner;

public class FermatPythagoras {
    /**
     * Método principal
     * @param args obrigatório para o método main
     * @see output()
     */
    public static void main(String[] args) {
        output(inputs());
    }
    /**
     * Método que lê os valores digitados pelo usuário. Recebe o valor de cada linha
     * em String, testa se não é um valor vazio, se não for, converte para inteiro,
     * e depois adiciona ao vetor inputs, que é retornado ao final. Usa o médtodo add() para isso.
     * Exetuta o while enquanto não é digitado o valor vazio
     * @return um array de inteiros, que foram digitados pelo usuário
     * @see push(int[] oldArray, int n)
     **/
    public static int [] inputs(){
        String input;
        int [] inputs = new int [0];
        Scanner scanner = new Scanner(System.in);
        do{
            input = scanner.nextLine();
            if (!input.equals(""))
                inputs = push(inputs, Integer.parseInt(input));
        }while(!input.equals(""));
        return inputs;
    }
    
     /**
     * Método que fatora o número especificado
     * @param n número que será fatorado
     * @return um array de inteiros que serão os fatores do número dado
     */
    public static int [] fatorar(int n){
        int [] fatores = new int[0];
        double half = n/2;
        int i = 1;
        do{
            if (n % i == 0){
                fatores = push(fatores, i);
            }
            i++;
        }while (i<=half);
        fatores = push(fatores, n);
        
        return fatores;
    }
    
    /**
     * Retorna se os números são ou não primos entre si.
     * @param x um dos três números a serem testados 
     * @param y outro dos números que serão testados
     * @param z aqui vai mais um número
     * @return true se os números forem primos entre si, e false caso contrário
     **/
    public static boolean calcPrime(int x, int y, int z){
        boolean arePrime = true;
        int [] fatorarX = fatorar(x);
        int [] fatorarY = fatorar(y);
        int [] fatorarZ = fatorar(z);
        
        for (int xFactor : fatorarX){
            for (int yFactor : fatorarY){
                for (int zFactor : fatorarZ){
                    if ((xFactor == yFactor) && (xFactor == zFactor) && (yFactor == zFactor) && (xFactor != 1)){
                        arePrime = false;
                        return arePrime;
                    }
                }
            }
        }
        return arePrime;
    }
    
    /**
     * Calcula a quantidade vezes que as condições do problema foram satisfeitas
     * @param n número tirado do input
     * @return um array onde a posição [0] é quantidade de vezes que a relação 
     * x² + y² = z² foi satisfeita, de forma que x &lt y &lt z e z, y e z são primos;
     * as demais posições são preenchidas por valores 0 ou 1, onde 0 quer dizer que
     * o número do índice pertence a alguma relação x² + y² = z², e 1 significa
     * o contráio
     */
    public static int [] calcRelation(int n){
        int [] result = new int[n+1];
        for(int i=1; i<=n; i++) result[i]=1;
        
        int numberOfPrimes = 0;
        for (int x = 1; x <= n-2; x++){
            for (int y = x+1; y <= n-1; y++){
                for (int z = y+1; z <= n; z++){
                    if (x*x + y*y == z*z){
                        result[x] = 0;
                        result[y] = 0;
                        result[z] = 0;
                        if (calcPrime(x, y, z)){
                            numberOfPrimes++;
                        }
                    }
                }
            }
        }
        result[0] = numberOfPrimes;
        return result;
    }
    
    /**
     * Adiciona um item ao um vetor dado. Usado quando não se sabe o tamanho que
     * o array vai ficar
     * @param oldArray o array ao qual será adicionado um elemento
     * @param n elemento a ser adicionado
     * @return o array dado com o elemento adicionado ao seu final
     */
    public static int[] push(int[] oldArray, int n){
        int [] array = new int[oldArray.length+1];
    
        for (int i = 0; i<oldArray.length; i++){
            array[i] = oldArray[i];
        }

        array[oldArray.length] = n;
        return array;
    }
    /**
    * Exibe o valor que está na primeira possição do array de resultado <br>
    * Testa os demais elementos do array, caso seja 0 não faz nada, se for 1, significa
    * que o índice é um número que não pertence a nenhuma relação x² + y² = z²
    * @param inputs array com os valores digitados no input, convertidos para String
    * @see calcRelation(int n)
    * @see inputs()
    */
    public static void output(int [] inputs){
        for (int i : inputs){
            int [] resultado = calcRelation(i);
            System.out.print(resultado[0]+" ");
            int count = 0;
            for (int j = 1; j<resultado.length; j++){
                if (resultado[j] == 1) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
