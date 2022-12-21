package Logica;

import javax.swing.JOptionPane;
import presentacion.vistas.VistaPanelControl;

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

    public RegistroMAR getMar() {
        return mar;
    }

    public void setMar(RegistroMAR mar) {
        this.mar = mar;
    }

    public ContadorPrograma getPc() {
        return pc;
    }

    public void setPc(ContadorPrograma pc) {
        this.pc = pc;
    }

    public RegistroInstrucciones getIr() {
        return ir;
    }

    public void setIr(RegistroInstrucciones ir) {
        this.ir = ir;
    }

    public Registro[] getBanco_de_registros() {
        return Banco_de_registros;
    }

    public void setBanco_de_registros(Registro[] Banco_de_registros) {
        this.Banco_de_registros = Banco_de_registros;
    }

    public Bus getAsociado() {
        return asociado;
    }

    public void setAsociado(Bus asociado) {
        this.asociado = asociado;
    }

    public Memoria getRAM() {
        return RAM;
    }

    public void setRAM(Memoria RAM) {
        this.RAM = RAM;
    }

    public int getPasos_reloj() {
        return Pasos_reloj;
    }

    public void setPasos_reloj(int Pasos_reloj) {
        this.Pasos_reloj = Pasos_reloj;
    }

    public ALU getAlu() {
        return alu;
    }

    public void setAlu(ALU alu) {
        this.alu = alu;
    }

    public boolean[] getLineascontrol() {
        return lineascontrol;
    }

    public void setLineascontrol(boolean[] lineascontrol) {
        this.lineascontrol = lineascontrol;
    }

    public TipoInstruccion getInstActual() {
        return instActual;
    }

    public void setInstActual(TipoInstruccion instActual) {
        this.instActual = instActual;
    }

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
        this.Banco_de_registros[4].setValor(24);
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

        //Prueba instruccion de carga desde memoria (Se carga el valor anterior al regitro 5)
        this.RAM.cambiarValor(8, (byte) 0b01000001);
        this.RAM.cambiarValor(9, (byte) 0b00000001);
        this.RAM.cambiarValor(10, (byte) 0b00001010);
        this.RAM.cambiarValor(11, (byte) 0b00000000);

        //Prueba instruccion de suma de registros (Se suma el contenido del registro 3 con el contenido del registro 5 y se guarda en el registtro 8)
        this.RAM.cambiarValor(12, (byte) 0b00000011);
        this.RAM.cambiarValor(13, (byte) 0b00101010);
        this.RAM.cambiarValor(14, (byte) 0b00000011);
        this.RAM.cambiarValor(15, (byte) 0b00000000);

        //Prueba instruccion de AND (Se usan como operandos el registro 5 y 3 y se guarda el resultado en el registro 8)
        this.RAM.cambiarValor(16, (byte) 0b00000101);
        this.RAM.cambiarValor(17, (byte) 0b00101010);
        this.RAM.cambiarValor(18, (byte) 0b00000011);
        this.RAM.cambiarValor(19, (byte) 0b00000000);

        //Prueba instruccion de OR (Se usan como operandos el registro 5 y 3 y se guarda el resultado en el registro 8)
        this.RAM.cambiarValor(20, (byte) 0b00000110);
        this.RAM.cambiarValor(21, (byte) 0b00101010);
        this.RAM.cambiarValor(22, (byte) 0b00000011);
        this.RAM.cambiarValor(23, (byte) 0b00000000);

        //Prueba instruccion de NOT (Se usa como operando el registro 5 y se guarda el resultado en el registro 8)
        this.RAM.cambiarValor(24, (byte) 0b00000111);
        this.RAM.cambiarValor(25, (byte) 0b00101010);
        this.RAM.cambiarValor(26, (byte) 0b00000011);
        this.RAM.cambiarValor(27, (byte) 0b00000000);
        
        //Prueba instruccion de suma inmediata (Se toma como registro operando 5, se le suma el inmediato 100 y se guarda en el registro 8)
        this.RAM.cambiarValor(28, (byte) 0b00000100);
        this.RAM.cambiarValor(29, (byte) 0b00101010);
        this.RAM.cambiarValor(30, (byte) 0b01100100);
        this.RAM.cambiarValor(31, (byte) 0b00000000);

        //System.out.println("PRUEBA: "+RAM.getContenidoEntero());
    }

    public void fetch() {
        if (this.fetched == false) {
            switch (this.Pasos_reloj) {
                case 1:
                    //El program counter pasa al direccion de memoria que tiene al bus
                    this.lineascontrol[CO]=true;
                    this.lineascontrol[MI]=true;
                    this.pc.escribiralbus();
                    //El MAR lee la direccion de memoria del bus
                    this.mar.leerdelbus();

                    break;
                case 2:
                    //Se vacian los lectores y el escritor del paso anterior
                    this.resetLineasControl();
                    this.asociado.limpiarlectoresyescritor();
                    this.asociado.CleanBus();
                    //La RAM escribe al bus los datos de la direccion actual de la mar
                    this.lineascontrol[RO]=true;
                    this.RAM.escribirEnteroAlBus();
                    //El registro de instrucciones lee la isntruccion del bus
                    this.ir.leerdelbus();
                    this.lineascontrol[II]=true;
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
                VistaPanelControl.getTxLogArea().append("La instruccion actual es NOP"+"\n");
                instActual = TipoInstruccion.NOP;
                break;

            case 1:
                System.out.println("La instruccion actual es LDW");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es LDW"+"\n");
                instActual = TipoInstruccion.LDW;
                break;

            case 2:
                System.out.println("La instruccion actual es SW");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es SW"+"\n");
                instActual = TipoInstruccion.SW;
                break;

            case 3:
                System.out.println("La instruccion actual es ADD");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es ADD"+"\n");
                instActual = TipoInstruccion.ADD;
                break;

            case 4:
                System.out.println("La instruccion actual es ADDI");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es ADDI"+"\n");
                instActual = TipoInstruccion.ADDI;
                break;

            case 5:
                System.out.println("La instruccion actual es AND");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es AND"+"\n");
                instActual = TipoInstruccion.AND;
                break;

            case 6:
                System.out.println("La instruccion actual es OR");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es OR"+"\n");
                instActual = TipoInstruccion.OR;
                break;

            case 7:
                System.out.println("La instruccion actual es NOT");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es NOT"+"\n");
                instActual = TipoInstruccion.NOT;
                break;

            case 8:
                System.out.println("La instruccion actual es LDI");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es LDI"+"\n");
                instActual = TipoInstruccion.LDI;
                break;

            case 9:
                System.out.println("La instruccion actual es BEQ");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es BEQ"+"\n");
                instActual = TipoInstruccion.BEQ;
                break;

            case 10:
                System.out.println("La instruccion actual es BGE");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es BGE"+"\n");
                instActual = TipoInstruccion.BGE;
                break;

            case 11:
                System.out.println("La instruccion actual es JMP");
                 VistaPanelControl.getTxLogArea().append("La instruccion actual es JMP"+"\n");
                instActual = TipoInstruccion.JMP;
                break;

            case 12:
                System.out.println("La instruccion actual es OUT");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es OUT"+"\n");
                instActual = TipoInstruccion.OUT;
                break;

            default:
                System.out.println("La instruccion actual es INVALIDA");
                VistaPanelControl.getTxLogArea().append("La instruccion actual es INVALIDA"+"\n");
                instActual = TipoInstruccion.INVALID;
                break;

        }

    }
    public boolean ReconocerinstruccionValidacion(int instruccion) {
        //Esta funcion deberia recibir una String o un arreglo de bytes?
        /*Ninguno, ya que debe recibir 32 bits del registro de instrucciones, por lo que
          se agrega una función en la memoria, la cual devuelve el entero correspondiente 
          a los 4 bytes de la instruccion
         */

        //Recibe el valor contenido en el registro de instrucciones
        

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
                JOptionPane.showMessageDialog(null, "Instruccion invalida");
                return false;
                
             
        }
     return true;
    }

    public void ejecutarInstruccion() {

        resetLineasControl();

        int inst = ir.getValor();

        if (instActual == TipoInstruccion.NOP) {
            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
            }
        }

        if (instActual == TipoInstruccion.LDW) {
            /*NO SE QUE LINEAS ACTIVAR EN CADA PASO*/

            if (this.Pasos_reloj == 3) {

            }

            if (this.Pasos_reloj == 4) {

                int registrodestino = (inst >>> 6) & 0b11111;
                this.mar.decodificarDireccion(inst);
                RAM.escribirEnteroAlBus();
                Banco_de_registros[registrodestino].leerdelbus();
                System.out.println("Contenido del registro " + registrodestino + ": " + Banco_de_registros[registrodestino].getValor());
            }

            if (this.Pasos_reloj == 5) {

                pc.contar();
                this.fetched = false;
            }
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
                int fourBytesIJustRead = registroorigen;
                long unsignedValue = fourBytesIJustRead & 0xffffffffL;

                Banco_de_registros[registroorigen].escribiralbus();

                RAM.leerPalabradelBus();

                
                for (int i = 0; i < 4; i++) {
                    System.out.println("Contenido Posición " + i + ": " + Integer.toBinaryString(RAM.cargarContDir(mar.getDireccionAct() + i)));
                    System.out.println("Direccion MAR "+mar.getDireccionAct());
                }
            }

            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.ADD) {

            if (this.Pasos_reloj == 3) {
                this.lineascontrol[IO] = true;
                this.lineascontrol[AO] = true;

                int regop1 = (inst >>> 11) & 0b11111;

                Banco_de_registros[regop1].escribiralbus();

                this.alu.leerOperandodelbus(true);

            }

            if (this.Pasos_reloj == 4) {
                this.lineascontrol[BI] = true;
                int regop2 = (inst >>> 16) & 0b11111;

                Banco_de_registros[regop2].escribiralbus();

                this.alu.leerOperandodelbus(false);
            }

            if (this.Pasos_reloj == 5) {
                this.lineascontrol[SO] = true;
                this.lineascontrol[FI] = true;
                this.lineascontrol[AI] = true;

                int regdestino = (inst >>> 6) & 0b11111;

                this.alu.escribirenelbus(this.alu.salidaALU(1));

                Banco_de_registros[regdestino].leerdelbus();

                System.out.println("Resultado de la suma:" + Banco_de_registros[regdestino].getValor());

                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.ADDI) {

            if (this.Pasos_reloj == 3) {

                this.lineascontrol[IO] = true;
                this.lineascontrol[AO] = true;

                int regop1 = (inst >>> 11) & 0b11111;

                Banco_de_registros[regop1].escribiralbus();

                this.alu.leerOperandodelbus(true);

            }

            if (this.Pasos_reloj == 4) {

                //NO SE QUE MÁS LINEAS ACTIVAR AQUI
                
                this.lineascontrol[BI] = true;
                
                int inmd = (inst >>> 16);

                //En esta parte se busca el primer registro que tenga 0 como valor para cargar el inmediato (Excepto el registro zero)
                for (int i = 1; i < Banco_de_registros.length; i++) {
                    if (Banco_de_registros[i].getValor() == 0) {
                        Banco_de_registros[i].setValor(inmd);
                        Banco_de_registros[i].escribiralbus();
                        this.alu.leerOperandodelbus(false);
                        break;
                    }
                }
            }

            if (this.Pasos_reloj == 5) {

                this.lineascontrol[SO] = true;
                this.lineascontrol[FI] = true;
                this.lineascontrol[AI] = true;

                int regdestino = (inst >>> 6) & 0b11111;

                this.alu.escribirenelbus(this.alu.salidaALU(1));

                Banco_de_registros[regdestino].leerdelbus();

                System.out.println("Resultado de la suma inmediata:" + Banco_de_registros[regdestino].getValor());

                pc.contar();
                this.fetched = false;

            }

        }

        if (instActual == TipoInstruccion.AND) {

            if (this.Pasos_reloj == 3) {
                this.lineascontrol[IO] = true;
                this.lineascontrol[AO] = true;

                int regop1 = (inst >>> 11) & 0b11111;

                Banco_de_registros[regop1].escribiralbus();

                this.alu.leerOperandodelbus(true);

            }

            if (this.Pasos_reloj == 4) {
                this.lineascontrol[BI] = true;
                int regop2 = (inst >>> 16) & 0b11111;

                Banco_de_registros[regop2].escribiralbus();

                this.alu.leerOperandodelbus(false);
            }

            if (this.Pasos_reloj == 5) {
                this.lineascontrol[SO] = true;
                this.lineascontrol[FI] = true;
                this.lineascontrol[AI] = true;

                int regdestino = (inst >>> 6) & 0b11111;

                this.alu.escribirenelbus(this.alu.salidaALU(3));

                Banco_de_registros[regdestino].leerdelbus();

                System.out.println("Resultado del AND:" + Integer.toBinaryString(Banco_de_registros[regdestino].getValor()));

                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.OR) {

            if (this.Pasos_reloj == 3) {
                this.lineascontrol[IO] = true;
                this.lineascontrol[AO] = true;

                int regop1 = (inst >>> 11) & 0b11111;

                Banco_de_registros[regop1].escribiralbus();

                this.alu.leerOperandodelbus(true);

            }

            if (this.Pasos_reloj == 4) {
                this.lineascontrol[BI] = true;
                int regop2 = (inst >>> 16) & 0b11111;

                Banco_de_registros[regop2].escribiralbus();

                this.alu.leerOperandodelbus(false);
            }

            if (this.Pasos_reloj == 5) {
                this.lineascontrol[SO] = true;
                this.lineascontrol[FI] = true;
                this.lineascontrol[AI] = true;

                int regdestino = (inst >>> 6) & 0b11111;

                this.alu.escribirenelbus(this.alu.salidaALU(4));

                Banco_de_registros[regdestino].leerdelbus();

                System.out.println("Resultado del OR:" + Integer.toBinaryString(Banco_de_registros[regdestino].getValor()));

                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.NOT) {

            if (this.Pasos_reloj == 3) {

                this.lineascontrol[IO] = true;
                this.lineascontrol[AO] = true;

                int regop1 = (inst >>> 11) & 0b11111;

                Banco_de_registros[regop1].escribiralbus();

                this.alu.leerOperandodelbus(true);

            }

            if (this.Pasos_reloj == 4) {

            }

            if (this.Pasos_reloj == 5) {

                this.lineascontrol[SO] = true;
                this.lineascontrol[FI] = true;
                this.lineascontrol[AI] = true;

                int regdestino = (inst >>> 6) & 0b11111;

                this.alu.escribirenelbus(this.alu.salidaALU(5));

                Banco_de_registros[regdestino].leerdelbus();

                System.out.println("Resultado del NOT:" + Integer.toBinaryString(Banco_de_registros[regdestino].getValor()));

                pc.contar();
                this.fetched = false;

            }

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

                System.out.println("Registro " + registro + ": Su valor ha cambiado a " + Banco_de_registros[registro].getValor());
            } else {
                if (Pasos_reloj == 5) {
                    pc.contar();
                    this.fetched = false;
                }
            }

        }

        if (instActual == TipoInstruccion.BEQ) {

            if (this.Pasos_reloj == 3) {

            }

            if (this.Pasos_reloj == 4) {

            }

            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.BGE) {

            if (this.Pasos_reloj == 3) {

            }

            if (this.Pasos_reloj == 4) {

            }

            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.JMP) {

            if (this.Pasos_reloj == 3) {

            }

            if (this.Pasos_reloj == 4) {

            }

            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.OUT) {

            if (this.Pasos_reloj == 3) {

            }

            if (this.Pasos_reloj == 4) {

            }

            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
            }

        }

        if (instActual == TipoInstruccion.INVALID) {

            if (this.Pasos_reloj == 3) {

            }

            if (this.Pasos_reloj == 4) {

            }

            if (this.Pasos_reloj == 5) {
                pc.contar();
                this.fetched = false;
                reset();
            }

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
