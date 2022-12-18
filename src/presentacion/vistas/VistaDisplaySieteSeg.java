package presentacion.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import presentacion.controladores.ControlDisplaySieteSeg;

@SuppressWarnings("serial")
public class VistaDisplaySieteSeg extends JPanel {

    private boolean conSigno; // Si 0 = sin signo; 1 = con signo, aplica complemento a 2
    private byte valor;
    
    private GridBagConstraints c;

    private JLabel lblA;
    private JLabel lblB;
    private JLabel lblC;
    private JLabel lblD;
    private JLabel[] lblArr;

   // private ControlDisplaySieteSeg control; //Controlador 7 segmentos

    private static ImageIcon iconNumeros[];
    private static ImageIcon iconNegativo;


    public VistaDisplaySieteSeg(byte val) {
        // Carga los íconos a la memoria
        String strRuta = "/iconos/icon_";
        iconNumeros = new ImageIcon[10];
        try {
            for (int i = 0; i < 10; i++) {
                BufferedImage pic = ImageIO.read(getClass().getResource(strRuta + i + ".png"));
                iconNumeros[i] = new ImageIcon(pic.getScaledInstance(204 / 4, 286 / 4, java.awt.Image.SCALE_SMOOTH));
            }            
            BufferedImage picNegative = ImageIO.read(getClass().getResource(strRuta + "neg.png"));
            iconNegativo = new ImageIcon(picNegative.getScaledInstance(204 / 4, 286 / 4, java.awt.Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(260, 105));

        // Layout
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        this.setBackground(new Color(223, 220, 206));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.valor = 0;

        // display
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.lblA = new JLabel(iconNumeros[0]);

        this.add(this.lblA, c);
        c.gridx = 1;
        c.gridy = 1;
        this.lblB = new JLabel(iconNumeros[0]);

        this.add(this.lblB, c);

        c.gridx = 2;
        c.gridy = 1;
        this.lblC = new JLabel(iconNumeros[0]);
        this.add(this.lblC, c);

        c.gridx = 3;
        c.gridy = 1;
        this.lblD = new JLabel(iconNumeros[0]);
        this.add(this.lblD, c);

        lblArr = new JLabel[5];
        lblArr[1] = lblD;
        lblArr[2] = lblC;
        lblArr[3] = lblB;
        lblArr[4] = lblA;
        VistaDisplaySieteSeg.this.setValor(val, this.conSigno);
    }

    public void getControl() {
        //Control 7 segmentos
    }

    private void setImagen(int pos, int val) {        
        if(val >=0 && val <=9){
            lblArr[pos].setIcon(iconNumeros[val]);
        }
        else if (val == -1) {
            lblArr[pos].setIcon(iconNegativo);
        }
    }

    public void setValor(byte val) {
        this.setValor(val, this.conSigno);
        this.valor = val;
    }

    private void setValor(byte val, boolean complemento) {
        if (!complemento) {
            int unsignedVal = 0b11111111 & val;

            // Calculo posición de unidades
            int onesPos = unsignedVal % 10;
            setImagen(1, onesPos);

            // Calculo posición de decenas
            int tensPos = (unsignedVal % 100) / 10;
            setImagen(2, tensPos);

            // Calculo posición de centenas
            int hundredsPos = (unsignedVal % 1000) / 100;
            setImagen(3, hundredsPos);

            // Sin signo, entonces dejar la última imagen como un 0
            setImagen(4, 0);
            return;
        }

        // Si nuestro número no es negativo, trátelo como un valor sin signo
        if (val >= 0) {
            VistaDisplaySieteSeg.this.setValor(val, false);
            return;
        }

        // De lo contrario, tenemos un número negativo.
        VistaDisplaySieteSeg.this.setValor((byte) (128 - (0b01111111 & val)), false);
        setImagen(4, -1);

    }

    public boolean isConSigno() {
        return conSigno;
    }

    public void setConSigno(boolean signo) {
        this.conSigno = signo;
    }

    public byte getByteValor() {
        return valor;
    }

    public void setByteValor(byte byteVal) {
        this.valor = byteVal;
    }


    
}
