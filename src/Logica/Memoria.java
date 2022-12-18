package Logica;

import java.math.BigInteger;
import java.util.ArrayList;

public class Memoria {

    private byte[] data;
    private int id;
    private RegistroMAR mar;

    private ArrayList<ObservadorRAM> suscriptores;

    private Bus Bus_Asociado;

    public Memoria(RegistroMAR mar, Bus Asociado) {
        this.data = new byte[16777216];
        this.mar = mar;
        this.suscriptores = new ArrayList<ObservadorRAM>();
        this.Bus_Asociado = Asociado;
        //No hay una razon en especifico para asignar el id 42 a la ram, pero siempre y cuando sea diferente al de los 32 registros esta bien.
        this.id = 42;
    }

    public void guardarMemoria(byte val) {
        this.data[this.mar.getDireccionAct()] = val;
        this.notifyObservers(this.mar.getDireccionAct());
    }

    
    //Devuelve el entero correspondiente a la instruccion que comienza donde apunte el MAR
    public int getInstr() {
        if (mar.getDireccionAct() <= 16777212) {
            byte[] temp = new byte[4];
            int ite = 0;
            for (int i = mar.getDireccionAct(); i < 4; i++) {
                temp[ite] = this.cargarContDir(i + ite);
                ite++;
            }
            
            int p1 = temp[0];
            int p2 = (int)temp[1]<<8;
            int p3 = (int)temp[2]<<16;
            int p4 = (int)temp[3]<<24;
  
            return p1+p2+p3+p4;
        }
        return 0;
    }

    //Carga el contenido de la direccion a la que apunta el MAR
    public byte cargarMemoria() {
        return this.data[this.mar.getDireccionAct()];
    }

    // Cambia manualmente una dirección de memoria (utilizada en WidgetRAM)
    public void cambiarValor(int address, byte newVal) {
        this.data[address] = newVal;
        this.notifyObservers(address);
    }

    //Carga el contenido de la direccion especifiacada
    public byte cargarContDir(int address) {
        return this.data[address];
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

    public void escribiralbus(int a) {
        this.Bus_Asociado.Escribirenelbus(id, this.data[a]);

    }

    public void leerdelbus() {
        //Actualiza el valor del registro al que esta en el Bus y le avisa que al Bus que lo hace.
        this.guardarMemoria(intTobytearray(this.Bus_Asociado.leerdelbus(id))[0]);

    }

    public int bytearrayToint(byte[] array) {
        //Solo convierte un array de bytes a un entero
        BigInteger Enterodecimal = new BigInteger(array);
        return Enterodecimal.intValue();

    }

    public byte[] intTobytearray(int valor) {
        //Solo convierte un entero a un array de bits
        BigInteger Enterodecimal = new BigInteger(valor + "");
        return Enterodecimal.toByteArray();

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
