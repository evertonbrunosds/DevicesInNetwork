package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe responsável por ler e gravar dados na memória de armazenamento, bem como, comportar-se como um grafo.
 * @author Everton Bruno Silva dos Santos
 */
public class GraphStream extends Graph {
    private String[] edgeListToMake;

    /**
     * Construtor responsável por inicializar a classe.
     */
    public GraphStream(){}

    /**
     * Método responsável por ler arquivos de texto contidos na memória de armazenamento.
     * @param fileName Refere-se ao nome do arquivo de texto a ser lido.
     * @return Retorna resultado da operação.
     */
    public Object loadFromFile(final String fileName) {
        super.getMapVertex().clear();
        try (BufferedReader fileStream = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            final ArrayList<String[]> dataList = new ArrayList<>();
            fileStream.lines().forEach((final String line) -> {
                edgeListToMake = line.split(",");
                if (edgeListToMake.length >= 2) {
                    super.addVertex(edgeListToMake[0], toBoolean(edgeListToMake[1])); // ADICIONA VÉRTICES
                }
                if (edgeListToMake.length == 4) {
                    dataList.add(edgeListToMake); // ARMAZENA ARESTAS
                }
            });
            dataList.forEach((line) -> {
                addEdge(line[0], line[2], Integer.parseInt(line[3])); // ADICIONA ARESTAS
            });
        } catch (final FileNotFoundException exception) {
            return exception;
        } catch (final IOException exception) {
            return exception;
        }
        return null;
    }

    /**
     * Método responsável por gerenciar a gravação de arquivos de texto na memória de armazenamento.
     * @param fileName Refere-se ao nome do arquivo de texto a ser gravado.
     * @return Retorna resultado da operação.
     */
    public Object saveFromFile(final String fileName) {
        try (PrintWriter fileStream = new PrintWriter(new FileOutputStream(fileName, false))) {
            for (final HashMap.Entry<String, Vertex> element : super.getMapVertex().entrySet()) {
                saveFromFile(fileStream, element.getValue());
            }
            ;
        } catch (final FileNotFoundException exception) {
            return exception;
        }
        return null;
    }

    /**
     * Método responsável por efetuar a gravação de arquivos de texto na memória de armazenamento.
     * @param fileStream Refere-se ao objeto responsável por efetuar a gravação dos dados.
     * @param vertex Refere-se ao vértice que será gravado.
     */
    private void saveFromFile(final PrintWriter fileStream, final Vertex vertex) {
        final String caption = vertex.getCaption();
        final String isTerminal = toString(vertex.isTerminal());
        if (vertex.getMapEdge().isEmpty()) { // VÉRTICE SEM LIGAÇÕES SENDO SALVO
            fileStream.println(caption + "," + isTerminal);
        } else {
            for (final HashMap.Entry<Vertex, Edge> element : vertex.getMapEdge().entrySet()) {
                fileStream.println(caption + "," + isTerminal + "," + element.getValue().getVertex().getCaption() + "," + element.getValue().getWeight());
            }
        }
    }

    /**
     * Método responsável por efetuar a conversão de um dado boleano em string.
     * @param string Refere-se ao dado boleano que será convertido.
     * @return Retorna dado boleano.
     */
    private boolean toBoolean(final String string) {
        return string.toLowerCase().equals("sim");
    }

    /**
     * Método responsável por efetuar a conversão de uma string em dado boleano.
     * @param bool Refere-se a string que será convertida.
     * @return Retorna string.
     */
    private String toString(final boolean bool) {
        if(bool){
            return "sim";
        } else {
            return "não";
        }
    }
    
}