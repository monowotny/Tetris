
package nowotnik.m.tetris.tetromino;

import java.awt.Color;
import java.util.Random;
/**
 * Klasa odpowiedzialna za generowanie klocków
 * @see Klocek
 * @see KolejkaKlockow
 * @author Mikołaj Nowotnik
 */
public class GenerowanieKlockow 
{
    
    
    private int wymiar = Kwadrat.getWymiar();
    /**
     * Tworzy klocek T do prezentacji w opcjach
     * @param kl 
     * pusta lista kwadratów
     */
    public GenerowanieKlockow(Klocek kl)
    {
        prezentacja(kl);
    }
    /**
     * Generuje klocek inny od klocków w kolejce i od aktywnego klocka,
     * następnie dodaje go do kolejki
     * @param aK
     * klocek sterowany przez gracza
     * @param kolejka 
     * kolejka 2 klocków
     */
    public GenerowanieKlockow (AktywnyKlocek aK,KolejkaKlockow kolejka)
    {
        
        
        int l = 0;
        boolean czyPowtorka;
        do{
            l = losowanie();
            czyPowtorka = false;
            for(Klocek tempKlocek : kolejka.zwrocKolejka())
            {
                if( tempKlocek.getIdKlocka().ordinal() == l)
                {
                    czyPowtorka = true;
                    break;

                }
            }
                            
            if( aK.getIdKlocka().ordinal() == l)
                czyPowtorka = true;
                        
            } while(czyPowtorka);
        
        
        
        ustawienie(l,kolejka);
    }
    /**
     * Tworzy jeden klocek i dodaje go do kolejki.
     * Jest uruchamiany 3 razy na początku gry, żeby wypenić kolejkę.
     * Każdy klocek w kolejce jest inny.
     * @param kolejka 
     * kolejka złożona z 0, 1 albo 2 klocków
     */
    public GenerowanieKlockow (KolejkaKlockow kolejka)
    {
        int l = 0;
        
        l = losowanie();
        boolean czyPowtorka = false;
        
            
            do{
                for(Klocek tempGrupa : kolejka.zwrocKolejka()){
                if(tempGrupa.getIdKlocka().ordinal() == l)
                {
                    l = losowanie();
                    czyPowtorka =  true;
                }
                else
                {
                    czyPowtorka = false;
                }  
                }
 
            } while(czyPowtorka);
        
        
        
        
        ustawienie(l, kolejka);
    }
    /**
     * Metoda losująca liczbę od 0 do 6.
     * @return 
     * wylosowaną liczbę
     */
    private int losowanie()
    {
        Random rand = new Random();
                
        return rand.nextInt(7);
    }
    /**
     * Metoda określająca wzajemne położenie kwadratów w klocku
     * @param e1
     * współrzędna pierwszego kwadratu
     * @param e2
     * współrzędna drugiego kwadratu
     * @param e3
     * współrzędna trzeciego kwadratu
     * @param e4
     * współrzędna czwartego kwadratu
     * @return 
     * tablicę współrzędnych 4 kwadratów jednego klocka
     * @see Kwadrat
     */
    private int [] pozycja(int e1,int e2,int e3,int e4)
    {
        int [] tabPozycja  = new int[]{e1,e2,e3,e4};

        return tabPozycja;
        
    }/**
     * Metoda wypełaniająca pusty klocek T 4 kwadratami
     * @param kl 
     * pusty klocek
     */
    private void prezentacja(Klocek kl)
    {
        int [] tabPozycjaX = new int[] {0, wymiar, wymiar, wymiar*2};
        int [] tabPozycjaY = new int[] {0, 0, wymiar,0};
        
        Color kolor =  new Color(128, 0, 128);
       
        
        for (int i=0; i<4;i++)
        {
            
            kl.dodajKwadrat(new Kwadrat(tabPozycjaX[i], tabPozycjaY[i],kolor));
        }
        
       
    }/**
     * Metoda tworząca nowy klocek i dodająca go do kolejki
     * @param l
     * liczba wylosowana przez metodę {@see losowanie}
     * @param kolejka 
     * kolejka, do której zostanie dodany klocek
     */
    private void ustawienie(int l, KolejkaKlockow kolejka)
    {
        int [] tabPozycjaX = new int[] {0,0,0,0};
        int [] tabPozycjaY = new int[] {0,0,0,0};
        Color kolor =  new Color(128, 0, 128);
        switch(l)
        {
            case 0:
                tabPozycjaX = pozycja(0, wymiar, 0, wymiar);
                tabPozycjaY = pozycja(0, 0,wymiar, wymiar);
                //O
                kolor = Color.YELLOW;
            break;
            case 1:
                tabPozycjaX = pozycja(0, wymiar,wymiar*2, wymiar*3);
                tabPozycjaY = pozycja(0, 0, 0, 0);
                //I
                kolor = Color.CYAN;
            break;
            case 2:
                tabPozycjaX = pozycja(0, 0, wymiar, wymiar*2);
                tabPozycjaY = pozycja(0, wymiar, wymiar,wymiar);
                //J
                kolor = Color.BLUE;
            break;
            case 3:
                tabPozycjaX = pozycja(0, wymiar, wymiar*2, wymiar*2);
                tabPozycjaY = pozycja(wymiar, wymiar, wymiar,0);
                //L
                kolor = Color.ORANGE;
            break;
            case 4:
                tabPozycjaX = pozycja(0, wymiar, wymiar, wymiar*2);
                tabPozycjaY = pozycja(0, 0, wymiar,0);
                //T
                
                
            break;
            case 5:
                tabPozycjaX = pozycja(0, wymiar, wymiar, wymiar*2);
                tabPozycjaY = pozycja(0, 0, wymiar, wymiar);
                //Z
                kolor = Color.RED;
            break;
            case 6:
                tabPozycjaX = pozycja(0, wymiar, wymiar, wymiar*2);
                tabPozycjaY = pozycja(wymiar, wymiar, 0, 0);
                //S
                kolor = Color.GREEN;
            break;
            
            
        }
        Klocek kl = new Klocek() {};
        
        kl.setPozycja(Pozycja.Pozycja1);
        kl.setIdKlocka(IdKlocka.values()[l]);

        
        
        for (int i=0; i<4;i++)
        {
            
            kl.dodajKwadrat(new Kwadrat(tabPozycjaX[i], tabPozycjaY[i],kolor));
        }
        kolejka.dodajDoKolejki(kl);
    }
}
