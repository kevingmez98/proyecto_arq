
package Logica;

public class ContadorPrograma extends Registro {

    public void contar() {
        if (this.getValor() == 79) {
            this.valor = 0;
        } else {
            this.valor++;
        }
    }

    public ContadorPrograma(Bus a) {
        super(31,a);
    }

}