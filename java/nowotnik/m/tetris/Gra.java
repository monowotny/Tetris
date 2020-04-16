package nowotnik.m.tetris;


import nowotnik.m.tetris.gui.Plansza;
import nowotnik.m.tetris.gui.KoniecGry;
import nowotnik.m.tetris.gui.Punktacja;
import nowotnik.m.tetris.gui.EkranGry;
import nowotnik.m.tetris.tetromino.Kolizja;
import nowotnik.m.tetris.tetromino.NieaktwyneKlocki;
import nowotnik.m.tetris.sterowanie.Ruch;
import nowotnik.m.tetris.tetromino.Kwadrat;
import nowotnik.m.tetris.tetromino.KolejkaKlockow;
import nowotnik.m.tetris.tetromino.AktywnyKlocek;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * Klasa zawierająca główną pętle rozgrywki.
 * @author Mikołaj Nowotnik
 */

public class Gra extends Canvas implements Runnable
{
    private static boolean preview = true;
    private boolean zatrzymuje = false;
    private boolean pracuje = false;
    private boolean pauza = false;
    private KolejkaKlockow kolejka = new KolejkaKlockow();
    private EkranGry ekran;
    private Thread watek;
    private Ruch ruch;
    private static int poziom = 0;
    private Plansza plansza = new Plansza();
    private Punktacja punktacja = new Punktacja();
    private AktywnyKlocek aK = new AktywnyKlocek(kolejka);
    private NieaktwyneKlocki nK = new NieaktwyneKlocki();
    private Kolizja kolizja = new Kolizja(aK, nK);
    /**
     * Tablica współrzędnych y kwadratów aktywnego klocka, 
     * który właśnie się zablokował.
     */
    private int [] tabY = new int[]{-1,-1,-1,-1};
    /**
     * Tworzy nową grę na określonym ekranie.
     * @param ekran 
     * ekran gry.
     */
    public Gra (EkranGry ekran)
    {
        this.ekran = ekran;
    }
    /**
     * Metoda zwracająca czy gra wciąż trwa.
     * @return 
     * <code>true</code> jeżeli gra trwa,
     * <code>false</code> jeżeli gra zotała przerwana.
     */
    public boolean czyPracuje()
    {
        return pracuje;
    }
    /** 
     * Metoda uruchamiająca główny wątek gry
     * i wątek odpowiedzialny za opadanie klocka.
     * @see Ruch
     */
    public synchronized void inicjalizacja()
    {
            
            
            
        pracuje=true;
        watek = new Thread(this);
        
        watek.start();
        ruch = new Ruch(aK,kolizja,this);
       
       

            ruch.inicjalizacja();
        
        
        
    }

   
    /**
     * Metoda z główną pętla rozgrywki.
     */
    @Override
    public synchronized void run() 
    {
  
              
        
        while(pracuje)
        {   
            
            if(pauza)
            {
               
                try
                {       
                        
                        wait();
                        
                }
                catch (Exception e)
                {
                    System.err.println(e);
                    break;
                }
                
            }
            else
            {
                try
                {       
                    //Ogranicza ilość wyświetlanych klatek
                    Thread.sleep(10);
                        
                }
                catch (Exception e)
                {
                    System.err.println(e);
                    break;
                }
            rysuj();
            
            if (ruch.czyKolizja())
            {

                
                int i = 0;
                for(Kwadrat tempKwadrat : aK.getKlocek())
                {
                    //Sprawdza czy klocek dotyka górnej cześci planszy
                   if(tempKwadrat.getY()==0)
                   {
                       pracuje = false;
                       
                   }
                   //Zapisuje y wszystkich kwadratów
                   tabY[i] = tempKwadrat.getY();
                   i++;
                   nK.przekazKwadrat(tempKwadrat);
                }
                
                
                
                
                aK.getKlocek().clear();
                
                sprTabY();
                for(int j = 0; j<4; j++)
                {
                    if(nK.opuszczanieLinii(tabY[j]))
                    {
                        punktacja.zwiekszCombo();
                        if(punktacja.getLinia()%10 == 0)
                        {
                            Gra.zwiekszPoziom();
                            Ruch.setCzas();
                        }
                        
                    }
                        
                            
                }
                punktacja.podliczPunkty();
                
                if(pracuje)
                {
                    aK.przekazanie(kolejka);
                    
                }
                ruch.brakKolizji();
                
            }
            
            }
        }
        if(!zatrzymuje){
            ekran.removeKeyListener(ekran.getKeyListeners()[0]);
            ekran.removeKeyListener(ekran.getKeyListeners()[0]);
            KoniecGry koniecGry = new KoniecGry(ekran);
            koniecGry.setBounds(getX()+Kwadrat.getWymiar()*10+3, getY(), getWidth()-Kwadrat.getWymiar()*10+2, getHeight());
            ekran.add(koniecGry,0);
            koniecGry.addMouseListener(ekran.new WindowFocus());

            ekran.addKeyListener(koniecGry.new RysujKoniec());
            System.gc();
        }
    }
    /**
     * Metoda sprawdzająca ile kwadratów aktywnego klocka
     * ma tą samą współrzędną y. Ma to znaczenie przy sprawdzaniu
     * pełnych wierszy.
     * @see NieaktwyneKlocki#opuszczanieLinii(int) 
     */
    private void sprTabY()
    {
        //Zamienia powtarzające się y na -1 i je sortuje
        //ogranicza to liczbę przeszukianych wierszy do usunięcia
        //po kolizji
        for(int i = 0; i<4; i++)
        {
            if(tabY[i] != -1){
                for(int j = i+1; j<4; j++)
                {
                    if(tabY[i] == tabY[j])
                        tabY[j] = -1;
                }
            }
        }
        sortowanie();
    }
    /**
     * Metoda sortująca tablicę współrzędnych y klocka.
     */
    private void sortowanie()
    {
        int naj = tabY[0];
        int temp = 0;
        for(int i = 0; i<4;i++)
        {
            for(int j = i+1; j<4; j++)
            {
                if(tabY[i] > tabY[j])
                {
                    temp = tabY[i];
                    tabY[i] = tabY[j];
                    tabY[j] = temp;
                }
            }
            
        }
    }

    /**
     * Metoda rysująca całą rozgrywkę.
     */
    private void rysuj ()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        plansza.rysuj(g);
        punktacja.rysuj(g);
        nK.rysuj(g);
        aK.rysuj(g);
        if(preview)
            kolejka.rysuj(g);
        g.dispose();
        bs.show();
    }
    /**
     * Metoda ustawiająca widoczność kolejki klocków/Preview.
     * @param newPreview 
     * <code>fasle</code> wyłącza widocznośc kolejki,
     * <code>true</code> włącza widoczność kolejki.
     */
    public static void setPreview(boolean newPreview)
    {
        preview = newPreview;
    }
    /**
     * Metoda zwracająca stan Preview.
     * @return 
     * <code>fasle</code> Preview wyłączone,
     * <code>true</code> Preview włączone.
     */
    public static boolean getPreview()
    {
        return preview;
    }
    /**
     * Metoda zwracająca aktywny klocek.
     * @return 
     * aktywny klocek
     */
    public AktywnyKlocek getKlocek()
    {
        return aK;
    }
    /**
     * Metoda zwracająca kolizje.
     * @return 
     * obiekt obsługujący kolizje.
     */
    public Kolizja getKolizje()
    {
        return kolizja;
    }
    
    /**
     * Metoda zwracająca aktualny poziom gry.
     * Poziom wpływa na trudonść gry, poprzez skrócenie czasu opadania klocka.
     * @return 
     * aktualny poziom gry.
     * @see Ruch#setCzas() 
     */
    public static int getPoziom()
    {
        return poziom;
    }
    /**
     * Metoda zwiększająca poziom gry. 
     * Poziom jest zwiększany co 10 usuniętych linii.
     */
    public static void zwiekszPoziom()
    {
        poziom++;
    }
    /**
     * Metoda budząca wątek ruchu po pauzie.
     * @see Ruch
     */
    public void uruchom()
    {
        pracuje = true;
        ruch.budzenie();
    }
    /**
     * Metoda kończąca grę. Jest wywoływana po naciśnięciu "ESC".
     */
    public void zatrzymaj()
    {
        zatrzymuje = true;
        pracuje = false;
        pauza = false;
        ruch.koniec();
        watek.interrupt();
        poziom = 0;
        ekran.setSize(800, 600);
        ekran.setLocationRelativeTo(null);
        
            
    }
    /**
     * Metoda wstrzymująca i wybudzająca grę z pauzy.
     */
    public void pauzuj ()
    {
        if(pauza)
        {
            setVisible(true);
            ekran.getComponent(0).setVisible(false);//ekran pauzy
            ruch.budzenie();
            budzenie();
        }
        else
        {
            setVisible(false);
            ekran.getComponent(0).setVisible(true);
            
        }
            
        pauza =! pauza;
    }
    /**
     * Metoda bydząca główny wątek gry.
     * @see #watek
     */
    private synchronized void budzenie ()
    {
        notifyAll();
    }
    /**
     * Metoda zwracająca czy pauza jest włączona.
     * @return 
     * <code>true</code> pauza jest wyłączona,
     * <code>false</code> pauza jest wyłączona.
     */
    public boolean czyPauza()      
    {
        
        return pauza;
    }
    
    
}
