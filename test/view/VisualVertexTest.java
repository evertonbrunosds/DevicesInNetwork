package view;

import model.Vertex;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes do vértice visual.
 * @author Everton Bruno Silva dos Santos
 */
public class VisualVertexTest {
    VisualVertex visualVertexOne, visualVertexTwo;
    
    public VisualVertexTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.visualVertexOne = new VisualVertex(new Vertex("A",true));
        this.visualVertexTwo = new VisualVertex(new Vertex("B",true));
    }
    
    @After
    public void tearDown() {
    }

    /**<TESTES DE DISTÂNCIA EUCLIDIANA ----------------------------------------->*/
    
    @Test
    public void testaDistânciaEuclidianaConferindoRetornoDeMetodo(){
        visualVertexOne.setLocation(300, 200);
        visualVertexTwo.setLocation(600, 200);
        assertEquals("Testa se a distância euclidiana corresponde ao valor esperado.",
                300, visualVertexOne.findDistance(visualVertexTwo), 0.1);
    }
    
    @Test
    public void testaSeADistânciaEuclidianaÉAMesmaTantoParaIrQuantoParaVoltar(){
        visualVertexOne.setLocation(300, 200);
        visualVertexTwo.setLocation(600, 200);
        assertEquals("Testa distância euclidiana de A para B.",
                300, visualVertexOne.findDistance(visualVertexTwo), 0.1);
        assertEquals("Testa distância euclidiana de B para A.",
                300, visualVertexTwo.findDistance(visualVertexOne), 0.1);
    }
    
    @Test
    public void testaDistânciaEuclidianaDeUmPontoAOutroComAmbosLocalizadosNasMesmasCoordenadas(){
        visualVertexOne.setLocation(300, 200);
        visualVertexTwo.setLocation(300, 200);
        assertEquals("Testa distância euclidiana de A para B.",
                0, visualVertexOne.findDistance(visualVertexTwo), 0.1);
        assertEquals("Testa distância euclidiana de B para A.",
                0, visualVertexTwo.findDistance(visualVertexOne), 0.1);
    }
}
