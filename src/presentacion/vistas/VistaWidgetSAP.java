/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import presentacion.Modelo;

/**
 *
 * @author Asus
 */
public class VistaWidgetSAP extends JPanel{
     // Contenido del widget
    private JLabel lblRegA;
    private JLabel lblRegB;
    private JLabel lblALU;
    private JLabel lblIR;
    private JLabel lblOut;
    private JLabel lblPC;
    private JLabel lblMAR;
    private JLabel lblControles;
    private JLabel lblStepCount;
    private JLabel lblBus;
    private JLabel lblStepCt;
    private JLabel lblFlags;

    private JLabel[] btns_bitsA;
    private JLabel[] btns_bitsB;
    private JLabel[] btns_bitsALU;
    private JLabel[] btns_bitsIR;
    private JLabel[] btns_bitsOUT;
    private JLabel[] btns_bitsPC;
    private JLabel[] btns_bitsMAR;
    private JLabel[] btns_bitsControl;
    private JLabel[] btns_bistBUS;
    
    
    private final Modelo modelo;  
     
    private JLabel btnCarry;
    private JLabel btnZero;
    private VistaWidgetRAM ramWidget;
    private VistaDisplaySieteSeg display7Seg; 
   
    //Controlador de la vista
    //private ControladorWindgetSAP control;
    
    private GridBagConstraints gridConstraint; 
    
    // Constantes
    public static final Dimension BUTTON_SIZE = new Dimension(22, 22);
    public static final Dimension WIDGET_SIZE = new Dimension(600, 200);
    public static final Color BUTTON_UNSELECTED_BG = new Color(238, 238, 238);
    public static final Color BUTTON_SELECTED_BG = new Color(255, 85, 85);
    public static final Color COLOR_BACKGROUND = new Color(203, 246, 245);
    public static final Color WIDGET_BORDER_COLOR = Color.BLACK;
    
    //Constructor con la vista de SieteSeg
    //public VistaWidgetSAP(Modelo m, VistaDisplaySieteSeg display, VistaWidgetRAM ramWidget) {
    public VistaWidgetSAP(Modelo m, VistaWidgetRAM ramWidget, VistaDisplaySieteSeg vista) {
        // Encapsula el modelo
        this.modelo = m;        
        //this.sistema = m.getSistema();
        this.display7Seg = vista;
        this.ramWidget = ramWidget;
        this.setBorder(BorderFactory.createLineBorder(WIDGET_BORDER_COLOR));

        //this.sistema.addObserver(getControl());  // Agrega el manejador del observador

        // Tamaño preferido
        this.setPreferredSize(WIDGET_SIZE);
        this.setBackground(COLOR_BACKGROUND);

        // Layout
        this.setLayout(new GridBagLayout());
        gridConstraint = new GridBagConstraints();
        gridConstraint.anchor = GridBagConstraints.WEST;
        gridConstraint.weightx = 1;
        gridConstraint.insets = new Insets(10, 10, 10, 0);

        crearEtiquetas();


       
        // Prepara espacio display 
        gridConstraint.anchor = GridBagConstraints.EAST;
        gridConstraint.fill = GridBagConstraints.HORIZONTAL;
        gridConstraint.insets = new Insets(0, 0, 0, 0);

        // Estados de las líneas de control
        this.btns_bitsControl = new JLabel[16];
        this.btns_bitsControl[0] = crearLabel("HLT"); 
        this.btns_bitsControl[1] = crearLabel("MI");
        this.btns_bitsControl[2] = crearLabel("RI");
        this.btns_bitsControl[3] = crearLabel("RO");
        this.btns_bitsControl[4] = crearLabel("IO");
        this.btns_bitsControl[5] = crearLabel("II");
        this.btns_bitsControl[6] = crearLabel("AI");
        this.btns_bitsControl[7] = crearLabel("AO");
        this.btns_bitsControl[8] = crearLabel("ΣO");
        this.btns_bitsControl[9] = crearLabel("SU");
        this.btns_bitsControl[10] = crearLabel("BI");
        this.btns_bitsControl[11] = crearLabel("OI");
        this.btns_bitsControl[12] = crearLabel("CE");
        this.btns_bitsControl[13] = crearLabel("CO");
        this.btns_bitsControl[14] = crearLabel("J");
        this.btns_bitsControl[15] = crearLabel("FI");

        gridConstraint.gridy = 18;
        for (int i = 0; i < 16; i++) {
            btns_bitsControl[i].setBackground(BUTTON_UNSELECTED_BG);
            if (i == 8) {
                gridConstraint.gridy++;
                gridConstraint.gridx = 1;
            } else if (i >= 8) {
                gridConstraint.gridx = i - 7;
            } else {
                gridConstraint.gridx = i + 1;
            }
            this.add(this.btns_bitsControl[i], gridConstraint);
        }

        //  BUS 
        gridConstraint.gridy = 0;
        gridConstraint.gridx = 1;
        btns_bistBUS = new JLabel[8];
        for (int i = 0; i <= 7; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bistBUS[i] = b;
        }

        //  A 
        gridConstraint.gridy = 2;
        gridConstraint.gridx = 1;
        btns_bitsA = new JLabel[8];
        for (int i = 0; i <= 7; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bitsA[i] = b;
        }

        // B 
        gridConstraint.gridy = 4;
        gridConstraint.gridx = 1;
        btns_bitsB = new JLabel[8];
        for (int i = 0; i <= 7; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bitsB[i] = b;
        }

        //  ALU 
        gridConstraint.gridy = 6;
        gridConstraint.gridx = 1;
        btns_bitsALU = new JLabel[8];
        for (int i = 0; i <= 7; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bitsALU[i] = b;
        }

        //  IR 
        gridConstraint.gridy = 8;
        gridConstraint.gridx = 1;
        btns_bitsIR = new JLabel[8];
        for (int i = 0; i <= 7; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bitsIR[i] = b;
        }

        //  out 
        gridConstraint.gridy = 12;
        gridConstraint.gridx = 1;
        btns_bitsOUT = new JLabel[8];
        for (int i = 0; i <= 7; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bitsOUT[i] = b;
        }

        //  PC
        gridConstraint.gridy = 10;
        gridConstraint.gridx = 1;
        btns_bitsPC = new JLabel[4];
        for (int i = 0; i <= 3; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bitsPC[i] = b;
        }

        //  MAR
        gridConstraint.gridy = 14;
        gridConstraint.gridx = 1;
        btns_bitsMAR = new JLabel[4];
        for (int i = 0; i <= 3; i++) {
            gridConstraint.gridx = i + 1;
            JLabel b = crearLabel("0"); 
            this.add(b, gridConstraint);
            btns_bitsMAR[i] = b;
        }

        //  Flags
        gridConstraint.gridy = 20;
        gridConstraint.gridx = 1;
        this.btnCarry = crearLabel("C");
        this.add(this.btnCarry, gridConstraint);
        gridConstraint.gridx = 2;
        this.btnZero = crearLabel("Z");
        this.add(this.btnZero, gridConstraint);

        //Controlador widgetSAP
        //getControl().cambioLineasControl();
        repaint();
    }
    
    protected JLabel crearLabel(String str){
        JLabel b = new JLabel(str);
        b.setPreferredSize(BUTTON_SIZE);
        b.setBackground(BUTTON_UNSELECTED_BG);
        b.setFont(new java.awt.Font("Arial", 0, 15)); 
        b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b.setOpaque(true);
        return b;
    }
    
    public void crearEtiquetas(){
        // Encabezado
        gridConstraint.gridx = 0;
        gridConstraint.fill = GridBagConstraints.HORIZONTAL;
        gridConstraint.gridy = 0;
        this.lblBus = new JLabel("BUS");
        this.add(this.lblBus, gridConstraint);

        // Etiquetas de registros
        gridConstraint.gridwidth = 1;
        gridConstraint.gridy = 2;
        gridConstraint.gridx = 0;
        this.lblRegA = new JLabel("Registro A");
        this.add(lblRegA, gridConstraint);

        //  B
        gridConstraint.gridy = 4;
        gridConstraint.gridx = 0;
        this.lblRegB = new JLabel("Registro B");
        this.add(lblRegB, gridConstraint);

        // ALU 
        gridConstraint.gridy = 6;
        gridConstraint.gridx = 0;
        this.lblALU = new JLabel("ALU");
        this.add(lblALU, gridConstraint);

        generarDivision(7);
        
         // PC 
        gridConstraint.gridy = 10;
        gridConstraint.gridx = 0;
        this.lblIR = new JLabel("Contador de Programa");
        this.add(lblIR, gridConstraint);

        // Line Break
        generarDivision(11);

        // OUT 
        gridConstraint.gridy = 12;
        gridConstraint.gridx = 0;
        this.lblOut = new JLabel("Salida");
        this.add(lblOut, gridConstraint);

        // Memory Address Register 
        gridConstraint.gridy = 14;
        gridConstraint.gridx = 0;
        this.lblMAR = new JLabel("MAR");
        this.add(lblMAR, gridConstraint);

        // Step Count 
        gridConstraint.gridy = 16;
        gridConstraint.gridx = 0;
        this.lblStepCount = new JLabel("Conteo de Ciclos");
        this.add(this.lblStepCount, gridConstraint);

        // Valor step count
        gridConstraint.gridy = 16;
        gridConstraint.gridx = 1;
        //Toma el valor del stepCount en el sistema
        //this.lblStepCt = new JLabel("" + this.sistema.getStepCount());
        this.lblStepCt = new JLabel("Contador");
        this.add(this.lblStepCt, gridConstraint);

        // Line Break
        generarDivision(17);

        // Lineas de Control 
        gridConstraint.gridwidth = 1;
        gridConstraint.gridy = 18;
        gridConstraint.gridx = 0;
        this.lblControles = new JLabel("Lineas de Control ");
        this.add(this.lblControles, gridConstraint);

        // Flags 
        gridConstraint.gridy = 20;
        gridConstraint.gridx = 0;
        this.lblFlags = new JLabel("Flags");
        this.add(this.lblFlags, gridConstraint);

    }
    
    public void generarDivision(int gridy){
        gridConstraint.gridx = 0;
        gridConstraint.gridy = gridy;
        gridConstraint.gridwidth = 12;
        this.add(new JLabel("========================================================================================"), gridConstraint);
        gridConstraint.gridwidth = 1;
    }
    
    public void getControl(){
        
    }

    public JLabel[] getBtns_bitsA() {
        return btns_bitsA;
    }

    public JLabel[] getBtns_bitsB() {
        return btns_bitsB;
    }

    public JLabel[] getBtns_bitsALU() {
        return btns_bitsALU;
    }

    public JLabel[] getBtns_bitsIR() {
        return btns_bitsIR;
    }

    public JLabel[] getBtns_bitsOUT() {
        return btns_bitsOUT;
    }

    public JLabel[] getBtns_bitsPC() {
        return btns_bitsPC;
    }

    public JLabel[] getBtns_bitsMAR() {
        return btns_bitsMAR;
    }

    public JLabel[] getBtns_bitsControl() {
        return btns_bitsControl;
    }

    public JLabel[] getBtns_bistBUS() {
        return btns_bistBUS;
    }

    public JLabel getBtnCarry() {
        return btnCarry;
    }

    public JLabel getBtnZero() {
        return btnZero;
    }

    public GridBagConstraints getGridConstraint() {
        return gridConstraint;
    }

    public VistaWidgetRAM getRamWidget() {
        return ramWidget;
    }

    public JLabel getLblRegA() {
        return lblRegA;
    }

    public JLabel getLblRegB() {
        return lblRegB;
    }

    public JLabel getLblALU() {
        return lblALU;
    }

    public JLabel getLblIR() {
        return lblIR;
    }

    public JLabel getLblOut() {
        return lblOut;
    }

    public JLabel getLblPC() {
        return lblPC;
    }

    public JLabel getLblMAR() {
        return lblMAR;
    }

    public JLabel getLblControles() {
        return lblControles;
    }

    public JLabel getLblStepCount() {
        return lblStepCount;
    }

    public JLabel getLblBus() {
        return lblBus;
    }

    public JLabel getLblStepCt() {
        return lblStepCt;
    }

    public JLabel getLblFlags() {
        return lblFlags;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public VistaDisplaySieteSeg getDisplay7Seg() {
        return display7Seg;
    }

    

}
