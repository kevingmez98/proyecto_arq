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
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import presentacion.Modelo;

/**
 *
 * @author Asus
 */
public class VistaPanelControl extends JPanel{
    
      private Modelo modelo;
      
      private VistaWidgetRAM vistaRAM;
      
      private VistaWidgetSAP vistaSAP;
      
      private JButton btnReset;
      
      private JButton btnPaso;
      
      private JButton btnEjecutar;
      
      private JSlider sliderVel;
      
      private JTextArea txLogArea;
      //Permite ajustar las posiciones de los elementos
      private GridBagConstraints gridConstraints;
      
      public VistaPanelControl(Modelo m){
        this.modelo = m;
        //this.sistema = modelo.getSistema();
        this.setBackground(new Color(223, 220, 206));
        
        this.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        //reajusta el tamaño de los componentes
        gridConstraints.fill=GridBagConstraints.VERTICAL;
        
        //Agregar widget SAP
        this.vistaRAM= new VistaWidgetRAM(m, this);
        //Agregar widget RAM
        this.vistaSAP= new VistaWidgetSAP(m, vistaRAM);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 8;
        this.add(vistaSAP, gridConstraints);
        
        
        //Agregar vista estado reloj
        
        //Agrega botones y sliders de reset
        agregarBotonReinicio();
        agregarBotonEjecutar();
        agregarBotonPaso();
        agregarSliderVelocidad();
        
        //Espacio para mostrar los registros
        agregarRegistros();
      }
      
      public void agregarBotonReinicio(){
        btnReset = new JButton("Reset");
        btnReset.setActionCommand("resetButtonClicked");
        //btnReset.addActionListener(getControl());
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
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
        this.btnEjecutar.setActionCommand("autoplay");
        //this.btnEjecutar.addActionListener(getControl());
        this.add(btnEjecutar, gridConstraints);
      }
      
      public void agregarBotonPaso(){
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.gridheight = 1;
        this.btnPaso = new JButton("Ejecutar 1 Paso");
        //this.btnClock.addActionListener(getControl());
        this.btnPaso.setActionCommand("clockButton");
        this.add(btnPaso, gridConstraints);
      }
      
      public void agregarVistaSAP(){
          this.vistaRAM = new VistaWidgetRAM(modelo, this);
      }
      
      public void agregarSliderVelocidad(){
      
        //Label respectivo
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        gridConstraints.ipady += 5;
        JLabel t = new JLabel("  Velocidad  ");
        t.setHorizontalAlignment(JLabel.CENTER);
        gridConstraints.insets = new Insets(0, 7, -1, 5);
        t.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(t, gridConstraints);
        gridConstraints.ipady -= 5;
        
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
        //sliderVel.addChangeListener(getControl());
        this.add(this.sliderVel, gridConstraints);
      }
      
      public void agregarRegistros(){
        gridConstraints.insets = new Insets(0, 6, 5, 0);
        txLogArea = new JTextArea(1, 1);
        txLogArea.setMaximumSize(new Dimension(20, 20));
        txLogArea.setEditable(false);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 6;
        gridConstraints.ipadx = 240;
        gridConstraints.ipady = 150;
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
      
      //Asigna un controlador del panelCPU
      public void getControl(){
          
      }
}
