/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_arq;

import Logica.Procesador;
import presentacion.Modelo;

/**
 *
 * @author Asus
 */
public class Proyecto_arq {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Procesador procs=new Procesador();
        Modelo modeloInicial= new Modelo();
        modeloInicial.inicializarModelo(procs);
    }
    
}
