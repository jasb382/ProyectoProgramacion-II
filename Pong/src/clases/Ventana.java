/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Henry Moreno / YouTube: El Rincón del programador
 * @see <a href = "http://www.youtube.com/c/ElRinconDelProgramador" /> El rincón del programador / tutoriales de programación </a>
 */
import javax.swing.*;

import control.EngineGraphics;

public class Ventana extends JFrame {

    Tablero canvas;
    Pelota p = new Pelota();

    public Ventana() {
        setTitle("Pong DEMO");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        canvas = new Tablero();
        add(canvas);

        addKeyListener(new EventoTeclado());
        new EngineGraphics(canvas).start();
    }

}
