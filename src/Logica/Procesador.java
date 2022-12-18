/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Logica;


public class Procesador implements ObservadorReloj{
    Registro[] Banco_de_registros;
    Bus asociado;
    Memoria RAM;
    int Pasos_reloj;
    
    public Procesador(){
        this.asociado=new Bus();
        inicializarBanco();
        this.RAM=new Memoria((RegistroMAR)Banco_de_registros[31],asociado);
        Reloj.obtenerReloj().addObserver(this);
        this.Pasos_reloj=0;
       
    }
    private void inicializarBanco(){
        Banco_de_registros=new Registro[32];
        for(int i=0;i<Banco_de_registros.length;i++){
            Banco_de_registros[i]=new Registro(i,this.asociado);
            if(i==0){
                Banco_de_registros[i]=new Registrocero(this.asociado);
            }
            if(i==30){
                 Banco_de_registros[i]=new RegistroInstrucciones(this.asociado);
            }
            if(i==31){
                 Banco_de_registros[i]=new RegistroMAR(81,this.asociado);
            }
            if(i==32){
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
    
    

}
