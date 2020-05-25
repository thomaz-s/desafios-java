import java.util.Scanner;

/**
 *
 * @author thomas
 * problem: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=43
 */
public class TheCatInTheHat {
    public static void main(String[] args) {
        output(inputs());
//        String [] input = {"4 3"};
//        output(input);
    }
    
    /**
     * Método que lê os valores digitados pelo usuário. Recebe o valor de cada linha
     * em String, testa se não é "0 0", se não for, adiciona ao vetor inputs,
     * que é retornado ao final. Usa o médtodo add() para isso.
     * Exetuta o while enquanto não é digitado "0 0"
     * @return um array de String, que foram digitados pelo usuário
     * @see #push(String[] oldArray, String n)
     **/
    public static String [] inputs(){
        String input;
        String [] inputs = new String [0];
        Scanner scanner = new Scanner(System.in);
        do{
            input = scanner.nextLine();
            if (!input.equals("0 0"))
                inputs = push(inputs, input);
        }while(!input.equals("0 0"));
        return inputs;
    }
    
    /**
     * Faz os cálculos exigidos pelo problema. Usa 2 loops. No mais externo, as
     * condições para encerrar o são: o peso do gato, que vai diminuindo 
     * geometricamente, chegar a 1; o número de trabalhadores ser igual ao informado
     * pelo input. O loop mais interno vai parar de ser executado quando o peso for
     * menor ou igual a 1.
     * @param height peso do primeiro gato, informado no input
     * @param workers número de gatos que trabalham, informado no input
     * @return um array de 2 inteiros: o primeiro é a quantidade de gatos que não
     * estão trabalhando, o segundo é o peso total dos gatos
     */
    public static int [] calc(double height, double workers){
        
        int [] result = new int[2];
        
        double N = 0;
        double calcWorkers = 1;
        double notWorkers = 0;
        double calcHeight = height;
        double totalHeight = height;
        
        while(!((calcHeight == 1.0) && (calcWorkers == workers))){
            N++;
            notWorkers = 0;
            calcHeight = height;
            totalHeight = height;
            
            double level = 0;
            while (calcHeight > 1){
                level++;
                calcHeight = calcHeight/(N+1);
                totalHeight += calcHeight*Math.pow(N, level);
                notWorkers+= Math.pow(N, level-1);
            }
            
            calcWorkers = Math.pow(N, level);
        }
        
        result[0] = (int) notWorkers;
        result[1] = (int) totalHeight;
            
        return result;
    }
    
    /**
     * Mostra o resultado dos cálculos na saída
     * @param inputs array de String informado no input
     */
    public static void output(String [] inputs){
        for (String input : inputs){
            int height = Integer.parseInt(input.split(" ")[0]);
            int workers = Integer.parseInt(input.split(" ")[1]);
            int [] result = calc(height, workers);
            System.out.println(result[0]+" "+result[1]);
        }
    }
    
    /**
     * Adiciona um item ao um vetor dado, quando não se sabe o tamanho que
     * o array vai ficar
     * @param oldArray o array ao qual será adicionado um elemento
     * @param n elemento a ser adicionado
     * @return o array dado com o elemento adicionado ao seu final
     */
    public static String[] push(String[] oldArray, String n){
        String [] array = new String[oldArray.length+1];
    
        for (int i = 0; i<oldArray.length; i++){
            array[i] = oldArray[i];
        }

        array[oldArray.length] = n;
        return array;
    }
}