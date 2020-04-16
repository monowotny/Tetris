
package nowotnik.m.tetris.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import nowotnik.m.tetris.tetromino.GenerowanieKlockow;
import nowotnik.m.tetris.Gra;
import nowotnik.m.tetris.tetromino.Klocek;
import nowotnik.m.tetris.tetromino.Kwadrat;

/**
 * Klasa obsługująca opcje gry.
 * @author Mikołaj Nowotnik
 */
public class PrzyciskiOpcje extends Przyciski
{

    private Wybrany wybrany;
    Prezentacja prezentacja;
    Klocek klocek;
    /**
     * Tworzy opcje na określonym ekranie.
     * @param ekran 
     * ekran gry.
     */
    public PrzyciskiOpcje(EkranGry ekran)
    {
        wybrany = new Wybrany();
        this.ekran = ekran;
    }

    @Override
    protected void spakuj() {
        
        dodajPrzyciski();
        Component comp;
        for(int i = 0; i <ekran.getComponentCount()-1; i++)
        {
            comp = ekran.getComponent(i);
            if(comp.getClass() == Przycisk.class)
            {
                if(!"Preview".equals(comp.getName()))
                {
                    dodajMlDoPrzyciski(i, new Wymiar());
                    
                    if(Integer.parseInt(comp.getName()) == Kwadrat.getWymiar())
                    {
                        dodajMlDoPrzyciski(i, wybrany);
                        comp.setBackground(Color.WHITE);
                        comp.setForeground(Color.BLACK);
                        
                    }
                }
                else
                {
                    dodajMlDoPrzyciski(i, new Wybieralny());
                    
                    if(Gra.getPreview())
                    {
                        dodajMlDoPrzyciski(i, wybrany);
                        comp.setBackground(Color.WHITE);
                        comp.setForeground(Color.BLACK);
                    }
                        
                }
            }
        }
            
        dodajMlDoPrzyciski(ekran.getComponentCount()-1, new Powrot());
        
        
        
    }

    
    @Override
    protected void dodajPrzyciski() {
        
        int x = (int)(ekran.getWidth()/2);
        int y = (int)((ekran.getHeight()/3)-20);
        
        
        
       
        
        ekran.add(new Tekst("Sterowanie", x, y-80, 30, 300));
        DodajTekst tekstSter = new DodajTekst();
        tekstSter.setBounds(x, y-30, 400, 400);
        ekran.add(tekstSter);
        
        x = 50;
        ekran.add(new Tekst("Opcje", x, y-80, 30, width));
        Tekst opis = new Tekst("Opis", x, y-30, 15, 200);
        opis.setText("Wybierz wymiar boku klocka");
        ekran.add(opis);
        klocek = new Klocek() {};
        new GenerowanieKlockow(klocek);
        prezentacja = new Prezentacja();
        
        y+=20;
        prezentacja.setBounds(x+150, y, 300, 100);
        //Prezentowany klocek
        ekran.add(prezentacja);
        
        
        ekran.add(new Przycisk("20",x,y));
        y+=60;
        ekran.add(new Przycisk("25",x,y));
        y+=60;
        ekran.add(new Przycisk("30",x,y));
        y+=60;
        ekran.add(new Przycisk("Preview", x+150, y));
        ekran.add(new Przycisk("35",x,y));
        y+=60;
        ekran.add(new Przycisk("Menu",x,y));
        
        
    }
    /**
     * Klasa rysująca klocek na ekranie 
     * w zależności od wybranego przez gracza rozmiaru 
     * po naciśnięciu przycisku.
     */
    private class Wymiar extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e) {
            Component comp;
            
            for(int i = 0; i <ekran.getComponentCount(); i++)
            {
                comp = ekran.getComponent(i);
                if(comp.getClass() == Przycisk.class)
                {
                    if(comp == e.getComponent()|| "Preview".equals(comp.getName()))
                        continue;
                    comp.removeMouseListener(wybrany);
                    comp.setBackground(Color.BLACK);
                    comp.setForeground(Color.WHITE);
                }
            }
            
            e.getComponent().addMouseListener(wybrany);
            Kwadrat.setWymiar(Integer.parseInt(e.getComponent().getName()));
            klocek.usunKlocek();
            new GenerowanieKlockow(klocek);
            
            prezentacja.repaint();
        }
        
    }
    /**
     * Klasa pozwalająca zaznaczyć i odznaczyć opcję "Preview",
     * czyli pokazanie/ukrycie kolejki klocków.
     */
    private class Wybieralny extends MouseAdapter
    {

        @Override
        public void mousePressed(MouseEvent e) {
            Component comp = e.getComponent();
            boolean usunieto = false;
            for (MouseListener mL : comp.getMouseListeners()) {
                if (mL == wybrany) {
                    comp.removeMouseListener(wybrany);
                    comp.setBackground(Color.BLACK);
                    comp.setForeground(Color.WHITE);
                    Gra.setPreview(false);
                    usunieto = true;
                }
            }
            if(!usunieto)
            {
                comp.addMouseListener(wybrany);
                comp.setBackground(Color.WHITE);
                comp.setForeground(Color.BLACK);
                Gra.setPreview(true);
            }
            
            
        }
        
        
    }
    /**
     * Klasa rysująca obługę sterowania na ekranie.
     */
    private class DodajTekst extends Canvas
    {
        
       

        @Override
        public void paint(Graphics g) {
            super.paint(g); 
            int y = 0;
            Font f = new Font("Lucida Sans", PLAIN, 15);
            g.setFont(f);
            g.setColor(Color.WHITE);
            
            g.drawString("Esc - wyj\u015bcie do menu", 0, y+=50);
            g.drawString("Ctrl - obr\u00f3t klocka w lewo", 0, y+=50);
            g.drawString("Spacja - pauza", 0, y+=50);
            g.drawString("Strza\u0142ka w g\u00f3r\u0119 - obr\u00f3t klocka w prawo", 0, y+=50);
            g.drawString("Strza\u0142ka prawo, lewo, d\u00f3\u0142", 0, y+=50);
            g.drawString("- odpowienie ruchy klocka", 0, y+=50);
            
            
        }
        
           
    }
    /**
     * Klasa rysująca podgląd klocka.
     */
    private class Prezentacja extends Canvas
    {
       
        @Override
        public void paint(Graphics g) 
        {
            super.paint(g); 
            klocek.rysuj(g);
            
        }
        
                
    }
 
    /**
     * Klasa zmieniająca zachowanie wciśniętego przycisku.
     */
    private class Wybrany extends MouseAdapter
    {

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setBackground(Color.WHITE);
            e.getComponent().setForeground(Color.BLACK);
            
        }
        
        
    }
    /**
     * Klasa pozwalająca na powrót do menu.
     */
    private class Powrot extends MouseAdapter
    {

        @Override
        public void mousePressed(MouseEvent e) {
            
           
            ekran.usunPrzyciski();
            new PrzyciskiMenu(ekran).spakuj();
        }
        
        
    }
    
}
