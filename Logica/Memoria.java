

package Logica;

import java.util.ArrayList;


public class Memoria {
    
    private byte [] data;
    
    private RegistroMAR mar;
    
    private ArrayList<ObservadorRAM> suscriptores;
    
    public Memoria(RegistroMAR mar){
        this.data =  new byte [16777216];
        this.mar = mar;
        this.suscriptores = new ArrayList<ObservadorRAM>();
    }
    
    public void guardarMemoria(byte val){
        this.data [this.mar.getDireccionAct()] = val;
        this.notifyObservers(this.mar.getDireccionAct());
    }
    
    public byte cargarMemoria(){
        return this.data[this.mar.getDireccionAct()];
    }
    
    // Cambia manualmente una dirección de memoria (utilizada en WidgetRAM)
    public void cambiarValor(int address, byte newVal) {
        this.data[address] = newVal;
        this.notifyObservers(address);
    }
    
    // Devuleve el contenido de la memoria
    public byte[] getData() {
        return this.data;
    }
    
     // Métodos para implementar el patrón de diseño Observer
    public void addRAMObserver(ObservadorRAM o) {
        if (o == null) {
            return;
        }
        this.suscriptores.add(o);
    }

    public void removeRAMObserver(ObservadorRAM o) {
        if (o == null) {
            return;
        }
        this.suscriptores.remove(o);
    }

    private void notifyObservers(int address) {
        for (ObservadorRAM o : suscriptores) {
            o.cambiaValorRAM(address);
        }
    }

}
