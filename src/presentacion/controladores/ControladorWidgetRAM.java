/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import Logica.ObservadorReloj;
import Logica.Procesador;
import Logica.Reloj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presentacion.Modelo;
import presentacion.vistas.VistaPanelControl;
import presentacion.vistas.VistaWidgetRAM;

/**
 *
 * @author Asus
 */
public class ControladorWidgetRAM implements  ActionListener,ObservadorReloj{

    private final VistaWidgetRAM widgetRAM;
    private VistaPanelControl vista;
    //private final SistemaSAP sistema; 
    private Procesador procs;
    private final Modelo modelo;

    public ControladorWidgetRAM(VistaWidgetRAM widgetRAM,Procesador procs) {
        this.procs=procs;
        this.widgetRAM = widgetRAM;
        this.vista= this.widgetRAM.getVistaControl();
        //this.sistema = this.widgetRAM.getSistema();
        this.modelo = this.widgetRAM.getModelo();
        Reloj.obtenerReloj().addObserver(this);
    }

    
 
    public void cambiaValorRAM() {
        // Iterar sobre todos los bits en la posición de memoria actual
        for(int j=0;j< widgetRAM.getBtnArrayBotones().length;j++){
        for (int i = 0; i < 31; i++) {
            if(j<29){
            widgetRAM.getBtnArrayBotones()[j][i].setText("" + this.buscarEnRAM(this.procs.getBanco_de_registros()[j].getValor(), 31 - i));
            }else{
                if(j==29){
                    
                widgetRAM.getBtnArrayBotones()[j][i].setText(""+this.buscarEnRAM(this.procs.getIr().getValor(),31-i));
                System.out.print(this.buscarEnRAM(this.procs.getIr().getValor(),31-i));
                }
                if(j==30){
                    widgetRAM.getBtnArrayBotones()[j][i].setText(""+this.buscarEnRAM(this.procs.getMar().getValor(),31-i));
                }
                if(j==31){
                    widgetRAM.getBtnArrayBotones()[j][i].setText(""+this.buscarEnRAM(this.procs.getPc().getValor(),31-i));
                }
            }
            // Si estmos en la posición más a la derecha, mantenga el borde
            
            if (i == 31) {
                widgetRAM.getBtnArrayBotones()[j][i].setBorder(widgetRAM.RIGHT_BORDER);
            } else {
                widgetRAM.getBtnArrayBotones()[j][i].setBorder(null);
            }

            // Si estamos en la fila inferior, mantener el borde.
            if (j == 31) {
                if (i == 31) {
                    widgetRAM.getBtnArrayBotones()[j][i].setBorder(widgetRAM.BOTTOM_RIGHT_BORDER);
                } else {
                    widgetRAM.getBtnArrayBotones()[j][i].setBorder(widgetRAM.BOTTOM_BORDER);
                }
            }
        
        }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         // Si el usuario desea alternar el resaltado MAR en la parte de RAM del widget
        if (e.getActionCommand().contentEquals("toggleMAR")) {
            // Alternar valor de estado
            widgetRAM.setResaltarMAR (!widgetRAM.isResaltarMAR());

            // Actualizar etiqueta de botón
            widgetRAM.getBtnResaltarMAR().setText(widgetRAM.isResaltarMAR() ? widgetRAM.MAR_ON_LABEL : widgetRAM.MAR_OFF_LABEL);

            // Actualizar visualización
            

            return;
    }
        
      // Si el programa se está ejecutando, primero detenerlo
//        if (this.modelo.isEjecutandoPrograma()) {
//            ActionEvent x = new ActionEvent("", 5, "autoplay");
//            this.view.getControl().actionPerformed(x);

            // Dormir para que el registro de eventos no se agrupe
//            try {
//                Thread.sleep(25);
//            } catch (InterruptedException e1) {
//                EventLog.getEventLog().addEntrada("Error en sleep para 100 ms");
//            }
//        }


        // Si el usuario hace clic en el botón borrar memoria
        if (e.getActionCommand().contentEquals("clearmem")) {
            // Obtener el contenido de la memoria
           // byte[] arr = this.sistema.getRAM().getData();

            for (int i = 0; i < 16; i++) {
                // Colocamos cada posición en 0
             //   arr[i] = 0;
            }

            // Obligar a la pantalla a volver a pintar dos veces, para manejar el retraso visual
            for (int i = 0; i < 16; i++) {
                this.cambiaValorRAM();
            }
            for (int i = 0; i < 16; i++) {
                this.cambiaValorRAM();
            }
            return;
        }

        // Si el usuario hace clic en el botón Mostrar los códigos de operación
        if (e.getActionCommand().contentEquals("showopcodes")) {
//            EventLog.getEventLog().addEntrada("=============");
//            EventLog.getEventLog().addEntrada("NOP\t0000");
//            EventLog.getEventLog().addEntrada("LDA\t0001");
//            EventLog.getEventLog().addEntrada("ADD\t0010");
//            EventLog.getEventLog().addEntrada("SUB\t0011");
//            EventLog.getEventLog().addEntrada("STA\t0100");
//            EventLog.getEventLog().addEntrada("LDI\t0101");
//            EventLog.getEventLog().addEntrada("JMP\t0110");
//            EventLog.getEventLog().addEntrada("JC\t0111");
//            EventLog.getEventLog().addEntrada("JZ\t1000");
//            EventLog.getEventLog().addEntrada("OUT\t1110");
//            EventLog.getEventLog().addEntrada("HLT\t1111");
//            EventLog.getEventLog().addEntrada("=============");
            return;
        }


        // el usuario debe haber solicitado un cambio de bit en algún lugar de la memoria.
        
        // Analizar la dirección de memoria
        byte address = Byte.parseByte(e.getActionCommand().substring(0, e.getActionCommand().indexOf(",")));
        // Analizar el cambio de  bit en la posición 
        byte bitPos = Byte.parseByte(e.getActionCommand().substring(e.getActionCommand().indexOf(",") + 1));

        bitPos = (byte) (31 - bitPos);
        // Obtener el valor actual del bit agregar la posición modificada
        int currVal = buscarEnRAM(address, bitPos);
        // Obtenga el valor actual de la memoria en la dirección especificada
//        byte memVal = this.sistema.getRAM().getData()[address];

        // Determinar si necesitamos restar o sumar
        byte newVal;
        if (currVal == 1) {
            // Restar   
//            newVal = (byte) (memVal - Math.pow(2, bitPos));
        } else {
            // Sumar
//            newVal = (byte) (memVal + Math.pow(2, bitPos));
        }
//        this.sistema.getRAM().cambiarValor(address, newVal);

        // Informe al log
//        logica.EventLog.getEventLog().addEntrada("Dirección de memoria " + address + " cambió a " + newVal);
    }
    
  
     
  // Función auxiliar para acceder a bits individuales en la memoria; Address: [0, 15]
    // bitPos: [0, 7]
    public int buscarEnRAM(int valor, int bitPos) {
        int val = 0b11111111111111111111111111111111 & valor;
        return (val >> bitPos) & 0b1;
        
        //Para  que el programa no explote por falta de return
       
    }

    @Override
    public void cambioReloj() {
        cambiaValorRAM();
    }
    
}
