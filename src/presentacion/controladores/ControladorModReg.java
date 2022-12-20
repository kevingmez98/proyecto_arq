/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static javax.swing.JOptionPane.showMessageDialog;
import presentacion.vistas.VistaModReg;

/**
 *
 * @author Asus
 */
public class ControladorModReg implements ActionListener, ChangeListener{

    
    VistaModReg vistaMod;
    
    public ControladorModReg(VistaModReg v){
        vistaMod=v;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getActionCommand().equals("compValorPos")) {
            if(esBinario(vistaMod.getTxtPosicion().getText())){
                System.out.println("Es binario, realizar accion ver la posicion");
            }else{
                showMessageDialog(null, "Se insertó un valor no apropiado de posicion");
            }
         }
         
         if (e.getActionCommand().equals("camInstruccion")) {
            if(esBinario(vistaMod.getTxtDirMod().getText())){
                 System.out.println("Es binario, realizar accion de cambio");
             }else{
                showMessageDialog(null, "Se insertó un valor no apropiado de dirección a modificar");
               }
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
    
}
