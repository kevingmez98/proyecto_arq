package Logica;

public class ALU {

    private Flags banderas;
    private int id, operando1, operando2;
    private Bus bus_asociado;
    public ALU(Bus asociado) {
        this.banderas = new Flags();
        this.id=43;
        this.bus_asociado=asociado;
    }
    


    //Activa banderas en las operaciones de suma/resta
    public void activarBanderas() {
        boolean zF, cF;

        long resultado;

        /*Codigo en el caso de que se implemente la resta por aparte, agregar parametro
        booleano 'op'*/
        /*
        if (op) {
            resultado = (0b11111111111111111111111111111111 & registroA.getValor())
                    + (0b11111111111111111111111111111111 & registroB.getValor());
        } else {
            resultado = (0b11111111111111111111111111111111 & registroA.getValor())
                    - (0b11111111111111111111111111111111 & registroB.getValor());
        }
        */
        
        resultado = (0b11111111111111111111111111111111 & operando1)
                    + (0b11111111111111111111111111111111 & operando2);

        if ((resultado & 0b11111111111111111111111111111111) == 0) {
            zF = true;
        } else {
            zF = false;
        }

        //Evaluar el carry
        if ((resultado & 0b100000000000000000000000000000000L) == 0) {
            cF = false;
        } else {
            cF = true;
        }

        banderas.flagsIn(zF, cF);
    }

    public int salidaALU(int op) {
        int salida = 0;

        switch (op) {
            case 1:
                salida = (int) (operando1 + operando2);
                break;

            //Case para la operacion de resta por aparte
            /*
            case 2:
                salida = (int) (registroA.getValor() - registroB.getValor());
                break;
            */
            
            case 3:
                salida = (operando1 & operando2);
                break;
                
            case 4:
                salida = (operando1 | operando2);
                break;
                
            case 5:
                salida = ~(operando1);
                break;
                
        }
        
        return salida;
    }
    
    public void escribirenelbus(int dato){
        this.bus_asociado.Escribirenelbus(this.id, dato);
    }
    
    public void leerOperandodelbus(boolean op){
        if(op){
            operando1 = this.bus_asociado.leerdelbus(this.id);
        }else{
            operando2 = this.bus_asociado.leerdelbus(this.id);
        }
    }
    
    // funciones Getter 
    public boolean getZeroFlag() {
        return banderas.getZF();
    }

    public boolean getCarryFlag() {
        return banderas.getCF();
    }

    public Flags getRegistroEstados() {
        return banderas;
    }
    
}
