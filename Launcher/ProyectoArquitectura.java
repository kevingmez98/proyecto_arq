
package Launcher;

import Logica.RegistroMAR;

public class ProyectoArquitectura {

    public static void main(String[] args) {
        
        //Ejemplo para obtener la ultima direccion de la memoria
        
        RegistroMAR mar = new RegistroMAR(80);
        
        byte [] prueba = new byte[4];
        
        //Los dos primeros bytes no son relevantes para direcccionar
        prueba[0] = 0b00000000; 
        prueba[1] = 0b00000000;
        prueba[2] = (byte)0b11000000;
        prueba[3] = (byte)0b00000000;
        
        mar.decodificarDireccion(prueba);
        
        System.out.println(mar.getDireccionAct());
        
    }
}
