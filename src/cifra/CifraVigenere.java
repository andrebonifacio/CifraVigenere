package src.cifra;
import java.util.Scanner;

public class CifraVigenere {

    private static Scanner in;
    private static String mensagem;
    private static String chave2;

    public static void main(String[] args) {

        in = new Scanner(System.in);
        System.out.println("1.Cifrar:\n2.Decifrar\nSelecione(1,2):");
        int escolha = in.nextInt();
        in.nextLine();

        if (escolha == 1) {
            System.out.println("-----Cifrador-----");
            menuMensagemChave();
            cifraMensagem(mensagem, chave2);
        } else if (escolha == 2) {
            System.out.println("-----Decifrador-----");
            menuMensagemChave();
            decifraMensagem(mensagem, chave2);
        } else {
            System.out.println("Escolha inexistente, por favor escolha entre as opções 1 ou 2.");
        }

    }

    private static void cifraMensagem(String mensagem, String chave2 ) {
        int[][] tabela = tabelaVigenere();
        String texto = "";

        for (int i = 0; i < mensagem.length(); i++) {
            if (mensagem.charAt(i) == (char) 32 && chave2.charAt(i) == (char) 32) {
                texto += " ";
            } else {

                texto += (char) tabela[(int) mensagem.charAt(i) - 65][(int) chave2.charAt(i) - 65];
            }
        }

        System.out.println("Texto cifrado: " + texto);

    }

    private static void decifraMensagem(String mensagem, String chave2){
        int[][] tabela = tabelaVigenere();
        String textoDecifrado = "";

        for(int i = 0; i<mensagem.length(); i++){
            if(mensagem.charAt(i) == (char) 32 && chave2.charAt(i) == (char) 32){
                textoDecifrado += " ";
            } else {
                textoDecifrado += (char) (65 + contadorItr((int)chave2.charAt(i), (int)mensagem.charAt(i)));
            }
        }

        System.out.println("Texto decifrado: " + textoDecifrado);

    }

    private static int contadorItr(int chave, int mensagem){
        int contador = 0;
        String resultado = "";
        for(int i = 0; i < 26; i++){
            if(chave + i > 90){
                resultado += (char) (chave + (i-26));
            }else{
                resultado += (char) (chave + i);
            }
        }

        for(int i = 0; i < resultado.length(); i++){
            if(resultado.charAt(i) == mensagem)  {
                break;
            }else{  
        
            contador++;
            }
        }
        return contador;
    }


    private static int[][] tabelaVigenere() {
        int[][] array = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int temp;
                if ((i + 65) + j > 90) {
                    temp = ((i + 65) + j) - 26;
                    array[i][j] = temp;
                } else {
                    temp = (i + 65) + j;
                    array[i][j] = temp;
                }
            }
        }

        // for(int i = 0; i<26;i++){
        // for(int j=0;j<26;j++){
        // System.out.print((char)array[i][j] + "");
        // }
        // System.out.println();
        // }

        return array;
    }

    private static void menuMensagemChave() {
        System.out.println("Digite a mensagem: ");
        String msg = in.nextLine();
        msg = msg.toUpperCase();

        System.out.println("Digite a chave: ");
        String chave = in.next();
        in.nextLine();
        chave = chave.toUpperCase();

        String chaveMap = "";
        for (int i = 0, j = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == (char) 32) {
                chaveMap += (char) 32;
            } else {
                if (j < chave.length()) {
                    chaveMap += chave.charAt(j);
                    j++;
                } else {
                    j = 0;
                    chaveMap += chave.charAt(j);
                    j++;
                }
            }

        }
        mensagem = msg;
        chave2 = chaveMap;

        // System.out.println("Mensagem: " + mensagem);
        // System.out.println("Chave: "+ chave);
    }

}