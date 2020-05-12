package view;

import control.Controller;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Classe responsável por constituir-se área de interação de homem/máquina.
 * @author Everton Bruno Silva dos Santos
 */
public class MainForm extends javax.swing.JFrame {
    protected HashMap<String,VisualVertex>  mapVisualVertex;
    protected final Controller controller;

    public MainForm() {
        this.mapVisualVertex = new HashMap<>();
        this.controller = new Controller();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround = new javax.swing.JPanel();
        mainMenuBar = new javax.swing.JMenuBar();
        subMenuFile = new javax.swing.JMenu();
        newFile = new javax.swing.JMenuItem();
        openFile = new javax.swing.JMenuItem();
        saveFile = new javax.swing.JMenuItem();
        subMenuTool = new javax.swing.JMenu();
        addDevice = new javax.swing.JMenuItem();
        removeDevice = new javax.swing.JMenuItem();
        subMenuConnection = new javax.swing.JMenu();
        identify = new javax.swing.JMenu();
        identifyDistance = new javax.swing.JMenuItem();
        identifyBestConnection = new javax.swing.JMenuItem();
        addConnection = new javax.swing.JMenuItem();
        removeConnection = new javax.swing.JMenuItem();
        markOff = new javax.swing.JMenuItem();
        subMenuAbout = new javax.swing.JMenu();
        author = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DevicesInNetwork v9.2");
        setBackground(new java.awt.Color(51, 51, 51));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/appIcon.png")));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        backGround.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout backGroundLayout = new javax.swing.GroupLayout(backGround);
        backGround.setLayout(backGroundLayout);
        backGroundLayout.setHorizontalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        backGroundLayout.setVerticalGroup(
            backGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        subMenuFile.setText("Arquivo");

        newFile.setText("Novo");
        newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileActionPerformed(evt);
            }
        });
        subMenuFile.add(newFile);

        openFile.setText("Abrir");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        subMenuFile.add(openFile);

        saveFile.setText("Salvar");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        subMenuFile.add(saveFile);

        mainMenuBar.add(subMenuFile);

        subMenuTool.setText("Dispositivos");

        addDevice.setText("Adicionar");
        addDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDeviceActionPerformed(evt);
            }
        });
        subMenuTool.add(addDevice);

        removeDevice.setText("Remover");
        removeDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDeviceActionPerformed(evt);
            }
        });
        subMenuTool.add(removeDevice);

        mainMenuBar.add(subMenuTool);

        subMenuConnection.setText("Conexões");

        identify.setText("Identificar");

        identifyDistance.setText("Distância Euclidiana");
        identifyDistance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identifyDistanceActionPerformed(evt);
            }
        });
        identify.add(identifyDistance);

        identifyBestConnection.setText("Melhor Trajeto");
        identifyBestConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identifyBestConnectionActionPerformed(evt);
            }
        });
        identify.add(identifyBestConnection);

        subMenuConnection.add(identify);

        addConnection.setText("Adicionar");
        addConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addConnectionActionPerformed(evt);
            }
        });
        subMenuConnection.add(addConnection);

        removeConnection.setText("Remover");
        removeConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeConnectionActionPerformed(evt);
            }
        });
        subMenuConnection.add(removeConnection);

        markOff.setText("Desmarcar");
        markOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markOffActionPerformed(evt);
            }
        });
        subMenuConnection.add(markOff);

        mainMenuBar.add(subMenuConnection);

        subMenuAbout.setText("Sobre");

        author.setText("Autores");
        author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorActionPerformed(evt);
            }
        });
        subMenuAbout.add(author);

        mainMenuBar.add(subMenuAbout);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void markOffActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.markOffAllConnections(this);
        VisualGraph.update(this, backGround);
    }

    private void removeConnectionActionPerformed(java.awt.event.ActionEvent evt) {
        Option.removeConnection(this);
        VisualGraph.update(this, this.backGround);
    }

    private void newFileActionPerformed(java.awt.event.ActionEvent evt) {
        Option.newFile(this);
        VisualGraph.update(this, this.backGround);
    }

    private void openFileActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.openFile(this);
        VisualGraph.update(this, this.backGround);
    }

    private void saveFileActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.saveFile(this);
    }

    private void addDeviceActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.addDevice(this);
        VisualGraph.update(this, this.backGround);
    }

    private void addConnectionActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.addConnection(this);
        VisualGraph.update(this, this.backGround);
    }

    private void removeDeviceActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.removeDevice(this);
        VisualGraph.update(this, this.backGround);
    }

    private void identifyBestConnectionActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.identfyBestConnection(this);
        VisualGraph.update(this, this.backGround);
    }

    private void authorActionPerformed(final java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, new String[] {"Everton Bruno Silva dos Santos - 19111746"}, "Sobre Autores", JOptionPane.INFORMATION_MESSAGE);
    }

    private void identifyDistanceActionPerformed(final java.awt.event.ActionEvent evt) {
        Option.identifyDistance(this);
    }

    private void formComponentResized(final java.awt.event.ComponentEvent evt) {
        VisualGraph.update(this, this.backGround);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String args[]) {
        try {
            for (final javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addConnection;
    private javax.swing.JMenuItem addDevice;
    private javax.swing.JMenuItem author;
    private javax.swing.JPanel backGround;
    private javax.swing.JMenu identify;
    private javax.swing.JMenuItem identifyBestConnection;
    private javax.swing.JMenuItem identifyDistance;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem markOff;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JMenuItem removeConnection;
    private javax.swing.JMenuItem removeDevice;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JMenu subMenuAbout;
    private javax.swing.JMenu subMenuConnection;
    private javax.swing.JMenu subMenuFile;
    private javax.swing.JMenu subMenuTool;
    // End of variables declaration//GEN-END:variables
}
