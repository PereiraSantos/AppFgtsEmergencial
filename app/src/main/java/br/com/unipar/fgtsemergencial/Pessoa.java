package br.com.unipar.fgtsemergencial;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import static java.util.Calendar.*;

public class Pessoa {

    private String cpf;
    private Date dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Calcula a Idade
    public int calculaIdade(Date dataNasc){

        Calendar dataUsuario = new GregorianCalendar();
        dataUsuario.setTime(dataNasc);

        // Cria um objeto calendar com a data atual
        Calendar today = getInstance();

        // Obtém a idade baseado no ano
        int idade = today.get(YEAR) - dataUsuario.get(YEAR);
        dataUsuario.add(YEAR, idade);

        return idade;
    }

    // converte data string para date
    public Date converterData(String data){

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dataConvertida = null;
        try {
            dataConvertida = format.parse(String.valueOf(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataConvertida;
    }

    // calcula pagamento fgts
    public String calculaPagamento(Date data, int p){

        // Através do Calendar, trabalhamos a data informada e adicionamos 1 dia nela
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(data);

        // Cria um objeto calendar com a data atual
        Calendar today = Calendar.getInstance();

        // recupera ano mes e dia
        int ano = today.get(Calendar.YEAR);
        int mesInt = dateOfBirth.get(Calendar.MONDAY) + 1;
        int diaInt = dateOfBirth.get(DAY_OF_MONTH);

        // cindicao para acrescentar mes
        if(p == 1){
            mesInt = mesInt + 1;
        }
        if(p == 2){
            mesInt = mesInt + 2;
        }
        if(p == 3){
            mesInt = mesInt + 3;
        }

        // condicao para comparar os dias
        if(diaInt >= 1 && diaInt <= 10){
            diaInt = 5;
        }if(diaInt >= 11 && diaInt <= 20){
            diaInt = 10;
        }if(diaInt >= 21 && diaInt <= 31){
            diaInt = 15;
        }

        //condicao para mes,se for maior que 12 os messes muda o ano
        if(mesInt > 12){
            int mesSobra = mesInt - 12;
            mesInt = 0;
            ano = ano + 1;
            mesInt = mesInt + mesSobra;
        }

        // converte mes e dia para string
        String mes = Integer.toString(mesInt);
        String dia = Integer.toString(diaInt);

        String dataPagamento = "";

        // condicao para dia, se for nemor que 9 acresentar 0
        // monta a data DD/MM/AAAA;
        if(Integer.parseInt(dia) <= 9){
            dataPagamento += "0";
        }

        dataPagamento += dia;
        dataPagamento += "/";

        // condicao para mes, se fornemor que 9 acresenta 0;
        if(Integer.parseInt(mes) <= 9){
            dataPagamento += "0";
        }

        dataPagamento += mes;
        dataPagamento += "/";
        dataPagamento += ano;

        return  dataPagamento;
    }
}
