
package nowotnik.m.tetris.tetromino;
import java.awt.Color;
import java.awt.Graphics;



/** 
 * Klasa definiująca pojedynczy kwadrat składający się na jeden {@see Klocek}
 * @author Mikołaj Nowotnik
 */
public class Kwadrat 
{
    /**
     * rozmiar boku kwadratu w pikselach
     */
    
    private static int wymiar;
    /**
     * współrzędna x kwadaratu 
     */
    private int x;
    /**
     * współrzędna y kwadaratu 
     */
    private int y;
    /**
     * współrzędna x kwadratu względem innego, w jednym klocku
     */
    private final int pozX;
    /**
     * współrzędna y kwadratu względem innego, w jednym klocku
     */
    private final int pozY;
    /**
     * zmienna określająca kolor kwadratu
     */
    private Color kolor;
    /**
     * Tworzy nowy kwadrat o podanych współrzędnych i kolorze
     * @param x współrzędna x kwadratu 
     * @param y współrzędna y kwadratu
     * @param kolor kolor kwadratu
     */
    public Kwadrat (int x, int y, Color kolor)
    {
        this.x = x;
        this.y = y;
        this.pozX = x;
        this.pozY = y;
        
        this.kolor = kolor;
        
        
        
    }
    /**
     * metoda zmieniająca współrzędną x
     * @param x
     * nowa współrzędna x kwadratu
     */
    public void setX (int x)
    {
        this.x = x;
    }
    /**
     * metoda zmieniająca współrzędną y
     * @param y 
     * nowa współrzędna y kwadratu
     */
    public void setY (int y)
    {
        this.y = y;
    }
    
    /**
     * metoda zwracająca długość boku kwadratu w pikselach
     * @return długość boku
     */
    public static int getWymiar()
    {
        return wymiar;
    }
    
    /**
     * metoda zmieniająca długość boku kwadratu
     * @param nowyWymiar 
     * nowa długość boku kwadratu wyrażona w pikselach
     */
    public static void setWymiar(int nowyWymiar)
    {
        wymiar = nowyWymiar;
    }
    /**
     * metoda zwracająca współrzędną x tego kwadratu względem innego, w jednym klocku
     * @return współrzędna x
     * @see Klocek
     */
    public int getPozX()
    {
        return pozX;
    }
     /**
     * metoda zwracająca współrzędną y tego kwadratu względem innego, w jednym klocku
     * @return współrzędna y
     * @see Klocek
     */
    public int getPozY()
    {
        return pozY;
    }
    /**
     * metoda zerująca współrzędne kwadratu
     */
    public void resetujXY()
    {
        x = pozX;
        y = pozY;
    }
    
    
    /**
     * metoda zwracająca aktualną wspórzędną x
     * @return współrzędna x
     */
    
    public int getX()
    {
        return x;
    }
     /**
     * metoda zwracająca aktualną wspórzędną y kwadratu
     * @return współrzędna y
     */
    public int getY()
    {
        return y;
    }
    /**
     * metoda rysująca kwadrat
     * @param g 
     * określony kontekst graficzny
     */
    public void rysuj(Graphics g)
    {
       
        
        g.setColor(kolor);
       
        g.fillRect(x, y, wymiar, wymiar);


        g.setColor(new Color(0, 0, 0, 60)); //cień prawo dół
        g.drawLine(x+wymiar-1, y+1, x+wymiar-1, y+wymiar-1);
        g.drawLine(x+1, y+wymiar-1, x+wymiar-1, y+wymiar-1);
        
        g.setColor(new Color(0, 0, 0, 200));//cień obwódka
        g.drawRect(x, y, wymiar, wymiar); 
        
        g.setColor(new Color(255, 255, 255, 100));//oświetlenie lewo góra
        g.drawLine(x+1, y+1, x+1, y+wymiar-1);
        g.drawLine(x+1, y+1, x+wymiar-1, y+1);
        
        
        
        
       
        
    }
    /**
     * metoda imitująca spadanie kwadratu
     */
    public void spadanie ()
    {
        y+=wymiar;
    }
    /**
     * metoda poruszająca kwadratem w prawo
     */
    public void ruchPrawo()
    {
        x+=wymiar;
    }
    /**
     * metoda poruszająca kwadratem w lewo
     */
    public void ruchLewo()
    {
        x-=wymiar;
    }

   
}
