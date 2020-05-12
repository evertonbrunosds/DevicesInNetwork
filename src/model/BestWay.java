package model;

/**
 * Classe responsável armazenar os vértices que descrevem o melhor caminho de um ponto a outro.
 * @author Everton Bruno Silva dos Santos
 */
public class BestWay{
    private final Vertex currentVertex;
    private Vertex lastVertex;
    private int totalWeight;
    private boolean end;

    /**
     * Construtor responsável por inicializar a classe, bem como seus atributos.
     * @param currentVertex Refere-se ao vértice atual.
     * @param lasVertex Refere-se ao vértice anterior.
     * @param totalWeight Refere-se ao peso total até este ponto.
     * @param end Refere-se ao fato do caminho atual ser o melhor.
     */
    public BestWay(final Vertex currentVertex, final Vertex lasVertex, final int totalWeight, final boolean end) {
        this.currentVertex = currentVertex;
        this.lastVertex = lasVertex;
        this.totalWeight = totalWeight;
        this.end = end;
    }

    /**
     * Método responsável por obiter o vértice atual.
     * @return Retorna o vértice atual.
     */
    public Vertex getCurrentVertex() {
        return currentVertex;
    }

    /**
     * Método responsável por obiter o vértice anterior.
     * @return Retorna o vértice anterior.
     */
    public Vertex getLastVertex() {
        return lastVertex;
    }

    /**
     * Método responsável por obiter o peso total até este ponto.
     * @return Retorna o peso total.
     */
    public int getTotalWeight() {
        return totalWeight;
    }

    /**
     * Método responsável por obiter a informação boleana que refere-se ao fato desse caminho ser o melhor.
     * @return Retorna informação boleana.
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * Método responsável por alterar o vértice anterior.
     * @param lastVertex Refere-se ao vértice anterior.
     */
    public void setLastVertex(final Vertex lastVertex) {
        this.lastVertex = lastVertex;
    }

    /**
     * Método responsável por alterar o peso total até este ponto.
     * @param totalWeight Refere-se ao peso total.
     */
    public void setTotalWeight(final int totalWeight) {
        this.totalWeight = totalWeight;
    }

    /**
     * Método responsável por alterar a informação boleana que refere-se ao fato desse caminho ser o melhor.
     * @param end Refere-se a informação boleana.
     */
    public void setEnd(final boolean end) {
        this.end = end;
    }
    
}