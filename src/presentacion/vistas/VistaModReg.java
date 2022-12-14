/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.vistas;

import Logica.Procesador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import presentacion.controladores.ControladorModReg;
//Agregar controlador al crear la vista

/**
 *
 * @author Asus
 */
public class VistaModReg extends JPanel {

    // txt de la instruccion a modificar
    private JTextField txtDirMod;

    // txt direccion a comprobar
    private JTextField txtPosicion;

    // txt de la nueva instruccion
    private JTextField txtNuevaInst;

    private JButton btnCambiarInstruccion;

    private JButton btnVerificarValorPos;

    private Procesador procs;

    private GridBagConstraints grid;

    private ControladorModReg controlador;

    public void setControlador(ControladorModReg controlador) {
        this.controlador = controlador;
    }

    public void setGrid(GridBagConstraints grid) {
        this.grid = grid;
    }

    public JButton getBtnCambiarInstruccion() {
        return btnCambiarInstruccion;
    }

    public void setBtnCambiarInstruccion(JButton btnCambiarInstruccion) {
        this.btnCambiarInstruccion = btnCambiarInstruccion;
    }

    public JButton getBtnVerificarValorPos() {
        return btnVerificarValorPos;
    }

    public void setBtnVerificarValorPos(JButton btnVerificarValorPos) {
        this.btnVerificarValorPos = btnVerificarValorPos;
    }

    public GridBagConstraints getGrid() {
        return grid;
    }

    public VistaModReg() {
        this.setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        this.setBackground(new Color(223, 220, 206));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        generarFormInstruccion();
        generarFormComprobacion();

    }

    public VistaModReg(Procesador procs){
        this.procs=procs;
          this.setLayout(new GridBagLayout());
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        this.setBackground(new Color(223, 220, 206));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        generarFormInstruccion();
        generarFormComprobacion();   
    }

    // Formulario para comprobar la posicion de memoria
    public void generarFormComprobacion() {
        grid.gridx = 2;
        grid.gridy = 0;
        JLabel lbl = new JLabel("Verificar valor en posicion");
        this.add(lbl, grid);

        grid.gridx = 2;
        grid.gridy = 1;
        txtPosicion = new JTextField("Posicion");
        txtPosicion.setMinimumSize(new Dimension(100, 20));
        this.add(txtPosicion, grid);

        grid.gridx = 2;
        grid.gridy = 3;
        btnVerificarValorPos = new JButton("Comprobar valor");
        btnVerificarValorPos.setActionCommand("compValorPos");
        btnVerificarValorPos.addActionListener(getControlador());
        this.add(btnVerificarValorPos, grid);
    }

    public void generarFormInstruccion() {
        grid.gridx = 0;
        grid.gridy = 0;
        JLabel lbl = new JLabel("Cambiar instruccion");
        this.add(lbl, grid);

        grid.gridx = 0;
        grid.gridy = 1;
        txtDirMod = new JTextField("Direccion");
        txtDirMod.setMinimumSize(new Dimension(100, 20));
        this.add(txtDirMod, grid);

        grid.gridx = 0;
        grid.gridy = 2;
        txtNuevaInst = new JTextField("instruccion");
        txtNuevaInst.setMinimumSize(new Dimension(100, 20));
        this.add(txtNuevaInst, grid);

        grid.gridx = 0;
        grid.gridy = 3;
        btnVerificarValorPos = new JButton("Cambiar instruccion");
        btnVerificarValorPos.setActionCommand("camInstruccion");
        btnVerificarValorPos.addActionListener(getControlador());
        this.add(btnVerificarValorPos, grid);
    }

    public JTextField getTxtDirMod() {
        return txtDirMod;
    }

    public void setTxtDirMod(JTextField txtDirMod) {
        this.txtDirMod = txtDirMod;
    }

    public JTextField getTxtPosicion() {
        return txtPosicion;
    }

    public void setTxtPosicion(JTextField txtDireccion) {
        this.txtPosicion = txtDireccion;
    }

    public JTextField getTxtNuevaInst() {
        return txtNuevaInst;
    }

    public void setTxtNuevaInst(JTextField txtNuevaInst) {
        this.txtNuevaInst = txtNuevaInst;
    }

    public ControladorModReg getControlador() {
        if(this.controlador==null){
           this.controlador=new ControladorModReg(this,this.procs);
       }
        return controlador;
    }

}
