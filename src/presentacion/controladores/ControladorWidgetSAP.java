/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import Logica.ObservadorReloj;
import Logica.Procesador;
import interfaces.SAPObserver;
import presentacion.Modelo;
import presentacion.vistas.VistaWidgetSAP;

/**
 *
 * @author Asus
 */
public class ControladorWidgetSAP implements SAPObserver,ObservadorReloj{

    private VistaWidgetSAP widgetSAP;
    private  Modelo modelo;
    private  Procesador sistema;
    
    
      // Si cambia el registro A/B o cambia la bandera SUB, vuelva a pintar
    private void actualizarALU() {
<<<<<<< HEAD
        for (int i = 0; i <= 7; i++) {
          //widgetSAP.getBtns_bitsALU()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.ALU, 7 - i));
        }
=======
         //this.widgetSAP.unalabel.setText(this.sistema.getAlu().getSalida()+"");
>>>>>>> origin/huwso1-banana
    }
    @Override
    public void cambioRegistroA() {
       this.widgetSAP.unalabel.setText(this.sistema.getAlu().getOperando1()+"");
       this.widgetSAP.unalabelB.setText(this.sistema.getMar().bytetoString(this.sistema.getAlu().getOperando1()));
        actualizarALU();
    }

    @Override
    public void cambioRegistroB() {
       this.widgetSAP.unalabel.setText(this.sistema.getAlu().getOperando2()+"");
       this.widgetSAP.unalabelB.setText(this.sistema.getMar().bytetoString(this.sistema.getAlu().getOperando2()));
        actualizarALU();
    }

    @Override
    public void cambioPC() {
       this.widgetSAP.unalabel.setText(this.sistema.getPc().getValor()+"");
       this.widgetSAP.unalabelB.setText(this.sistema.getMar().bytetoString(this.sistema.getPc().getValor()));
    }

    @Override
    public void cambioMAR() {
        this.widgetSAP.unalabel.setText(this.sistema.getMar().getValor()+"");
        this.widgetSAP.unalabelB.setText(this.sistema.getMar().bytetoString( (this.sistema.getMar().getValor())));
    }

    @Override
    public void cambioOUT() {
        /*for (int i = 0; i <= 7; i++) {
            //widgetSAP.getBtns_bitsOUT()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.OUT, 7 - i));
        }
<<<<<<< HEAD
=======
        widgetSAP.getDisplay7Seg().setValor(v);*/
>>>>>>> origin/huwso1-banana
    }

    @Override
    public void cambioIR() {
       this.widgetSAP.unalabelB.setText(this.sistema.getMar().bytetoString(this.sistema.getIr().getValor()));
    }

    @Override
    public void cambioConteoPaso() {
        widgetSAP.getLblStepCt().setText(this.sistema.getPasos_reloj()+"");

    }

    @Override
    public void cambioFLAGS() {
        if(this.sistema.getAlu().getZeroFlag()){
            widgetSAP.getBtnZero().setBackground(VistaWidgetSAP.BUTTON_SELECTED_BG);
        }else{
            widgetSAP.getBtnZero().setBackground(VistaWidgetSAP.BUTTON_UNSELECTED_BG);
        }
        if(this.sistema.getAlu().getCarryFlag()){
            widgetSAP.getBtnCarry().setBackground(VistaWidgetSAP.BUTTON_SELECTED_BG);
        }else{
            widgetSAP.getBtnCarry().setBackground(VistaWidgetSAP.BUTTON_UNSELECTED_BG);
        }        
    }

    @Override
    public void cambioBUS() {
        this.widgetSAP.unalabel.setText(this.sistema.getAsociado().getvalorbus());
        this.widgetSAP.unalabelB.setText(this.sistema.getMar().bytetoString(this.sistema.getAsociado().getvalorbus()));
    }

    @Override
    public void cambioLineasControl() {
        boolean[] newLines = this.sistema.getLineascontrol();
        for (int i = 0; i < newLines.length; i++) {
            if (newLines[i]) {
                widgetSAP.getBtns_bitsControl()[i].setBackground(widgetSAP.BUTTON_SELECTED_BG);
            } else {
                widgetSAP.getBtns_bitsControl()[i].setBackground(widgetSAP.BUTTON_UNSELECTED_BG);
            }
        }
    }

    @Override
    public void cambioReloj() {
        this.actualizarALU();
        this.cambioBUS();
        this.cambioConteoPaso();
        this.cambioFLAGS();
        this.cambioIR();
        this.cambioLineasControl();
        this.cambioMAR();
        this.cambioPC();
        this.cambioRegistroA();
        this.cambioRegistroB();
        
    }
    
}
