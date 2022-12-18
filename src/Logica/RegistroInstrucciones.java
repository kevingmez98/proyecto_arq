/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author jason
 */
public class RegistroInstrucciones extends Registro{
    //Un registro que guarda la instruccion que se esta ejecutando en el momento
    public RegistroInstrucciones(Bus asociado){
        super(29,asociado);
    }
}
