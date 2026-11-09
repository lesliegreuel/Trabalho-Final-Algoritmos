package trabalho;

public class Fila<T> {

    private class No {
        T info;
        No proximo;
    }

    private No primeiro;
    private No ultimo;

    public Fila() {
        primeiro = null;
        ultimo = null;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public void inserir(T valor) {
        No novo = new No();
        novo.info = valor;
        novo.proximo = null;
        if (estaVazia()) {
            primeiro = novo;
        } else {
            ultimo.proximo = novo;
        }
        ultimo = novo;
    }

    public T retirar() {
        if (estaVazia()) {
            throw new RuntimeException("Fila vazia");
        }
        T valor = primeiro.info;
        primeiro = primeiro.proximo;
        if (primeiro == null) {
            ultimo = null;
        }
        return valor;
    }

    public T peek() {
        if (estaVazia()) {
            throw new RuntimeException("Fila vazia");
        }
        return primeiro.info;
    }

    public void liberar() {
        primeiro = null;
        ultimo = null;
    }

    @Override
    public String toString() {
        String s = "";
        No p = primeiro;
        while (p != null) {
            s += p.info;
            if (p.proximo != null) {
                s += ",";
            }
            p = p.proximo;
        }
        return s;
    }
}
