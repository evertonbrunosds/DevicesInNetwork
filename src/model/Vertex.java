package model;

import java.util.HashMap;

/**
 * Classe responsável comportar-se como um vértice.
 * @author Everton Bruno Silva dos Santos
 */
public class Vertex{
    private final String caption;
    private final HashMap<Vertex,Edge> mapEdge = new HashMap<>();
    private final boolean terminal;

    /**
     * Construtor responsável por inicializar não só a classe, bem como, seus atributos.
     * @param caption Refere-se a assinatura do vértice.
     * @param terminal Refere-se a terminalidade do vértice.
     */
    public Vertex(final String caption, final boolean terminal) {
        this.caption = caption;
        this.terminal = terminal;
    }

    /**
     * Método responsável por adicionar uma aresta.
     * @param vertex Refere-se ao vértice que ficará contido na aresta.
     * @param weight Refere-se ao peso da aresta.
     */
    public void addEdge(final Vertex vertex, final int weight) {
        if (mapEdge.get(vertex) == null) {
            mapEdge.put(vertex, new Edge(weight, vertex));
        }
    }
    
    /**
     * Método responsável por desmarcar todas as arestas de um vértice.
     */
    public void turnOffAllEdges(){
        mapEdge.entrySet().forEach((element) -> {
            element.getValue().setTurnOn(false);
        });
    }

    /**
     * Método responsável por obiter a assinatura do vértice.
     * @return Retorna a assinatura do vértice.
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Método responsável por obiter o mapa de arestas do vértice.
     * @return Retorna mapa de arestas.
     */
    public HashMap<Vertex,Edge> getMapEdge() {
        return mapEdge;
    }

    /**
     * Método responsável por obiter informação boleana que indica se o vértice é um terminal.
     * @return Retorna informação boleana.
     */
    public boolean isTerminal() {
        return terminal;
    }    
    
}