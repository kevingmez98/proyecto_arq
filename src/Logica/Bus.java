/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author jason
 */
public class Bus {
    private int Datoenelbus;
    public Bus(){
        //Inicializa una instancia de Bus, la idea es utilizar el Bus como un patron "Comunication Object"
        //Ya se que communication  object es una mierda pero nos sirve para este proyecto.
        this.Datoenelbus=0;
    }
    public int leerdelbus(int id){
        //Devuelve el dato que se encuentra en el bus
        //Si se vincula el Bus a la vista, podria activar una de las lineas de control dependiendo del id que lea del bus
        System.out.println(id+" Leyo del Bus");
        return this.Datoenelbus;
    }
    public void Escribirenelbus(int id,int dato){
        //Carga un dato en el Bus y avisa de que lo hizo
        //Si se vincula el Bus a la vista, podria activar una de las lineas de control dependiendo del id que escriba en el bus
        this.Datoenelbus=dato;
        System.out.println(id+"Escribio en el bus");
        
    }
    
    
    
    
    
}
