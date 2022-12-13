/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.vistas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import presentacion.Modelo;

/**
 *
 * @author Asus
 */
public class VistaPanelControl extends JPanel{
    
      private Modelo modelo;
      
      private JButton btnReset;
      
      private JButton btnPaso;
      
      private JButton btnEjecutar;
      
      private JSlider sliderVel;
      
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
        
        //Agregar widget RAM
        
        //Agregar vista estado reloj
        
        //Agrega boton de reset
        agregarBotonReinicio();
        agregarSliderVelocidad();
      }
      
      public void agregarBotonReinicio(){
        btnReset = new JButton("Reset");
        btnReset.setActionCommand("resetButtonClicked");
        //btnReset.addActionListener(getControl());
        gridConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 1;
        gridConstraints.gridheight = 1;
        this.add(btnReset, gridConstraints);
      }
      
      public void agregarSliderVelocidad(){
      
        //Label respectivo
        gridConstraints.gridx = 3;
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
        gridConstraints.gridx = 3;
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
      
      
      //Asigna un controlador del panelCPU
      public void getControl(){
          
      }
}
