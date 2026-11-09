package trabalho;

public class Pilha<T> {

    private class No {
        T info;
        No proximo;
    }

    private No topo;

    public Pilha() {
        topo = null;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void push(T valor) {
        No novo = new No();
        novo.info = valor;
        novo.proximo = topo;
        topo = novo;
    }

    public T pop() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha vazia");
        }
        T valor = topo.info;
        topo = topo.proximo;
        return valor;
    }

    public T peek() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha vazia");
        }
        return topo.info;
    }

    public void liberar() {
        topo = null;
    }
}
