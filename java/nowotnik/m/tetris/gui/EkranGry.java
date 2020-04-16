/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nowotnik.m.tetris.gui;


import nowotnik.m.tetris.sterowanie.Sterowanie;
import nowotnik.m.tetris.tetromino.Kwadrat;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import nowotnik.m.tetris.Gra;


/**
 * Klasa obsługująca ekran gry.
 * @author Mikołaj Nowotnik
 */
public class EkranGry extends Frame{
    private Gra gra;
    private EkranGry ekran = this;
    private Sterowanie sterowanie;
    private Zatrzymaj zatrzymaj = new Zatrzymaj();
    private Pauza pauza;
    private String nazwa;
  
    private static int w = 800;
    private static int h = 600;
    /**
     * Tworzy okno o określonej nazwie.
     * @param nazwa 
     * nazwa gry.
     */
    public EkranGry(String nazwa)
    {
        this.nazwa = nazwa;
       
    }
    /**
     * Metoda ustawiająca parametry okna i wyświetlająca menu.
     */
    public void menu ()
    {
        
        
      
        setTitle(nazwa);
        setBackground(Color.BLACK);
        Kwadrat.setWymiar(30);
        
        
        
        
        setSize(w,h);
        new PrzyciskiMenu(this).spakuj();
        
        
        
        
        setLayout(null);
        
        setAutoRequestFocus(true);
        setResizable(false);
        setLocationRelativeTo(null); //Środek ekranu
        setVisible(true);
        addWindowListener(new ZamykanieOkna());
        
        
       
    }
    /**
     * Metoda uruchamiająca grę.
     */
    public void graStart()
    {
        
        usunPrzyciski();
        w = Kwadrat.getWymiar()*30;
        h = Kwadrat.getWymiar()*22+30;
        
        setSize(new Dimension(w,h));
        gra = new Gra(this);
        sterowanie = new Sterowanie(gra);
        gra.addMouseListener(new WindowFocus());
        pauza = new Pauza(this);
        
        
        add(pauza);
        gra.setBounds(5, 25, w,h);
        
        
        
        gra.setVisible(true);
        add(gra);
        
        
        
        setLocationRelativeTo(null); 
        addKeyListener(zatrzymaj);
        addKeyListener(sterowanie); 
        
        gra.inicjalizacja();

        
        

    }

    
    /**
     * Klasa umożliwiająca zamknięcie okna.
     */
    private class ZamykanieOkna extends WindowAdapter
    {

        @Override
        public void windowClosing(WindowEvent e) 
        {
            System.exit(0);
        }
        
    }
    /**
     * Klasa przywracająca Focus grze po kliknięciu myszą.
     */
    public class WindowFocus extends MouseAdapter 
    {
        private Thread watek;
        @Override
        public void mousePressed(MouseEvent e) {
            
            if(watek == null)
            {
                watek = new Thread(new Focus());
                watek.start();
            }
            else if(!watek.isAlive())
            {
                watek = new Thread(new Focus());
                watek.start();
            }
       
            
            
        }
        private class Focus implements Runnable
        {
            @Override
            public void run() {
           
                requestFocus();
           
            
            }
        }

       
        
        
    }
    /**
     * Klasa umożliwiająca wyjście do menu po wciśnięciu "ESC".
     */
    public class Zatrzymaj extends KeyAdapter
    {

        @Override
        public void keyReleased(KeyEvent e) 
        {
            if(e.getKeyCode() == 27)
            {
                
                System.out.println("Zatrzymuje");
                gra.zatrzymaj();
                removeKeyListener(sterowanie);
                removeKeyListener(zatrzymaj);
                new PrzyciskiMenu(ekran).spakuj();
                gra.setVisible(false);
                
                pauza.setVisible(false);
                gra.getKlocek().getKlocek().clear();
                remove(pauza);
                remove(gra);
                System.gc();
                
            }
                             
        }
        
   
    }
    

   /**
    * Metoda usuwająca elementy gui, które nie są potrzebne.
    */
   public void usunPrzyciski()
   {
       int ile = getComponentCount();
       for(int i = 0; i<ile; i++)
            remove(getComponent(0));
   }
   
 
   
}
