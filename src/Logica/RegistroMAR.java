/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author jason
 */
public class RegistroMAR extends Registro {

    private int direccionEfect;
  
    public RegistroMAR(Bus asociado) {
        super(30,asociado);
        this.setValor(0);
        //Decidi asignar a la Mar la id del registro 31, sin embargo cambiarla si quieren xd
    }

    //Set para fijar la direccion de carga/guardado de las instrucciones lw o sw
    //Decodifica la direccion a partir de los offset
    //Recibe como parametro los 2 ultimos bytes de la instruccion como arreglo
    public void decodificarDireccion(byte[] instruccion) {

        //Se obtiene desde el bit 0 hasta el bit 3 (Offset de Bytes)
        int p = (0b11111111 & instruccion[2]);
        int p1 = p;
        p1 = p1 & 0b11111111111111111111111111111111;
        p1 = (byte) (p1 << 4);
        p1 = (p1 >> 4);

        //Se obtiene desde el bit 0 hasta el bit 3 (Offset de Words)
        int p2 = p;
        p2 = p2 & 0b11111111111111111111111111111111;
        p2 = (p2 >> 4);

        //Se obtiene desde el bit 4 hasta el bit 11 (Offset de Words)
        int p3 = (0b11111111 & instruccion[3]);
        p3 = p3 & 0b11111111111111111111111111111111;
        p3 = (p3 << 4);

        p3 = p2 + p3;

        //Se unifican todos los bits para obtener la direccion
        int dir_bin = p1 + (int)Math.pow(4,p3)  + this.direccionEfect-1;

        this.setValor(dir_bin);
    }

    //Set para fijar la direccion actual proporcionada por el program counter
    public void setDireccion(int direccionAct) {
        this.setValor(direccionAct);
    }
    
    public void setDireccionEfect(int direccionEfect){
        this.direccionEfect = direccionEfect;
    }
    
    public int getDireccionEfect(){
        return this.direccionEfect;
    }

    public int getDireccionAct() {
        return this.getValor();
    }
    @Override
     public void escribiralbus(){
        this.Bus_asociado.Escribirenelbus(id, this.getDireccionAct());
        
    }
    @Override
    public void leerdelbus(){
        //Actualiza el valor del registro al que esta en el Bus y le avisa que al Bus que lo hace.
        //Seguramente se utilice para que el Program Counter le indique la direccion de la instruccion que debe leer
       this.setDireccion(this.Bus_asociado.leerdelbus(id));
        
    }
}
