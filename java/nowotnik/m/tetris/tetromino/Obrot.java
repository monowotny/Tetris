
package nowotnik.m.tetris.tetromino;



/**
 * Klasa obsługująca obrót aktywnego klocka.
 * @author Mikołaj Nowotnik
 */
public class Obrot {
    AktywnyKlocek aK;
  
    private int w = Kwadrat.getWymiar();
    
    /**
     * Tworzy nowy obiekt pozwalający na obrót aktywnego klocka.
     * @param aK 
     * aktywny klocek, sterowany przez gracza
     */
    public Obrot(AktywnyKlocek aK)
    {
        this.aK = aK;
      
    }
    /**
     * Metoda obracająca konkretny klocek w prawo albo w lewo.
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych po obrocie w zależności od aktualnej pozycji klocka.
     * @see Pozycja
     */
    private int[][] obrotKlockaJ(boolean prawo)
    {
       
        int [][] tabPozycjaXY = {{0,0,0,0},{0,0,0,0}};
        int nrPoz = aK.getPozycja().ordinal();
        if(!prawo)
        {
            if(nrPoz == 0)
                nrPoz = 3;
            else
                nrPoz--;
        }
        
        switch(nrPoz)
        {
            case 0:
                tabPozycjaXY[0] = pozycjaX(2, 1, 0, -1, prawo);
                tabPozycjaXY[1] = pozycjaY(0, -1, 0, 1, prawo);
                break;
            case 1:
                tabPozycjaXY[0] = pozycjaX(0, 1, 0, -1, prawo);
                tabPozycjaXY[1] = pozycjaY(2, 1, 0, -1, prawo);
                break;
            case 2:
                tabPozycjaXY[0] = pozycjaX(-2, -1, 0, 1, prawo);
                tabPozycjaXY[1] = pozycjaY(0, 1, 0, -1, prawo);
                break;
            case 3:
                tabPozycjaXY[0] = pozycjaX(0, -1, 0, 1, prawo);
                tabPozycjaXY[1] = pozycjaY(-2, -1, 0, 1, prawo);
                break;
        }

        return tabPozycjaXY;
                
    }
    /**
     * Metoda obracająca konkretny klocek w prawo albo w lewo.
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych po obrocie w zależności od aktualnej pozycji klocka.
     * @see Pozycja
     */
    
    private int[][] obrotKlockaL(boolean  prawo)
    {
        
        int [][] tabPozycjaXY = new int[][]{{0,0,0,0},{0,0,0,0}};
        int nrPoz = aK.getPozycja().ordinal();
        if(!prawo)
        {
            if(nrPoz == 0)
                nrPoz = 3;
            else
                nrPoz--;
        }
        switch(nrPoz)
        {
            case 0:
                tabPozycjaXY[0] = pozycjaX(1, 0, -1, 0, prawo);
                tabPozycjaXY[1] = pozycjaY(-1, 0, 1, 2, prawo);
                break;
            case 1:
                tabPozycjaXY[0] = pozycjaX(1, 0, -1, -2, prawo);
                tabPozycjaXY[1] = pozycjaY(1, 0, -1, 0, prawo);
                break;
            case 2:
                tabPozycjaXY[0] = pozycjaX(-1, 0, 1, 0, prawo);
                tabPozycjaXY[1] = pozycjaY(1, 0, -1, -2, prawo);
                break;
            case 3:
                tabPozycjaXY[0] = pozycjaX(-1, 0, 1, 2, prawo);
                tabPozycjaXY[1] = pozycjaY(-1, 0, 1, 0, prawo);
                break;
        }

        return tabPozycjaXY;
                
    }
    /**
     * Metoda obracająca konkretny klocek w prawo albo w lewo.
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych po obrocie w zależności od aktualnej pozycji klocka.
     * @see Pozycja
     */
    private int[][] obrotKlockaT(boolean prawo)
    {
        
        int [][] tabPozycjaXY = new int[][]{{0,0,0,0},{0,0,0,0}};
        int nrPoz = aK.getPozycja().ordinal();
        if(!prawo)
        {
            if(nrPoz == 0)
                nrPoz = 3;
            else
                nrPoz--;
        }
        
        switch(nrPoz)
        {
            case 0:
                tabPozycjaXY[0] = pozycjaX(1, 0, -1, -1, prawo);
                tabPozycjaXY[1] = pozycjaY(-1, 0, -1, 1, prawo);
                break;
            case 1:
                tabPozycjaXY[0] = pozycjaX(1, 0, 1, -1, prawo);
                tabPozycjaXY[1] = pozycjaY(1, 0, -1, -1, prawo);
                break;
            case 2:
                tabPozycjaXY[0] = pozycjaX(-1, 0, 1, 1, prawo);
                tabPozycjaXY[1] = pozycjaY(1, 0, 1, -1, prawo);
                break;
            case 3:
                tabPozycjaXY[0] = pozycjaX(-1, 0, -1, 1, prawo);
                tabPozycjaXY[1] = pozycjaY(-1, 0, 1, 1, prawo);
                break;
        }

        return tabPozycjaXY;
                
    }
    /**
     * Metoda obracająca konkretny klocek w prawo albo w lewo.
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych po obrocie w zależności od aktualnej pozycji klocka.
     * @see Pozycja
     */
    private int[][] obrotKlockaZ(boolean prawo)
    {
        
        int [][] tabPozycjaXY = new int[][]{{0,0,0,0},{0,0,0,0}};
        int nrPoz = aK.getPozycja().ordinal();
        if(!prawo)
        {
            if(nrPoz == 0)
                nrPoz = 3;
            else
                nrPoz--;
        }
        
        switch(nrPoz)
        {
            case 0:
                tabPozycjaXY[0] = pozycjaX(1, 0, -1, -2, prawo);
                tabPozycjaXY[1] = pozycjaY(-1, 0, -1, 0, prawo);
                break;
            case 1:
                tabPozycjaXY[0] = pozycjaX(1, 0, 1, 0, prawo);
                tabPozycjaXY[1] = pozycjaY(1, 0, -1, -2, prawo);
                break;
            case 2:
                tabPozycjaXY[0] = pozycjaX(-1, 0, 1, 2, prawo);
                tabPozycjaXY[1] = pozycjaY(1, 0, 1, 0, prawo);
                break;
            case 3:
                tabPozycjaXY[0] = pozycjaX(-1, 0, -1, 0, prawo);
                tabPozycjaXY[1] = pozycjaY(-1, 0, 1, 2, prawo);
                break;
        }

        return tabPozycjaXY;
                
    }
    /**
     * Metoda obracająca konkretny klocek w prawo albo w lewo.
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych po obrocie w zależności od aktualnej pozycji klocka.
     * @see Pozycja
     */
    private int[][] obrotKlockaS(boolean prawo)
    {
       
        int [][] tabPozycjaXY = new int[][]{{0,0,0,0},{0,0,0,0}};
        
        int nrPoz = aK.getPozycja().ordinal();
        if(!prawo)
        {
            if(nrPoz == 0)
                nrPoz = 3;
            else
                nrPoz--;
        }
        
        switch(nrPoz)
        {
            case 0:
                tabPozycjaXY[0] = pozycjaX(0, -1, 0, -1, prawo);
                tabPozycjaXY[1] = pozycjaY(-2, -1, 0, 1, prawo);
                break;
            case 1:
                tabPozycjaXY[0] = pozycjaX(2, 1, 0, -1, prawo);
                tabPozycjaXY[1] = pozycjaY(0, -1, 0, -1, prawo);
                break;
            case 2:
                tabPozycjaXY[0] = pozycjaX(0, 1, 0, 1, prawo);
                tabPozycjaXY[1] = pozycjaY(2, 1, 0, -1, prawo);
                break;
            case 3:
                tabPozycjaXY[0] = pozycjaX(-2, -1, 0, 1, prawo);
                tabPozycjaXY[1] = pozycjaY(0, 1, 0, 1, prawo);
                break;
        }

        return tabPozycjaXY;
                
    }
    /**
     * Metoda obracająca konkretny klocek w prawo albo w lewo.
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych po obrocie w zależności od aktualnej pozycji klocka.
     * @see Pozycja
     */
    private int[][] obrotKlocekI(boolean prawo)
    {
        
        int [][] tabPozycjaXY = new int[][]{{0,0,0,0},{0,0,0,0}};
        int nrPoz = aK.getPozycja().ordinal();
        if(!prawo)
        {
            if(nrPoz == 0)
                nrPoz = 3;
            else
                nrPoz--;
        }
        
        switch(nrPoz)
        {
            case 0:
                tabPozycjaXY[0] = pozycjaX(1, 0, -1, -2, prawo);
                tabPozycjaXY[1] = pozycjaY(-2, -1, 0, 1, prawo);
               
                break;
            case 1:
                tabPozycjaXY[0] = pozycjaX(2, 1, 0, -1, prawo);
                tabPozycjaXY[1] = pozycjaY(1, 0, -1, -2, prawo);
                
                break;
            case 2:
                tabPozycjaXY[0] = pozycjaX(-1, 0, 1, 2, prawo);
                tabPozycjaXY[1] = pozycjaY(2, 1, 0, -1, prawo);
              
                break;
            case 3:
                tabPozycjaXY[0] = pozycjaX(-2, -1, 0, 1, prawo);
                tabPozycjaXY[1] = pozycjaY(-1, 0, 1, 2, prawo);
               
                break;
        }

        return tabPozycjaXY;
    }
    /**
     * Metoda zwracająca tablicę współrzędnych x 4 kwadratów aktywnego klocka
     * @param e1
     * @param e1
     * współrzędna pierwszego kwadratu
     * @param e2
     * współrzędna drugiego kwadratu
     * @param e3
     * współrzędna trzeciego kwadratu
     * @param e4
     * współrzędna czwartego kwadratu
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych x 4 kwadratów aktywnego klocka
     */
    private int [] pozycjaX(int e1,int e2,int e3,int e4, boolean prawo)
    {
        int i = 0;
        int [] tabPozycja;
        if(prawo)
        {
            tabPozycja = new int[]{e1*w,e2*w,e3*w,e4*w};
        }
        else
        {
            tabPozycja = new int[]{-e1*w,-e2*w,-e3*w,-e4*w};
        }
        for(Kwadrat tempKlocek : aK.getKlocek())
        {
            tabPozycja[i] += tempKlocek.getX();
            i++;
        }
        return tabPozycja;
    }
    /**
     * Metoda zwracająca tablicę współrzędnych y 4 kwadratów aktywnego klocka
     * @param e1
     * współrzędna pierwszego kwadratu
     * @param e2
     * współrzędna drugiego kwadratu
     * @param e3
     * współrzędna trzeciego kwadratu
     * @param e4
     * współrzędna czwartego kwadratu
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych y 4 kwadratów aktywnego klocka
     */
    private int [] pozycjaY (int e1, int e2, int e3, int e4, boolean prawo)
    {
        int i = 0;
        int [] tabPozycja;
        if(prawo)
        {
            tabPozycja = new int[]{e1*w,e2*w,e3*w,e4*w};
        }
        else
        {
            tabPozycja = new int[]{-e1*w,-e2*w,-e3*w,-e4*w};
        }
        for(Kwadrat tempKlocek : aK.getKlocek())
        {
            tabPozycja[i] += tempKlocek.getY();
            i++;
        }
        return tabPozycja;
    }
    
    /**
     * Metoda sprawdzająca rodzaj klocka.
     * @param idKlocka
     * rodzaj klocka
     * @param prawo
     * <code>false</code> oznacza obrót w lewo
     * <code>true</code> oznacza obrót w prawo
     * @return 
     * tablicę współrzędnych 4 kwadratów aktywnego klocka po obrocie
     */
   public int[][] sprawdzanieIdKlocka(IdKlocka idKlocka, boolean prawo)
    {
        switch(idKlocka.name())
        {
            case "O":
                return null;
            case "I":
                return obrotKlocekI(prawo);
            case "J":
                return obrotKlockaJ(prawo);
            case "L":
                return obrotKlockaL(prawo);
            case "T":
                return obrotKlockaT(prawo);
            case "Z":
                return obrotKlockaZ(prawo);
            case "S":
                return  obrotKlockaS(prawo);
        }
        return null;
    }
    
}
