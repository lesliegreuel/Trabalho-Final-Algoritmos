package trabalho;

public class Calculadora {

    public Fila<String> extrairTermos(String expressao) {
        Fila<String> termos = new Fila<String>();
        String ultimoTermo = null;
        int i = 0;

        while (i < expressao.length()) {
            char c = expressao.charAt(i);

            if (c == ' ') {
                i++;
            } else if (ehDigito(c) || c == ',') {
                String numero = "";
                while (i < expressao.length() && (ehDigito(expressao.charAt(i)) || expressao.charAt(i) == ',')) {
                    numero += expressao.charAt(i);
                    i++;
                }
                termos.inserir(numero);
                ultimoTermo = numero;
            } else if (c == '-' && ehSinalNegativo(ultimoTermo) && proximoEhNumero(expressao, i + 1)) {

                String numero = "-";
                i++;
                while (i < expressao.length() && (ehDigito(expressao.charAt(i)) || expressao.charAt(i) == ',')) {
                    numero += expressao.charAt(i);
                    i++;
                }
                termos.inserir(numero);
                ultimoTermo = numero;
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                String termo = "" + c;
                termos.inserir(termo);
                ultimoTermo = termo;
                i++;
            } else {
                throw new RuntimeException("Caractere invalido: " + c);
            }
        }
        return termos;
    }

    public Fila<String> gerarExprPosfixada(Fila<String> exprInfixada) {
        Fila<String> filaC = new Fila<String>();
        Pilha<String> pilhaB = new Pilha<String>();

        while (!exprInfixada.estaVazia()) {
            String termo = exprInfixada.retirar();

            if (termo.equals("(")) {
                pilhaB.push(termo);
            } else if (termo.equals(")")) {

                boolean achou = false;
                while (!pilhaB.estaVazia() && !achou) {
                    String t = pilhaB.pop();
                    if (t.equals("(")) {
                        achou = true;
                    } else {
                        filaC.inserir(t);
                    }
                }
                if (!achou) {
                    throw new RuntimeException("Parenteses invalidos");
                }
            } else if (ehOperador(termo)) {

                while (!pilhaB.estaVazia() && !pilhaB.peek().equals("(")
                        && precedencia(pilhaB.peek()) >= precedencia(termo)) {
                    filaC.inserir(pilhaB.pop());
                }
                pilhaB.push(termo);
            } else {

                filaC.inserir(termo);
            }
        }

        while (!pilhaB.estaVazia()) {
            String t = pilhaB.pop();
            if (t.equals("(")) {
                throw new RuntimeException("Parenteses invalidos");
            }
            filaC.inserir(t);
        }
        return filaC;
    }

    public double calcularExprPosfixada(Fila<String> exprPosfixada) {
        Pilha<Double> pilha = new Pilha<Double>();

        while (!exprPosfixada.estaVazia()) {
            String termo = exprPosfixada.retirar();

            if (ehOperador(termo)) {
                double b = pilha.pop();
                double a = pilha.pop();
                double resultado = 0;

                if (termo.equals("+")) {
                    resultado = a + b;
                } else if (termo.equals("-")) {
                    resultado = a - b;
                } else if (termo.equals("*")) {
                    resultado = a * b;
                } else {
                    if (b == 0) {
                        throw new RuntimeException("Divisao por zero");
                    }
                    resultado = a / b;
                }
                pilha.push(resultado);
            } else {

                pilha.push(Double.parseDouble(termo.replace(',', '.')));
            }
        }

        double resultadoFinal = pilha.pop();
        if (!pilha.estaVazia()) {
            throw new RuntimeException("Expressao invalida");
        }
        return resultadoFinal;
    }

    private boolean ehDigito(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean ehOperador(String t) {
        return t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/");
    }

    private int precedencia(String op) {
        if (op.equals("*") || op.equals("/")) {
            return 2;
        }
        return 1;
    }

    private boolean ehSinalNegativo(String ultimoTermo) {
        return ultimoTermo == null || ultimoTermo.equals("(") || ehOperador(ultimoTermo);
    }

    private boolean proximoEhNumero(String expressao, int pos) {
        if (pos >= expressao.length()) {
            return false;
        }
        char c = expressao.charAt(pos);
        return ehDigito(c) || c == ',';
    }
}
