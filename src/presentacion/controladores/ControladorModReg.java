/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import Logica.Procesador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static javax.swing.JOptionPane.showMessageDialog;
import presentacion.vistas.VistaModReg;
import presentacion.vistas.VistaPanelControl;

/**
 *
 * @author Asus
 */
public class ControladorModReg implements ActionListener, ChangeListener{

    Procesador Prosac;
    VistaModReg vistaMod;
    
    public ControladorModReg(VistaModReg v,Procesador Aderall){
        vistaMod=v;
        this.Prosac=Aderall;
        
    }
    public ControladorModReg(VistaModReg v){
        vistaMod=v;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Gordon");
         if (e.getActionCommand().equals("compValorPos")) {
            
                System.out.println("Es binario, realizar accion ver la posicion");
            
         }
         
         if (e.getActionCommand().equals("camInstruccion")) {
            
                 System.out.println("Es binario, realizar accion de cambio");
                 int direccion=Integer.parseInt(vistaMod.getTxtDirMod().getText()); //Direccion
                 
                 int instruccion=this.binaryStringToInteger(vistaMod.getTxtNuevaInst().getText()); //Instruccion
                 if(this.Prosac.ReconocerinstruccionValidacion(instruccion)){
                 this.Prosac.getRAM().guardarMemoriaWord(instruccion,direccion);
                 }
             
         
         }
         if(e.getSource()==vistaMod.getBtnVerificarValorPos()){
             int direccion =  Integer.parseInt(vistaMod.getTxtPosicion().getText());
                int contenido = Prosac.getRAM().getContenidoEnteroDir(direccion);        
                VistaPanelControl.getTxLogArea().append("Valor de la palabra contenida desde la dirección "+ direccion + ": "+Prosac.getMar().bytetoString(contenido)+"\n");
         }
         
    }
    
    protected boolean esBinario(String s){
        if(s.matches("[01]+")){
            return true;
        }
        return false;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public int binaryStringToInteger (String binaryString){
    char[] digits = binaryString.toCharArray();
    int binaryInteger = 0;
    int count = 0;
    for(int i=digits.length-1;i>=0;i--)
      {
        if(digits[i]=='1') 
             {
              binaryInteger +=(long)Math.pow(2, count);
             }
        count++;
      }
     return binaryInteger;
    }
}
