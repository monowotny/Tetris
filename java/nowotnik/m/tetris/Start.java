
package nowotnik.m.tetris;

import nowotnik.m.tetris.gui.EkranGry;

/**
 * Klasa uruchamiąjąca program
 * @author Mikołaj Nowotnik
 */
public class Start {

    /**
     * Metoda główna klasy Start
     * @param args zmienne wejściowe
     */
    
    public static void main(String[] args) {
        
        EkranGry ekran = new EkranGry("Tetris");
        ekran.menu();
        
    }
    
}
