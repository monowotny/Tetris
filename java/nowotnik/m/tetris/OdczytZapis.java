
package nowotnik.m.tetris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Klasa odpowiadająca za zapis do i odczyt z pliku.
 * @author Mikołaj Nowotnik
 */
public class OdczytZapis {
    
    /**
     * Metoda zapisująca linię tekstu do pliku
     * o określonej nazwie
     * @param nazwa
     * nazwa pliku
     * @param zapLinia
     * imię gracza wraz z wynikiem.
     * @throws IOException 
     * wyjątek I/O przy zapisie do pliku
     */
    public static void save (String nazwa, String zapLinia) throws IOException
    {
        
        BufferedWriter bw = new BufferedWriter(new FileWriter (nazwa,true));
        bw.write(zapLinia, 0, zapLinia.length());
        bw.newLine();
        bw.close();
        
        
        
    }
    /**
     * Metoda sortująca linie w pliku, o określonej nazwie,
     * według wyniku. 
     * @param nazwa
     * nazwa pliku
     * @throws IOException 
     * wyjątek I/O przy otwieraniu pliku i zapisie do pliku
     */
    public static void sortuj(String nazwa) throws IOException
    {
        
        String linia = null;
        String imie = null;
        String punkty = null;
        
        try
        {
            ArrayList<String> linie = new ArrayList<>(load(nazwa));
            ArrayList<ImiePunkty> listaSort = new ArrayList<>();
            for(int i = 0; i <linie.size(); i++)
            {
                try{
                
                    linia = linie.get(i);
                    imie = linia.substring(3, 13);
                    punkty = linia.substring(14,linia.length());
                
                    listaSort.add(new ImiePunkty(imie, Integer.parseInt(punkty.trim())));
                }
                catch (NumberFormatException ne)
                {
                    
                    System.err.println(ne);
                    break;
                }
                catch(StringIndexOutOfBoundsException siob)
                {
                    System.out.println("B\u0142\u0105d w pliku :"+nazwa);
                    break;
                }
            }
            linie.clear();
            Collections.sort(listaSort, Collections.reverseOrder(new SortByPunkty()));
           
           
            BufferedWriter bw = new BufferedWriter(new FileWriter (nazwa));
            for(int i = 0; i< listaSort.size() && i<15; i ++)
            {
                bw.write(listaSort.get(i).wLlinie(), 0, listaSort.get(i).wLlinie().length());
                bw.newLine();
                
            }
            
            
            bw.close();
            
        }
        catch(IOException ioe)
        {
            System.err.print(ioe);
        }
        
        
    }
    /**
     * Klasa przechowująca imie i wynik gracza sczytanych z pliku.
     */
    private static class ImiePunkty 
    {
        private String imie;
        private int punkty;
        /**
         * Tworzy obiekty o polach
         * @param imie
         * imie gracza
         * @param punkty 
         * wynik gracza
         */
        public ImiePunkty(String imie, int punkty) {
            this.imie = imie;
            this.punkty = punkty;
        }

        /**
         * Metoda zwracająca punkty gracza.
         * @return 
         * punkty gracza sczytane z pliku.
         */
        public int getPunkty()
        {
            return punkty;
        }
        /**
         * Metoda zamieniająca imię i punkty gracza
         * w linię tekstu do zapisu do pliku.
         * @return 
         * linia tekstu.
         */
        public String wLlinie()
        {
            String s = imie;
            for(int i = imie.length(); i<11; i++)
                s+=' ';
            s+=Integer.toString(punkty);
            return s;
        }
        
        
    }
    /**
     * Klasa sortująca wyniki graczy.
     */
    private static class SortByPunkty implements Comparator<ImiePunkty>
    {

        @Override
        public int compare(ImiePunkty ip1, ImiePunkty ip2) {
            return ip1.getPunkty() - ip2.getPunkty();
        }
        
    }
    /**
     * Metoda sczytująca linie z pliku o określonej nazwie.
     * @param nazwa
     * nazwa pliku
     * @return
     * listę linii tekstu z imionami i wynikami.
     * @throws IOException 
     * wyjątek I/O przy otwieraniu pliku.
     */
    public static ArrayList<String> load (String nazwa) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(nazwa));
        ArrayList<String> linie = new ArrayList<>();
        String linia = null;
        int i = 1;
        String numer;
        while((linia = br.readLine()) != null)
        {
            numer = Integer.toString(i);
            linie.add(numer+". "+linia);
            i++;
            
        }
        br.close();
        
        
        return linie;
    }
    
}
