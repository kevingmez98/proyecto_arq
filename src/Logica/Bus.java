/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> ce6608afc585f2a73460476aaf0dae485a960ae2
/**
 *
 * @author jason
 */
public class Bus {
    private int Datoenelbus;
<<<<<<< HEAD
    public Bus(){
        //Inicializa una instancia de Bus, la idea es utilizar el Bus como un patron "Comunication Object"
        //Ya se que communication  object es una mierda pero nos sirve para este proyecto.
=======
    ArrayList<Integer> lectores;//Guarda las constantes de la linea de control de los componentes que se encuentran leyendo
    private int escritor; //Guarda la constante de la linea de control correspondiente al componente que se encuentra escribiendo
    public Bus(){
        //Inicializa una instancia de Bus, la idea es utilizar el Bus como un patron "Comunication Object"
        //Ya se que communication  object es una mierda pero nos sirve para este proyecto.
        this.lectores=new ArrayList<Integer>();
>>>>>>> ce6608afc585f2a73460476aaf0dae485a960ae2
        this.Datoenelbus=0;
    }
    public int leerdelbus(int id){
        //Devuelve el dato que se encuentra en el bus
        //Si se vincula el Bus a la vista, podria activar una de las lineas de control dependiendo del id que lea del bus
<<<<<<< HEAD
=======
        this.Reconocerlectores(id);
>>>>>>> ce6608afc585f2a73460476aaf0dae485a960ae2
        System.out.println(id+" Leyo del Bus");
        return this.Datoenelbus;
    }
    public void Escribirenelbus(int id,int dato){
        //Carga un dato en el Bus y avisa de que lo hizo
        //Si se vincula el Bus a la vista, podria activar una de las lineas de control dependiendo del id que escriba en el bus
<<<<<<< HEAD
        this.Datoenelbus=dato;
        System.out.println(id+"Escribio en el bus");
        
    }
=======
        Reconoceralescritor(id);
        this.Datoenelbus=dato;
        System.out.println(id+" Escribio en el bus");
        
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
    }
>>>>>>> ce6608afc585f2a73460476aaf0dae485a960ae2
    
    
    
    
    
<<<<<<< HEAD
}
=======

>>>>>>> ce6608afc585f2a73460476aaf0dae485a960ae2
