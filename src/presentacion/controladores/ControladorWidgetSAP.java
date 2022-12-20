/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import interfaces.SAPObserver;
import presentacion.Modelo;
import presentacion.vistas.VistaWidgetSAP;

/**
 *
 * @author Asus
 */
public class ControladorWidgetSAP implements SAPObserver{

    private VistaWidgetSAP widgetSAP;
    private  Modelo modelo;
    //private  SistemaSAP sistema;
    //Comentario de diferencia para el main
    
      // Si cambia el registro A/B o cambia la bandera SUB, vuelva a pintar
    private void actualizarALU() {
        for (int i = 0; i <= 7; i++) {
          //widgetSAP.getBtns_bitsALU()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.ALU, 7 - i));
        }
    }
    @Override
    public void cambioRegistroA(byte v) {
        for (int i = 0; i <= 7; i++) {
            //widgetSAP.getBtns_bitsA()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.A, 7 - i));
        }
        actualizarALU();
    }

    @Override
    public void cambioRegistroB(byte v) {
        for (int i = 0; i <= 7; i++) {
            //widgetSAP.getBtns_bitsB()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.B, 7 - i));
        }
        actualizarALU();
    }

    @Override
    public void cambioPC(byte v) {
        for (int i = 0; i <= 3; i++) {
         //   widgetSAP.getBtns_bitsPC()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.PC, 3 - i));
        }
    }

    @Override
    public void cambioMAR(byte v) {
        for (int i = 0; i <= 3; i++) {
            //widgetSAP.getBtns_bitsMAR()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.MAR, 3 - i));
        }
       // widgetSAP.getRamWidget().getControl().cambioMAR(v);
    }

    @Override
    public void cambioOUT(byte v) {
        for (int i = 0; i <= 7; i++) {
            //widgetSAP.getBtns_bitsOUT()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.OUT, 7 - i));
        }
    }

    @Override
    public void cambioIR(byte v) {
        for (int i = 0; i <= 7; i++) {
           // widgetSAP.getBtns_bitsIR()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.IR, 7 - i));
        }
    }

    @Override
    public void cambioConteoPaso(byte v) {
        widgetSAP.getLblStepCt().setText("" + v);

    }

    @Override
    public void cambioFLAGS() {
//        if(this.sistema.getFlags().getZF()){
//            widgetSAP.getBtnZero().setBackground(VistaWidgetSAP.BUTTON_SELECTED_BG);
//        }else{
//            widgetSAP.getBtnZero().setBackground(VistaWidgetSAP.BUTTON_UNSELECTED_BG);
//        }
//        if(this.sistema.getFlags().getCF()){
//            widgetSAP.getBtnCarry().setBackground(VistaWidgetSAP.BUTTON_SELECTED_BG);
//        }else{
//            widgetSAP.getBtnCarry().setBackground(VistaWidgetSAP.BUTTON_UNSELECTED_BG);
//        }        
    }

    @Override
    public void cambioBUS(byte v) {
        for (int i = 0; i <= 7; i++) {
            //widgetSAP.getBtns_bistBUS()[i].setText(sistema.decodificarRegistro(SistemaSAP.TipoRegistro.BUS, 7 - i));
        }
    }

    @Override
    public void cambioLineasControl() {
//        boolean[] newLines = this.sistema.getControlLines();
//        for (int i = 0; i < newLines.length; i++) {
//            if (newLines[i]) {
//                widgetSAP.getBtns_bitsControl()[i].setBackground(widgetSAP.BUTTON_SELECTED_BG);
//            } else {
//                widgetSAP.getBtns_bitsControl()[i].setBackground(widgetSAP.BUTTON_UNSELECTED_BG);
//            }
//        }
    }
    
}
