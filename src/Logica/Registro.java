/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.math.BigInteger;

public class Registro {

    public void setValor(int valor) {

        this.valor = valor;
        this.Stringbytevalue = bytetoString(valor);

    }

    public int getValor() {
        return this.valor;
    }

    public String getStringValue() {
        if (this.Stringbytevalue == null) {
            return "00000000000000000000000000000000";
        }
        return this.Stringbytevalue;
    }

    public void clear() {
        this.valor = 0;
    }

    public Registro(int id, Bus Asociado) {
        this.valor = 0;
        this.id = id;
        this.Bus_asociado = Asociado;
    }

    public void escribiralbus() {
        this.Bus_asociado.Escribirenelbus(id, valor);

    }

    public void leerdelbus() {
        //Actualiza el valor del registro al que esta en el Bus y le avisa que al Bus que lo hace.
        this.setValor(this.Bus_asociado.leerdelbus(id));

    }

    public String bytetoString(int a) {
        //Funcion que convierte un Arreglo de Bytes a su representacion literal en String
        String s2 = "";
        BigInteger holandesherrante = BigInteger.valueOf(a);
        byte[] array = holandesherrante.toByteArray();
        for (int i = 0; i < array.length; i++) {
            String s1 = String.format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0');
            s2 = s2 + s1;
        }
        for (int i = 0; s2.length() < 32; i++) {
            s2 = "0" + s2;
        }
        
        return s2;
    }
    
    protected int id;
    protected int valor;
    protected String Stringbytevalue;
    protected Bus Bus_asociado;
}
