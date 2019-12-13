/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import clases.Ventana;
import javax.swing.JFrame;

/**
 *
 * 
 * @author Henry Moreno / YouTube: El Rincón del programador
 * @see <a href = "http://www.youtube.com/c/ElRinconDelProgramador" /> El rincón del programador / tutoriales de programación </a>
 */

public class Principal {
    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
