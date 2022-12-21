/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.vistas;


import Logica.Procesador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import presentacion.Modelo;
import presentacion.controladores.ControladorWidgetSAP;

/**
 *
 * @author Asus
 */
public class VistaWidgetSAP extends JPanel{
     // Contenido del widget
    private JLabel lblRegA;
    private JLabel lblRegB;
    private JLabel lblALU;
    private JLabel lblPC;
    private JLabel lblOut;
    private JLabel lblIR;
    private JLabel lblMAR;
    private JLabel lblControles;
    private JLabel lblStepCount;
    private JLabel lblBus;
    private JLabel lblStepCt;
    private JLabel lblFlags;

    private JLabel[] btns_bitsIR;
    private JLabel[] btns_bitsOUT;
    private JLabel[] btns_bitsControl;
    
    private JLabel lblBUSBin;
    private JLabel lblBUSEn;
    
    private JLabel lblOper1Bin;
    private JLabel lblOper1En;
    
    private JLabel lblOper2Bin;
    private JLabel lblOper2En;
    
    private JLabel lblALUBin;
    private JLabel lblALUEn;
 
    private JLabel lblPCBin;
    private JLabel lblPCEn;
    
     
    private JLabel lblMARBin;
    private JLabel lblMAREn;
    
    private JLabel lblIRBin;
    
    private final Modelo modelo;  
     
    private JLabel btnCarry;
    private JLabel btnZero;
    private VistaWidgetRAM ramWidget;
    private Procesador procs;
    private ControladorWidgetSAP control;
    
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
    public VistaWidgetSAP(Modelo m, VistaWidgetRAM ramWidget, Procesador procs) {
        // Encapsula el modelo
        
        this.procs=procs;
        this.modelo = m;        
        //this.sistema = m.getSistema();
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
        lblBUSBin = new JLabel("011010101010010101011010110001110");
        lblBUSBin.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblBUSBin,gridConstraint);
        
        gridConstraint.gridy = 0;
        gridConstraint.gridx = 3;
        lblBUSEn= new JLabel("022333");
        lblBUSEn.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblBUSEn,gridConstraint);
        
        //  Operando 1
        gridConstraint.gridy = 2;
        gridConstraint.gridx = 1;
        lblOper1Bin = new JLabel("011010101010010101011010110001110");
        lblOper1Bin.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblOper1Bin,gridConstraint);
    
        gridConstraint.gridy = 2;
        gridConstraint.gridx = 3;
        lblOper1En= new JLabel("12333");
        lblOper1En.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblOper1En,gridConstraint);
        
        

        // Operando 2
        gridConstraint.gridy = 4;
        gridConstraint.gridx = 1;
        lblOper2Bin = new JLabel("011010101010010101011010110001110");
        lblOper2Bin.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblOper2Bin,gridConstraint);
        
        
        gridConstraint.gridy = 4;
        gridConstraint.gridx = 3;
        lblOper2En= new JLabel("02333");
        lblOper2En.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblOper2En,gridConstraint);
        
        
        //  ALU 
        gridConstraint.gridy = 6;
        gridConstraint.gridx = 1;
        lblALUBin = new JLabel("011010101010010101011010110001110");
        lblALUBin.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblALUBin,gridConstraint);
        
        gridConstraint.gridy = 6;
        gridConstraint.gridx = 3;
        lblALUEn= new JLabel("02333");
        lblALUEn.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblALUEn,gridConstraint);
        
        
        //  IR 
        gridConstraint.gridy = 8;
        gridConstraint.gridx = 1;
        lblIRBin = new JLabel("1010101010101011101001");
        lblIRBin.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblIRBin,gridConstraint);
        

        //  PC
        gridConstraint.gridy = 10;
        gridConstraint.gridx = 1;
        lblPCBin = new JLabel("011010101010010101011010110001110");
        lblPCBin.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblPCBin,gridConstraint);
        
        gridConstraint.gridy = 10;
        gridConstraint.gridx = 3;
        lblPCEn= new JLabel("02333");
        lblPCEn.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblPCEn,gridConstraint);
          

        //  MAR
        gridConstraint.gridy = 14;
        gridConstraint.gridx = 1;
        lblMARBin = new JLabel("011010101010010101011010110001110");
        lblMARBin.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblMARBin,gridConstraint);
        
        gridConstraint.gridy = 14;
        gridConstraint.gridx = 3;
        lblMAREn= new JLabel("02333");
        lblMAREn.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(lblMAREn,gridConstraint);
        
        
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
        getControl();
    }
    
    protected JLabel crearLabel(String str){
        JLabel b = new JLabel(str);
        b.setPreferredSize(BUTTON_SIZE);
        b.setBackground(BUTTON_UNSELECTED_BG);
        b.setFont(new java.awt.Font("Arial", 0, 15)); 
        b.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        b.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b.setOpaque(true)
                ;
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
        this.lblRegA = new JLabel("Operando 1");
        this.add(lblRegA, gridConstraint);

        //  B
        gridConstraint.gridy = 4;
        gridConstraint.gridx = 0;
        this.lblRegB = new JLabel("Operando 2");
        this.add(lblRegB, gridConstraint);

        // ALU 
        gridConstraint.gridy = 6;
        gridConstraint.gridx = 0;
        this.lblALU = new JLabel("ALU");
        this.add(lblALU, gridConstraint);

        generarDivision(7);
        //IR
        gridConstraint.gridy = 8;
        gridConstraint.gridx = 0;
        this.lblIR = new JLabel("IR");
        this.add(lblIR, gridConstraint);

         // PC 
        gridConstraint.gridy = 10;
        gridConstraint.gridx = 0;
        this.lblPC = new JLabel("Contador de Programa");
        this.add(lblPC, gridConstraint);

        // Line Break
        generarDivision(11);

     
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
    
    public ControladorWidgetSAP getControl(){
        if(this.control==null){
            this.control=new ControladorWidgetSAP(this,this.procs);
        }
        return this.control;
    }

    public JLabel[] getBtns_bitsIR() {
        return btns_bitsIR;
    }

    public JLabel[] getBtns_bitsOUT() {
        return btns_bitsOUT;
    }



    public JLabel[] getBtns_bitsControl() {
        return btns_bitsControl;
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

    public JLabel getLblPC() {
        return lblPC;
    }

    public JLabel getLblOut() {
        return lblOut;
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

    public JLabel getLblIR() {
        return lblIR;
    }

    public JLabel getLblBUSBin() {
        return lblBUSBin;
    }

    public JLabel getLblBUSEn() {
        return lblBUSEn;
    }

    public JLabel getLblOper1Bin() {
        return lblOper1Bin;
    }

    public JLabel getLblOper1En() {
        return lblOper1En;
    }

    public JLabel getLblOper2Bin() {
        return lblOper2Bin;
    }

    public JLabel getLblOper2En() {
        return lblOper2En;
    }

    public JLabel getLblALUBin() {
        return lblALUBin;
    }

    public JLabel getLblALUEn() {
        return lblALUEn;
    }

    public JLabel getLblPCBin() {
        return lblPCBin;
    }

    public JLabel getLblPCEn() {
        return lblPCEn;
    }

    public JLabel getLblMARBin() {
        return lblMARBin;
    }

    public JLabel getLblMAREn() {
        return lblMAREn;
    }

    public JLabel getLblIRBin() {
        return lblIRBin;
    }

    

}
