package src.cifra;

import java.util.Arrays;
import java.util.Scanner;

public class CifraVigenere {

    private static Scanner in;
    private static String mensagem;
    private static String chave2;

    public static void main(String[] args) {

        in = new Scanner(System.in);
        System.out.println("1.Cifrar:\n2.Decifrar\n3.Analise de frequencia\nSelecione(1,2,3):");
        int escolha = in.nextInt();
        in.nextLine();

        if (escolha == 1) {
            System.out.println("-----Cifrador-----");
            menuMensagemChave();
            cifraMensagem(mensagem, chave2);
        }
        if (escolha == 2) {
            System.out.println("-----Decifrador-----");
            menuMensagemChave();
            decifraMensagem(mensagem, chave2);
        } else if (escolha == 3) {
            menuMensagemChave2();
        }

    }


    private static void cifraMensagem(String mensagem, String chave2) {
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

    private static void decifraMensagem(String mensagem, String chave2) {
        int[][] tabela = tabelaVigenere();
        String textoDecifrado = "";

        for (int i = 0; i < mensagem.length(); i++) {
            if (mensagem.charAt(i) == (char) 32 && chave2.charAt(i) == (char) 32) {
                textoDecifrado += " ";
            } else {
                textoDecifrado += (char) (65 + contadorItr((int) chave2.charAt(i), (int) mensagem.charAt(i)));
            }
        }

        System.out.println("Texto decifrado: " + textoDecifrado);

    }

    private static int contadorItr(int chave, int mensagem) {
        int contador = 0;
        String resultado = "";
        for (int i = 0; i < 26; i++) {
            if (chave + i > 90) {
                resultado += (char) (chave + (i - 26));
            } else {
                resultado += (char) (chave + i);
            }
        }

        for (int i = 0; i < resultado.length(); i++) {
            if (resultado.charAt(i) == mensagem) {
                break;
            } else {

                contador++;
            }
        }
        return contador;
    }

    //matriz para a criação da tabela de Vigenere
    private static int[][] tabelaVigenere() {
        int[][] array = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int aux;
                if ((i + 65) + j > 90) {
                    aux = ((i + 65) + j) - 26;
                    array[i][j] = aux;
                } else {
                    aux = (i + 65) + j;
                    array[i][j] = aux;
                }
            }
        }

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

    }

    private static void menuMensagemChave2() {

        String msg1 = "rvgllakieg tye tirtucatzoe. whvnvvei i" +
                "winu mpsecf xronieg giid abfuk thv mfuty; wyenvvvr ik ij a drmg," +
                "drzzqly eomemsei in dy jouc; wyenvvvr i wied mpsvlf znmollnkarzlp " +
                "palszng seworv cfffzn narvhfusvs, rnd srzngznx up khv rerr ff emeiy" +
                "flnvrac i deek; aed ejpvcirlcy wyeeevvr dy hppfs gvt jucy ae upgei" +
                "haed ff mv, tyat zt ieqliies r skroeg dorrl grieczplv tf prvvvnt de" +
                "wrod dvliseiatvlp stvpginx ieto khv stievt, aed detyouicrlcy keotkieg" +
                "geoglv's hrtj ofw--tyen, z atcolnk it yixh tzmv to xek to jer as jofn" +
                "aj i tan.  khzs ij mp susskitltv foi pzstfl rnd sacl.  wzty a" +
                "pyicosfpyicrl wlolrzsh tako tyrfws yidsecf lpoe hzs snoid; i huzetcy" +
                "kakv tf thv syip.  khvre zs eotyieg slrgrijieg ie tyis.  zf khep blt" +
                "keen it, rldosk acl mvn zn tyezr dvgiee, jode tzmv or ftyer, thvrijh" +
                "merp nvarcy khe jade fvecinxs kowrrus tye fcern nity mv.";
        msg1 = msg1.toUpperCase();

        System.out.println(
                "Digite o tamanho estimado da chave:");
        System.out.println(crackChave(msg1, in.nextInt()));

    }

    public static double[] LETTER_FREQUENCIES = new double[] {
            8.167,
            1.492,
            2.782,
            4.253,
            12.702,
            2.228,
            2.015,
            6.094,
            6.966,
            0.153,
            0.772,
            4.025,
            2.406,
            6.749,
            7.507,
            1.929,
            0.095,
            5.987,
            6.327,
            9.056,
            2.758,
            0.978,
            2.360,
            0.150,
            1.974,
            0.074
    };

    public static String numberToKey(long chaveAtual) {
        if (chaveAtual < 0) {
            return "";
        } else {
            return numberToKey((chaveAtual / 26) - 1) + (char) (65 + chaveAtual % 26);
        }
    }

    public static String crackChave(String encString, int maxKeyLength) {
        long chave = 0;
        double desloc = Double.MAX_VALUE;
        for (long chaveAtual = 1; chaveAtual < Math.pow(27, maxKeyLength); chaveAtual++) {
            double offset = calculaFreqDif(codificaString(encString, numberToKey(chaveAtual), false));

            if (offset < desloc) {
                desloc = offset;
                chave = chaveAtual;
            }
        }

        return numberToKey(chave);
    }

    public static String codificaString(String s, String keyString, Boolean menu) {
        StringBuilder textoCodificado = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int chave = ((int) keyString.charAt(i % keyString.length())) - 65;
            if (menu) {
                textoCodificado.append(codificador(s.charAt(i), chave));
            } else {
                textoCodificado.append(codificador(s.charAt(i), -chave));
            }

        }
        return textoCodificado.toString();
    }

    public static double calculaFreqDif(String posDecString) {
        char[] Array = posDecString.toCharArray();
        Arrays.sort(Array);
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        double atualFreq = 0;
        String msgOrdenada = (Arrays.toString(Array));

        for (int i = 0; i < alfabeto.length(); i++) {
            String c = Character.toString(alfabeto.charAt(i));
            int charAmount = msgOrdenada.length() - msgOrdenada.replace(c, "").length();
            double desloc = (LETTER_FREQUENCIES[i] - charAmount / (double) posDecString.length());
            desloc *= desloc;
            atualFreq += desloc;
        }

        return atualFreq / 26D;
    }

    public static char codificador(char c, int chave) {

        if ((int) c == 32) {
            return (char) 32; 

        } else if (((int) c + chave) > 90) {
            return (char) (((int) c + chave) - 26);

        } else if (((int) c + chave) < 65) {
            return (char) ((int) c + 26 + chave);

        } else {
            return (char) ((int) c + chave);

        }
    }

}