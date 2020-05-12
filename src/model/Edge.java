package model;

/**
 * Classe responsável por comportar-se como uma aresta.
 * @author Everton Bruno Silva dos Santos
 */
public class Edge {
    private final int weight;
    private final Vertex vertex;
    private boolean turnOn;

    /**
     * Construtor responsável por inicializar classe, bem como seus atributos.
     * @param weight Refere-se a peso da aresta.
     * @param vertex Refere-se ao vértice que será contido na aresta.
     */
    public Edge(final int weight, final Vertex vertex) {
        this.weight = weight;
        this.vertex = vertex;
        turnOn = false;
    }

    /**
     * Método responsável por obiter informação boleana que indica se a aresta está marcada.
     * @return Retorna informação boleana.
     */
    public boolean isTurnOn() {
        return turnOn;
    }

    /**
     * Método responsável por alterar informação boleana que indica se a aresta está marcada.
     * @param turnOn Refere-se a informação boleana.
     */
    public void setTurnOn(final boolean turnOn) {
        this.turnOn = turnOn;
    }

    /**
     * Método responsável por obiter o peso da aresta.
     * @return Retorna o peso da aresta.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Método responsável por obiter o vértice contido na aresta.
     * @return Retorna o vértice contido na aresta.
     */
    public Vertex getVertex() {
        return vertex;
    }
    
}