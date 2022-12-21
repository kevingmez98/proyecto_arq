/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;
import presentacion.vistas.VistaPanelControl;

/**
 *
 * @author jason
 */
public class Bus {
    private int Datoenelbus;
    ArrayList<Integer> lectores;//Guarda las constantes de la linea de control de los componentes que se encuentran leyendo
    private int escritor; //Guarda la constante de la linea de control correspondiente al componente que se encuentra escribiendo
    public Bus(){
        //Inicializa una instancia de Bus, la idea es utilizar el Bus como un patron "Comunication Object"
        //Ya se que communication  object es una mierda pero nos sirve para este proyecto.
        this.lectores=new ArrayList<Integer>();
        this.Datoenelbus=0;
    }
    public int leerdelbus(int id){
        //Devuelve el dato que se encuentra en el bus
        //Si se vincula el Bus a la vista, podria activar una de las lineas de control dependiendo del id que lea del bus
        this.Reconocerlectores(id);
        if(id<29){
        System.out.println("Registro "+id+" Leyo del Bus");
        VistaPanelControl.getTxLogArea().append("Registro "+id+" Leyo del Bus"+"\n");
        }
        if(id==29){
             VistaPanelControl.getTxLogArea().append("Registro de Instrucciones Leyo del Bus"+"\n");  
        }
         if(id==30){
             VistaPanelControl.getTxLogArea().append("Registro MAR Leyo del Bus"+"\n");
        }
         if(id==31){
             VistaPanelControl.getTxLogArea().append("Registro PC Leyo del Bus"+"\n");
        }
        return this.Datoenelbus;
    }
    public void Escribirenelbus(int id,int dato){
        //Carga un dato en el Bus y avisa de que lo hizo
        //Si se vincula el Bus a la vista, podria activar una de las lineas de control dependiendo del id que escriba en el bus
        if(id<29){
        System.out.println("Registro "+id+" Escribio del Bus");
        VistaPanelControl.getTxLogArea().append("Registro "+id+" Escribio en el Bus"+"\n");
        }
        if(id==29){
             VistaPanelControl.getTxLogArea().append("Registro de Instrucciones Escribio en el Bus"+"\n");  
        }
         if(id==30){
             VistaPanelControl.getTxLogArea().append("Registro MAR Escribio en el Bus"+"\n");
        }
         if(id==31){
             VistaPanelControl.getTxLogArea().append("Registro PC Escribio en el Bus"+"\n");
        }
        Reconoceralescritor(id);
        
        this.Datoenelbus=dato;
       
        
    }
    public void CleanBus(){
        //Carga un dato en el Bus y avisa de que lo hizo
        //Si se vincula el Bus a la vista, podria activar una de las lineas de control dependiendo del id que escriba en el bus
       this.Datoenelbus=0;
        
    }
    private void Reconoceralescritor(int id){
        switch(id){
            case 29: 
                this.escritor=4; //IO
                break;
            case 42:
                this.escritor=3; //RO
                break;
            case 43:
                this.escritor=7;
                break;
             /* agregare al resto de lineas de control cuando sepa que son :v*/
        }
    }
        private void Reconocerlectores(int id){
        if(id==30){
            this.lectores.add(1); //MI
        }
         if(id==42){
            this.lectores.add(2); //RI
        }
         if(id==43){
            this.lectores.add(6); //AI
        }
            /*Agregare el resto de lineas de control cuando sepa que son :v*/
        }
        public void limpiarlectoresyescritor(){
            this.lectores.clear();
            this.escritor=0;
        }
        public ArrayList<Integer> getlectores(){
            return this.lectores;
        }
        public int getescritor(){
            return this.escritor;
        }
        public int getvalorbus(){
            return this.Datoenelbus;
        }
    }
    
    
    
    
    

