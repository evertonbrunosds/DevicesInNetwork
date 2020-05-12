package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Edge;
import model.Vertex;

/**
 * Classe responsável por constituir-se um grafo visual.
 * @author Everton Bruno Silva dos Santos
 */
public class VisualGraph {
    
    /**
     * Método responsável por atualizar a interface do grafo.
     * @param mainForm Refere-se a janela da aplicação.
     * @param backGround Refere-se ao painel onde ficará localizado o grafo.
     */
    public static void update(final MainForm mainForm, final JPanel backGround) {
        mainForm.mapVisualVertex.clear();
        if (!mainForm.controller.getMapVertex().isEmpty()) {
            final int pice = 360 / mainForm.controller.getMapVertex().size();
            int total = pice;
            int x, y;
            final int width = (backGround.getWidth() - 100) / 2;
            final int heigth = (backGround.getHeight() - 60) /2;
            final HashMap<Vertex, VisualVertex> visualVertexMap = new HashMap<>();
            VisualVertex visualVertex;
            for (final HashMap.Entry<String, Vertex> element : mainForm.controller.getMapVertex().entrySet()) {
                visualVertex = new VisualVertex(element.getValue());
                visualVertex.setVerticalTextPosition(JLabel.BOTTOM);
                visualVertex.setHorizontalTextPosition(JLabel.CENTER);
                visualVertex.setVisible(true);
                visualVertexMap.put(element.getValue(), visualVertex);
                mainForm.mapVisualVertex.put(visualVertex.getVertex().getCaption(), visualVertex);
                visualVertex.setSize(60, 60);
                x = (int) ((Math.cos(Math.toRadians(total)) * width) + width);
                y = (int) ((Math.sin(Math.toRadians(total)) * heigth) + heigth);
                visualVertex.setLocation(x + 40, y);
                visualVertex.setBackground(Color.yellow);
                createBestConnectionEvent(mainForm, visualVertex);
                total += pice;
            }
            updateBackGround(backGround, gerateGraph(visualVertexMap));
        } else {
            updateBackGround(backGround, new JPanel());
        }
    }
    
    /**
     * Método responsável por criar evento de click nos dispositivos da rede.
     * @param mainForm Refere-se a janela da aplicação.
     * @param visualVertex Refere-se ao dispositivo da rede.
     */
    private static void createBestConnectionEvent(MainForm mainForm, VisualVertex visualVertex){
        visualVertex.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Option.identifyBestAllConnections(mainForm, visualVertex);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
    }

    /**
     * Método responsável por atualizar o painel de fundo da aplicação.
     * @param backGround Refere-se ao painel de fundo.
     * @param panelGraph Refere-se ao painel do grafo.
     */
    private static void updateBackGround(final JPanel backGround, final JPanel panelGraph) {
        backGround.removeAll();
        backGround.setVisible(false);
        backGround.setVisible(true);
        backGround.add(panelGraph);
        panelGraph.setLocation(0, 0);
        panelGraph.setSize(backGround.getSize());
        panelGraph.setVisible(true);
        panelGraph.setBackground(backGround.getBackground());
    }

    /**
     * Método responsável por construir o grafo visual.
     * @param visualVertexMap Refere-se ao mapa de vértices visuais.
     * @return Retorna o painel onde encontra-se o grafo visual.
     */
    private static JPanel gerateGraph(final HashMap<Vertex, VisualVertex> visualVertexMap) {
        final JPanel mainPanel = new JPanel() {
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);
                for (final HashMap.Entry<Vertex, VisualVertex> element : visualVertexMap.entrySet()) { //PERCORRE HASHMAP PARA DESENHAR ARESTAS
                    final Vertex vertex = element.getKey(); //NÓ A TER HASHMAP DE ARESTAS PERCORRIDO
                    for (final HashMap.Entry<Vertex, Edge> edgeElement : vertex.getMapEdge().entrySet()) {  //PERCORRE HASHMAP DE ARESTAS
                        final int x1 = visualVertexMap.get(vertex).getX() + 16;    //POSIÇÃO DO VETOR X DO NÓ A TER HASHMAP DE ARESTAS PERCORRIDO
                        final int y1 = visualVertexMap.get(vertex).getY() + 16;    //POSIÇÃO DO VETOR Y DO NÓ A TER HASHMAP DE ARESTAS PERCORRIDO
                        int x2 = 0;     //POSIÇÃO DO VETOR X DO NÓ CONECTADO AO NÓ A TER HASHMAP DE ARESTAS PERCORRIDO
                        int y2 = 0;     //POSIÇÃO DO VETOR Y DO NÓ CONECTADO AO NÓ A TER HASHMAP DE ARESTAS PERCORRIDO
                        g.setColor(Color.GREEN);    //MUDA A COR DOS DESENHOS PARA VERDE
                        try {
                            if(edgeElement.getValue().isTurnOn())   //CONDICIONAL QUE CHECA SE A ARESTA ESTÁ MARCADA
                                g.setColor(Color.RED);  //MUDA A COR DOS DESENHOS PARA VERMELHO
                            x2 = visualVertexMap.get(edgeElement.getValue().getVertex()).getX() + 16;   //POSIÇÃO DO VETOR X DO NÓ CONECTADO AO NÓ A TER HASHMAP DE ARESTAS PERCORRIDO
                            y2 = visualVertexMap.get(edgeElement.getValue().getVertex()).getY() + 16;   //POSIÇÃO DO VETOR Y DO NÓ CONECTADO AO NÓ A TER HASHMAP DE ARESTAS PERCORRIDO
                            g.drawLine(x1, y1, x2, y2);     //DESENHA ARESTA
                        } catch (final Exception e) {}
                        int x3, y3;  //POSIÇÃO DO VETOR X E Y DO CENTRO DA ARESTA
                        x3 = x1 - x2;
                        y3 = y1 - y2;
                        if(x3 < 0){
                            x3 = -x3;
                            x3 = x3 / 2 + x1;
                        }
                        else
                            x3 = x3 / 2 + x2;
                        if(y3 < 0 ){
                            y3 = -y3;
                            y3 = y3 / 2 + y1;
                        }
                        else
                            y3 = y3 / 2 + y2;
                        g.drawString(edgeElement.getValue().getWeight() + "", x3 + 5, y3 + 5);  //DESENHA VALOR DA ARESTA
                    }
                    this.add(element.getValue());   //ADICIONA COMPONENTE DO NO PAINEL
                }
            }
        };
        return mainPanel;
    }
}
