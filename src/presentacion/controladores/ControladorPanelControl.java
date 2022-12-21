/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import Logica.HiloReloj;
import Logica.Reloj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import presentacion.vistas.VistaPanelControl;

/**
 *
 * @author Asus
 */
public class ControladorPanelControl implements ActionListener, ChangeListener {

     private VistaPanelControl vistaControl;
     private int executecount=1;
     HiloReloj hilo;

    public ControladorPanelControl(VistaPanelControl vista) {
        this.vistaControl = vista;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("btnReset")) {
            System.out.println("Reiniciar valores");
            this.vistaControl.getProcs().reset();
            VistaPanelControl.getTxLogArea().setText("Se ha resetado el sistema"+"\n");
        }
         
        if (e.getActionCommand().equals("ejecutar")) {
            if(executecount==1){
            hilo=new HiloReloj(this.vistaControl.getSliderVel().getValue());
           hilo.start();
           this.vistaControl.getBtnEjecutar().setText("Detener");
           executecount=2;
            }else{
               hilo.terminar();
               this.vistaControl.getBtnEjecutar().setText("Ejecutar");
               executecount=1;
            }

    
            
            
        }
        
        //Btn un paso
        if (e.getActionCommand().equals("clockButton")) {
            System.out.println("btn un paso");
            Reloj.obtenerReloj().cambiarReloj();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("Cambiar velocidad ejecucion desde modelo");
    }
    
}
