/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.awt.Dimension;
import javax.swing.JFrame;
import presentacion.vistas.VistaPanelControl;

/**
 *
 * @author Asus
 */
public class Modelo {

    private JFrame ventanaPrincipal;
    
    private VistaPanelControl panelControl;
    
    
    public Modelo(){
        
    }
    
    public void inicializarModelo(){
        //Ajusta el tamaño de la ventana
        getVentanaPrincipal();
        this.ventanaPrincipal.pack();
        this.ventanaPrincipal.setVisible(true);
    }

    public JFrame getVentanaPrincipal() {
       if(ventanaPrincipal == null){              
            ventanaPrincipal = new JFrame();
            ventanaPrincipal.setTitle("Simulador");
            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setContentPane(getPanelPrincipal());
            ventanaPrincipal.setPreferredSize(new Dimension(1200, 768));
            ventanaPrincipal.setResizable(true);
        }
        return ventanaPrincipal;
    }
    
    public VistaPanelControl getPanelPrincipal(){
        if(panelControl == null){
            panelControl = new VistaPanelControl(this);
        }
        return panelControl;
    }
    
}
