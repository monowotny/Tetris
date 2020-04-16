
package nowotnik.m.tetris.tetromino;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Klasa tworząca kolejkę 3 klocków
 * @author Mikołaj Nowotnik
 */

public class KolejkaKlockow 
{   
    /**
     * lista 3 klocków
     * @see Klocek
     */
    private ArrayList<Klocek> kolejkaKlockow = new ArrayList<>(3);
    /**
     * @see Kwadrat#wymiar
     */
    private final int wymiar = Kwadrat.getWymiar();
    
    
    /**
     * Tworzy kolejkę 3 klocków na początku gry
     * @see GenerowanieKlockow#GenerowanieKlockow(KolejkaKlockow) 
     */
    public KolejkaKlockow()
    {
        new GenerowanieKlockow(this);
        new GenerowanieKlockow(this);
        new GenerowanieKlockow(this);
        
    } 
    /**
     * Metoda dodający klocek do kolejki 
     * @param kl 
     * obiekt klasy {@see Klocek}
     */
    public void dodajDoKolejki(Klocek kl)
    {

        int y = wymiar*7;
        int x = wymiar*16;
        kolejkaKlockow.add(kl);
        kl.zwiekszXY(x, y+wymiar*3*kolejkaKlockow.size());
        
    }
    /**
     * metoda rysująca kolejkę klocków
     * @param g 
     * konteks graficzny
     */
    public void rysuj (Graphics g)
    {
        for( Klocek tempKlocek : kolejkaKlockow)
        {
                tempKlocek.rysuj(g);
          
        }
     
        
    }
    /**
     * Metoda usuwająca z kolejki pierwszy klocek
     * przsuwająca pozostałe klocki o jeden
     * i genrująca nowy klocek
     * @param aK 
     * obiekt klasy AktywnyKlocek
     * {@see GenerowanieKlockow#GenerowanieKlockow(AktywnyKlocek, KolejkaKlockow)}
     */
    public void usunZKolojeki(AktywnyKlocek aK)
    {
        kolejkaKlockow.remove(0);
        System.gc();
        for( Klocek tempKlocek : kolejkaKlockow)
        {
            tempKlocek.zwiekszXY(0, -(wymiar*3));
        }
        new GenerowanieKlockow(aK,this);
    }
    /**
     * Metoda zwracająca kolejkę klocków
     * @return 
     * kolejka klocków
     */
    public ArrayList<Klocek> zwrocKolejka()
    {
        return kolejkaKlockow;
    }
    
}
