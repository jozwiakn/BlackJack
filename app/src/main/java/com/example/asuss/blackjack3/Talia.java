package com.example.asuss.blackjack3;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by waga on 2016-08-07.
 */
public class Talia {
    public Talia(int i){
        ile = i;
    }
    List<Karta> talia = new LinkedList();
    int ile;

    public void stworz_talie(){
        int id = 0;
        int ile_kart = ile;
        while(ile_kart>0) {
            int licznik = 0;
            while (licznik < 13) { //karo
                id++;
                Karta karta = new Karta(1, licznik + 2, id);
                talia.add(karta);
                licznik = licznik + 1;
            }
            licznik = 0;
            while (licznik < 13) { //serce
                id++;
                Karta karta = new Karta(2, licznik + 2, id);
                talia.add(karta);
                licznik = licznik + 1;
            }
            licznik = 0;
            while (licznik < 13) { //pik
                id++;
                Karta karta = new Karta(3, licznik + 2, id);
                talia.add(karta);
                licznik = licznik + 1;
            }
            licznik = 0;
            while (licznik < 13) { //trefl
                id++;
                Karta karta = new Karta(4, licznik + 2, id);
                talia.add(karta);
                licznik = licznik + 1;
            }
            ile_kart = ile_kart-1;
        }
    }

    public void tasuj_karty(){
        int[] tab = new int[52*ile];
        int x;
        boolean byl = true;
        Random generator = new Random();
        for(int i=0; i<52*ile; i++) {
            x = generator.nextInt(52 * ile);
            while(byl==true) {
                byl=false;
                for (int c : tab
                        ) {
                    if (c == x) {
                        x = generator.nextInt(52 * ile);
                        byl = true;
                    }
                }
            }
        tab[i] = x;
        }
        for(int i=0; i<52*ile; i++){
            Karta karta = talia.get(0);
            talia.remove(0);
            talia.add(tab[i], karta);
        }
    }
}
