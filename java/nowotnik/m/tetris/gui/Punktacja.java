
package nowotnik.m.tetris.gui;

import nowotnik.m.tetris.tetromino.Kwadrat;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;
import nowotnik.m.tetris.Gra;

/**
 * Klasa zliczająca punkty i rysująca je na ekranie.
 * @author Mikołaj Nowotnik
 */
public class Punktacja {
    private int wymiar = Kwadrat.getWymiar();
    Font f = new Font("Lucida Sans", PLAIN, wymiar);
    private int punkty = 0;
    private static String wynik = "";
    private int linie = 0;
    private int combo = 0;
    private int szerokosc = wymiar*10;
    /**
     * Metoda rysująca punktację na ekranie.
     * @param g 
     * kontekst graficzny.
     */
    public void rysuj(Graphics g)
    {
        String punktyNapis = Integer.toString(punkty);
        String liczbaZer = new String();
        //Dodaje zera przed wynikiem
        for(int i = 0; i<20-punktyNapis.length(); i++)
        {
            liczbaZer += "0";
        }
        wynik = punktyNapis;
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString("Punkty", szerokosc+5, 3*wymiar);

        g.drawString(liczbaZer+punktyNapis, szerokosc+5, 5*wymiar);
        
        g.drawString("Linie", szerokosc+5, 7*wymiar);
        g.drawString(Integer.toString(linie), szerokosc+5, 9*wymiar);
    }
    /**
     * Metoda zwiększająca zarejestrowaną 
     * liczbę linii usuniętych za jednym razem.
     */
    public void zwiekszCombo()
    {
        linie ++;
        combo ++;
    }
    /**
     * Metoda licząca punkty.
     */
    public void podliczPunkty()
    {
        int mnoznik = 0;
        switch(combo)
        {
            case 1:
                mnoznik = 40;
                break;
            case 2:
                mnoznik = 100;
                break;
            case 3:
                mnoznik = 300;
                break;
            case 4:
                mnoznik = 1200;
                break;
            
        }
    punkty += mnoznik*(Gra.getPoziom()+1);
        combo = 0;
        
        
        
        
    }
    /**
     * Metoda zwracająca liczbę linii.
     * @return 
     * liczbę linii.
     */
    public int getLinia()
    {
        return linie;
    }
    /**
     * Metoda zwracająca wynik.
     * @return 
     * wynik w postaci napisu.
     */
    public static String getWynik()
    {
        return wynik;
    }
}
