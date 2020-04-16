/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nowotnik.m.tetris.tetromino;

/**
 * Zbiór wartości określający w jakie j pozycji w trakcie obrotu jest klocek.
 * @author Mikołaj
 */
public enum Pozycja {
    Pozycja1(),
    Pozycja2(),
    Pozycja3(),
    Pozycja4();
    
    /**
     * Metoda używana przy obracaniu klocka w prawo. 
     * Zmienia pozycję klocka na następną.
     * @param pozycja
     * pozycja klocka przed obrotem.
     * @return 
     * pozycję klocka po obrocie.
     */
    public Pozycja zmianaPozycji(Pozycja pozycja)
    {
        int nr = pozycja.ordinal();
        int i = 0;
        for(Pozycja tempPozycja : Pozycja.values())
        {
            if(nr+1 == i)
            {
                return tempPozycja;
            }
            i++;
        }
        return Pozycja1;
    }
    /**
     * Metoda używana przy obracaniu klocka w lewo. 
     * Zmienia pozycję klocka na poprzednią.
     * @param pozycja
     * pozycja klocka przed obrotem.
     * @return 
     * pozycję klocka po obrocie.
     */
    public Pozycja zmianaPozycjiL(Pozycja pozycja)
    {
        int nr = pozycja.ordinal();
        int i = 0;
        for(Pozycja tempPozycja : Pozycja.values())
        {
            if(nr-1 == i)
            {
                return tempPozycja;
            }
            i++;
        }
        return Pozycja4;
    }
    
}
