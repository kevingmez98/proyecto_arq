
package Logica;

public class ContadorPrograma extends Registro {

    public void contar() {
        if (this.valor == 79) {
            this.valor = 0;
        } else {
            this.valor++;
        }
    }

    public ContadorPrograma(Bus a) {
        super(32,a);
    }

}
