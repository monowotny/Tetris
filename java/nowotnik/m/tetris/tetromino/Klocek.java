
package nowotnik.m.tetris.tetromino;

import java.util.ArrayList;




import java.awt.Graphics;
/**
 * Klasa abstrakcyjna tworząca jeden Klocek z 4 kwadratów
 * @see Kwadrat
 * @author Mikołaj Nowotnik
 */
public abstract class Klocek
        
{
    /**
     * lista 4 kwadratów tworząca jeden klocek
     * @see Kwadrat
     */
    protected ArrayList<Kwadrat> klocek = new ArrayList<>(4);
    /**
     * zmienna określająca rodzaj klocka
     */
    protected IdKlocka id;
    /**
     * zmienna określająca aktualną pozycję klocka
     */
    protected Pozycja pozycja;

    
   
    
    
    
    /**
     * Metoda rysująca klocek
     * @param g 
     * kontekst graficzny
     * @see Kwadrat#rysuj(java.awt.Graphics) 
     */
    public void rysuj (Graphics g)
    {
        for( Kwadrat tempKwadrat : klocek)
        {
            tempKwadrat.rysuj(g);
        }
    }
 
    /**
     * Metoda dodająca kwadrat do listy 4 kwadratów
     * @param kwadrat 
     * obiekt klasy @see Kwadrat
     */
    public void dodajKwadrat(Kwadrat kwadrat)
    {
        klocek.add(kwadrat);
    }
    /**
     * Metoda usuwająca klocek
     */
    public void usunKlocek ()
    {
        for( int i = 0; i<4; i++)
        {
            klocek.remove(klocek.get(0));
        }
    }
    /**
     * Metoda nadająca id klocka
     * @param id 
     * zmienna określająca rodzaj klocka
     */
    public void setIdKlocka(IdKlocka id)
    {
        this.id = id;
    }
    /**
     * Metoda zwracająca id klocka
     * @return 
     * rodzaj klocka
     */
    public IdKlocka getIdKlocka()
    {
        return id;
    }
    /**
     * Metoda zmieniające pozycję klocka
     * @param pozycja 
     * określa pozycję klocka
     */
    public void setPozycja(Pozycja pozycja)
    {
        this.pozycja = pozycja;
    }
    /**
     * Metoda zwracająca aktualną pozycję klocka
     * @return 
     * pozycja klocka
     */
    public Pozycja getPozycja()
    {
        return pozycja;
    }
    /**
     * Metoda powiększająca współrzędne x i y klocka
     * @param x 
     * określa o ile zostanie zwiększona współrzędna x
     * @param y 
     * określa o ile zostanie zwiększona współrzędna y
     */
    public void zwiekszXY(int x,int y)
    {
       for(Kwadrat tempKwadrat : klocek)
       {
           tempKwadrat.setX(tempKwadrat.getX()+x);
           tempKwadrat.setY(tempKwadrat.getY()+y);
       }
    }
    /**
     * Metoda restująca wpółrzędne x i y klocka
     */
    public void resetujXY()
    {
        for(Kwadrat tempKwadrat : klocek)
           tempKwadrat.resetujXY();
    }
    /**
     * Metoda zwracająca klocek
     * @return 
     * klocek
     */
    public ArrayList<Kwadrat> getKlocek()
    {
        return klocek;
    }
 
}

    

