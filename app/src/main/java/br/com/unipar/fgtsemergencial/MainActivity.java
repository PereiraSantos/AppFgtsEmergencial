package br.com.unipar.fgtsemergencial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Variaveis globais
    private EditText cpfText, dataNascimentoText;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Entrada dados tela do xml
        cpfText = findViewById(R.id.cpf);
        dataNascimentoText = findViewById(R.id.datadatanascimento);

        // coloca mascara nos campos
        cpfText.addTextChangedListener(new MaskTextWatcher(cpfText, new SimpleMaskFormatter("NNN.NNN.NNN-NN")));
        dataNascimentoText.addTextChangedListener(new MaskTextWatcher(dataNascimentoText, new SimpleMaskFormatter("NN/NN/NNNN")));

    }

    // validar entrada de dados
    // Verificar variaveis e seta mensagens
    public  void validar(View view){

        if(cpfText.getText().toString().equals("")){
            menssage("Campo Cpf Vazio!");
        }
        else if(dataNascimentoText.getText().toString().equals("")){
            menssage("Campo data Nascimento esta Vazio!");
        }
       else {

            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(cpfText.getText().toString());
            pessoa.setDataNascimento(pessoa.converterData(dataNascimentoText.getText().toString()));

            if(pessoa.calculaIdade(pessoa.getDataNascimento()) <= 18 ){
                menssage("Voçẽ não tem direito ao FGTS");
            }else{
                segundaTela(view, pessoa);
            }
        }
    }

    //Classe prepara para ir para segunda tela
    public void segundaTela(View view, Pessoa pessoa){
        //Formata dados com 3 casas decimais
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(3);

        //Prepara a trasferancia de dados
        Intent intent = new Intent(this,SaqueFgts.class);
        Bundle b = new Bundle();

        b.putString("dataPrimeiroPagamento", pessoa.calculaPagamento(pessoa.getDataNascimento(), 1));
        b.putString("dataSegundoPagamento", pessoa.calculaPagamento(pessoa.getDataNascimento(),2));
        b.putString("dataTerceiroPagamento", pessoa.calculaPagamento(pessoa.getDataNascimento(),3));

        intent.putExtras(b);
        startActivity(intent);

    }

    // Menssagens de alerta na tela
    public void menssage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // finaliza aplicaçao
    public void sair(View view){
        this.finish();
    }
}