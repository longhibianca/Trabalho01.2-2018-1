package br.edu.iff.pooa20181.trabalho012_2018_1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    EditText edtCargo;
    EditText edtHorasExtras;
    EditText edtNumFaltas;
    EditText edtNumFilhos;
    Button btnCalcular;
    TextView proventos;
    TextView descontos;
    TextView salLiquido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCargo = (EditText) findViewById(R.id.edtCargo);
        edtHorasExtras = (EditText) findViewById(R.id.edtHoraExtra);
        edtNumFaltas = (EditText) findViewById(R.id.edtNFaltas);
        edtNumFilhos = (EditText) findViewById(R.id.edtNFilhos);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        proventos = (TextView) findViewById(R.id.totalProventos);
        descontos = (TextView) findViewById(R.id.descontos);
        salLiquido = (TextView) findViewById(R.id.salLiquido);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edtCargo.getText().toString().isEmpty()) || (edtHorasExtras.getText().toString().isEmpty()) ||
                        (edtNumFaltas.getText().toString().isEmpty()) || (edtNumFilhos.getText().toString().isEmpty()))
                {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setMessage("Os campos não podem estar em branco");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
                else
                {
                    calculos();
                }
            }
        });
    }

    public double calcularHoraExtra()
    {
        String cargo;
        String horas, hora, minuto;
        double horaExtra = 0.00000000;
        double horaExtraPorMin;
        double qtdHrExtra;
        int posicao;

        cargo = edtCargo.getText().toString();
        cargo = cargo.toLowerCase();
        cargo = cargo.trim();

        qtdHrExtra = Double.parseDouble(edtHorasExtras.getText().toString());

        if(cargo.equals("gerente")) {
            horaExtra = (2000.00*2)/240;
        }
        else if (cargo.equals("supervisor"))
        {
            horaExtra = (900.00 / 240) * 2;
        }
        else if(cargo.equals("servente"))
        {
            horaExtra = (300.00 / 240) * 2;
        }

        horaExtraPorMin = horaExtra/60;

        horas = String.valueOf(qtdHrExtra);
        posicao = horas.indexOf(".");
        minuto = horas.substring(posicao+1);

        hora = horas.substring(0,posicao);

        qtdHrExtra = (60 * Integer.parseInt(hora)) + Integer.parseInt(minuto);

        return horaExtraPorMin * qtdHrExtra;
    }
    public double calcularPrecoDaFalta()
    {
        String cargo;
        int numFaltas;
        double precoFalta = 0.0;

        cargo = edtCargo.getText().toString();
        cargo = cargo.toLowerCase();
        cargo = cargo.trim();

        numFaltas = Integer.parseInt(edtNumFaltas.getText().toString());

        if(cargo.equals("gerente")) {
            precoFalta = 2000/30;
        }
        else if (cargo.equals("supervisor"))
        {
            precoFalta = 900/30;
        }
        else if(cargo.equals("servente"))
        {
            precoFalta = 300/30;
        }
        return precoFalta * numFaltas;
    }

    public double precoFilhos()
    {
        String cargo;
        int qtdFilhos;
        double precoFilho = 0.0;

        cargo = edtCargo.getText().toString();
        cargo = cargo.toLowerCase();
        cargo = cargo.trim();

        qtdFilhos = Integer.parseInt(edtNumFilhos.getText().toString());

        if(cargo.equals("gerente")) {
            precoFilho = 0.03 * 2000;
        }
        else if (cargo.equals("supervisor"))
        {
            precoFilho = 0.03 * 900;
        }
        else if(cargo.equals("servente"))
        {
            precoFilho = 0.03 * 300;
        }

        return precoFilho * qtdFilhos;
    }

    public double calculaProventos()
    {
        String cargo;
        double proventos = 0.0;

        cargo = edtCargo.getText().toString();
        cargo = cargo.toLowerCase();
        cargo = cargo.trim();

        if(cargo.equals("gerente")) {
            proventos = 2000 + calcularHoraExtra() + precoFilhos();
        }
        else if (cargo.equals("supervisor"))
        {
            proventos = 900 + calcularHoraExtra() + precoFilhos();
        }
        else if(cargo.equals("servente"))
        {
            proventos = 300 + calcularHoraExtra() + precoFilhos();
        }

        return proventos;
    }

    public double calcularINSS()
    {
        return 0.10 * calculaProventos();
    }

    public double calculaDescontos()
    {
        return calcularPrecoDaFalta() + calcularINSS();
    }

    public double calcularSalarioLiquido()
    {
        return calculaProventos() - calculaDescontos();
    }

    public void calculos()
    {
        double provento, desconto, salarioLiq;
        DecimalFormat df = new DecimalFormat("###,##0.00");
        provento = calculaProventos();
        desconto = calculaDescontos();
        salarioLiq = calcularSalarioLiquido();

        proventos.setText("Proventos:" + df.format(provento));
        descontos.setText("Descontos: " + df.format(desconto));
        salLiquido.setText("Salário Líquido: " + df.format(salarioLiq));
    }
}


