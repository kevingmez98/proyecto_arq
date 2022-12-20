
package Logica;

public interface ObservadorProcesador {
    
        // Indica que el valor del Registro reg ha cambiado
	public void cambioRegistro(int v, byte reg);

	// Indica que el valor del Contador de Programa PC ha cambiado con un nuevo valor v 
	public void cambioPC(int v);

	// Indica que el valor del Registro de direccionamiento de Memoria MAR ha cambiado con un nuevo valor v 
	public void cambioMAR(int v);

	// Indica que el valor del Registro de Salida ha cambiado con un nuevo valor v 
	public void cambioOUT(int v);

	// Indica que el valor del Registro de Instruccion IR ha cambiado con un nuevo valor v 
	public void cambioIR(int v);

	// Indica que el valor del ciclo ha cambiado con un nuevo valor v 
	public void cambioConteoPaso(int v);

	// Indica que el valor del Registro de Estados FLAGS ha cambiado con un nuevo valor v 
	public void cambioFLAGS();

	// Indica que el valor del BUS ha cambiado con un nuevo valor v 
	public void cambioBUS(int v);

	// Indica que una o más líneas de control han cambiado
	public void cambioLineasControl();
    
}
