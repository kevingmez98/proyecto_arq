/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Logica;


public class Procesador implements ObservadorReloj{
    
     // Asignamos valores enteros constantes a cada señal de línea de control
    public static final int HLT = 0;
    public static final int MI = 1;
    public static final int RI = 2;
    public static final int RO = 3;
    public static final int IO = 4;
    public static final int II = 5;
    public static final int AI = 6;
    public static final int AO = 7;
    public static final int SO = 8;
    public static final int SU = 9;
    public static final int BI = 10;
    public static final int OI = 11;
    public static final int CE = 12;
    public static final int CO = 13;
    public static final int J = 14;
    public static final int FI = 15;
    
    Registro[] Banco_de_registros;
    Bus asociado;
    Memoria RAM;
    int Pasos_reloj;
    ALU alu;
    private boolean[] lineascontrol;
    
    public Procesador(){
        inicializarBanco();
        this.asociado=new Bus();   
        this.RAM=new Memoria((RegistroMAR)Banco_de_registros[31],asociado);
        Reloj.obtenerReloj().addObserver(this);
        this.Pasos_reloj=0;
        this.alu = new ALU();
        this.lineascontrol = new boolean[16];
       
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
    
    public void Reconocerinstruccion(byte[] Nostradamus){
        //Esta funcion deberia recibir una String o un arreglo de bytes?
        
    }

    @Override
    public void cambioReloj() {
        this.Pasos_reloj++;
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
