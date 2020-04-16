/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nowotnik.m.tetris.tetromino;

import java.awt.Graphics;
import nowotnik.m.tetris.gui.Punktacja;


/**
 * Klasa obsługująca klocki, które zostały unieruchomione i 
 * gracz stracił nad nimi kontrolę.
 * @author Mikołaj Nowotnik
 */
public class NieaktwyneKlocki {
    
    Punktacja punktacja;
    /**
     * Tablica obejmująca całą plansze, zawierająca nieaktywne klocki
     * albo pola null.
     */
    private Kwadrat[][] tablicaKlockow = new Kwadrat[22][10];
    

    
    /**
     * Metoda dodająca klocek do tablicy nieaktywnych klocków 
     * po jednym kwadracie
     * @param kwadrat 
     * kwadrat aktywnego klocka, który ma zostać dodany do tej tablicy.
     */
    public void przekazKwadrat(Kwadrat kwadrat)
    {
        int nrKolumny = Math.round(kwadrat.getX()/Kwadrat.getWymiar());
        int nrWiersza = Math.round(kwadrat.getY()/Kwadrat.getWymiar());
        
        tablicaKlockow[nrWiersza][nrKolumny] = kwadrat;
        
    }
    /**
     * Metoda usuwająca pełną linię nieaktywnych klocków.
     * @param nrW 
     * numer wiersza, który zostanie usunięty.
     */
    private void usunLinie (int nrW)
    {
        for(int j = 0; j<10; j++)
        {
            
            tablicaKlockow[nrW][j] = null;
        }
        
    }
    /**
     * Metoda przesuwająca klocek w dół, 
     * znajdujący się nad usuniętą linią.
     * 
     * @param stareNrW
     * obecny numer wiersza w tablic nieaktywnych klocków
     * @param NrK
     * numer kolumny w tablic nieaktywnych klocków
     * @param noweNrW
     * nowy numer wiersza w tablic nieaktywnych klocków
     * @see #usunLinie(int) 
     */
    private void przesunKlocek(int stareNrW, int NrK, int noweNrW)
    {
        
        tablicaKlockow[stareNrW][NrK] = tablicaKlockow[noweNrW][NrK];
    }
    /**
     * Metoda sprawdzająca czy w danym miejscu tablicy istnieje klocek.
     * Używana przy sprawdzaniu kolizji.
     * @param nrW
     * numer przeszukiwanego wiersza
     * @param nrK
     * numer przeszukiwanej kolumny
     * @return 
     * <code>false</code> gdy klocek nie istnieje,
     * <code>true</code> gdy klocek istnieje.
     * @see Kolizja
     */
    public boolean getNieaktywneKlocki(int nrW, int nrK)
    {
        if(tablicaKlockow[nrW][nrK]==null)
        {
            return false;
        }
           
        return true;
        
    }
    /**
     * Metoda rysująca niepuste linie na ekranie.
     * @param g 
     * kontekst graficzny
     */
    public void rysuj (Graphics g)
    {

        
        for(int i = 21; i>=0; i--)
        {
            if(pustaLinia(i))
            {
                break;
            }
            for(int j = 0; j<10; j++)
            {
                if(tablicaKlockow[i][j] != null)
                    tablicaKlockow[i][j].rysuj(g);
            }
        }
        
    }
    /**
     * Metoda sprawdzająca czy dana linia jest pusty.
     * @param nrW
     * numer przeszukiwanego wiersza.
     * @return 
     * <code>false</code> gdy linia nie jest pusta,
     * <code>true</code> gdy linia jest pusta.
     */
    private boolean pustaLinia(int nrW)
    {
        for(int j = 0; j<10; j++)
        {
            if(tablicaKlockow[nrW][j] != null )
                return false;
        }
        return true;
    }
    /**
     * Metoda sprawdzająca czy dana linia jest pusta.
     * @param nrW
     * numer przeszukiwanego wiersza.
     * @return 
     * <code>false</code> gdy linia nie jest pełna,
     * <code>true</code> gdy linia jest pełna.
     */
    private boolean pelnaLinia (int nrW)
    {
        for(int j = 0; j<10; j++)
        {
            if(tablicaKlockow[nrW][j] == null )
                return false;
        }
        return true;
    }
    /**
     * Metoda zamieniająca współrzędną y klocka na numer
     * wiersza w tablicy nieaktywnych klocków.
     * @param Y
     * współrzędna y jednego z kwadratów klocka, który właśnie się zablokował.
     * @return 
     * numer wiersza w tablic nieaktywnych klocków.
     */
    private int wNr (int Y)
    {
        int nr = Math.round(Y/Kwadrat.getWymiar());
        return nr;
    }
    /**
     * Metoda usuwająca pełne linie i opuszczająca linie powyżej.
     * @param Y
     * współrzędna y jednego z kwadratów klocka, który właśnie się zablokował.
     * @return 
     * <code>false</code> gdy linia jest pełna,
     * <code>true</code> gdy linia jest pełna.
     */
    public boolean opuszczanieLinii (int Y)
    {
        if(Y != -1){
            int nrW = wNr(Y);
            if(pelnaLinia(nrW))
            {
                int y = 0;
                usunLinie(nrW);
                
                //Jeżeli to był pierwszy wiersz to nic nie opuszcza
                if(nrW == 0)
                {
                    return true;
                }
                for(int i = nrW-1; i>=0; i--)
                {
                    //Jeżeli linia powyżej jest pusta, to nic się nie dzieje
                    if(pustaLinia(i))
                        return true;
               
                    
                    for(int j = 0; j<10; j++)
                    {
                        if(tablicaKlockow[i][j] != null)
                        {
                            tablicaKlockow[i][j].setY((i+1)*Kwadrat.getWymiar());
                            przesunKlocek(i+1,j, i);
                            
                        }
                           
                        
                        
                    }
                    usunLinie(i);

                }
                return true;
            }
            
        }
        return false;
    }
    
}
