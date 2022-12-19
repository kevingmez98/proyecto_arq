
package Logica;

public class Procesador implements ObservadorReloj {

    // Asignamos valores enteros constantes a cada señal de línea de control
    public static final int HLT = 0; // no c que es
    public static final int MI = 1; //La MAR lee
    public static final int RI = 2; //La RAM lee
    public static final int RO = 3; //La RAM escribe
    public static final int IO = 4; //Registro de instrucciones escribe
    public static final int II = 5; // Registro de instrucciones lee
    public static final int AI = 6; // Registro Operando 1 lee
    public static final int AO = 7; // Valor del Registro Operando 1
    public static final int SO = 8; // Valor del ALU
    public static final int SU = 9; // Actualización del registro de estados
    public static final int BI = 10; // Registro Operando 2 lee
    public static final int OI = 11; //Registro de salida lee del bus
    public static final int CE = 12; //Ingremento del PC
    public static final int CO = 13; //Valor del PC en el Bus
    public static final int J = 14; // Nuevo valor del PC cuando hay un salto
    public static final int FI = 15; // No c

    // Enumera los tipos de instrucciones válidas admitidas en el simulador
    /* 000000 - NOP
       000001 - LDW
       000010 - SW
       000011 - ADD
       000100 - ADDI
       000101 - AND
       000110 - OR
       000111 - NOT
       001000 - LDI
       001001 - BEQ
       001010 - BGE
       001011 - JMP
       001100 - OUT
       001101 - INVALID
     */
    public enum TipoInstruccion {
        NOP, LDW, SW, ADD, ADDI, AND, OR, NOT, LDI, BEQ, BGE, JMP, OUT, INVALID
    }

    /*
      Se crean estos registros por aparte para poder usar sus metodos caracteristicos
      de forma sencilla ademas, esto se hace para que no se sobreescriban 
      valores en estos registros
     */
    RegistroMAR mar;
    ContadorPrograma pc;
    RegistroInstrucciones ir;

    Registro[] Banco_de_registros;
    Bus asociado;
    Memoria RAM;
    int Pasos_reloj;
    ALU alu;
    private boolean[] lineascontrol;
    TipoInstruccion instActual;
    //Indica si el fetch de la instruccion actual ya se ejecuto
    boolean fetched;

    public Procesador() {
        this.asociado = new Bus();
        inicializarBanco();
        this.RAM = new Memoria(mar, asociado);
        Reloj.obtenerReloj().addObserver(this);
        this.Pasos_reloj = 0;
        this.alu = new ALU(asociado);
        this.lineascontrol = new boolean[16];
        this.fetched = false;
        //Para probar las instrucciones
        //Prueba instruccion de carga inmediata (Se carga un valor en el registro 3)
        this.RAM.cambiarValor(0, (byte) 0b11001000);
        this.RAM.cambiarValor(1, (byte) 0b00000000);
        this.RAM.cambiarValor(2, (byte) 0b00000000);
        this.RAM.cambiarValor(3, (byte) 0b10000000);

        //Prueba instruccion de guardado (Guarda el valor anterior desde la posicion 90)
        this.RAM.cambiarValor(4, (byte) 0b11000010);
        this.RAM.cambiarValor(5, (byte) 0b00000000);
        this.RAM.cambiarValor(6, (byte) 0b00001010);
        this.RAM.cambiarValor(7, (byte) 0b00000000);

        //System.out.println("PRUEBA: "+RAM.getInstr());
    }

    public void fetch() {
        if (this.fetched == false) {
            switch (this.Pasos_reloj) {
                case 1:
                    //El program counter pasa al direccion de memoria que tiene al bus
                    this.pc.escribiralbus();
                    //El MAR lee la direccion de memoria del bus
                    this.mar.leerdelbus();

                    break;
                case 2:
                    //Se vacian los lectores y el escritor del paso anterior
                    this.asociado.limpiarlectoresyescritor();
                    this.asociado.CleanBus();
                    //La RAM escribe al bus los datos de la direccion actual de la mar
                    this.RAM.escribirInstAlBus(this.RAM.getInstr());
                    //El registro de instrucciones lee la isntruccion del bus
                    this.ir.leerdelbus();
                    //Se Analizan las isntrucciones
                    Reconocerinstruccion();
                    //Se marca el fetch como realizado para que no haga conflicto a la hora de ejecutar las instrucciones paso a paso
                    this.fetched = true;
                    //Se marcan los pasos de la instruccion devuelta a 0
                    break;
            }
        } else {
            //Ejecutar la instruccion
            ejecutarInstruccion();
        }

    }

    private void inicializarBanco() {
        Banco_de_registros = new Registro[29];
        this.mar = new RegistroMAR(this.asociado);
        this.pc = new ContadorPrograma(this.asociado);
        this.ir = new RegistroInstrucciones(this.asociado);

        for (int i = 0; i < Banco_de_registros.length; i++) {
            Banco_de_registros[i] = new Registro(i, this.asociado);
            if (i == 0) {
                Banco_de_registros[i] = new Registrocero(this.asociado);
            }
            //En el registro 28 se guarda la direccion efectiva de memoria
            if (i == 28) {
                Banco_de_registros[i].setValor(80);
                mar.setDireccionEfect(Banco_de_registros[i].getValor());
            }
        }
    }

    public void Reconocerinstruccion() {
        //Esta funcion deberia recibir una String o un arreglo de bytes?
        /*Ninguno, ya que debe recibir 32 bits del registro de instrucciones, por lo que
          se agrega una función en la memoria, la cual devuelve el entero correspondiente 
          a los 4 bytes de la instruccion
         */

        //Recibe el valor contenido en el registro de instrucciones
        int instruccion = ir.getValor();

        //NOP, LDW, SW, ADD, ADDI, AND, OR, NOT, LDI, BEQ, BGE, JMP, OUT, INVALID
        byte opcode = (byte) (instruccion & 0b00111111);

        switch (opcode) {
            case 0:
                System.out.println("La instruccion actual es NOP");
                instActual = TipoInstruccion.NOP;
                break;

            case 1:
                System.out.println("La instruccion actual es LDW");
                instActual = TipoInstruccion.LDW;
                break;

            case 2:
                System.out.println("La instruccion actual es SW");
                instActual = TipoInstruccion.SW;
                break;

            case 3:
                System.out.println("La instruccion actual es ADD");
                instActual = TipoInstruccion.ADD;
                break;

            case 4:
                System.out.println("La instruccion actual es ADDI");
                instActual = TipoInstruccion.ADDI;
                break;

            case 5:
                System.out.println("La instruccion actual es AND");
                instActual = TipoInstruccion.AND;
                break;

            case 6:
                System.out.println("La instruccion actual es OR");
                instActual = TipoInstruccion.OR;
                break;

            case 7:
                System.out.println("La instruccion actual es NOT");
                instActual = TipoInstruccion.NOT;
                break;

            case 8:
                System.out.println("La instruccion actual es LDI");
                instActual = TipoInstruccion.LDI;
                break;

            case 9:
                System.out.println("La instruccion actual es BEQ");
                instActual = TipoInstruccion.BEQ;
                break;

            case 10:
                System.out.println("La instruccion actual es BGE");
                instActual = TipoInstruccion.BGE;
                break;

            case 11:
                System.out.println("La instruccion actual es JMP");
                instActual = TipoInstruccion.JMP;
                break;

            case 12:
                System.out.println("La instruccion actual es OUT");
                instActual = TipoInstruccion.OUT;
                break;

            default:
                System.out.println("La instruccion actual es INVALIDA");
                instActual = TipoInstruccion.INVALID;
                break;

        }

    }

    public void ejecutarInstruccion() {

        resetLineasControl();

        int inst = ir.getValor();

        if (instActual == TipoInstruccion.NOP) {

        }

        if (instActual == TipoInstruccion.LDW) {

        }

        if (instActual == TipoInstruccion.SW) {
            
            

            if (this.Pasos_reloj == 3) {
                this.lineascontrol[IO] = true;
                this.lineascontrol[MI] = true;
            }

            if (this.Pasos_reloj == 4) {
                mar.decodificarDireccion(inst);
                this.lineascontrol[AO] = true;
                this.lineascontrol[RI] = true;
                int registroorigen = (inst >> 6) & 0b11111;

                Banco_de_registros[registroorigen].escribiralbus();

                RAM.leerPalabradelBus();

                for (int i = 0; i < 4; i++) {
                    System.out.println("Contenido Posición " + i + ": " + Integer.toBinaryString(RAM.cargarContDir(mar.getDireccionAct() + i)));
                }
            }

            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.ADD) {

        }

        if (instActual == TipoInstruccion.ADDI) {

        }

        if (instActual == TipoInstruccion.AND) {

        }

        if (instActual == TipoInstruccion.OR) {

        }

        if (instActual == TipoInstruccion.NOT) {

        }

        if (instActual == TipoInstruccion.LDI) {

            if (this.Pasos_reloj == 3) {
                /*Activar lineas de control*/
                lineascontrol[IO] = true;
                lineascontrol[AI] = true;
                //Hacer la actualización en la vista.....

                int inmediato = inst >>> 11;
                int registro = inst >> 6;
                registro = registro & 0b11111;
                //El registro de instrucciones toma el valor del inmediato a cargar
                // y lo escribe en el bus para que el registro destino lo lea
                ir.setValor(inmediato);
                ir.escribiralbus();
                //TENER CUIDADDO DE NO USAR LOS VALORES 29,30,31 YA QUE EL ARREGLO SOLO TIENE 29 POSICIONES
                Banco_de_registros[registro].leerdelbus();
                //Linea de prueba
                System.out.println("Registro " + registro + ": Su valor ha cambiado a " + inmediato);
            } else {            
                if(Pasos_reloj == 5){
                    pc.contar();
                    this.fetched = false;
                }
            }

        }

        if (instActual == TipoInstruccion.BEQ) {

        }

        if (instActual == TipoInstruccion.BGE) {

        }

        if (instActual == TipoInstruccion.JMP) {

        }

        if (instActual == TipoInstruccion.OUT) {

        }

        if (instActual == TipoInstruccion.INVALID) {
            reset();
        }

    }

    @Override
    public void cambioReloj() {

        if (this.Pasos_reloj == 5) {
            this.Pasos_reloj = 1;
            fetch();
        } else {
            this.Pasos_reloj++;
            fetch();
        }

    }

    public void reset() {
        if (Reloj.obtenerReloj().obtenerEstado()) {
            Reloj.obtenerReloj().cambiarReloj();
        }

        inicializarBanco();

        Pasos_reloj = 0;

        this.alu.getRegistroEstados().clear();

        this.asociado.CleanBus();
        this.asociado.limpiarlectoresyescritor();

        for (int i = 0; i < lineascontrol.length; i++) {
            lineascontrol[i] = false;
        }

        //Asigné una id fuera de los 32 registros para hacer su borrado
        this.asociado.Escribirenelbus(40, 0);

        //Actualizar la vista.....
    }

    private void resetLineasControl() {
        for (int i = 0; i < lineascontrol.length; i++) {
            lineascontrol[i] = false;
        }

        this.asociado.CleanBus();
        this.asociado.limpiarlectoresyescritor();

    }

}
