package trabalho;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Calculadora c = new Calculadora();

        System.out.println("Calculadora - digite 'sair' para encerrar");

        while (true) {
            System.out.print("Expressão: ");
            String expressao = entrada.nextLine();

            if (expressao.trim().equalsIgnoreCase("sair")) {
                break;
            }

            try {
                Fila<String> termosInfixada = c.extrairTermos(expressao);
                Fila<String> termosPosfixada = c.gerarExprPosfixada(termosInfixada);
                double resultado = c.calcularExprPosfixada(termosPosfixada);
                System.out.println("Resultado: " + formatar(resultado));
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }

        entrada.close();
    }

    private static String formatar(double valor) {
        if (valor == Math.floor(valor) && Math.abs(valor) < 1e15) {
            return "" + (long) valor;
        }
        return ("" + valor).replace('.', ',');
    }
}
