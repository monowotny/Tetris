
package nowotnik.m.tetris.sterowanie;

import nowotnik.m.tetris.tetromino.Kolizja;
import nowotnik.m.tetris.tetromino.AktywnyKlocek;
import nowotnik.m.tetris.Gra;

/**
 * Klasa odpowaidająca za opadanie aktywnego klocka 
 * i sprawdzanie kolizji w pionie.
 * @author Mikołaj Nowotnik
 */
public class Ruch implements Runnable{
    
    AktywnyKlocek aK;
    private boolean pauza = true;
    Kolizja kolizja;
    Gra gra;
    Thread watek;
    /**
     * Czas, w którym aktywny klocek 
     * przebywa jedną długość swojego boku w dół;
     */
    private static int czas = 500;
    /**
     * Czas na ruch po wykryciu kolizji w pionie.
     */
    private static final int poslizg = 500;
    boolean wystapilaKolizja = false;
    /**
     * Tworzy nowy obiekt obłsugujący dane kolizje i daną grę.
     * @param aK
     * aktywny klocek
     * @param kolizja
     * dane kolizje.
     * @param gra 
     * dana gra.
     */
    public Ruch (AktywnyKlocek aK, Kolizja kolizja, Gra gra)
    {
        this.aK = aK;
        this.kolizja = kolizja;
        this.gra = gra;
    }
    /**
     * Metoda uruchamiająca wątek sterujący opadaniem klocka.
     */
    public void inicjalizacja()
    {
        watek = new Thread(this);
        watek.start();
    }
     
    /**
     * Metoda z pętlą sterującą opadaniem klocka 
     * i sprawdzaniem kolizji pionowych.
     */
    @Override
    public synchronized void run() 
    {
            
                try
                {
                    
                    Thread.sleep(500);
                    resetCzas();
                }
                catch (Exception e)
                {
                    System.err.println(e);
                }
                
          
        while(gra.czyPracuje())
        {
          if(gra.czyPauza())
            {
                try
                {
                    wait();
                    Thread.sleep(500);
                }
                catch (Exception e)
                {
                    System.err.println(e);
                    break;
                }
            }
            
            if(kolizja.czyKolizja())
            {
                try
                {  
                    //Dodatkowy czas tuż przed kolizją pionową
                    Thread.sleep(poslizg);
                    if(kolizja.czyKolizja())
                    {
                        wystapilaKolizja = true;
                    }
                }
                catch(InterruptedException ie)
                {
                    System.err.println(ie);
                    break;
                }                
            }
            else{
                aK.spadanie();
            }
          
            
            try
            {
                Thread.sleep(czas);
            }
            catch(InterruptedException ie)
            {
                System.err.println(ie);
                break;
            }
            
            
            
        
        
            }
        
        
    }
    /**
     * Metoda ustawiająca czas opadanie klocka.
     */
    public static void setCzas()
    {
        if(czas > 125)
        {
            czas = 500 - (Gra.getPoziom()*25);
        }
        
    }
    /**
     * Metoda resetująca czas opadanie klocka.
     */
    private static void resetCzas()
    {
        czas = 500;
    }
    /**
     * Metoda zwracająca wystąpienie kolizji.
     * @return 
     * <code>false</code> jeżeli nie wystąpiła,
     * <code>true</code> jeżeli wystąpiła.
     */
    public boolean czyKolizja()
    {
        return wystapilaKolizja;
    }
    /**
     * Metoda resetująca wystąpienie kolizji.
     */
    public void brakKolizji()
    {
        wystapilaKolizja = false;
    }

    /**
     * Metoda budząca wątek tego obiektu.
     */
    public synchronized void budzenie ()
    {
        notifyAll();
    }
    /**
     * Metoda przerywająca wątek tego obiektu.
     */
    public void koniec()
    {
        watek.interrupt();
    }

}
