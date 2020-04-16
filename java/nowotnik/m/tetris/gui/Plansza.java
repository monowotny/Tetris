/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nowotnik.m.tetris.gui;

import nowotnik.m.tetris.tetromino.Kwadrat;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Klasa rysująca obrys planszy na ekranie.
 * @author Mikołaj Nowotnik
 */
public class Plansza {
    private int szerokosc = Kwadrat.getWymiar()*10;
    private int wysokosc = Kwadrat.getWymiar()*22;
    /**
     * Metoda rysująca plansze.
     * @param g 
     * kontekst graficzny.
     */
    public void rysuj (Graphics g)
    {
        
        
        g.setColor(Color.WHITE);
        g.drawLine(szerokosc+2, 0, szerokosc+2, wysokosc);
    }

}
