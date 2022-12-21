

package Logica;

public class HiloReloj extends Thread {

    private boolean finalizado;
    private double tiempoPausa;

    public HiloReloj(double t) {
        this.finalizado = false;

        // Valida la entrada
        this.tiempoPausa=t;
    }

    public void terminar() {
        finalizado = true;
    }

    public double getTiempoPausa() {
        return this.tiempoPausa;
    }

    public void run() {
        while (!finalizado) {            
            try {
                
                Thread.sleep((long)tiempoPausa);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Hace el cambio, luego repite el ciclo
            Reloj.obtenerReloj().cambiarReloj();
        }
    }
}
