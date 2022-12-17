

package Logica;

import java.util.ArrayList;


public class Reloj {
    
    private boolean estado;
    private boolean detenido;
    
    
    private ArrayList<ObservadorReloj> suscriptores;
    
    private static Reloj reloj;
    
    private Reloj(){
        this.estado = false;
        this.detenido =  false;
        this.suscriptores = new ArrayList<ObservadorReloj>();
    }
    
    public static Reloj obtenerReloj(){
        if(reloj==null){
            reloj = new Reloj();
        }
        return reloj;
    }
    
    public boolean obtenerEstado(){
        return obtenerReloj().estado;
    }
    
    public void activarReloj(boolean v){
        this.detenido = v;
    }
    
    public void cambiarReloj(){
        obtenerReloj();
        
        if(this.detenido){
            return;
        }
        
        this.estado = !this.estado;
        
        notifyObservers();
        return;    
    }
    
    // Implementación del Patrón Observer
    public void addObserver(ObservadorReloj o) {
        if (o == null) {
            return;
        }
        this.suscriptores.add(o);
    }

    public void removeObserver(ObservadorReloj o) {
        if (o == null) {
            return;
        }
        this.suscriptores.remove(o);
    }

    public void notifyObservers() {
        for (ObservadorReloj o : suscriptores) {
            o.cambioReloj();
        }
    }

}
