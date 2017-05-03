package com.example.asuss.blackjack3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Zaklad extends AppCompatActivity  {

    int kaasa=0;
    int bbudzet = 10000;
    int zaklad;

    //suma kart
    int suma = 0;
    int suma_przeciwnika = 0;
    int suma_L3 = 0;

    private LinearLayout cardLine1;
    private LinearLayout cardLine2;
    private LinearLayout cardLine3;
    private LinearLayout cardLine4;

    int ile_kart = 0;
    int ile_kart_przeciwnika = 0;
    int ile_kart_L3 = 0;

    //wpolrzedne kart
    int osX = 0; //dla karty
    int osX2 = 155; //dla numeru
    int osXP = 0;
    int osX2P = 155;

    //wspolrzedne kart do rozdwojenia
    int osXL3 = -150;
    int osXL32 =40;
    int osXL3_ = 150;
    int osXL3_2 = 340;

    Talia talia;

    Karta karta_odwrocona;
    ImageView image_odwr;
    TextView textView_odwr;

    //przyciski
    TextView kasa;
    TextView budzet;
    TextView za_ile;
    TextView suma_kart_przeciwnika;
    TextView suma_kart;
    TextView suma2;

    int mam_as = 0;
    int przeciwnik_ma_as = 0;
    int mam_as_L3 = 0;
    boolean rozstrzygniete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obstawianie);

        budzet = (TextView) findViewById(R.id.budzet);
        budzet.setText(Integer.toString(bbudzet));

        Intent intent = getIntent();
        String poziom = intent.getStringExtra("poziom");

        int ppoziom = Integer.parseInt(poziom);

        if(ppoziom==1) {
            talia = new Talia(2);
            talia.stworz_talie();
            talia.tasuj_karty();
        }
        else if(ppoziom==2){
            talia = new Talia(4);
            talia.stworz_talie();
            talia.tasuj_karty();
        }
    }

    public void podwojenie(View v){
        if(rozstrzygniete == true || rozdwojenie!=0){
            return;
        }
        if(suma < 21 && bbudzet >= zaklad) {
            za_ile = (TextView) findViewById(R.id.za_ile);
            zaklad = zaklad + zaklad;
            za_ile.setText(Integer.toString(zaklad));
            bbudzet = bbudzet - zaklad / 2;
            budzet = (TextView) findViewById(R.id.bbudzet);
            budzet.setText(Integer.toString(bbudzet));
            rysuj_karte();
            nie(v);
        }
    }

    RelativeLayout relativeLayout;

    int rozdwojenie = 0;

    public void rozdwojenie(View v){
        if(rozstrzygniete == true || bbudzet<zaklad || rozdwojenie!=0){
            return;
        }
    if(pierwsza.nr == druga.nr || (pierwsza.nr == 10 || pierwsza.nr == 11 || pierwsza.nr == 12 || pierwsza.nr == 13) && (druga.nr == 10 || druga.nr == 11 || druga.nr == 12 || druga.nr == 13)) {
        zaklad = zaklad * 2;
        bbudzet = bbudzet - zaklad/2;
        za_ile = (TextView) findViewById(R.id.za_ile);
        za_ile.setText(Integer.toString(zaklad));
        budzet = (TextView) findViewById(R.id.bbudzet);
        budzet.setText(Integer.toString(bbudzet));
        rozdwojenie = 1;
        suma_kart = (TextView) findViewById(R.id.suma);
        cardLine1.removeAllViews();
        cardLine3.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout cala_karta = (RelativeLayout) inflater.inflate(R.layout.karty, null);
        cala_karta.setPadding(10,0,0,0);
        ImageView image =(ImageView) cala_karta.findViewById(R.id.kolor);
        TextView textView = (TextView) cala_karta.findViewById(R.id.numer);

        ile_kart = 0;
        mam_as = 0;
        suma = 0;
        int numer;
        int kolor = pierwsza.kolor;

        String numerS = Integer.toString(pierwsza.nr);
        switch (kolor) {
            case 1:
                image.setImageResource(R.drawable.kier);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 2:
                image.setImageResource(R.drawable.serce);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 3:
                image.setImageResource(R.drawable.pik);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
            case 4:
                image.setImageResource(R.drawable.trefl);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
        }
        numer = pierwsza.nr;
//        ile_kart = ile_kart + 1;
        if (pierwsza.nr == 14) suma = suma + 11;
        else if (pierwsza.nr == 11 || pierwsza.nr == 12 || pierwsza.nr == 13) suma = suma + 10;
        else suma = suma + numer;
        if (mam_as > 0 && suma > 21) {
            suma = suma - 10;
            mam_as = mam_as - 1;
        }

        suma_kart.setText(Integer.toString(suma));


        kolor = druga.kolor;
        LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout cala_karta2 = (RelativeLayout) inflater2.inflate(R.layout.karty, null);
        cala_karta2.setPadding(10,0,0,0);
        ImageView image2 =(ImageView) cala_karta2.findViewById(R.id.kolor);
        TextView textView2 = (TextView) cala_karta2.findViewById(R.id.numer);
        numerS = Integer.toString(druga.nr);

        switch (kolor) {
            case 1:
                image2.setImageResource(R.drawable.kier);
                dodaj_4(textView2, cala_karta2, numerS);
                textView2.setTextColor(Color.RED);
                break;
            case 2:
                image2.setImageResource(R.drawable.serce);
                dodaj_4(textView2, cala_karta2, numerS);
                textView2.setTextColor(Color.RED);
                break;
            case 3:
                image2.setImageResource(R.drawable.pik);
                dodaj_4(textView2, cala_karta2, numerS);
                textView2.setTextColor(Color.BLACK);
                break;
            case 4:
                image2.setImageResource(R.drawable.trefl);
                dodaj_4(textView2, cala_karta2, numerS);
                textView2.setTextColor(Color.BLACK);
                break;
        }
        numer = druga.nr;
        if (druga.nr == 14) suma_L3 = suma_L3 + 11;
        else if (druga.nr == 11 || druga.nr == 12 || druga.nr == 13) suma_L3 = suma_L3 + 10;
        else suma_L3 = suma_L3 + numer;
        if (mam_as_L3 > 0 && suma_L3 > 21) {
            suma_L3 = suma_L3 - 10;
            mam_as_L3 = mam_as_L3 - 1;
        }

        suma2 = (TextView) findViewById(R.id.suma2);
        suma2.setText(Integer.toString(suma_L3));

    }
    }

    public void rysuj_rozdwojenie_1(){
        int numer;
        int kolor = talia.talia.get(0).kolor;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout cala_karta = (RelativeLayout) inflater.inflate(R.layout.karty, null);
        cala_karta.setPadding(10,0,0,0);
        ImageView image =(ImageView) cala_karta.findViewById(R.id.kolor);
        TextView textView = (TextView) cala_karta.findViewById(R.id.numer);
        String numerS = Integer.toString(talia.talia.get(0).nr);

        switch (kolor){
            case 1:
                image.setImageResource(R.drawable.kier);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 2:
                image.setImageResource(R.drawable.serce);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 3:
                image.setImageResource(R.drawable.pik);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
            case 4:
                image.setImageResource(R.drawable.trefl);
                dodaj_3(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
        }
        numer = talia.talia.get(0).nr;
        if(talia.talia.get(0).nr == 14) suma = suma+11;
        else if(talia.talia.get(0).nr ==11 || talia.talia.get(0).nr==12 || talia.talia.get(0).nr ==13) suma = suma + 10;
        else suma = suma + numer;
        if(mam_as >0 && suma > 21) {
            suma = suma - 10;
            mam_as = mam_as - 1;
        }
        Karta karta = talia.talia.get(0);
        talia.talia.remove(karta);
        talia.talia.add(51, karta);
        suma_kart.setText(Integer.toString(suma));
    }

    public void rysuj_rozdwojenie_2(){
        int numer;
        int kolor = talia.talia.get(0).kolor;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout cala_karta = (RelativeLayout) inflater.inflate(R.layout.karty, null);
        cala_karta.setPadding(10,0,0,0);
        ImageView image =(ImageView) cala_karta.findViewById(R.id.kolor);
        TextView textView = (TextView) cala_karta.findViewById(R.id.numer);
        String numerS = Integer.toString(talia.talia.get(0).nr);

        switch (kolor){
            case 1:
                image.setImageResource(R.drawable.kier);
                dodaj_4(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 2:
                image.setImageResource(R.drawable.serce);
                dodaj_4(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 3:
                image.setImageResource(R.drawable.pik);
                dodaj_4(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
            case 4:
                image.setImageResource(R.drawable.trefl);
                dodaj_4(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
        }
        numer = talia.talia.get(0).nr;
        if(talia.talia.get(0).nr == 14) suma_L3 = suma_L3+11;
        else if(talia.talia.get(0).nr ==11 || talia.talia.get(0).nr==12 || talia.talia.get(0).nr ==13) suma_L3 = suma_L3 + 10;
        else suma_L3 = suma_L3 + numer;
        if(mam_as_L3 >0 && suma_L3 > 21) {
            suma_L3 = suma_L3 - 10;
            mam_as_L3 = mam_as_L3 - 1;
        }

        Karta karta = talia.talia.get(0);
        talia.talia.remove(karta);
        talia.talia.add(51, karta);
        suma2.setText(Integer.toString(suma_L3));
    }

    public void obstaw(final View view) {
        kasa = (TextView) findViewById(R.id.kasa);
        if (kasa.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "WYBIERZ KWOTE DO ZAKLADU", Toast.LENGTH_SHORT).show();
            return;
        }
        bbudzet = bbudzet - kaasa;
        budzet = (TextView) findViewById(R.id.budzet);
        budzet.setText(Integer.toString(bbudzet));
        kasa.setText("");

        mam_as = 0;
        przeciwnik_ma_as = 0;
        rozstrzygniete = false;
        rozdwojenie = 0;

        osX = 0;
        osX2 = 155;
        osXP = 0;
        osX2P = 155;
        osXL3 = -150;
        osXL32 =40;
        osXL3_ = 150;
        osXL3_2 = 340;
        suma = 0;
        suma_przeciwnika = 0;
        ile_kart = 0;
        ile_kart_przeciwnika = 0;
        suma_L3 = 0;
        setContentView(R.layout.activity_zaklad);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);

        za_ile = (TextView) findViewById(R.id.za_ile);
        za_ile.setText(Integer.toString(kaasa));

        budzet = (TextView) findViewById(R.id.bbudzet);
        budzet.setText(Integer.toString(bbudzet));

        cardLine1 = (LinearLayout) findViewById(R.id.cardline1);
        cardLine2 = (LinearLayout) findViewById(R.id.cardline2);
        cardLine3 = (LinearLayout) findViewById(R.id.cardline3);
        cardLine4 = (LinearLayout) findViewById(R.id.cardline4);

        zaklad = kaasa;
        kaasa = 0;


        rysuj_karte();
        rysuj_karte();
        rysuj_karte_przeciwnika();
        rysuj_karte_przeciwnika();

        if (suma == 21) {
            nie(view);
        }

    }

    public void rysuj_karte_przeciwnika(){
        Karta karta_przeciwnika = talia.talia.get(0);
        int numer_przeciwnika;
        int kolor_przeciwnika = talia.talia.get(0).kolor;

        String numer_przeciwnikaS = Integer.toString(talia.talia.get(0).nr);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout cala_karta = (RelativeLayout) inflater.inflate(R.layout.karty, null);
        cala_karta.setPadding(10,0,0,0);
        ImageView image =(ImageView) cala_karta.findViewById(R.id.kolor);
        TextView textView = (TextView) cala_karta.findViewById(R.id.numer);

        switch(ile_kart_przeciwnika){
            case 0:
                karta_odwrocona = talia.talia.get(0);
                image_odwr = (ImageView) cala_karta.findViewById(R.id.kolor);
                textView_odwr = (TextView) cala_karta.findViewById(R.id.numer);
                image_odwr.setImageResource(R.drawable.odwrocona);
                dodaj_2(textView_odwr, cala_karta, "");
                numer_przeciwnika = talia.talia.get(0).nr;
                if(kolor_przeciwnika == 1 || kolor_przeciwnika == 2)
                    textView_odwr.setTextColor(Color.RED);
                else
                    textView_odwr.setTextColor(Color.BLACK);
                break;
            default:
                switch (kolor_przeciwnika){
                    case 1:
                        image.setImageResource(R.drawable.kier);
                        dodaj_2(textView, cala_karta, numer_przeciwnikaS);
                        textView.setTextColor(Color.RED);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.serce);
                         dodaj_2(textView, cala_karta, numer_przeciwnikaS);
                         textView.setTextColor(Color.RED);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.pik);
                        dodaj_2(textView, cala_karta, numer_przeciwnikaS);
                         textView.setTextColor(Color.BLACK);
                        break;
                    case 4:
                        image.setImageResource(R.drawable.trefl);
                        dodaj_2(textView, cala_karta, numer_przeciwnikaS);
                        textView.setTextColor(Color.BLACK);
                        break;
                }
                numer_przeciwnika = talia.talia.get(0).nr;
        }
        talia.talia.remove(karta_przeciwnika);
        talia.talia.add(51, karta_przeciwnika);

//        osXP = osXP + 70;
//        osX2P = osX2P + 70;
        ile_kart_przeciwnika = ile_kart_przeciwnika + 1;
        if(karta_przeciwnika.nr == 14) suma_przeciwnika = suma_przeciwnika + 11;
        else if(karta_przeciwnika.nr == 11 || karta_przeciwnika.nr==12 || karta_przeciwnika.nr == 13) suma_przeciwnika = suma_przeciwnika + 10;
        else suma_przeciwnika = suma_przeciwnika + numer_przeciwnika;
        if(przeciwnik_ma_as >0 && suma_przeciwnika > 21){
            suma_przeciwnika = suma_przeciwnika - 10;
            przeciwnik_ma_as = przeciwnik_ma_as - 1;
        }
    }
    Karta pierwsza;
    Karta druga;

    public void rysuj_karte(){
        if(ile_kart==0){
            pierwsza = talia.talia.get(0);

        }
        if(ile_kart==1) {
            druga = talia.talia.get(0);
            if (pierwsza.nr == druga.nr || (pierwsza.nr == 10 || pierwsza.nr == 11 || pierwsza.nr == 12 || pierwsza.nr == 13) && (druga.nr == 10 || druga.nr == 11 || druga.nr == 12 || druga.nr == 13)) {
                Button rozd = (Button) findViewById(R.id.rozdwojenie);
                rozd.setVisibility(View.VISIBLE);
            }
        }
        Karta karta = talia.talia.get(0);
        int numer;
        int kolor = talia.talia.get(0).kolor;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout cala_karta = (RelativeLayout) inflater.inflate(R.layout.karty, null);
        cala_karta.setPadding(10,0,0,0);
        ImageView image =(ImageView) cala_karta.findViewById(R.id.kolor);
        TextView textView = (TextView) cala_karta.findViewById(R.id.numer);
        String numerS = Integer.toString(talia.talia.get(0).nr);

        switch (kolor){
            case 1:
                image.setImageResource(R.drawable.kier);
                dodaj_1(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 2:
                image.setImageResource(R.drawable.serce);
                dodaj_1(textView, cala_karta, numerS);
                textView.setTextColor(Color.RED);
                break;
            case 3:
                image.setImageResource(R.drawable.pik);
                dodaj_1(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
            case 4:
                image.setImageResource(R.drawable.trefl);
                dodaj_1(textView, cala_karta, numerS);
                textView.setTextColor(Color.BLACK);
                break;
        }
        numer = talia.talia.get(0).nr;
        talia.talia.remove(karta);
        talia.talia.add(51, karta);
        ile_kart = ile_kart + 1;
        if(karta.nr == 14) suma = suma+11;
        else if(karta.nr ==11 || karta.nr==12 || karta.nr ==13) suma = suma + 10;
        else suma = suma + numer;
        if(mam_as >0 && suma > 21) {
            suma = suma - 10;
            mam_as = mam_as - 1;
        }
        if(suma < 21 && bbudzet >= zaklad){
            Button podw = (Button) findViewById(R.id.podwojenie);
            podw.setVisibility(View.VISIBLE);
        }
        suma_kart = (TextView) findViewById(R.id.suma);
        suma_kart.setText(Integer.toString(suma));
      }


    public void dobierz(final View view) {
        if(rozstrzygniete == true){
            return;
        }
        if(rozdwojenie==2) {
            if (suma_L3 < 21)
                rysuj_rozdwojenie_2();
            if (suma_L3 >= 21) {
                if (suma > 21) {
                    komunikat_1("FURA", view);
                }
                if (suma_L3 > 21) {
                    komunikat_2("FURA", view);
                }
                if (suma <= 21) {
                    while (suma_przeciwnika <= 17) {
                        rysuj_karte_przeciwnika();
                    }
                    rozstrz(suma, view);
                    rozstrz(suma_L3, view);
                    return;
                } else if (suma_L3 == 21) {
                    while (suma_przeciwnika <= 17) {
                        rysuj_karte_przeciwnika();
                    }
                    rozstrz(suma, view);
                    rozstrz(suma_L3, view);
                    return;
                }
            }
        }
        else if(rozdwojenie==1){
            if (suma < 21)
                rysuj_rozdwojenie_1();
            if (suma >= 21) {
                rozdwojenie = 2;
            }
        }

        else {
            if (suma < 21)
                rysuj_karte();
            if (suma > 21) {
                komunikat("FURA", view);
            } else if (suma == 21) {
                while (suma_przeciwnika <= 17) {
                    rysuj_karte_przeciwnika();
                }
                rozstrz(suma, view);
            }
        }
    }

    public void nie(final View view){
        if(rozstrzygniete == true){
            return;
        }
        if(rozdwojenie == 2){
            while (suma_przeciwnika <= 17) {
                rysuj_karte_przeciwnika();
            }
            rozstrz(suma, view);
            rozstrz(suma_L3, view);
            return;
        }
        else if(rozdwojenie == 1){
            rozdwojenie = 2;
            return;
        }
        while (suma_przeciwnika <= 17) {
            rysuj_karte_przeciwnika();
        }

        rozstrz(suma, view);
    }
    ImageButton zwr;
    int budzet_temp;
    public void jeden(View v){
        zwr = (ImageButton) findViewById(R.id.zwroc);
        zwr.setVisibility(View.VISIBLE);
        if(kaasa+1 <= bbudzet) {
            budzet_temp = bbudzet - kaasa;
            kasa = (TextView) findViewById(R.id.kasa);
            kaasa = kaasa + 1;
            kasa.setText(Integer.toString(kaasa));
            budzet_temp = budzet_temp - 1;
            budzet = (TextView) findViewById(R.id.budzet);
            budzet.setText(Integer.toString(budzet_temp));
        }
    }

    public void piec(View v){
        zwr = (ImageButton) findViewById(R.id.zwroc);
        zwr.setVisibility(View.VISIBLE);
        if(kaasa+5 <= bbudzet) {
            budzet_temp = bbudzet - kaasa;
            kasa = (TextView) findViewById(R.id.kasa);
            kaasa = kaasa + 5;
            kasa.setText(Integer.toString(kaasa));
            budzet_temp = budzet_temp - 5;
            budzet = (TextView) findViewById(R.id.budzet);
            budzet.setText(Integer.toString(budzet_temp));
        }
    }

    public void piecdziesiat(View v){
        zwr = (ImageButton) findViewById(R.id.zwroc);
        zwr.setVisibility(View.VISIBLE);
        if(kaasa+50 <= bbudzet) {
            budzet_temp = bbudzet - kaasa;
            kasa = (TextView) findViewById(R.id.kasa);
            kaasa = kaasa + 50;
            kasa.setText(Integer.toString(kaasa));
            budzet_temp = budzet_temp - 50;
            budzet = (TextView) findViewById(R.id.budzet);
            budzet.setText(Integer.toString(budzet_temp));
        }
    }

    public void  sto(View v){
        zwr = (ImageButton) findViewById(R.id.zwroc);
        zwr.setVisibility(View.VISIBLE);
        if(kaasa+100 <= bbudzet) {
            budzet_temp = bbudzet - kaasa;
            kasa = (TextView) findViewById(R.id.kasa);
            kaasa = kaasa + 100;
            kasa.setText(Integer.toString(kaasa));
            budzet_temp = budzet_temp - 100;
            budzet = (TextView) findViewById(R.id.budzet);
            budzet.setText(Integer.toString(budzet_temp));
        }
    }

    public void tysiac(View v){
        zwr = (ImageButton) findViewById(R.id.zwroc);
        zwr.setVisibility(View.VISIBLE);
        if(kaasa+1000 <= bbudzet) {
            budzet_temp = bbudzet - kaasa;
            kasa = (TextView) findViewById(R.id.kasa);
            kaasa = kaasa + 1000;
            kasa.setText(Integer.toString(kaasa));
            budzet_temp = budzet_temp - 1000;
            budzet = (TextView) findViewById(R.id.budzet);
            budzet.setText(Integer.toString(budzet_temp));
        }
    }

    public void timer(final View view){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.obstawianie);
                        budzet = (TextView) findViewById(R.id.budzet);
                        budzet.setText(Integer.toString(bbudzet));
                    }
                });
            }
        };
        timer.schedule(timerTask, 6 * 1000);
    }

    public void ustaw(){
        switch (karta_odwrocona.kolor){
            case 1:
                image_odwr.setImageResource(R.drawable.kier);
                break;
            case 2:
                image_odwr.setImageResource(R.drawable.serce);
                break;
            case 3:
                image_odwr.setImageResource(R.drawable.pik);
                break;
            case 4:
                image_odwr.setImageResource(R.drawable.trefl);
                break;
        }

        switch (karta_odwrocona.nr) {
            case 11:
                textView_odwr.setText("J");
                break;
            case 12:
                textView_odwr.setText("D");
                break;
            case 13:
                textView_odwr.setText("K");
                break;
            case 14:
                textView_odwr.setText("A");
                break;
            default:
                textView_odwr.setText(Integer.toString(karta_odwrocona.nr));
        }

        suma_kart_przeciwnika = (TextView) findViewById(R.id.suma_przeciwnika);
        suma_kart_przeciwnika.setText(Integer.toString(suma_przeciwnika));

        budzet = (TextView) findViewById(R.id.bbudzet);
        budzet.setText(Integer.toString(bbudzet));
        za_ile = (TextView) findViewById(R.id.za_ile);
        //za_ile.setText("");
    }

    public void dodaj_2(TextView textView, RelativeLayout cala_karta, String nr){
//        cardLine2.addView(image);
//        image.setX(osXP);
//        image.setY(0);
        int numer = talia.talia.get(0).nr;
        if(nr!="") {
            switch (numer) {
                case 11:
                    textView.setText("J");
                    break;
                case 12:
                    textView.setText("D");
                    break;
                case 13:
                    textView.setText("K");
                    break;
                case 14:
                    przeciwnik_ma_as = przeciwnik_ma_as + 1;
                    textView.setText("A");
                    break;
                default:
                    textView.setText(nr);
            }
        }
        else
            textView.setText(nr);
        if(ile_kart_przeciwnika<5)
        cardLine2.addView(cala_karta);
        else
            cardLine4.addView(cala_karta);
//        textView.setX(osX2P);
//        textView.setTextSize(20);
//        textView.setY(0);
    }

    public void dodaj_1(TextView textView, RelativeLayout cala_karta, String nr){
        //cardLine1.addView(image);
//        image.setX(osX);
//        image.setY(0);
        int numer = Integer.parseInt(nr);
        switch(numer){
            case 11:
                textView.setText("J");
                break;
            case 12:
                textView.setText("D");
                break;
            case 13:
                textView.setText("K");
                break;
            case 14:
                mam_as = mam_as + 1;
                textView.setText("A");
                break;
            default:
                textView.setText(nr);
        }
        //cardLine1.addView(textView);
        if(ile_kart<4)
        cardLine1.addView(cala_karta);
        else
            cardLine3.addView(cala_karta);
//        textView.setX(osX2);
//        textView.setTextSize(20);
//        textView.setY(0);
    }

    public void dodaj_3(TextView textView, RelativeLayout cala_karta, String nr){
//        cardLine3.addView(image);
//        image.setX(osXL3);
//        image.setY(0);
        int numer = Integer.parseInt(nr);
        switch(numer){
            case 11:
                textView.setText("J");
                break;
            case 12:
                textView.setText("D");
                break;
            case 13:
                textView.setText("K");
                break;
            case 14:
                mam_as = mam_as + 1;
                textView.setText("A");
                break;
            default:
                textView.setText(nr);
        }
        cardLine1.addView(cala_karta);
//        textView.setX(osXL32);
//        textView.setTextSize(10);
//        textView.setY(0);
    }

    public void dodaj_4(TextView textView, RelativeLayout cala_karta, String nr){
//        cardLine3.addView(image);
//        image.setX(osXL3_);
//        image.setY(0);
        int numer = Integer.parseInt(nr);
        switch(numer){
            case 11:
                textView.setText("J");
                break;
            case 12:
                textView.setText("D");
                break;
            case 13:
                textView.setText("K");
                break;
            case 14:
                mam_as = mam_as + 1;
                textView.setText("A");
                break;
            default:
                textView.setText(nr);
        }
        cardLine3.addView(cala_karta);
    }

    public void rozstrz(int s, View view){
        if(rozdwojenie!=0){
        if (s > 21) {
            if(s==suma) komunikat_1("FURA", view);
            else komunikat_2("FURA", view);
        }
        else if(s==21){
            if(suma_przeciwnika==21){
                if(s==suma) komunikat_1("REMIS", view);
                else komunikat_2("REMIS", view);
                bbudzet = bbudzet + zaklad/2;
            }
            else{
                if(s==suma) komunikat_1("BLACK JACK, WYGRYWASZ", view);
                else komunikat_2("BLACK JACK, WYGRYWASZ", view);
                bbudzet = bbudzet +zaklad;
            }
        }
        else if(s<21) {
            if (suma_przeciwnika > 21) {
                if(s==suma) komunikat_1("WYGRYWASZ", view);
                else komunikat_2("WYGRYWASZ", view);
                bbudzet = bbudzet + zaklad;
            } else if (suma_przeciwnika < 21) {
                if (s > suma_przeciwnika) {
                    if(s==suma) komunikat_1("WYGRYWASZ", view);
                    else komunikat_2("WYGRYWASZ", view);
                    bbudzet = bbudzet + zaklad;
                } else if (s < suma_przeciwnika) {
                    if(s==suma) komunikat_1("PRZEGRYWASZ", view);
                    else komunikat_2("PRZEGRYWASZ", view);
                }
                if (s == suma_przeciwnika) {
                    if(s==suma) komunikat_1("REMIS", view);
                    else komunikat_2("REMIS", view);
                    bbudzet = bbudzet + zaklad/2;
                }
            } else if (suma_przeciwnika == 21) {
                if(s==suma) komunikat_1("BLACK JACK, PRZEGRYWASZ", view);
                else komunikat_2("BLACK JACK, PRZEGRYWASZ", view);
            }
        }
        }
        else{
            if (s > 21) {
                komunikat("FURA", view);
            }
            else if(s==21){
                if(suma_przeciwnika==21){
                    komunikat("REMIS", view);
                    bbudzet = bbudzet + zaklad;
                }
                else{
                    komunikat("BLACK JACK, WYGRYWASZ", view);
                    bbudzet = bbudzet + zaklad*2;
                }
            }
            else if(s<21) {
                if (suma_przeciwnika > 21) {
                    komunikat("WYGRYWASZ", view);
                    bbudzet = bbudzet + zaklad * 2;
                } else if (suma_przeciwnika < 21) {
                    if (s > suma_przeciwnika) {
                        komunikat("WYGRYWASZ", view);
                        bbudzet = bbudzet + zaklad * 2;
                    } else if (s < suma_przeciwnika) {
                        komunikat("PRZEGRYWASZ", view);
                    }
                    if (s == suma_przeciwnika) {
                        komunikat("REMIS", view);
                            bbudzet = bbudzet + zaklad;
                    }
                } else if (suma_przeciwnika == 21) {
                    komunikat("BLACK JACK, PRZEGRYWASZ", view);
                }
            }
        }
    }

    public void zwroc(View view){
        zwr = (ImageButton) findViewById(R.id.zwroc);
        zwr.setVisibility(View.INVISIBLE);
        kaasa = 0;
        bbudzet = bbudzet + kaasa;
        kasa = (TextView) findViewById(R.id.kasa);
        kasa.setText(Integer.toString(kaasa));
        budzet = (TextView) findViewById(R.id.budzet);
        budzet.setText(Integer.toString(bbudzet));
    }

    public void komunikat(String wiadomosc,final View view){
        Button podw = (Button) findViewById(R.id.podwojenie);
        podw.setVisibility(View.GONE);
        Button rozd = (Button) findViewById(R.id.rozdwojenie);
        rozd.setVisibility(View.GONE);
        Button ni = (Button) findViewById(R.id.nie);
        ni.setVisibility(View.GONE);
        Button dob = (Button) findViewById(R.id.dobierz);
        dob.setVisibility(View.GONE);

        TextView textView = (TextView) findViewById(R.id.komunikat);
        textView.setBackgroundResource(R.drawable.green3);
        textView.setText(wiadomosc);

        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setContentView(R.layout.obstawianie);
                budzet = (TextView) findViewById(R.id.budzet);
                budzet.setText(Integer.toString(bbudzet));
            }
        });
        rozstrzygniete = true;
        ustaw();
        timer(view);
    }

    public void komunikat_1(String wiadomosc, final View view){
        Button podw = (Button) findViewById(R.id.podwojenie);
        podw.setVisibility(View.GONE);
        Button rozd = (Button) findViewById(R.id.rozdwojenie);
        rozd.setVisibility(View.GONE);
        Button ni = (Button) findViewById(R.id.nie);
        ni.setVisibility(View.GONE);
        Button dob = (Button) findViewById(R.id.dobierz);
        dob.setVisibility(View.GONE);

        TextView komunikat1 = (TextView) findViewById(R.id.komunikat1);
        komunikat1.setBackgroundResource(R.drawable.green3);
        komunikat1.setText(wiadomosc);

        komunikat1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setContentView(R.layout.obstawianie);
                budzet = (TextView) findViewById(R.id.budzet);
                budzet.setText(Integer.toString(bbudzet));
            }
        });
        rozstrzygniete = true;
        ustaw();
        timer(view);
    }

    public void komunikat_2(String wiadomosc, final View view) {
        Button podw = (Button) findViewById(R.id.podwojenie);
        podw.setVisibility(View.GONE);
        Button rozd = (Button) findViewById(R.id.rozdwojenie);
        rozd.setVisibility(View.GONE);
        Button ni = (Button) findViewById(R.id.nie);
        ni.setVisibility(View.GONE);
        Button dob = (Button) findViewById(R.id.dobierz);
        dob.setVisibility(View.GONE);

        TextView komunikat2 = (TextView) findViewById(R.id.komunikat2);
        komunikat2.setBackgroundResource(R.drawable.green3);
        komunikat2.setText(wiadomosc);

        komunikat2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setContentView(R.layout.obstawianie);
                budzet = (TextView) findViewById(R.id.budzet);
                budzet.setText(Integer.toString(bbudzet));
            }
        });
        rozstrzygniete = true;
        ustaw();
        timer(view);
    }
}
