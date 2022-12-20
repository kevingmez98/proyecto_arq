
import Logica.HiloReloj;
import Logica.Procesador;
import presentacion.Modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jason
 */
public class Text {
    
    public static void main(String[] args) {
        Procesador Test=new Procesador();
        HiloReloj hilo=new HiloReloj(1000);
        hilo.run();
        
        
    }
}
