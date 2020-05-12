package control;

import java.util.HashMap;
import model.GraphStream;
import model.Vertex;

/**
 * Classe responsável controlar o sistema.
 * @author Everton Bruno Silva dos Santos
 */
public class Controller {
    private final GraphStream graphStream;

    /**
     * Construtor responsável por inicializar a classe, bem como seu grafo.
     */
    public Controller() {
        this.graphStream = new GraphStream();
    }
    
    /**
     * Método responsável por ler arquivos de texto contidos na memória de armazenamento.
     * @param fileName Refere-se ao nome do arquivo de texto a ser lido.
     * @return Retorna resultado da operação.
     */
    public Object loadFromFile(final String fileName) {
        return this.graphStream.loadFromFile(fileName);
    }

    /**
     * Método responsável por gerenciar a gravação de arquivos de texto na memória de armazenamento.
     * @param fileName Refere-se ao nome do arquivo de texto a ser gravado.
     * @return Retorna resultado da operação.
     */
    public Object saveFromFile(final String fileName) {
        return this.graphStream.saveFromFile(fileName);
    }

    /**
     * Método responsável por adicionar um vértice no grafo.
     * @param caption Refere-se a assinatura do vértice.
     * @param isTerminal Refere-se ao valor boleano que descreve a terminalidade do vértice.
     * @return Retorna o resultado da operação.
     */
    public Object addVertex(final String caption, final boolean isTerminal) {
        return this.graphStream.addVertex(caption, isTerminal);
    }

    /**
     * Método responsável retornar um mapa de vértices.
     * @return Retorna mapa de vértices.
     */
    public HashMap<String, Vertex> getMapVertex() {
        return this.graphStream.getMapVertex();
    }

    /**
     * Método responsável retornar um mapa de vértices terminais.
     * @return Retorna um mapa de vértices terminais.
     */
    public HashMap<String,Vertex> getMapTerminalVertex() {
        return this.graphStream.getMapTerminalVertex();
    }

    /**
     * Método responsável por remover um vértice.
     * @param caption Refere-se a assinatura do vértice.
     */
    public void removeVertex(final String caption) {
        this.graphStream.removeVertex(caption);
    }

    /**
     * Método responsável por adicionar uma aresta.
     * @param captionOne Refere-se a assinatura do primeiro vértice.
     * @param captionTwo Refere-se a assinatura do segundo vértice.
     * @param weigthInt Refere-se ao peso da aresta.
     * @return Resultado da operação.
     */
    public Object addEdge(final String captionOne, final String captionTwo, final int weigthInt) {
        return this.graphStream.addEdge(captionOne, captionTwo, weigthInt);
    }

    /**
     * Método responsável por remover uma aresta.
     * @param captionOne Refere-se a assinatura do primeiro vértice.
     * @param captionTwo Refere-se a assinatura do segundo vértice.
     */
    public void removeEdge(final String captionOne, final String captionTwo) {
        this.graphStream.removeEdge(captionOne, captionTwo);
    }

    /**
     * Método responsável por retornar o melhor caminho de um vértice para todos os demais.
     * @param captionSelected Refere-se a assinatura do vértice selecionado.
     * @return Retorna resultado da operação.
     */
    public String[] lowerCostPathToAll(final String captionSelected) {
        HashMap<String,Vertex> mapTerminalVertex = this.getMapTerminalVertex();
        if(mapTerminalVertex.size() <= 1){
            return new String[1];
        } else {
            final String[] strings = new String[mapTerminalVertex.size() - 1];
            String tmpString = null;
            int counter = 0;
            Object result;
            for (final HashMap.Entry<String,Vertex> element : mapTerminalVertex.entrySet()) {
                result = this.graphStream.lowerCostPathTo(captionSelected, element.getKey(), false);
                if (result instanceof String) {
                    tmpString = (String) result;
                }
                if (tmpString != null) {
                    strings[counter] = tmpString;
                    counter++;
                }
                tmpString = null;
            }
            return strings;
        }
    }

    /**
     * Método responsável por retornar o melhor caminho de um vértice para outro.
     * @param captionOne Refere-se a assinatura do primeiro vértice.
     * @param captionTwo Refere-se a assinatura do segundo vértice.
     * @return Retorna resultado da operação.
     */
    public Object lowerCostPathTo(final String captionOne, final String captionTwo) {
        if ((boolean)this.graphStream.lowerCostPathTo(captionOne, captionTwo, true)){
            return null;
        } else {
            return false;
        }
    }
    
    /**
     * Método responsável por desmarcar todos os caminhos marcados.
     */
    public void turnOffAllPath(){
        this.graphStream.turnOffAllPath();
    }  
    
}
