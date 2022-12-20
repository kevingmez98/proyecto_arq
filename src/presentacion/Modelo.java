
package presentacion;

import java.awt.Dimension;
import java.awt.Toolkit;
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
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaPrincipal.setContentPane(getPanelPrincipal());
            ventanaPrincipal.setPreferredSize(screenSize);
            ventanaPrincipal.setResizable(false);
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
