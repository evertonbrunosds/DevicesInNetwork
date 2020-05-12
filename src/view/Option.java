package view;

import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Vertex;

/**
 * Classe responsável por efetuar o processo de comunicação entre o usuário e a aplicação por meio da interface gráfica.
 * @author Everton Bruno Silva dos Santos
 */
public class Option {
    
    /**
     * Método responsável por criar novos arquivos.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void newFile(MainForm mainForm){
        mainForm.controller.getMapVertex().clear();
    }
    
    /**
     * Método responsável por carregar arquivos.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void openFile(MainForm mainForm){
        JFileChooser openFileDialog = new JFileChooser();
        openFileDialog.setDialogTitle("Abrir Arquivo");
        int result = openFileDialog.showOpenDialog(mainForm);
        if (result == JFileChooser.APPROVE_OPTION) {
            if(mainForm.controller.loadFromFile(openFileDialog.getSelectedFile().toString()) != null){
                JOptionPane.showMessageDialog(null, "O arquivo '" + openFileDialog.getSelectedFile().getName()+ "' não existe ou não pode ser carregado!", "Falha na Operação!",JOptionPane.OK_OPTION);
            }
        }
    }
    
    /**
     * Método responsável por gravar arquivos.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void saveFile(MainForm mainForm){
        JFileChooser saveFileDialog = new JFileChooser();
        saveFileDialog.setDialogTitle("Salvar Arquivo");
        int result = saveFileDialog.showSaveDialog(mainForm);
        if (result == JFileChooser.APPROVE_OPTION) {
            if(mainForm.controller.saveFromFile(saveFileDialog.getSelectedFile().toString()) != null){
                JOptionPane.showMessageDialog(null, "O arquivo '" + saveFileDialog.getSelectedFile().getName() + "' não pode ser gravado!", "Falha na Operação!",JOptionPane.OK_OPTION);
            }
        }
    }
    
    /**
     * Método responsável por adicionar dispositivos a rede.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void addDevice(MainForm mainForm){
        String caption = JOptionPane.showInputDialog(null,"Informe o nome do dispositivo:", "Adicionar Dispositivo", JOptionPane.QUESTION_MESSAGE);
        if(caption != null){
            boolean isTerminal = 0 == JOptionPane.showConfirmDialog(null,"O dispositivo '"+caption+"' é um terminal?", "Adicionar Dispositivo", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
            if(mainForm.controller.addVertex(caption, isTerminal) != null){
                JOptionPane.showMessageDialog(null, "O dispositivo '"+caption+"' já existe!", "Falha na Operação!",JOptionPane.OK_OPTION);
            }
        }
    }
    
    /**
     * Método responsável por remover dispositivos da rede.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void removeDevice(MainForm mainForm){
        if(mainForm.controller.getMapVertex().size() > 0){
            String caption = SelectOptionList.selectVertex(new String[mainForm.controller.getMapVertex().size()], null, mainForm.controller.getMapVertex(), "Selecione o dispositivo: ", "Remover Dispositivo");
            if(caption != null){
                mainForm.controller.removeVertex(caption);
            }
        } else {
            JOptionPane.showMessageDialog(null, "O sistema deve conter ao menos um dispositivo na rede!", "Falha na Operação!", JOptionPane.OK_OPTION);
        }
    }
    
    /**
     * Método responsável por adicionar conexões entre dispositivos da rede.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void addConnection(MainForm mainForm){
        if(mainForm.controller.getMapVertex().size() > 1){
            String captionOne = SelectOptionList.selectVertex(new String[mainForm.controller.getMapVertex().size()], null, mainForm.controller.getMapVertex(), "Selecione o primeiro dispositivo: ", "Adicionar Conexão");
            if(captionOne != null){
                String captionTwo = SelectOptionList.selectVertex(new String[mainForm.controller.getMapVertex().size()-1], captionOne, mainForm.controller.getMapVertex(), "Selecione o segundo dispositivo: ", "Adicionar Conexão");
                if(captionTwo != null){
                    int weigthInt;
                    String weigthStr;
                    while (true) {
                        try {
                            weigthStr = JOptionPane.showInputDialog(null,"Informe o peso da conexão:", "Adicionar Conexão", JOptionPane.QUESTION_MESSAGE);
                            if(weigthStr == null){                              //o usuário cancelou a operação
                                break;
                            }
                            weigthInt = Integer.parseInt(weigthStr);            //converte a string para inteiro
                            if(weigthInt > 0){                                 //o usuário digitou um valor válido
                                if(mainForm.controller.addEdge(captionOne, captionTwo, weigthInt) != null){
                                    JOptionPane.showMessageDialog(null, "O dispositivo '"+captionOne+"' não pode efetuar múltiplas conexões com o dispositivo '"+captionTwo+"'!", "Falha na Operação!",JOptionPane.OK_OPTION);
                                }
                                break;
                            } else {                                            //o usuário digitou um valor inferior à zero
                                JOptionPane.showMessageDialog(null, "O valor não pode ser menor ou igual a zero!", "Falha na Operação!",JOptionPane.OK_OPTION);
                            }
                        } catch (final NumberFormatException exception) {       //o usuário não digitou um número inteiro
                            JOptionPane.showMessageDialog(null, "Por favor, digite um valor numérico inteiro!", "Falha na Operação!",JOptionPane.OK_OPTION);
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "O sistema deve conter mais de um dispositivo na rede!", "Falha na Operação!", JOptionPane.OK_OPTION);
        }
    }
    
    /**
     * Método responsável por remover conexões de dispositivos conectados a rede.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void removeConnection(MainForm mainForm){
        if(mainForm.controller.getMapVertex().size() > 1){
            String captionOne = SelectOptionList.selectVertex(new String[mainForm.controller.getMapVertex().size()], null, mainForm.controller.getMapVertex(), "Selecione o primeiro dispositivo: ", "Remover Conexão");
            if(captionOne != null){
                String captionTwo = SelectOptionList.selectVertex(new String[mainForm.controller.getMapVertex().size()-1], captionOne, mainForm.controller.getMapVertex(), "Selecione o segundo dispositivo: ", "Remover Conexão");
                if(captionTwo != null){
                    mainForm.controller.removeEdge(captionOne, captionTwo);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "O sistema deve conter mais de um dispositivo na rede!", "Falha na Operação!", JOptionPane.OK_OPTION);
        }
    }
    
    /**
     * Método responsável por identificar todas as melhores conexões de um dispositivo a todos os demais da rede aos quais ele pode se conectar.
     * @param mainForm Refere-se ao JFrame da aplicação.
     * @param visualVertex Refere-se ao vértice selecionado.
     */
    public static void identifyBestAllConnections(MainForm mainForm, VisualVertex visualVertex){
        if(mainForm.controller.getMapVertex().size() > 1){
            if(visualVertex.getVertex().isTerminal()){
                String[] strings = mainForm.controller.lowerCostPathToAll(visualVertex.getVertex().getCaption());
                if(strings[0] == null){
                    JOptionPane.showMessageDialog(null, "Sem conexão com outros terminais!","Conexões Identificadas", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, strings,"Conexões Identificadas", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "O dispositivo selecionado não é um terminal!", "Falha na Operação!",JOptionPane.OK_OPTION);
            }
        } else {
            JOptionPane.showMessageDialog(null, "O sistema deve conter mais de um dispositivo na rede!", "Falha na Operação!",JOptionPane.OK_OPTION);
        }
    }
    
    /**
     * Método responsável por identificar a distância euclidiana entre dois dispositivos.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void identifyDistance(MainForm mainForm){
        if(mainForm.mapVisualVertex.size() > 1){
            String captionOne = SelectOptionList.selectVisualVertex(new String[mainForm.mapVisualVertex.size()], null, mainForm.mapVisualVertex, "Selecione um Terminal: ", "Calcular Distância Euclidiana");
            if(captionOne != null){
                String captionTwo = SelectOptionList.selectVisualVertex(new String[mainForm.mapVisualVertex.size()-1], captionOne, mainForm.mapVisualVertex, "Selecione um Terminal: ", "Calcular Distância Euclidiana");
                if(captionTwo != null){
                    JOptionPane.showMessageDialog(null, mainForm.mapVisualVertex.get(captionOne).findDistance(mainForm.mapVisualVertex.get(captionTwo)), "Distância Euclidiana",JOptionPane.OK_OPTION);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "O sistema deve conter mais de um dispositivo na rede!", "Falha na Operação!",JOptionPane.OK_OPTION);
        }
    }
    
    /**
     * Método responsável por identificar a melhor conexão de um dispositivo a outro.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void identfyBestConnection(MainForm mainForm){
        HashMap<String,Vertex> mapTerminalVertex = mainForm.controller.getMapTerminalVertex();
        if(mapTerminalVertex.size() > 1){
            String captionOne = SelectOptionList.selectVertex(new String[mapTerminalVertex.size()], null, mapTerminalVertex, "Selecione um Terminal: ", "Identificar Menor Caminho");
            if(captionOne != null){
                String captionTwo = SelectOptionList.selectVertex(new String[mapTerminalVertex.size()-1], captionOne, mapTerminalVertex, "Selecione um Terminal: ", "Identificar Menor Caminho");
                if(captionTwo != null){
                    if(mainForm.controller.lowerCostPathTo(captionOne, captionTwo) != null){
                        JOptionPane.showMessageDialog(null, "Não há conexão entre os nós selecionados.", "Falha na Operação!",JOptionPane.OK_OPTION);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "O sistema deve conter mais de um dispositivo na rede!", "Falha na Operação!",JOptionPane.OK_OPTION);
        }
    }
    
    /**
     * Método responsável por desmarcar todas as conexões marcadas.
     * @param mainForm Refere-se ao JFrame da aplicação.
     */
    public static void markOffAllConnections(MainForm mainForm){
        mainForm.controller.turnOffAllPath();
    }
    
}
