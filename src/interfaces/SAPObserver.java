/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

/**
 *
 * @author Asus
 */

public interface SAPObserver {
	// Indica que el valor del Registro A ha cambiado con un nuevo valor v 
	public void cambioRegistroA();

	// Indica que el valor del Registro B ha cambiado con un nuevo valor v 
	public void cambioRegistroB();

	// Indica que el valor del Contador de Programa PC ha cambiado con un nuevo valor v 
	public void cambioPC();

	// Indica que el valor del Registro de direccionamiento de Memoria MAR ha cambiado con un nuevo valor v 
	public void cambioMAR();

	// Indica que el valor del Registro de Salida ha cambiado con un nuevo valor v 
	public void cambioOUT();

	// Indica que el valor del Registro de Instruccion IR ha cambiado con un nuevo valor v 
	public void cambioIR();

	// Indica que el valor del ciclo ha cambiado con un nuevo valor v 
	public void cambioConteoPaso();

	// Indica que el valor del Registro de Estados FLAGS ha cambiado con un nuevo valor v 
	public void cambioFLAGS();

	// Indica que el valor del BUS ha cambiado con un nuevo valor v 
	public void cambioBUS();

	// Indica que una o más líneas de control han cambiado
	public void cambioLineasControl();
}

