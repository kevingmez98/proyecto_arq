/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Logica;


public class Procesador implements ObservadorReloj{
    
     // Asignamos valores enteros constantes a cada señal de línea de control
    public static final int HLT = 0; // no c que es
    public static final int MI = 1; //La MAR lee
    public static final int RI = 2; //La RAM lee
    public static final int RO = 3; //La RAM escribe
    public static final int IO = 4; //Registro de instrucciones escribe
    public static final int II = 5;// Registro de instrucciones lee
    public static final int AI = 6;//ALU lee
    public static final int AO = 7;//ALU Escribe
    public static final int SO = 8;//no c
    public static final int SU = 9;// no c
    public static final int BI = 10;//no c
    public static final int OI = 11;//No c
    public static final int CE = 12;//No c
    public static final int CO = 13;//No c
    public static final int J = 14;// una J
    public static final int FI = 15;// No c
    
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
    
    Registro[] Banco_de_registros;
    Bus asociado;
    Memoria RAM;
    int Pasos_reloj;
    ALU alu;
    private boolean[] lineascontrol;
    TipoInstruccion instActual;
    //Indica si el fetch de la instruccion actual ya se ejecuto
    boolean fetched;
    
    public Procesador(){
        this.asociado=new Bus(); 
        inicializarBanco();
        this.RAM=new Memoria((RegistroMAR)Banco_de_registros[30],asociado);
        Reloj.obtenerReloj().addObserver(this);
        this.Pasos_reloj=0;
        this.alu = new ALU(asociado);
        this.lineascontrol = new boolean[16];
       this.fetched=false;
    }
    public void fetch(){
       if(this.fetched==false){
        switch(this.Pasos_reloj){
            case 1:
                //El program counter pasa al direccion de memoria que tiene al bus
        this.Banco_de_registros[31].escribiralbus();
        //El MAR lee la direccion de memoria del bus
        this.Banco_de_registros[30].leerdelbus();
        
        break;
            case 2:
                //Se vacian los lectores y el escritor del paso anterior
                this.asociado.limpiarlectoresyescritor();
        this.asociado.CleanBus();
        //La RAM escribe al bus los datos de la direccion actual de la mar
        this.RAM.escribiralbus(this.RAM.getInstr());
        //El registro de instrucciones lee la isntruccion del bus
        this.Banco_de_registros[29].leerdelbus();
        //Se Analizan las isntrucciones
        Reconocerinstruccion();
        //Se marca el fetch como realizado para que no haga conflicto a la hora de ejecutar las instrucciones paso a paso
        this.fetched=true;
        //Se marcan los pasos de la instruccion devuelta a 0
        this.Pasos_reloj=0;
        break;
        }
       }else{
           //Ejecutar la instruccion
       }
        
        
    }
    
    
    private void inicializarBanco(){
        Banco_de_registros=new Registro[32];
        for(int i=0;i<Banco_de_registros.length;i++){
            Banco_de_registros[i]=new Registro(i,this.asociado);
            if(i==0){
                Banco_de_registros[i]=new Registrocero(this.asociado);
            }
            if(i==29){
                 Banco_de_registros[i]=new RegistroInstrucciones(this.asociado);
            }
            if(i==30){
                 Banco_de_registros[i]=new RegistroMAR(this.asociado);
            }
            if(i==31){
                 Banco_de_registros[i]=new ContadorPrograma(this.asociado);
            }
        }
    }
    
    public void Reconocerinstruccion(){
        //Esta funcion deberia recibir una String o un arreglo de bytes?
        /*Ninguno, ya que debe recibir 32 bits del registro de instrucciones, por lo que
          se agrega una función en la memoria, la cual devuelve el entero correspondiente 
          a los 4 bytes de la instruccion
        */
        
        //Recibe el valor contenido en el registro de instrucciones
        int instruccion = Banco_de_registros[29].getValor();
        
        //NOP, LDW, SW, ADD, ADDI, AND, OR, NOT, LDI, BEQ, BGE, JMP, OUT, INVALID
                
        byte opcode = (byte)(instruccion & 0b00111111);
      
        
        switch(opcode){
            case 0:
                System.out.println("La instruccion actual es NOP");
                instActual = TipoInstruccion.NOP;
                break;
            
            case 1: 
                instActual = TipoInstruccion.LDW;
                break;
            
            case 2:
                instActual = TipoInstruccion.SW;
                break;
            
            case 3:
                instActual = TipoInstruccion.ADD;
                break;
                
            case 4:
                instActual = TipoInstruccion.ADDI;
                break;
            
            case 5:
                instActual = TipoInstruccion.AND;
                break;
            
            case 6:
                instActual = TipoInstruccion.OR;
                break;
                
            case 7:
                instActual = TipoInstruccion.NOT;
                break;
            
            case 8:
                instActual = TipoInstruccion.LDI;
                break;
            
            case 9:
                instActual = TipoInstruccion.BEQ;
                break;
                
            case 10: 
                instActual = TipoInstruccion.BGE;
                break;
                
            case 11:
                instActual = TipoInstruccion.JMP;
                break;
            
            case 12:
                instActual = TipoInstruccion.OUT;
                break;
                
            default:
                instActual = TipoInstruccion.INVALID;
                break;             
                
        }
        
    }

    @Override
    public void cambioReloj() {
        this.Pasos_reloj++;
        fetch();
        switch (Pasos_reloj){
            case 1:
                
            case 2:
                
                
            default:
                
        }
    }
    
    public void reset(){
        if(Reloj.obtenerReloj().obtenerEstado()){
            Reloj.obtenerReloj().cambiarReloj();
        }
        
        inicializarBanco();
        
        Pasos_reloj = 0;
        
        this.alu.getRegistroEstados().clear();
        
        for(int i = 0; i<lineascontrol.length;i++){
            lineascontrol[i] = false;
        }
        
        //Asigné una id fuera de los 32 registros para hacer su borrado
        this.asociado.Escribirenelbus(40, 0);
        
        //Actualizar la vista.....
        
    }
    
    
    
    
    
    
    

}
