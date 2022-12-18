/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import presentacion.Modelo;
import presentacion.vistas.VistaPanelControl;

/**
 *
 * @author Asus
 */
public class VistaWidgetRAM extends JPanel{
    
    private VistaPanelControl vistaControl;
    
    private GridBagConstraints gridConstraint;
    
    private JButton[][] btnArrayBotones;
    
    private JButton btnLimpiarMemoria;
    
    private JButton btnMostrarOpcodes;
    
    private JButton btnResaltarMAR;
    
    private JPanel panelPadre;
      
    private Modelo modelo;
    
    private Byte valorMAR;
    
    private boolean resaltarMAR;
            
    //Constantes de diseño
    private static final Dimension buttonSize = new Dimension(22, 22);
    private static final Dimension WIDGET_SIZE = new Dimension(280, 700);
    public static final Color COLOR_ON = new Color(246, 203, 225);
    public static final Color COLOR_OFF = new Color(246, 213, 203);
    public static final Color COLOR_MAR = Color.gray;
    public static final Border BOTTOM_BORDER = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
    public static final Border RIGHT_BORDER = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
    public static final Border BOTTOM_RIGHT_BORDER = BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK);
    public static final Border TOP_LEFT_RIGHT_BORDER = BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK);
    public static final Border LEFT_RIGHT_BORDER = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK);
    public static final Border FULL_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    public static final String MAR_ON_LABEL = "[ON] Mostrar MAR en RAM";
    public static final String MAR_OFF_LABEL = "[OFF] Mostrar MAR en RAM";
    
     public VistaWidgetRAM(Modelo m, JPanel parentPanel){
        this.modelo = m;
        
        //Sistema SAP del modelo
        //this.sistema = modelo.getSistema();

        this.valorMAR = 0;
        this.panelPadre = parentPanel;

        this.vistaControl = (VistaPanelControl) parentPanel;
        this.resaltarMAR = true;

        // Agregar manejador del observador 
        //this.sistema.getRAM().addRAMObserver(getControl());

        crearBotones();

        // Tamaño ocupado
        this.setPreferredSize(WIDGET_SIZE);
        this.setBackground(new Color(223, 220, 206));

        // Layout
        this.setLayout(new GridBagLayout());
        gridConstraint = new GridBagConstraints();
        gridConstraint.fill = GridBagConstraints.HORIZONTAL;

        mostrarBtnOP();
        mostrarBtnResaltarMAR();
        mostrarContenidoRAM();
        //getControl().cambioMAR(this.valorMAR);

        repaint();
    }
     
    // Crea el array de botones que representan cada bit en cada posición de memory
     public void crearBotones(){
       
        btnArrayBotones = new JButton[32][8]; // 32 posiciones de 8 bit cada una
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 8; j++) {
                //Busca en la RAM cada una de 
                //this.btnArrayBotones[i][j] = new JButton("" + getControl().buscarEnRAM(i, 7 - j));
                this.btnArrayBotones[i][j]= new JButton("0");
                this.btnArrayBotones[i][j].setPreferredSize(buttonSize);
                this.btnArrayBotones[i][j].setActionCommand(i + "," + j);
                //this.btnArrayBotones[i][j].addActionListener(getControl());
                this.btnArrayBotones[i][j].setBorder(null);
                this.btnArrayBotones[i][j].setBackground(btnArrayBotones[i][j].getText().equals("1") ? COLOR_ON : COLOR_OFF);
                this.btnArrayBotones[i][j].setOpaque(true);
            }
        }

     }
     
     //boton de borrar RAM
     public void agregarBtnRAM(){
         // Botón borrar RAM 
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 0;
        gridConstraint.gridwidth = 9;
        this.btnLimpiarMemoria = new JButton("Borrar memoria");
        this.btnLimpiarMemoria.setActionCommand("clearmem");
        //this.btnLimpiarMemoria.addActionListener(getControl());
        this.add(this.btnLimpiarMemoria, gridConstraint);
     }
     
     public void mostrarBtnOP(){
         
        // Botón OPCodes 
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 1;
        gridConstraint.gridwidth = 9;
        this.btnMostrarOpcodes = new JButton("Mostrar Códigos de Operación");
        this.btnMostrarOpcodes.setActionCommand("showopcodes");
       // this.btnMostrarOpcodes.addActionListener(getControl());
        this.add(this.btnMostrarOpcodes, gridConstraint);
     }
     
      // Botón resaltar posición de memoria respecto al MAR 
     public void mostrarBtnResaltarMAR(){
        gridConstraint.gridx = 0;
        gridConstraint.gridy = 5;
        gridConstraint.gridwidth = 9;
        this.btnResaltarMAR = new JButton(this.resaltarMAR ? MAR_ON_LABEL : MAR_OFF_LABEL);
        this.btnResaltarMAR.setActionCommand("toggleMAR");
       // this.btnResaltarMAR.addActionListener(getControl());
        this.add(this.btnResaltarMAR, gridConstraint);
     }
     
     public void mostrarContenidoRAM(){
         // Contenido RAM 
        gridConstraint.gridx = 0;
        gridConstraint.gridheight = 1;
        gridConstraint.gridwidth = 10;
        gridConstraint.gridy = 6;
        JLabel tmp = new JLabel("Contenido de Memoria");
        tmp.setHorizontalAlignment(SwingConstants.CENTER);
        tmp.setBorder(FULL_BORDER);
        this.add(tmp, gridConstraint);
        
        //

        // El contenido de la memoria
        gridConstraint.gridx = 3;
        gridConstraint.gridwidth = 1;
        gridConstraint.fill = GridBagConstraints.BOTH;
        for (int i = 1; i <= 32; i++) {
            gridConstraint.gridx = 1;
            gridConstraint.gridy = i + 5 + 1;
            String n = String.format("%5s", Integer.toBinaryString(i - 1)).replace(" ", "0");
            JLabel tmp1 = new JLabel(" [" + n + "] ");
            tmp1.setBorder(FULL_BORDER);
            this.add(tmp1, gridConstraint);

            for (int j = 2; j < 10; j++) {
                gridConstraint.gridx = j;
                this.add(btnArrayBotones[gridConstraint.gridy - 1 - 5 - 1][j - 2], gridConstraint);
            }
        }

        // Agregue los bordes derechos a la visualización de RAM
        for (int i = 0; i < this.btnArrayBotones.length; i++) {
            this.btnArrayBotones[i][this.btnArrayBotones[0].length - 1].setBorder(RIGHT_BORDER);
        }

        // Agregue el borde inferior a la visualización de RAM
        for (int i = 0; i < this.btnArrayBotones[0].length; i++) {
            // La pieza inferior derecha tiene un borde especial
            if (i == 7) {
                this.btnArrayBotones[this.btnArrayBotones.length - 1][i]
                        .setBorder(BOTTOM_RIGHT_BORDER);
            } else {
                this.btnArrayBotones[this.btnArrayBotones.length - 1][i]
                        .setBorder(BOTTOM_BORDER);
            }
        }
         
     }
    
}
