
package nowotnik.m.tetris.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;

import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import nowotnik.m.tetris.OdczytZapis;

/**
 * Klasa obsługująca menu.
 * @author Mikołaj Nowotnik
 */
public class PrzyciskiMenu extends Przyciski
{
    /**
     * Tworzy przyciski na określonym ekranie.
     * @param ekran 
     * ekran gry.
     */
    public PrzyciskiMenu (EkranGry ekran)
    {
        this.ekran = ekran;
    }
    @Override
    public void spakuj()
    {
        int i = 0;
        dodajPrzyciski();
        //Dodaje odpowiednie zdarzenia tylko do przycisków
        for(Component comp : ekran.getComponents())
        {
            if(comp.getClass() == Przycisk.class)
                break;
            i++;
        }
        dodajMlDoPrzyciski(i, new NowaGra());
        dodajMlDoPrzyciski(++i, new Opcje());
        dodajMlDoPrzyciski(++i, new Wyniki());
        dodajMlDoPrzyciski(++i, new Wyjscie());
    }
    @Override
    protected void dodajPrzyciski()
    {
        int x = (int)(50);
        int y = (int)((ekran.getHeight()/3)-20);
        ekran.add(new Tekst("Tetris", x, y-80,30,width));
        ekran.add(new Przycisk("Nowa Gra",x,y));
        y+=60;
        ekran.add(new Przycisk("Opcje",x,y));
        y+=60;
        ekran.add(new Przycisk("Wyniki",x,y));
        y+=60;
        ekran.add(new Przycisk("Wyj\u015bcie",x,y));
        
    }
        
        
    /**
     * Klasa umożliwiająca uruchomienie gry po naciśnięciu przycisku.
     */
    private class NowaGra extends MouseAdapter
    {
        @Override
        public void mousePressed (MouseEvent e) {
            ekran.graStart();
        }
        
        
    }
    /**
     * Klasa umożliwiająca otwarcie opcji po naciśnięciu przycisku.
     */
    private class Opcje extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
            ekran.usunPrzyciski();
            new PrzyciskiOpcje(ekran).spakuj();
            
        }
        
    }
    /**
     * Klasa rysująca wyniki z pliku "Wyniki.txt" na ekranie
     * po naciśnięciu przycisku.
     */
    private class Wyniki extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
           
            Canvas can = new Canvas()
            {
                @Override
                public void paint(Graphics g) {
                    super.paint(g); 
                        Font f = new Font("Lucida Sans", PLAIN, 15);
                        g.setFont(f);
                        g.setColor(Color.WHITE);
                    try {
                        ArrayList<String> linie = new ArrayList<>(OdczytZapis.load("Wyniki.txt"));
                        for(int i = 0; i<linie.size(); i++)
                        {
                            g.drawString(linie.get(i).substring(0, 13), 200, 
                                    (int)((ekran.getHeight()/3)-20)-80+30*i);
                            g.drawString(linie.get(i).substring(14, linie.get(i).length()), 400, 
                                    (int)((ekran.getHeight()/3)-20)-80+30*i);
                        }
                    } catch (IOException ioe) {
                        System.err.println(ioe);
                    }
                    catch (NumberFormatException ne)
                    {
                    
                        System.err.println(ne);
                    }
                    catch(StringIndexOutOfBoundsException siob)
                    {
                        System.err.println("B\u0142\u0105d w pliku :"+"Wyniki.txt");
                    }
                }
                
               
                
                
            };
            
            int w = e.getComponent().getWidth();
            can.setBounds(w+10, 0, ekran.getWidth()-w, ekran.getHeight());
            ekran.add(can);
        }
        
    }
    /**
     * Klasa umożliwiająca wyjście z programu.
     */
    private class Wyjscie extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
            
            System.exit(0);
        }
        
    }
   
}
