package view;

import java.awt.Color;
import javax.swing.JLabel;
import model.Vertex;

/**
 * Classe responsável por constituir-se um vértice visual.
 * @author Everton Bruno Silva dos Santos
 */
public class VisualVertex extends JLabel{
    private final Vertex vertex;
    
    /**
     * Construtor responsável por inicializar a classe, bem como seu atributo.
     * @param vertex Refere-se ao vértice contido no vértice visual.
     */
    public VisualVertex(final Vertex vertex) {
        super(vertex.getCaption());
        this.vertex = vertex;
        if(vertex.isTerminal()){
            super.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/terminal.png")));
        } else if (vertex.getCaption().equals("Internet")){
            super.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/internet.png")));
        } else {
            super.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/router.png")));
        }
        this.setForeground(Color.WHITE);
    }
    
    /**
     * Método responsável por buscar a distância euclidiana.
     * @param destiny Refere-se ao vértice visual destino.
     * @return Retorna distância euclidiana.
     */
    public double findDistance(final VisualVertex destiny) {
        final double x1 = this.getX();
        final double y1 = this.getY();
        final double x2 = destiny.getX();
        final double y2 = destiny.getY();
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Método responsável por obiter o vértice contido no vértice visual.
     * @return Retorna vértice contido.
     */
    public Vertex getVertex() {
        return vertex;
    }

}
