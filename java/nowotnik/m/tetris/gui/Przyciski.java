/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nowotnik.m.tetris.gui;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Klasa abstrakcyjna tworząca gui.
 * @author Mikołaj Nowotnik
 */
public abstract class Przyciski 
{
    EkranGry ekran;
    /**
     * Wysokość komponentu.
     */
    protected int height = 40;
    /**
     * Szerokość komponentu.
     */
    protected int width = 100;
    /**
     * Metoda dodająca obsługę wydarzeń do elementów gui.
     */
    protected void spakuj()
    {
        
    }
    /**
     * Metoda dodająca przyciski do ekranu.
     */
    protected void dodajPrzyciski()
    {
    }
    /**
     * Metoda dodająca obsługę wydarzeń generowanych przez mysz,
     * do elemntu o odpowiednim indeksie.
     * @param n
     * numer indeksu elementu gui
     * @param ml 
     * obiekt obsługujący wydarzenia generowane przez mysz.
     */
    protected void dodajMlDoPrzyciski(int n, MouseListener ml)
    {
        ekran.getComponent(n).addMouseListener(ml);
    }
    /**
     * Klasa opisująca rozmieszczenie i wygląd przycisków.
     */
    protected class Przycisk extends Label
    {
        
        /**
         * Tworzy przycisk o współrzędnych x i y, oraz określonym tekście.
         * @param tekst
         * tekst wyświetlany przez przycisk
         * @param x
         * współrzędna na osi x
         * @param y 
         * współrzędna na osi y
         */
        protected Przycisk (String tekst,int x,int y)
        {
           
            ustawPrzycisk(tekst,x, y);
        }
        /**
         * Metoda ustawiająca parametry przycisku.
         * @param tekst
         * tekst wyświetlany przez przycisk
         * @param x
         * współrzędna na osi x
         * @param y 
         * współrzędna na osi y
         */
        private void ustawPrzycisk(String tekst,int x, int y)
        {
            Font f = new Font("Lucida Sans", PLAIN, 20);
            setFont(f);
            setText(tekst);
            setName(tekst);
            setAlignment(Label.CENTER);
            addMouseListener(new Hover());
            setForeground(Color.WHITE);
            setBounds(x,y,width,height);
        }
        
        
        
    }
    /**
     * Klasa opisująca tekst, z którym gracz nie może
     * wejść w interakcje.
     */
    protected class Tekst extends Label
    {
        /**
         * Tworzy nieinteraktywny określony tekst, 
         * w określnym miejscu, o określonej szerokości 
         * i o określonej wielkości czcionki.
         * @param tekst
         * wyświetlany tekst
         * @param x
         * współrzędna na osi x
         * @param y 
         * współrzędna na osi y
         * @param rozF
         * wielkość czcionki
         * @param width 
         * szerokość elementu
         */
        protected Tekst (String tekst,int x,int y, int rozF, int width)
        {
           
            ustawTytul(tekst,x, y, rozF, width);
        }
        /**
         * Metoda ustawiająca parametry tekstu.
         * @param tekst
         * wyświetlany tekst
         * @param x
         * współrzędna na osi x
         * @param y 
         * współrzędna na osi y
         * @param rozF
         * wielkość czcionki
         * @param width 
         * szerokość elementu
         */
        private void ustawTytul(String tekst,int x, int y, int rozF, int width)
        {
            Font f = new Font("Lucida Sans", ITALIC, rozF);
            setFont(f);
            setText(tekst);
            setName(tekst);
            setAlignment(Label.LEFT);
            setForeground(Color.WHITE);
            setBounds(x,y,width,height);
            
           
        }
    }
    /**
     * Klasa odpowiadająca za podświetlanie przycisku
     * przy najechaniu na niego myszą.
     */
    protected class Hover extends MouseAdapter
    {

        @Override
        public void mouseEntered(MouseEvent e) {
           e.getComponent().setForeground(Color.BLACK);
           e.getComponent().setBackground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setForeground(Color.WHITE);
            e.getComponent().setBackground(Color.BLACK);
        }
        
        
        
    }
    
    
    
}
