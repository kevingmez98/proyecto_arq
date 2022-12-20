/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presentacion.Modelo;
import presentacion.vistas.VistaPanelControl;
import presentacion.vistas.VistaWidgetRAM;

/**
 *
 * @author Asus
 */
public class ControladorWidgetRAM implements interfaces.IRAMObserver, ActionListener{

    private final VistaWidgetRAM widgetRAM;
    private VistaPanelControl vista;
    //private final SistemaSAP sistema;    
    private final Modelo modelo;

    public ControladorWidgetRAM(VistaWidgetRAM widgetRAM) {
        this.widgetRAM = widgetRAM;
        this.vista= this.widgetRAM.getVistaControl();
        //this.sistema = this.widgetRAM.getSistema();
        this.modelo = this.widgetRAM.getModelo();
    }

    
    @Override
    public void cambiaValorRAM(int address) {
        // Iterar sobre todos los bits en la posición de memoria actual
        for (int i = 0; i <= 31; i++) {
<<<<<<< HEAD
           //widgetRAM.getBtnArrayBotones()[address][i].setText("" + this.buscarEnRAM(address, 31 - i));
=======
            widgetRAM.getBtnArrayBotones()[address][i].setText("" + this.buscarEnRAM(address, 7 - i));
>>>>>>> origin/huwso1-banana

            // Compruebar si es el valor MAR, en cuyo caso se necesita un color para resaltar
            if (widgetRAM.isResaltarMAR() && address == widgetRAM.getValorMAR()) {
                widgetRAM.getBtnArrayBotones()[address][i].setBackground(widgetRAM.COLOR_MAR);
            } else {
                widgetRAM.getBtnArrayBotones()[address][i].setBackground(widgetRAM.getBtnArrayBotones()[address][i].getText().equals("1") ? widgetRAM.COLOR_ON : widgetRAM.COLOR_OFF);
            }

            // Si estamos en la posición más a la derecha, mantenga el borde
            if (i == 32) {
                widgetRAM.getBtnArrayBotones()[address][i].setBorder(widgetRAM.RIGHT_BORDER);
            } else {
                widgetRAM.getBtnArrayBotones()[address][i].setBorder(null);
            }

            // Si estamos en la fila inferior, mantener el borde.
            if (address == 32) {
                if (i == 32) {
                    widgetRAM.getBtnArrayBotones()[address][i].setBorder(widgetRAM.BOTTOM_RIGHT_BORDER);
                } else {
                    widgetRAM.getBtnArrayBotones()[address][i].setBorder(widgetRAM.BOTTOM_BORDER);
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
            this.cambioMAR(widgetRAM.getValorMAR());

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

            for (int i = 0; i < 32; i++) {
                // Colocamos cada posición en 0
             //   arr[i] = 0;
            }

            // Obligar a la pantalla a volver a pintar dos veces, para manejar el retraso visual
            for (int i = 0; i < 32; i++) {
                this.cambiaValorRAM(i);
            }
            for (int i = 0; i < 32; i++) {
                this.cambiaValorRAM(i);
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
    
     public void cambioMAR(byte v) {
        // Si no estamos en modo resaltado
        if (!widgetRAM.isResaltarMAR()) {
            cambiaValorRAM(v);
            return;
        }

        // Coge el antiguo valor MAR
        int oldVal = widgetRAM.getValorMAR();

        // Pintar el nuevo valor con el color correcto
        widgetRAM.setValorMAR(v);
        cambiaValorRAM(widgetRAM.getValorMAR());

        // Elimina la coloración especial del antiguo valor MAR
        cambiaValorRAM(oldVal);
    }
     
  // Función auxiliar para acceder a bits individuales en la memoria; Address: [0, 15]
    // bitPos: [0, 7]
    public int buscarEnRAM(int valor, int bitPos) {
        int val = 0b11111111 & valor;
        return (val >> bitPos) & 0b1;
        
        //Para  que el programa no explote por falta de return
       
    }
    
}
