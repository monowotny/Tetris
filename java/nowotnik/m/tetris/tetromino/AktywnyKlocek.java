
package nowotnik.m.tetris.tetromino;
/**
 * Klasa definiująca {@see Klocek} sterowany przez gracza
 * @author Mikołaj Nowotnik
 */
public class AktywnyKlocek extends Klocek
{   
    /**
     * Nowy obiekt klasy Obrot odpowiadający za obrót klocka
     */
    Obrot obrot = new Obrot(this);
    /**
     * Obiekt klasy Kolizja odpowiadający za kolizje klocka z otoczeniem
     */
    Kolizja kolizja;
    /**
     * Tworzy aktywny klocek z klocka z kolejki klocków
     * @param kolejka 
     * kolejka klocków zawierająca 3 klocki
     */
    public AktywnyKlocek(KolejkaKlockow kolejka)
    {
        przekazanie(kolejka);
        
    }
    /**
     * Metoda przekazująca klocek z kolejki klocków do aktywnego klocka
     * @param kolejka 
     * kolejka klocków zawierająca 3 klocki
     */
    public final void przekazanie(KolejkaKlockow kolejka)
    {
        Klocek kl = kolejka.zwrocKolejka().get(0);
        int wymiar = Kwadrat.getWymiar();
        //Przemiszcza klocek do punku (0,0) planszy
        kl.resetujXY();
        //Odsuwa klocek od lewej ściany ekranu
        kl.zwiekszXY(3*wymiar, 0);
        klocek = kl.getKlocek();
        id = kl.getIdKlocka();
        pozycja = kl.getPozycja();
        kolejka.usunZKolojeki(this);
        
    }
    /**
     * Metoda odpowiedzialna za opadanie klocka
     * @see Kwadrat#spadanie() 
     */
    public void spadanie ()
    {
        for( Kwadrat tempKwadrat : klocek)
        {
            tempKwadrat.spadanie();
        }
    }
    /**
     * Metoda odpowiedzialna za ruch klocka w prawo
     * @see Kwadrat#ruchPrawo()  
     */
    public void ruchPrawo ()
    {
        for( Kwadrat tempKwadrat : klocek)
        {
            tempKwadrat.ruchPrawo();
        }
    }
    /**
     * Metoda odpowiedzialna za ruch klocka w lewo
     * @see Kwadrat#ruchLewo() 
     */
    public void ruchLewo ()
    {
        for( Kwadrat tempKwadrat : klocek)
        {
            tempKwadrat.ruchLewo();
        }
    }
    /**
     * Metoda zwracająca tablice współrzędnych klocka 
     * po obrocie do sprawdzenia kolizji
     * @param prawo
     * zmienna określająca kierunek obrotu
     * <code>false</code> oznacza obrót w lewo,
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych klocka po obrocie
     * @see Obrot
     */
    public int [][] kolizjaObrot(boolean prawo)
    {
        return obrot.sprawdzanieIdKlocka(id,prawo);
    }

    
    /**
     * Metoda obracająca klocek w odpowiednim kierunku
     * @param tabXY
     * tablica współrzędnych klocka po obrocie,
     * <code>null</code> oznacza, że  nastąpiła kolizja i 
     * klocek nie zostanie obrócony
     * @param prawo 
     * odpowiada za kierunek obrotu {@see AktywnyKlocek#kolizjaObrot(boolean)}
     * @see Kolizja
     */
    public void obrot(int [][] tabXY, boolean prawo)
    {
        if(tabXY != null)
        {
            int i = 0;
            if(prawo)
                pozycja = pozycja.zmianaPozycji(pozycja);
            else
                pozycja = pozycja.zmianaPozycjiL(pozycja);
            
            for(Kwadrat tempKwadrat : klocek)
            {
                tempKwadrat.setX(tabXY[0][i]);
                tempKwadrat.setY(tabXY[1][i]);
                i++;
            }
        }
    }
    
}
