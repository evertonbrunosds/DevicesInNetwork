package model;

/**
 * Classe responsável por constituir-se como uma pilha de strings.
 * @author Everton Bruno Silva dos Santos
 */
public class PathStack {
    private StringNode stringNode;
    
    /**
     * Construtor responsável por inicializar a classe, bem como seu atributo.
     */
    public PathStack(){
        stringNode = null;
    }
    
    /**
     * Método responsável por empilhar strings.
     * @param string 
     */
    public void push(final String string) {
        final StringNode newStringNode = new StringNode(string);
        newStringNode.setNext(stringNode);
        stringNode = newStringNode;
    }
    
    /**
     * Método responsável por retornar a lista de strings armazenadas.
     * @return Retorna strings armazenadas.
     */
    public String getStrings(){
        String string = "";
        for(StringNode element = stringNode; element != null; element = element.getNext()){
            string += "["+element.getString()+"]";
            if(element.getNext() != null){
                string+=" -> ";
            }
        }
        return string;
    }
}
