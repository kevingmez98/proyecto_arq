/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Logica;


public class Registro {
    
    public void setValor(int valor){
        this.valor = valor;
    }
    
    public int getValor(){
        return this.valor;
    }
    
    public void clear(){
        this.valor = 0;
    }
    
    public Registro(){
        this.valor = 0;
    }

    protected int valor;
}
