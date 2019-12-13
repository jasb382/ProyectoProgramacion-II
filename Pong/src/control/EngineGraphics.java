/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import clases.Tablero;
import clases.Pelota;

/**
 *
 * @author Henry Moreno / YouTube: El Rincón del programador
 * @see <a href = "http://www.youtube.com/c/ElRinconDelProgramador" /> El rincón
 * del programador / tutoriales de programación </a>
 */
public class EngineGraphics extends Thread {

    private final Tablero canvas;

    public EngineGraphics(Tablero canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        while (!Pelota.finJuego) {
            canvas.repaint();
            try {
                //Paint Velocity 
                Thread.sleep(6);
            } catch (Exception ex) {
                System.out.println("error in graphics engine: " + ex.getMessage());
            }
        }
    }
}
