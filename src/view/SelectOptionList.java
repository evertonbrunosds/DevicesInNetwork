package view;

import java.util.HashMap;
import javax.swing.JOptionPane;
import model.Vertex;

/**
 * Classe responsável por constituir-se janela de seleção de vértices.
 * @author Everton Bruno Silva dos Santos
 */
public class SelectOptionList {

    /**
     * Método responsável por selecionar um vértice dentre todos disponíveis.
     * @param itens Refere-se ao itens que o usuário pode selecionar.
     * @param excectCaption Refere-se a assinatura de um dado vértice que não pode ser selecionado.
     * @param mapVertex Refere-se ao mapa de vértices.
     * @param msg Refere-se a mensagem da janela de seleção.
     * @param title Refere-se eu título da janela de seleção.
     * @return Refere-se a assinatura do vértice selecionado.
     */
    public static String selectVertex(final String[] itens, final String excectCaption, final HashMap<String,Vertex> mapVertex, final String msg, final String title){
        int index = 0;        
        for(final HashMap.Entry<String,Vertex> element : mapVertex.entrySet()){
            if(excectCaption != null){
                if(!element.getValue().getCaption().equals(excectCaption)){
                    itens[index] = element.getValue().getCaption();
                    index++;
                }
            } else {
                itens[index] = element.getValue().getCaption();
                index++;
            }
        }
        return (String) JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE, null, itens, itens[0]);
    }
    
    /**
     * Método responsável por selecionar um vértice visual dentre todos disponíveis.
     * @param itens Refere-se ao itens que o usuário pode selecionar.
     * @param excectCaption Refere-se a assinatura de um dado vértice visual que não pode ser selecionado.
     * @param mapVisualVertex Refere-se ao mapa de vértices visuais.
     * @param msg Refere-se a mensagem da janela de seleção.
     * @param title Refere-se eu título da janela de seleção.
     * @return Refere-se a assinatura do vértice selecionado.
     */
    public static String selectVisualVertex(final String[] itens, final String excectCaption, final HashMap<String,VisualVertex> mapVisualVertex, final String msg, final String title){
        int index = 0;        
        for(final HashMap.Entry<String,VisualVertex> element : mapVisualVertex.entrySet()){
            if(excectCaption != null){
                if(!element.getValue().getVertex().getCaption().equals(excectCaption)){
                    itens[index] = element.getValue().getVertex().getCaption();
                    index++;
                }
            } else {
                itens[index] = element.getValue().getVertex().getCaption();
                index++;
            }
        }
        return (String) JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE, null, itens, itens[0]);
    }
    
}
