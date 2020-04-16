/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nowotnik.m.tetris.gui;

import nowotnik.m.tetris.tetromino.Kwadrat;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import nowotnik.m.tetris.OdczytZapis;

/**
 * Klasa rysująca na ekranie dane po końcu gry.
 * @author Mikołaj Nowotnik
 */
public class KoniecGry extends Canvas{

    EkranGry ekran;
    private String imie = "_";
    
    private int wymiar = Kwadrat.getWymiar();
    Font f = new Font("Lucida Sans", PLAIN, wymiar);
    /**
     * Tworzy obiekt na określonym ekranie.
     * @param ekran 
     * ekran gry.
     */
    public KoniecGry(EkranGry ekran) {
        this.ekran = ekran;
    }
    /**
     * Metoda rysująca na ekranie napis "koniec gry",
     * wynik oraz miejsce na podanie imienia.
     * @param g 
     * kontekst graficzny
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString("Koniec gry",7,3*wymiar);
        g.drawString("Wynik",7,5*wymiar);
        g.drawString(Punktacja.getWynik(),7,7*wymiar);
        g.drawString("Wprowad\u017a imi\u0119 i naci\u015bnij Enter",7,9*wymiar);
        g.drawString(imie,7,11*wymiar);
        
    }
    /**
     * Metoda ponownie rysująca tylko pole z imieniem.
     * @param g 
     * kontekst graficzny
     */
    @Override
    public void update(Graphics g) {
        g.clearRect(5, 10*wymiar, getWidth(), wymiar*2);
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString(imie,7,11*wymiar);
    }
    
        
            
     
    
    
    /**
     * Klasa obsługująca wpisywanie imienia, rysowanie go na ekranie
     * oraz zapisywanie go do pliku.
     */
    public class RysujKoniec extends KeyAdapter
    {
    
        @Override
        public void keyPressed(KeyEvent e) {
            
            if(e.getKeyCode()>31 && imie.length()<11)
            {
                //Dodaje znak wciśniętego klawisza do imienia i rysuje
                imie = imie.substring(0, imie.length()-1);
                imie+=e.getKeyChar();
                imie+='_';
                repaint();
            }
            else if(e.getKeyCode() == 8)//Backspace
            {
                if(imie.length()>1)
                {
                    //Usuwa znak z imienia i rysuje
                    imie = imie.substring(0, imie.length()-2);
                    imie+='_';
                    repaint();
                }
            }
            else if(e.getKeyCode() == 10)//Enter
            {
                imie = imie.substring(0, imie.length()-1);
                for(int i = imie.length(); i<10 ; i++)
                {
                    imie+=' ';
                }
                try
                {
                    OdczytZapis.save("Wyniki.txt", imie+" "+Punktacja.getWynik());
                    OdczytZapis.sortuj("Wyniki.txt");
                }
                catch(IOException ioe)
                {
                    System.err.println(ioe);
                }
               
                int ile = ekran.getComponentCount();
                for(int i = 0; i < ile; i++)//Usuwa wszystko z ekranu
                {
                    ekran.remove(ekran.getComponent(0));
                    
                    
                }
                ekran.setSize(800, 600);
                ekran.setLocationRelativeTo(null);
                //Tworzy menu
                new PrzyciskiMenu(ekran).spakuj();
                ekran.removeKeyListener(this);
                
                
            }
            
        }
    
       
        
    }
    
   
    
}
