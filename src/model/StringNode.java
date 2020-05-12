package model;

/**
 * Classe responsável por constituir-se os nós da pilha de strings.
 * @author Everton Bruno Silva dos Santos
 */
public class StringNode {
    private final String string;
    private StringNode next;
    
    /**
     * Construtor responsável por inicializar a classe, bem como seu atributo.
     * @param string Refere-se a string que será armazenada.
     */
    public StringNode(final String string) {
        this.string = string;
        next = null;
    }

    /**
     * Método responsável por obiter o próximo nó.
     * @return Retorna o próximo nó.
     */
    public StringNode getNext() {
        return next;
    }

    /**
     * Método responsável por alterar o próximo nó.
     * @param next Refere-se ao próximo nó.
     */
    public void setNext(final StringNode next) {
        this.next = next;
    }

    /**
     * Método responsável por obiter a string armazenada no nó.
     * @return Retorna string armazenada no nó.
     */
    public String getString() {
        return string;
    }

}
