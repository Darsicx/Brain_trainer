package com.example.carlos.brain_trainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.math.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    Button principal;
    Button juegaotra;
    TextView muestramen;
    TextView muestratimer;
    TextView muestraop;
    TextView muestraresul;
    TableLayout table;
    boolean estado;
    TableRow tableRow;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    int score=0;
    int partidas=0;
    CountDownTimer contador;
    ArrayList<Integer> listi;
    int locationAnswer;
    int correctAnswer;



    public void Startgame(View view){
        principal = (Button) findViewById(R.id.boton_principal);
        juegaotra = (Button) findViewById(R.id.playagain);
        muestramen = findViewById(R.id.muestra_mensaje);
        muestratimer = findViewById(R.id.muestra_timer);
        muestraop = findViewById(R.id.muestra_operacion);
        muestraresul = findViewById(R.id.muestra_resultados);
        table = findViewById(R.id.tableLayout);

        principal.setVisibility(View.GONE);
        muestratimer.setVisibility(View.VISIBLE);
        muestraop.setVisibility(View.VISIBLE);
        muestraresul.setVisibility(View.VISIBLE);
        table.setVisibility(View.VISIBLE);

        asignaAleatorios(generaOperacion());
        estado = true;
        iniciaTimer();




    }

    public void iniciaTimer(){
        contador =new CountDownTimer(30100,1000){
            public void onTick(long millisecondsUntilDone){
                muestratimer.setText(String.valueOf(millisecondsUntilDone/1000));
            }

            @Override
            public void onFinish() {
                estado =false;
                muestramen.setText("Tu resultado fue: "+score+"/16" );
                juegaotra.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public int generaOperacion(){
        int numero1 = (int) (Math.random() * 40) + 1;
        int numero2 = (int) (Math.random() * 40) + 1;
        muestraop.setText(numero1+"+"+numero2);
        correctAnswer=numero1+numero2;
        return correctAnswer;
    }

    public void asignaAleatorios(int resultado){

        listi =new ArrayList<>();
        Random rand=new Random();
        locationAnswer=rand.nextInt(4);
        int incorrectanswer;

        for(int i=0;i<4;i++){
            if(i==locationAnswer){
                listi.add(resultado);
            }
            else {
                incorrectanswer=rand.nextInt(41);
                while (incorrectanswer == resultado){
                    incorrectanswer=rand.nextInt(41);
                }
                listi.add(incorrectanswer);

            }
        }


        b1.setText(String.valueOf(listi.get(0)));
        b2.setText(String.valueOf(listi.get(1)));
        b3.setText(String.valueOf(listi.get(2)));
        b4.setText(String.valueOf(listi.get(3)));

//
//        while (listi.size() !=0){
//
//        }
//

    }
    public void comprueba(View view){
        if(estado==true) {
            muestramen.setVisibility(View.VISIBLE);
            switch (view.getId()) {
                case R.id.button:
                    if(listi.get(0) == correctAnswer){
                        muestramen.setText("CORRECTO");
                        sumaScore(true);
                        asignaAleatorios(generaOperacion());
                        break;
                    }
                    else {
                        muestramen.setText("INCORRECTO");
                        sumaScore(false);
                        asignaAleatorios(generaOperacion());
                        break;
                    }

                case R.id.button2:
                    if(listi.get(1) == correctAnswer){
                        muestramen.setText("CORRECTO");
                        sumaScore(true);
                        asignaAleatorios(generaOperacion());
                        break;
                    }
                    else {
                        muestramen.setText("INCORRECTO");
                        sumaScore(false);
                        asignaAleatorios(generaOperacion());
                        break;
                    }
                case R.id.button3:
                    if(listi.get(2) == correctAnswer){
                        muestramen.setText("CORRECTO");
                        sumaScore(true);
                        asignaAleatorios(generaOperacion());
                        break;
                    }
                    else {
                        muestramen.setText("INCORRECTO");
                        sumaScore(false);
                        asignaAleatorios(generaOperacion());
                        break;
                    }
                case R.id.button4:
                    if(listi.get(3) == correctAnswer){
                        muestramen.setText("CORRECTO");
                        sumaScore(true);
                        asignaAleatorios(generaOperacion());
                        break;
                    }
                    else {
                        muestramen.setText("INCORRECTO");
                        sumaScore(false);
                        asignaAleatorios(generaOperacion());
                        break;
                    }
            }
        }
    }

    public void sumaScore(boolean valida){
        partidas+=1;
        if(valida==true){
            score+=1;
            muestraresul.setText(score+"/16");
        }
        if(partidas==16){
            estado=false;
            muestramen.setText("Tu resultado fue: "+score+"/16" );
            juegaotra.setVisibility(View.VISIBLE);
            contador.cancel();
        }


    }
    public void weGoAgain(View view){
        estado=true;
        Button otra=(Button) findViewById(R.id.playagain);
        otra.setVisibility(View.GONE);
        muestramen.setVisibility(View.INVISIBLE);
        asignaAleatorios(generaOperacion());
        score=0;
        partidas=0;
        muestraresul.setText("0/16");
        iniciaTimer();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button) findViewById(R.id.button);
        b2=(Button) findViewById(R.id.button2);
        b3=(Button) findViewById(R.id.button3);
        b4=(Button) findViewById(R.id.button4);

    }
}
