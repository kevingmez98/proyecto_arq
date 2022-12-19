package Logica;

public class RegistroMAR {

    private int direccionAct;
    private int direccionEfect;

    public RegistroMAR(int direccionEfect) {
        this.direccionEfect = direccionEfect;
        this.direccionAct = 0;
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

        this.direccionAct = dir_bin;
    }

    //Set para fijar la direccion actual proporcionada por el program counter
    public void setDireccion(int direccionAct) {
        this.direccionAct = direccionAct;
    }

    public int getDireccionAct() {
        return this.direccionAct;
    }

}
