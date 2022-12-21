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
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import presentacion.Modelo;
import presentacion.controladores.ControladorModReg;
import presentacion.controladores.ControladorPanelControl;

/**
 *
 * @author Asus
 */
public class VistaPanelControl extends JPanel{
    
      private Modelo modelo;
      private Procesador procs;
      private VistaWidgetRAM vistaRAM;

    public Procesador getProcs() {
        return procs;
    }
      
      private VistaWidgetSAP vistaSAP;

      private VistaDisplaySieteSeg display7Seg;
      
      private VistaModReg vistaModRegistros;
      
      private ControladorPanelControl controlador;
      
      private JButton btnReset;
      
      private JButton btnPaso;
      
      private JButton btnEjecutar;
      
      private JSlider sliderVel;

    public JSlider getSliderVel() {
        return sliderVel;
    }

    public void setSliderVel(JSlider sliderVel) {
        this.sliderVel = sliderVel;
    }
      
      private static JTextArea txLogArea;
      
      private GridBagConstraints gridConstraints;

    public static JTextArea getTxLogArea() {
        if(txLogArea==null){
            txLogArea=new JTextArea();
        }
        return txLogArea;
    }
    
    public void setTxLogArea(JTextArea txLogArea) {
        this.txLogArea = txLogArea;
    }
      //Permite ajustar las posiciones de los elementos
      
      
      public VistaPanelControl(Modelo m){
        this.modelo = m;
        //this.sistema = modelo.getSistema();
        this.setBackground(new Color(223, 220, 206));
        
        this.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        //reajusta el tamaño de los componentes
        gridConstraints.fill=GridBagConstraints.VERTICAL;
        
        //Agregar widget SAP y RAM
        agregarVistaSAP();     
        gridConstraints.gridheight=0;
        //Agrega botones y sliders de reset
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;

        agregarBotonReinicio();
        agregarBotonPaso();
        agregarBotonEjecutar();
        agregarSliderVelocidad();

         //Espacio para mostrar los registros
        agregarFormulariosMod();
        agregarRegistros();
        
        
        //Campos para modificar las instrucciones
        
      


      }
        public VistaPanelControl(Modelo m,Procesador procs){
            this.procs=procs;
         this.modelo = m;
        //this.sistema = modelo.getSistema();
        this.setBackground(new Color(223, 220, 206));
        
        this.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        //reajusta el tamaño de los componentes
        gridConstraints.fill=GridBagConstraints.VERTICAL;
        
        //Agregar widget SAP y RAM
        agregarVistaSAP();     
        gridConstraints.gridheight=0;
        //Agrega botones y sliders de reset
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;

        agregarBotonReinicio();
        agregarBotonPaso();
        agregarBotonEjecutar();
        agregarSliderVelocidad();

         //Espacio para mostrar los registros
        agregarFormulariosMod();
        agregarRegistros();
        
        
        //Campos para modificar las instrucciones
      


      }
      public void agregarBotonReinicio(){
        btnReset = new JButton("Reset");
        btnReset.setActionCommand("btnReset");
        btnReset.addActionListener(getControl());
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridheight = 1;
        this.add(btnReset, gridConstraints);
      }
      
      public void agregarBotonEjecutar(){
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.gridheight = 1;
        this.btnEjecutar = new JButton("Ejecutar");
        this.btnEjecutar.setActionCommand("ejecutar");
        this.btnEjecutar.addActionListener(getControl());
        this.add(btnEjecutar, gridConstraints);
      }
      
      public void agregarBotonPaso(){
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.gridheight = 1;
        this.btnPaso = new JButton("Ejecutar 1 Paso");
        this.btnPaso.addActionListener(getControl());
        this.btnPaso.setActionCommand("clockButton");
        this.add(btnPaso, gridConstraints);
      }
      
      public void agregarVistaSAP(){
          this.display7Seg = new VistaDisplaySieteSeg((byte)11111);
          
          this.vistaRAM = new VistaWidgetRAM(modelo, this,this.procs);
          this.vistaSAP= new VistaWidgetSAP(modelo, this.vistaRAM,this.procs);
          gridConstraints.gridx = 1;
          gridConstraints.gridy = 0;
          gridConstraints.gridheight = 8;
          this.add(vistaSAP, gridConstraints);
          
          gridConstraints.gridx = 3;
          gridConstraints.gridy = 0;
          this.add(vistaRAM, gridConstraints);
      }
      
      public void agregarSliderVelocidad(){
      
        //Label respectivo
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        gridConstraints.ipady += 5;
        JLabel t = new JLabel("  Velocidad  ");
        t.setHorizontalAlignment(JLabel.HORIZONTAL);
        gridConstraints.insets = new Insets(0, 7, -1, 5);
        t.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(t, gridConstraints);
        gridConstraints.ipady = 0;
        
        //Slider
        // Agregar slider velocidad
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        this.sliderVel = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        sliderVel.setMajorTickSpacing(10);
        sliderVel.setBorder(BorderFactory.createLineBorder(Color.black));
        gridConstraints.insets = new Insets(0, 7, 5, 5);

        sliderVel.setPaintTicks(true);
        
        //labels extremos del slider
        // Create the label table
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(15, new JLabel("Lento"));
        labelTable.put(95, new JLabel("Rápido"));
        sliderVel.setLabelTable(labelTable);
        sliderVel.setPaintLabels(true);
        sliderVel.addChangeListener(getControl());
        this.add(this.sliderVel, gridConstraints);
         gridConstraints.insets = new Insets(0, 0, 0, 0);
      }
      
      public void agregarRegistros(){
          
        txLogArea = getTxLogArea();
        txLogArea.setMaximumSize(new Dimension(30, 10));
        txLogArea.setEditable(false);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 6;
        gridConstraints.ipadx = 240;
        gridConstraints.ipady = 400;
        gridConstraints.gridheight = 1;
        gridConstraints.fill = GridBagConstraints.VERTICAL;
        DefaultCaret caret = (DefaultCaret) txLogArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane sv = new JScrollPane(txLogArea);
        sv.setAutoscrolls(true);
        sv.setPreferredSize(new Dimension(20, 100));
        sv.setMaximumSize(new Dimension(20, 100));
        this.add(sv, gridConstraints);
      }
      
      public void asignarSieteSeg(){
        //Agregar vista siete segmentos tomando el valor del registro del sistema
        //this.display7Seg = new VistaDisplaySieteSeg(this.sistema.getRegistroSalida().getValor());
        gridConstraints.ipadx = 0;
        gridConstraints.ipady =0;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 8;
        this.display7Seg = new VistaDisplaySieteSeg((byte)11111);

        this.add(this.display7Seg, gridConstraints);
      }
      
      public void agregarFormulariosMod(){
          gridConstraints.gridy=7;
          
          this.vistaModRegistros= new VistaModReg(this.procs);
          this.add(vistaModRegistros,gridConstraints);

      }
      //Asigna un controlador del panelCPU
      public ControladorPanelControl getControl(){
          if(this.controlador==null){
              controlador= new ControladorPanelControl(this);
          }
          return controlador;
      }
}
