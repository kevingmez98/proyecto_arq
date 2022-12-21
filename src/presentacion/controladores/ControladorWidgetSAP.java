/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import Logica.ObservadorReloj;
import Logica.Procesador;
import Logica.Reloj;
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
    
    public ControladorWidgetSAP(VistaWidgetSAP widgetSAP,Procesador procs){
        this.sistema=procs;
        this.widgetSAP=widgetSAP;
        Reloj.obtenerReloj().addObserver(this);
    }
        
      // Si cambia el registro A/B o cambia la bandera SUB, vuelva a pintar
    private void actualizarALU() {
        System.out.println("Funcion");
         this.widgetSAP.getLblALUEn().setText(this.sistema.getAlu().getSalida()+"");
         this.widgetSAP.getLblALUBin().setText(this.sistema.getMar().bytetoString(this.sistema.getAlu().getSalida())+"");
    }
    @Override
    public void cambioRegistroA() {
       this.widgetSAP.getLblOper1En().setText(this.sistema.getAlu().getOperando1()+"");
       this.widgetSAP.getLblOper1Bin().setText(this.sistema.getMar().bytetoString(this.sistema.getAlu().getOperando1()));
        actualizarALU();
    }

    @Override
    public void cambioRegistroB() {
       this.widgetSAP.getLblOper2En().setText(this.sistema.getAlu().getOperando2()+"");
       this.widgetSAP.getLblOper2Bin().setText(this.sistema.getMar().bytetoString(this.sistema.getAlu().getOperando2()));
        actualizarALU();
    }

    @Override
    public void cambioPC() {
       this.widgetSAP.getLblPCEn().setText(this.sistema.getPc().getValor()+"");
       this.widgetSAP.getLblPCBin().setText(this.sistema.getMar().bytetoString(this.sistema.getPc().getValor()));
    }

    @Override
    public void cambioMAR() {
        this.widgetSAP.getLblMAREn().setText(this.sistema.getMar().getValor()+"");
        this.widgetSAP.getLblMARBin().setText(this.sistema.getMar().bytetoString( (this.sistema.getMar().getValor())));
    }

    @Override
    public void cambioOUT() {
        /*for (int i = 0; i <= 7; i++) {
            //widgetSAP.getBtns_bitsOUT()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.OUT, 7 - i));
        }
        widgetSAP.getDisplay7Seg().setValor(v);*/
    }

    @Override
    public void cambioIR() {
       this.widgetSAP.getLblIRBin().setText(this.sistema.getMar().bytetoString(this.sistema.getIr().getValor()));
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
        this.widgetSAP.getLblBUSEn().setText(this.sistema.getAsociado().getvalorbus()+"");
        this.widgetSAP.getLblBUSBin().setText(this.sistema.getMar().bytetoString(this.sistema.getAsociado().getvalorbus()));
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
