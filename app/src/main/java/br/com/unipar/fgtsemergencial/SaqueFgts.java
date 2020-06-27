package br.com.unipar.fgtsemergencial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SaqueFgts extends AppCompatActivity {

    // Classe carrega xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saquefgts);

        setaValores();
    }

    // Classe seta valores textView
    public void setaValores(){

        // Recebe valor intent
        Bundle b = getIntent().getExtras();
        String primeiroBeneficio = b.getString("dataPrimeiroPagamento");
        String segundoBeneficio = b.getString("dataSegundoPagamento");
        String terceiroBeneficio = b.getString("dataTerceiroPagamento");

        //seta valores xml
        TextView textPrimeiroPagamento = (TextView) findViewById(R.id.primeiraparcela);
        textPrimeiroPagamento.setText("Primeira \n"+primeiroBeneficio);

        TextView textSegundoPagamento = (TextView) findViewById(R.id.segundaparcela);
        textSegundoPagamento.setText("Segunda \n"+segundoBeneficio);

        TextView textTerceiroPagamento= (TextView) findViewById(R.id.terceiraparcela);
        textTerceiroPagamento.setText("Terceira \n"+terceiroBeneficio);

    }
}
