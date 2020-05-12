package model;

import java.util.HashMap;

/**
 * Classe responsável por comportar-se como um grafo.
 * @author Everton Bruno Silva dos Santos
 */
public class Graph {
    private final HashMap<String,Vertex> mapVertex;

    /**
     * Construtor responsável por inicializar a classe, bem como seus atributos.
     */
    public Graph(){
        mapVertex = new HashMap<>();
    }

    /**
     * Método responsável por adicionar um vértice no grafo.
     * @param caption Refere-se a assinatura do vértice.
     * @param terminal Refere-se ao valor boleano que descreve a terminalidade do vértice.
     * @return Retorna resultado da operação.
     */
    public Object addVertex(final String caption, final boolean terminal) {
        if (!mapVertex.containsKey(caption)) {
            mapVertex.put(caption, new Vertex(caption, terminal));
            return null;
        } else {
            return true;
        }
    }

    /**
     * Método responsável por adicionar arestas.
     * @param captionOne Refere-se a assinatura do primeiro vértice.
     * @param captionTwo Refere-se a assinatura do segundo vértice.
     * @param weight Refere-se ao peso da aresta.
     * @return Retorna resultado da operação.
     */
    public Object addEdge(final String captionOne, final String captionTwo, final int weight) {
        if (weight > 0) {
            final Vertex vertexOne = mapVertex.get(captionOne);
            final Vertex vertexTwo = mapVertex.get(captionTwo);
            if (vertexOne != null && vertexTwo != null) {
                if (!vertexOne.equals(vertexTwo)) {
                    if(!vertexOne.getMapEdge().containsKey(vertexTwo)){
                        vertexOne.addEdge(vertexTwo, weight);
                        vertexTwo.addEdge(vertexOne, weight);
                        return null;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Método responsável por remover arestas.
     * @param captionOne Refere-se a assinatura do primeiro vértice.
     * @param captionTwo Refere-se a assinatura do segundo vértice.
     */
    public void removeEdge(final String captionOne, final String captionTwo) {
        final Vertex vertexOne = mapVertex.get(captionOne);
        final Vertex vertexTwo = mapVertex.get(captionTwo);
        if (vertexOne != null && vertexTwo != null) {
            if (!vertexOne.equals(vertexTwo)) {
                if(vertexOne.getMapEdge().get(vertexTwo) != null){
                    if (vertexOne.getMapEdge().get(vertexTwo).isTurnOn()){
                        this.turnOffAllPath();
                    }
                }
                vertexOne.getMapEdge().remove(vertexTwo);
                vertexTwo.getMapEdge().remove(vertexOne);
            }
        }
    }

    /**
     * Método responsável por remover vértices e suas respectivas arestas.
     * @param caption Refere-se a assinatura do vértice.
     */
    public void removeVertex(final String caption) {
        final Vertex vertex = mapVertex.remove(caption);
        if (vertex != null) {
            mapVertex.entrySet().forEach((element) -> {
                element.getValue().getMapEdge().remove(vertex);
            });
        }
    }

    /**
     * Método responsável por encontrar o melhor caminho de um vértice a outro.
     * @param beginCaption Refere-se ao vértice que será usado como ponto de partida.
     * @param endCaption Refere-se ao vértice que será usado como ponto de chegada.
     * @param turnOnPath Refere-se a possibilidade do método marcar os caminhos até um dado ponto ou retorna-los como string.
     * @return Retorna resultado da operação.
     */
    public Object lowerCostPathTo(final String beginCaption, final String endCaption, final boolean turnOnPath) {
        final Vertex beginVertex = mapVertex.get(beginCaption);
        final Vertex endVertex = mapVertex.get(endCaption);
        boolean find = true;    //VARIÁVEL QUE GUARDA SE UM CAMINHO PODE SER ACHADO OU FOI ACHADO
        if (beginVertex != null && endVertex != null) {     //CONDICIONAL QUE IMPEDE A EXECUÇÃO DO RESTO DO MÉTODO CASO OS VERTEX ADICIONADOS NO MÉTODO NÃO ESTEJAM NO GRAFO
            if (!beginVertex.equals(endVertex) && beginVertex.isTerminal() && endVertex.isTerminal()) {     //CONDICIONAL QUE IMPEDE A EXECUÇÃO DO RESTO DO MÉTODO CASO O DESTINO SEJA O MESMO DA PARTIDA OU UM DELES NÃO SEJA UM TERMINAL
                final HashMap<Vertex, BestWay> bestWayMap = new HashMap<>();
                BestWay bestWay;    //VARIÁVEL PARA GUARDAR MENOR CAMINHO NÃO MARCADO
                Vertex currentVertex = beginVertex, lastVertex = beginVertex;   //VARIÁVEL QUE GUARDA O VERTEX ATUAL E VARIÁVEL QUE GUARDA O ÚLTIMO ATUAL VERTEX
                boolean keep = true;    //VARIÁVEL QUE GUARDA SE O LOOP CONTINUA FUNCIONANDO
                int counter = 0;    //VARIÁVEL QUE GUARDA A DIST NCIA PERCORRIDA ATÉ O ATUAL
                while (keep) {      //LOOP PRINCIPAL
                    for (final HashMap.Entry<Vertex, Edge> element : currentVertex.getMapEdge().entrySet()) {
                        if (!element.getValue().getVertex().equals(lastVertex)) {
                            if (!bestWayMap.containsKey(element.getValue().getVertex())) {
                                bestWayMap.put(element.getValue().getVertex(), new BestWay(element.getValue().getVertex(), currentVertex, counter + element.getValue().getWeight(), false));
                            } else {
                                if (bestWayMap.get(element.getValue().getVertex()).getTotalWeight() > counter + element.getValue().getWeight()) {
                                    bestWayMap.get(element.getValue().getVertex()).setTotalWeight(counter + element.getValue().getWeight());
                                    bestWayMap.get(element.getValue().getVertex()).setLastVertex(currentVertex);
                                }
                            }
                        }
                    }
                    bestWay = lowerWeight(bestWayMap);  //GUARDA O MENOR DOS CAMINHOS AINDA NÃO MARCADO
                    if (bestWay != null) {  //CONDICIONAL PARA CHECAR SE AINDA EXISTEM CAMINHOS POSSÍVEIS
                        bestWay.setEnd(true);   //MARCA COMO MELHOR CAMINHO POSSÍVEL PARA SE CHEGAR EM UM NÓ
                        if (bestWay.getCurrentVertex().equals(endVertex)) { //CONDICIONAL PARA CHECAR DE O MENOR CAMINHO ATÉ O DESTINO FOI ENCONTRADO
                            keep = false;   //TORNA A VARIÁVEL QUE MANTÉM O LOOP PRINCIPAL QUANDO VERDADEIRA LIGADO FALSA
                        } else {
                            currentVertex = bestWay.getCurrentVertex();     //TORNA O ATUAL O MENOR DOS CAMINHOS AINDA NÃO MARCADO
                            counter = bestWay.getTotalWeight();   //ATUALIZA O CONTADOR
                            lastVertex = bestWay.getLastVertex();   //ATUALIZA A VARIÁVEL QUE GUARDA O ÚLTIMO ATUAL VERTEX
                        }
                    } else {
                        keep = false;   //TORNA A VARIÁVEL QUE MANTÉM O LOOP PRINCIPAL QUANDO VERDADEIRA LIGADO FALSA
                        find = false;   //GUARDA QUE UM CAMINHO NÃO PODE SER ACHADO
                    }
                }
                if (find) {    //CONDICIONAL QUE CHECA SE UM CAMINHO FOI ENCONTRADO
                    if (turnOnPath) {
                        turnOnPath(bestWayMap, endVertex, beginVertex);     //MARCA AS ARESTAS DO GRAFO QUE FAZEM PARTE DO MENOR CAMINHO
                    } else {
                        return selectPath(bestWayMap, endVertex, beginVertex).getStrings();
                    }
                }
            }
        } else {
            return false;
        }
        return find;
    }

    /**
     * Método responsável por buscar o caminho cujo peso seja menor, nesse caso, o melhor caminho.
     * @param bestWayMap Refere-se ao mapa de melhores caminhos.
     * @return Retorna o melhor caminho dentre todos.
     */
    private BestWay lowerWeight(final HashMap<Vertex, BestWay> bestWayMap) {
        int lowerWay = Integer.MAX_VALUE;
        BestWay bestWay = null;
        for (final HashMap.Entry<Vertex, BestWay> element : bestWayMap.entrySet()) {
            if (bestWay == null && !element.getValue().isEnd()) {
                bestWay = element.getValue();
                lowerWay = element.getValue().getTotalWeight();
            } else if (lowerWay > element.getValue().getTotalWeight() && !element.getValue().isEnd()) {
                lowerWay = element.getValue().getTotalWeight();
                bestWay = element.getValue();
            }
        }
        return bestWay;
    }

    /**
     * Método responsável por marcar um conjunto de arestas que correspondem o melhor caminho de um vértice a outro.
     * @param bestWayMap Refere-se ao mapa de melhores caminhos.
     * @param endVertex Refere-se ao vértice final.
     * @param beginVertex Refere-se ao vértice inicial.
     */
    private void turnOnPath(final HashMap<Vertex, BestWay> bestWayMap, final Vertex endVertex, final Vertex beginVertex) {
        turnOffAllPath();
        BestWay bestWay = bestWayMap.get(endVertex);
        Vertex currentVertex = null;
        while (true) {
            currentVertex = bestWay.getCurrentVertex(); 
            bestWay = bestWayMap.get(bestWay.getLastVertex());
            if (bestWay != null) {
                bestWay.getCurrentVertex().getMapEdge().get(currentVertex).setTurnOn(true);
                currentVertex.getMapEdge().get(bestWay.getCurrentVertex()).setTurnOn(true);
            } else {
                break;
            }
        }
        beginVertex.getMapEdge().get(currentVertex).setTurnOn(true);
        currentVertex.getMapEdge().get(beginVertex).setTurnOn(true);
    }

    /**
     * Método responsável por montar uma pilha com o melhor caminho.
     * @param bestWayMap Refere-se ao mapa de melhores caminhos.
     * @param endVertex Refere-se ao vértice final.
     * @param beginVertex Refere-se ao vértice inicial.
     * @return Retorna pilha com o melhor caminho.
     */
    private PathStack selectPath(final HashMap<Vertex, BestWay> bestWayMap, final Vertex endVertex, final Vertex beginVertex) {
        final PathStack pathStack = new PathStack();
        BestWay bestWay = bestWayMap.get(endVertex);
        while (bestWay != null) {
            pathStack.push(bestWay.getCurrentVertex().getCaption());
            bestWay = bestWayMap.get(bestWay.getLastVertex());
        }
        pathStack.push(beginVertex.getCaption());
        return pathStack;
    }

    /**
     * Método responsável por desmarcar todos as arestas marcadas.
     */
    public void turnOffAllPath() {
        mapVertex.entrySet().forEach((element) -> {
            element.getValue().turnOffAllEdges();
        });
    }

    /**
     * Método responsável por obiter o mapa de vértices que são terminais.
     * @return 
     */
    public HashMap<String, Vertex> getMapTerminalVertex() {
        final HashMap<String, Vertex> mapVertexIsTerminal = new HashMap<>();
        mapVertex.entrySet().forEach((element) -> {
            if(element.getValue().isTerminal()){
                mapVertexIsTerminal.put(element.getKey(), element.getValue());
            }
        });
        return mapVertexIsTerminal;
    }

    /**
     * Método responsável por obiter o mapa onde todos os vértices estão contidos.
     * @return 
     */
    public HashMap<String, Vertex> getMapVertex() {
        return mapVertex;
    }
    

}