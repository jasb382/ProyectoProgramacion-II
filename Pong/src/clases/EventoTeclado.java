/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.*;

/**
 *
 * @author Henry Moreno / YouTube: El Rincón del programador
 * @see <a href = "http://www.youtube.com/c/ElRinconDelProgramador" /> El rincón del programador / tutoriales de programación </a>
 */
public class EventoTeclado extends KeyAdapter {

    static boolean w, s, up, down;

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = true;

        }
        if (id == KeyEvent.VK_S) {
            s = true;

        }
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int id = e.getKeyCode();
        if (id == KeyEvent.VK_W) {
            w = false;
        }
        if (id == KeyEvent.VK_S) {
            s = false;
        }
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }
    }

}
