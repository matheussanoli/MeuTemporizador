package br.com.fatecpg.meutemporizador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    public TextView resultado;
    private final long interval = 1 *1000;
    EditText e;
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DANDO GET NO EDITTEXT
        e = (EditText)findViewById(R.id.segundos);
        //GET NO BOTAO
        botao = (Button)findViewById(R.id.btn);
        resultado = (TextView) this.findViewById(R.id.Resultado);
        botao.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
               //COMEÇANDO O COUTDOWNTIMER
                int timeVal = Integer.parseInt(e.getText().toString());
//FAZER O COUTDOWN EM SEGUNDOS
                timeVal = timeVal*1000;
//SE NÃO TIVER INICIADO FAZER ESSAS FUNCOES
                if (!timerHasStarted) {
                    //iniciando o metodo
                    countDownTimer = new MyCountDownTimer(timeVal, interval);
                    //setando o valor em segundos
                    resultado.setText(resultado.getText() + String.valueOf(timeVal/1000));
                    //começando o metodo
                    countDownTimer.start();
                    //setando o timer para true, para fazer a comparação no if.
                    timerHasStarted = true;
                    //setando o texto do botao para parar
                    botao.setText("PARAR");

                } else {
                    //pausa o coutdowntimer
                    countDownTimer.cancel();
                    //coloca o timer pra false, para quando clicar no botao novamente ele entrar no if de cima
                    timerHasStarted = false;
                    //seta o botao para reiniciar
                    botao.setText("REINICIAR");
                }
            }
            //metodo do temporizador
            class MyCountDownTimer extends CountDownTimer {
                public MyCountDownTimer(long timeVal, long interval) {
                    super(timeVal, interval);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    resultado.setText("" + millisUntilFinished/1000);
                }
                @Override
                public void onFinish() {
                    //quando acabar ele exibe esse texto
                    resultado.setText("Finalizou");
                    //aqui finaliza a activity
                    finish();
                }
            }
        });
    }


}