
package Logica;

import presentacion.vistas.VistaPanelControl;

public class ContadorPrograma extends Registro {

    public void contar() {
        if (this.getValor() >= 79) {
            this.valor = 0;
             VistaPanelControl.getTxLogArea().append("El program counter ha cambiado a "+this.valor+"\n");
        } else {
            this.valor+=4;
            VistaPanelControl.getTxLogArea().append("El program counter ha cambiado a "+this.valor+"\n");
        }
    }

    public ContadorPrograma(Bus a) {
        super(31,a);
    }

}
