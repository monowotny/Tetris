
package nowotnik.m.tetris.gui;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Label;

/**
 * Klasa wyświetlająca pauze na ekranie.
 * @author Mikołaj Nowotnik
 */
public class Pauza extends Label
{
    EkranGry ekran;
    /**
     * Tworzy obiekt klasy Label, z tesktem "PAUZA" na ekranie.
     * @param ekran 
     * ekran gry.
     */
    public Pauza(EkranGry ekran)
    {
        super("PAUZA",Label.CENTER);
        this.ekran = ekran;
        ustaw();
    }
    /**
     * Ustawia parametry obiektu.
     */
    private void ustaw()
    {
          Font f = new Font("Lucida Sans", PLAIN, 50);
          setVisible(false);
            setFont(f);
            int w = 200;
            int h = 100;
            setForeground(Color.white);
            setBounds((int)(ekran.getWidth()/2-w/2),
                    (int)(ekran.getHeight()/3)-h/2, w, h);
    }
    
    
}
