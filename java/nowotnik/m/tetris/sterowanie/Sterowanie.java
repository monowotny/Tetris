
package nowotnik.m.tetris.sterowanie;

import nowotnik.m.tetris.tetromino.Kolizja;
import nowotnik.m.tetris.tetromino.AktywnyKlocek;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import nowotnik.m.tetris.Gra;

/**
 * Klasa obsługująca sterowanie w trakcie gry.
 * @author Mikołaj Nowotnik
 */
public class Sterowanie extends KeyAdapter
{
    AktywnyKlocek aK;
    Gra gra;
    Kolizja kolizja;

   
    /**
     * Tworzy obiekt umożliwiający sterowanie w grze.
     * @param gra 
     * dana gra.
     */
    public Sterowanie(Gra gra)
    {
        this.gra = gra;
        this.aK = gra.getKlocek();
        this.kolizja = gra.getKolizje();
        
    }

    /**
     * Metoda obsługująca ruch aktywnego klocka, po wciśnięciu
     * klawisza przez gracza.
     * @param e 
     * wciśnięcie klawisza.
     */
    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(!gra.czyPauza())        
        {
        switch (e.getKeyCode())
        {
            case 39://Strzałka w prawo

                if(kolizja.czyKolizjaBocznaPrawo())
                    break;
                aK.ruchPrawo(); break;
                
            case 37://Strzałka w lewo
                if(kolizja.czyKolizjaBocznaLewo())
                    break;
                aK.ruchLewo(); break;
            case 40://Strzałka w dół
                if(kolizja.czyKolizja())
                    break;
                aK.spadanie();
                break;
            } 
        }
    }
    /**
     * Metoda obsługująca ruch aktywnego klocka, po zwolnieniu
     * klawisza przez gracza.
     * @param e 
     * zwolnienie klawisza.
     */
    @Override
    public void keyReleased(KeyEvent e) 
    {
        
        if(gra.czyPauza())
        {
            if(e.getKeyCode() == 32)//Spacja
            {
                gra.pauzuj();
                
            }
                
        }
        else
        {
            boolean prawo = false;
            int [][] tabPozycjaXY = new int[][] {{0,0,0,0},{0,0,0,}};
        switch (e.getKeyCode())
        {
            
            case 38:
              
                prawo = true;
                //Zwraca tablicę ze współrzędnymi 4 kwadratów klocka
                //po obrocie
                tabPozycjaXY = aK.kolizjaObrot(prawo);
                //Kolizja jest sprawdzana 2 razy na wypadek przesunięcia
                //klocka po pierwszym sprawdzeniu
                tabPozycjaXY = kolizja.czyKolizjaObrot(tabPozycjaXY);
                tabPozycjaXY = kolizja.czyKolizjaObrot(tabPozycjaXY);
                aK.obrot(tabPozycjaXY,prawo);
                break;
        
            
            case 32:
                gra.pauzuj();
                break;
            case 17:
              
                prawo = false;
                tabPozycjaXY = aK.kolizjaObrot(prawo);
                tabPozycjaXY = kolizja.czyKolizjaObrot(tabPozycjaXY);
                tabPozycjaXY = kolizja.czyKolizjaObrot(tabPozycjaXY);
                aK.obrot(tabPozycjaXY,prawo);
                
                break;
            } 
        }
        
        
        
    }
    
}
    
    

