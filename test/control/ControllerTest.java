package control;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe de testes do controlador.
 * @author Everton Bruno Silva dos Santos.
 */
public class ControllerTest {
    private Controller controller;
    
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.controller = new Controller();
    }
    
    @After
    public void tearDown() {
        this.controller = null;
    }

    /**<TESTES DE ADIÇÃO DE DISPOSITIVOS ----------------------------------------->*/
    
    @Test
    public void tentaAdicionarDoisDispositivosComMesmaAssinaturaConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        assertNotNull("Testa se dois dispositivos com mesma assinatura podem ser adicionados com retorno do metodo.",
                controller.addVertex("A", true));
    }
    
    @Test
    public void testaSeUmDispositivoFoiAdicionadoConferindoRetornoDoMetodo(){
        assertNull("Testa se um dispositivo foi adicionado conferindo retorno do método.",
                controller.addVertex("A", true));
    }
    
    @Test
    public void testaSeDoisDispositivosForamAdicionadosConferindoQuantidade(){
        assertEquals("Testa se não há dispositivos conferindo a quantidade.",
                0, controller.getMapVertex().size());
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertEquals("Testa se dois dispositivos foram adicionados conferindo a quantidade.",
                2, controller.getMapVertex().size());
    }
    
    @Test
    public void testaSeDoisDispositivosForamAdicionadosConferindoAsAssinaturas(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertEquals("Testa se o dispositivo A foi adicionado conferindo a assinatura.",
                "A", controller.getMapVertex().get("A").getCaption());
        assertEquals("Testa se o dispositivo B foi adicionado conferindo a assinatura.",
                "B", controller.getMapVertex().get("B").getCaption());
    }
    
    @Test
    public void testaSeDoisDispositivosForamAdicionadosConferindoExistencia(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertTrue("Testa se o dispositivo A foi adicionado conferindo existência.",
                controller.getMapVertex().containsKey("A"));
        assertTrue("Testa se o dispositivo B foi adicionado conferindo existência.",
                controller.getMapVertex().containsKey("B"));
    }
    
    @Test
    public void testaAdicionarUmDispositivoAposTerRemovidoConferindoRetornoDeMetodo(){
        controller.addVertex("A", true);
        controller.removeVertex("A");
        assertNull("Testa se um dispositivo foi adicionado aṕos ter sido removido conferindo retorno do método.",
                controller.addVertex("A", true));
    }
    
    /**<TESTES DE REMOÇÃO DE DISPOSITIVOS ----------------------------------------->*/
    
    @Test
    public void testaRemoverDeDoisDispositivosConferindoQuantidade(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertEquals("Testa se há dois dispositivos conferindo a quantidade.",
                2, controller.getMapVertex().size());
        controller.removeVertex("A");
        controller.removeVertex("B");
        assertEquals("Testa remoção dois dispositivos conferindo a quantidade.",
                0, controller.getMapVertex().size());
    }
    
    @Test
    public void testaRemoverUmDispositivoInexistenteConferindoInexistencia(){
        controller.removeVertex("Inexistente");
        assertFalse("Testa remoção de um dispositivo inexistente na rede.",
                controller.getMapVertex().containsKey("Inexistente"));
    }
    
    @Test
    public void testaRemoverDispositivoConferindoExistencia(){
        controller.addVertex("Existente", true);
        assertTrue("Testa existência do dispositivo.",
                controller.getMapVertex().containsKey("Existente"));
        controller.removeVertex("Existente");
        assertFalse("Testa inexistência do dispositivo.",
                controller.getMapVertex().containsKey("Existente"));
    }
    
    @Test
    public void testaRemoverDispositivoConferindoInexistencia(){
        controller.addVertex("Existente", true);
        assertTrue("Testa existência do dispositivo.",
                controller.getMapVertex().containsKey("Existente"));
        controller.removeVertex("Existente");
        assertFalse("Testa inexistência do dispositivo.",
                controller.getMapVertex().containsKey("Existente"));
    }
    
    /**<TESTES DE CRIAÇÃO DE CONEXÕES ----------------------------------------->*/
    
    @Test
    public void testaAdicionarConexaoComUmDispositivoInexistenteConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        assertNotNull("Testa criação de conexão com um dispositivo existente com um inexistente conferindo retorno do metodo.",
                controller.addEdge("A", "Inexistente", 5));
    }
    
    @Test
    public void testaAdicionarConexaoComDoisDispositivoInexistentesConferindoRetornoDoMetodo(){
        assertNotNull("Testa criação de conexão com dois dispositivos inexistentes conferindo retorno de metodo.",
                controller.addEdge("Inexistente B", "Inexistente A", 5));
    }
    
    @Test
    public void testaAdicionarConexaoDeUmDispositivoComEleMesmoConferindoRetoroDoMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertNotNull("Testa criar conexão de um dispositivo com ele mesmo conferindo retorno de metodo.",
                controller.addEdge("A", "A", 5));
    }
    
    @Test
    public void testaAdicionarConexaoComPesoMenorQueZeroConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertNotNull("Testa criar conexão com peso menor que zero conferindo retorno do metodo.",
                controller.addEdge("A", "B", -1));
    }
    
    @Test
    public void testaAdicionarConexãoComPesoIgualAZeroConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertNotNull("Testa criar conexão com peso igual a zero conferindo retorno do metodo.",
                controller.addEdge("A", "B", 0));
    }
    
    @Test
    public void testaAdicionarConexaoComUmDispositivoInexistenteVerificandoOMapaDeConexoes(){
        controller.addVertex("A", true);
        controller.addEdge("A", "Inexistente", 5);
        assertEquals("Testa criar conexão com dispositivo inexistente conferindo quantidade das conexões.",
                0, controller.getMapVertex().get("A").getMapEdge().size());
    }
    
    @Test
    public void testaAdicionarConexaoComdoisDispositivosExistentesConferindoRetornoDeMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertNull("Testa criar conexão com dois dispositivos conferindo retorno de metodo.",
                controller.addEdge("A", "B", 5));
    }
    
    @Test
    public void testaAdicionarConexaoComDoisDispositivosExistentesConferindoQuantidadeDeConexoes(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertEquals("Testa se o dispositivo A não tem conexão com nenhum outro dispositivo.",
                0, controller.getMapVertex().get("A").getMapEdge().size());
        assertEquals("Testa se o dispositivo B não tem conexão com nenhum outro dispositivo.",
                0, controller.getMapVertex().get("B").getMapEdge().size());
        controller.addEdge("A", "B", 5);
        assertEquals("Testa se o dispositivo A não tem uma conexão com outro dispositivo.",
                1, controller.getMapVertex().get("A").getMapEdge().size());
        assertEquals("Testa se o dispositivo B não tem uma conexão com outro dispositivo.",
                1, controller.getMapVertex().get("B").getMapEdge().size());
    }
    
    @Test
    public void testaAdicionarConexaoComDoisDispositivosExistentesConferindoPesoDaConexao(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addEdge("A", "B", 5);
        assertEquals("Testa se o peso da conexão do dispositivo A para o dispositivo B está correto.",
                5, controller.getMapVertex().get("A").getMapEdge().get(controller.getMapVertex().get("B")).getWeight());
    }
    
    @Test
    public void testaAdicionarConexãoDeDispositivosConectadosConferindoExistenciaDaConexao(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertFalse("Testa se não existe conexão do dispositivo A no dispositivo B conferindo inexistência da conexão.",
                controller.getMapVertex().get("A").getMapEdge().containsKey(controller.getMapVertex().get("B")));
        controller.addEdge("A", "B", 5);
        assertTrue("Testa se existe conexão do dispositivo A no dispositivo B conferindo existência da conexão.",
                controller.getMapVertex().get("A").getMapEdge().containsKey(controller.getMapVertex().get("B")));
    }
    
    @Test
    public void testaAdicionarDuasVezesAMesmaConexãoEntreDoisDispositivosConferindoRetornoDeMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertNull("Testa se o dispositivo A foi conectado ao dispositivo B.", controller.addEdge("A", "B", 5));
        assertNotNull("Testa se uma mesma conexão pode ser adicionada duas vezes.", controller.addEdge("B", "A", 5));
    }
    
    /**<TESTES DE REMOÇÃO DE CONEXÕES ----------------------------------------->*/
    
    @Test
    public void testaRemoverConexãoDeDispositivosConectadosConferindoExistenciaDaConexao(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addEdge("A", "B", 5);
        controller.removeEdge("A", "B");
        assertFalse("Testa se existe conexão do dispositivo A no dispositivo B conferindo existência da conexão.",
                controller.getMapVertex().get("A").getMapEdge().containsKey(controller.getMapVertex().get("B")));
    }
    
    @Test
    public void testaRemoverConexãoDeDispositivosConectadosConferindoQuantidadeDeConexoes(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addEdge("A", "B", 5);
        controller.removeEdge("A", "B");
        assertEquals("Testa se existe conexão do dispositivo A no dispositivo B conferindo quantidade de conexões.",
                0, controller.getMapVertex().get("A").getMapEdge().size());
    }
    
    @Test
    public void testaSeRemoverConexãoDeDispositivosConectadosNaoInterferenosDemais(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addVertex("C", true);
        controller.addEdge("A", "B", 5);
        controller.addEdge("A", "C", 5);
        assertEquals("Testa quantidade de conexões do dispositivo A antes de remover uma de suas conexões.",
                2, controller.getMapVertex().get("A").getMapEdge().size());
        controller.removeEdge("A", "B");
        assertEquals("Testa se remover a conexão entre dois dispositivos afeta as demais conexões.",
                1, controller.getMapVertex().get("A").getMapEdge().size());
    }
    
    /**<TESTES DE IDENTIFICAR LISTAS DE TODAS AS MELHORES CONEXÕES DE UM PONTO A TODOS--->*/
    
    @Test
    public void testaMelhoresConexoesDeUmDispositivoSemConexoesConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        assertNull("Testa se ocorrem erros ao testar a conexão de um dispositivo sem conexões.",
                controller.lowerCostPathToAll("A")[0]);
    }
    
    @Test
    public void testaMelhoresConexoesDeUmDispositivoInexistenteConferindoRetornoDoMetodo(){
        assertNull("Testa se ocorrem erros ao testar a conexão de um dispositivo sem conexões.",
                controller.lowerCostPathToAll("Inexistente")[0]);
    }
    
    @Test
    public void testaMelhoresConexoesDeUmDispositivoNaoTerminalComConexoesConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", false);
        controller.addEdge("A", "B", 5);
        assertNull("Testa resultado da busca de conexões de um dispositivo terminal conectado outro dispositivo não terminal.",
                controller.lowerCostPathToAll("A")[0]);
    }
    
    @Test
    public void testaMelhoresConexoesDeUmDispositivoComConexoesConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addEdge("A", "B", 5);
        assertEquals("Testa resultado da busca de conexões de um dispositivo com uma conexão.",
                "[A] -> [B]",controller.lowerCostPathToAll("A")[0]);
    }
    
    @Test
    public void testaMelhoresConexoesDeUmDispositivoComMaisDeUmaConexaoConferindoRetornoDoMetodo(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addVertex("C", true);
        controller.addEdge("A", "C", 10);
        controller.addEdge("A", "B", 1);
        controller.addEdge("B", "C", 1);
        assertEquals("Testa resultado da busca de conexões de um dispositivo com mais de uma conexão.",
                "[A] -> [B]", controller.lowerCostPathToAll("A")[0]);
        assertEquals("Testa resultado da busca de conexões de um dispositivo com mais de uma conexão.",
                "[A] -> [B] -> [C]", controller.lowerCostPathToAll("A")[1]);
    }
    
    /**<TESTES DE IDENTIFICAR A MELHOR CONEXÃO DE UM PONTO A OUTRO------------->*/
    
    @Test
    public void testaMelhorConexaoDeUmDispositivoExistenteComUmInexistente(){
        controller.addVertex("A", true);
        assertNotNull("Testa se buscar a melhor conexão de um dispositivo existente com um inexistente provoca falhas.",
                controller.lowerCostPathTo("A", "Inexistente B"));
        
    }
    
    @Test
    public void testaMelhorConexaoDeDoisDispositivosInexistentes(){
        assertNotNull("Testa se buscar a melhor conexão de dois dispositivo inexistentes provoca falhas.",
                controller.lowerCostPathTo("Inexistente A", "Inexistente B"));
    }
    
    @Test
    public void testaMelhorConexaoDeDoisDispositivosExistentesEConectados(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addEdge("A", "B", 5);
        assertNull("Testa se buscar a melhor conexão de um dispositivo conectado com outro provoca flhas.",
                controller.lowerCostPathTo("A", "B"));
    }
    
    @Test
    public void testaMelhorConexaoDeDoisDispositivosExistentesENaoConectados(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        assertNotNull("Testa se buscar a melhor conexão de um dispositivo não conectado com outro provoca flhas.",
                controller.lowerCostPathTo("A", "B"));
    }
    
    @Test 
    public void testaMelhorConexaoComMarcacaoDeCaminho(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addVertex("C", true);        
        controller.addEdge("A", "B", 5);
        controller.addEdge("A", "C", 1);
        controller.addEdge("C", "B", 1);
        controller.lowerCostPathTo("A", "B");
        assertTrue("Testa se o caminho de A para C foi marcado.",
                controller.getMapVertex().get("A").getMapEdge().get(controller.getMapVertex().get("C")).isTurnOn());
        assertTrue("Testa se o caminho de C para B foi marcado.",
                controller.getMapVertex().get("C").getMapEdge().get(controller.getMapVertex().get("B")).isTurnOn());
    }
    
    @Test 
    public void testaSeMelhorConexaoComMarcacaoDeCaminhoNaoAfetaOutrosCaminhosQueNaoSejamOsMelhores(){
        controller.addVertex("A", true);
        controller.addVertex("B", true);
        controller.addVertex("C", true);        
        controller.addEdge("A", "B", 5);
        controller.addEdge("A", "C", 1);
        controller.addEdge("C", "B", 1);
        controller.lowerCostPathTo("A", "B");
        assertFalse("Testa se o caminho de A para B não foi marcado.",
                controller.getMapVertex().get("A").getMapEdge().get(controller.getMapVertex().get("B")).isTurnOn());
    }
    
    @Test 
    public void testaSeMelhorConexaoComMarcacaoDeCaminhoDeTerminalComNaoTerminal(){
        controller.addVertex("A", true);
        controller.addVertex("B", false);
        controller.addVertex("C", true);        
        controller.addEdge("A", "B", 5);
        controller.addEdge("A", "C", 1);
        controller.addEdge("C", "B", 1);
        controller.lowerCostPathTo("A", "B");
        assertFalse("Testa se o caminho de A para C não foi marcado.",
                controller.getMapVertex().get("A").getMapEdge().get(controller.getMapVertex().get("C")).isTurnOn());
        assertFalse("Testa se o caminho de C para B não foi marcado.",
                controller.getMapVertex().get("C").getMapEdge().get(controller.getMapVertex().get("B")).isTurnOn());
    }
    
    @Test 
    public void testaSeMelhorConexaoComMarcacaoDeCaminhoDeDoisNaoTerminais(){
        controller.addVertex("A", false);
        controller.addVertex("B", false);
        controller.addVertex("C", true);        
        controller.addEdge("A", "B", 5);
        controller.addEdge("A", "C", 1);
        controller.addEdge("C", "B", 1);
        controller.lowerCostPathTo("A", "B");
        assertFalse("Testa se o caminho de A para C não foi marcado.",
                controller.getMapVertex().get("A").getMapEdge().get(controller.getMapVertex().get("C")).isTurnOn());
        assertFalse("Testa se o caminho de C para B não foi marcado.",
                controller.getMapVertex().get("C").getMapEdge().get(controller.getMapVertex().get("B")).isTurnOn());
    }
    
}
