
package nowotnik.m.tetris.tetromino;

import nowotnik.m.tetris.gui.Plansza;






/**
 * Klasa sprawdzająca kolizje aktywnego klocka z otoczeniem.
 * @see AktywnyKlocek
 * @author Mikołaj Nowotnik
 */
public class Kolizja 
{
   
    
    NieaktwyneKlocki nK;
    AktywnyKlocek aK;
    /**
     * Tworzy nowy obiekt sprawdzający kolizje
     * @param aK
     * aktywny klocek, którego kolizje będą sprawdzane
     * @param nK 
     * nieaktywne klocki, z którymi aktywny klocek może kolidować
     */
    public Kolizja(AktywnyKlocek aK, NieaktwyneKlocki nK)
    {
        this.aK = aK;
        this.nK = nK;
    }
    
    /**
     * Metoda sprawdzająca kolizję aktywnego klocka, w osi pionowej, 
     * z nieaktywnymi klockami oraz podłożem.
     * @return 
     * <code>true</code> gdy nastąpiła kolizja, 
     * <code>false</code> gdy brak kolizji.
     */
    public boolean czyKolizja()
    {
        int nrKolumny = 0;
        int nrWiersza = 0;
        if(aK.getKlocek()!= null){
        for(Kwadrat tempKlocek : aK.getKlocek())
        {
            
            
            
            nrWiersza = Math.round(tempKlocek.getY()/Kwadrat.getWymiar());
            //Kolizja z podłożem
            if(nrWiersza >= 21)
            {
                return true;
            }
            else
            {
                nrWiersza += 1;
            }
            //Kolizja z innymi klockami
            nrKolumny = Math.round(tempKlocek.getX()/Kwadrat.getWymiar());
            
            
            if(nK.getNieaktywneKlocki(nrWiersza,nrKolumny))
            {
                return true;
            }
        }
        }
        return false;
       
    }
    /**
     * Metoda sprawdzająca kolizję prawej strony aktywnego klocka 
     * z nieaktywnymi klockami oraz prawą ścianą planszy.
     * @return 
     * <code>true</code> gdy nastąpiła kolizja, 
     * <code>false</code> gdy brak kolizji.
     * @see Plansza
     */
    public boolean czyKolizjaBocznaPrawo()
    {
        int nrKolumny = 0;
        int nrWiersza = 0;
        
        for(Kwadrat tempKlocek : aK.getKlocek())
        {
            
            
            nrKolumny = Math.round(tempKlocek.getX()/Kwadrat.getWymiar());
            
            //Kolizja prawo ściana
            if(nrKolumny>=9)
            {
                return true;
            }
            //Kolizja z innymi klockami prawo
            nrWiersza = Math.round(tempKlocek.getY()/Kwadrat.getWymiar());
            
            
            if(nK.getNieaktywneKlocki(nrWiersza,nrKolumny+1))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Metoda sprawdzająca kolizję lewej strony aktywnego klocka
     * z nieaktywnymi klockami oraz lewą ścianą planszy.
     * @return 
     * <code>true</code> gdy nastąpiła kolizja, 
     * <code>false</code> gdy brak kolizji.
     * @see Plansza
     */
    public boolean czyKolizjaBocznaLewo()
    {
        int nrKolumny = 0;
        int nrWiersza = 0;
        for(Kwadrat tempKlocek : aK.getKlocek())
        {
            
            
            nrKolumny = Math.round(tempKlocek.getX()/Kwadrat.getWymiar());
            //Kolizja lewo ściana
            if(nrKolumny <=0)
            {
                return true;
            }
            nrWiersza = Math.round(tempKlocek.getY()/Kwadrat.getWymiar());
            
            //Kolizja lewo klocek
            if(nK.getNieaktywneKlocki(nrWiersza,nrKolumny-1))
            {
                return true;
            }
            
        }
        return false;
    }
    /**
     * Metoda sprawdzająca kolizję aktywnego klocka przy obrocie.
     * @param tabXY
     * tablica współrzędnych 4 kwadratów aktywnego klocka po obrocie. 
     * @return 
     * zmodyfikowaną tablicę współrzędnych 4 kwadratów aktywnego 
     * klocka po obrocie. Klocek zostaje odsunięty od ściany lub podłoża, jeżeli 
     * w skutek obrotu miałby wyjść poza obszar planszy.
     * Tablicę współrzędnych 4 kwadratów aktywnego 
     * klocka po obrocie, jeżeli nie doszło do żadnej kolizji.
     * <code>null</code>, jeżeli doszło do kolizji z nieaktywnym klockiem,
     * wtedy obrót zostaje anulowany.
     * @see Obrot
     */
    public int[][] czyKolizjaObrot(int tabXY[][])
    {   
        int nrKolumny = 0;
        int nrWiersza = 0;
        if(tabXY != null)
        {
            
        
            for(int i = 0; i< 4; i++)
            {
                
                nrKolumny = Math.round(tabXY[0][i]/Kwadrat.getWymiar());
            
                //Sprawdza czy klocek wychodzi poza prawą ścianę planszy
                if(nrKolumny>9)
                {
               
                    //Przesuwa klocek w przeciwną stronę o 1
                    //tzw. wall kick
                    for(int j = 0; j<4; j ++)
                    {
                        tabXY[0][j] -= Kwadrat.getWymiar();
                    }
                    break;
                
                }
                else if(nrKolumny<0)
                {   
                    //jw. tylko w drugą stronę
                    for(int j = 0; j<4; j ++)
                    {
                        tabXY[0][j] += Kwadrat.getWymiar();
                    }
                    break;
                }
            
            
                nrWiersza = Math.round(tabXY[1][i]/Kwadrat.getWymiar());
                //Umożliwia obrót na samej górze i dole planszy
                //odsuwa o 1
                if(nrWiersza > 21)
                {
                    for(int j = 0; j<4; j ++)
                    {
                        tabXY[1][j] -= Kwadrat.getWymiar();
                    }
                    break;
                }
                else if(nrWiersza<0)
                {
                    for(int j = 0; j<4; j ++)
                    {
                        tabXY[1][j] += Kwadrat.getWymiar();
                    }
                    break;
                }
            
            
                //Sprawdza czy w wybranej komórce tablicy istnieje już klocek
                if(nK.getNieaktywneKlocki(nrWiersza,nrKolumny))
                {
                    return null;
                }
            
            }
        }
        return tabXY;
      
    }
 
    

}

