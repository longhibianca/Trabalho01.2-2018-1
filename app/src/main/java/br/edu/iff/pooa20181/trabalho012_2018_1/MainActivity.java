package br.edu.iff.pooa20181.trabalho012_2018_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

            }
        });
    }

    public double calcularHoraExtra()
    {
        String cargo;
        String horas, hora, minuto;
        double horaExtra = 0.0;
        double horaExtraPorMin;
        double qtdHrExtra;
        int posicao;

        cargo = edtCargo.getText().toString();
        cargo = cargo.toLowerCase();
        cargo = cargo.trim();

        qtdHrExtra = Double.parseDouble(edtHorasExtras.getText().toString());

        if(cargo.equals("gerente")) {
            horaExtra = (2000 / 240) * 2;
        }
        else if (cargo.equals("supervisor"))
        {
            horaExtra = (900 / 240) * 2;
        }
        else if(cargo.equals("servente"))
        {
            horaExtra = (300 / 240) * 2;
        }
        horaExtraPorMin = horaExtra/60;

        horas = String.valueOf(qtdHrExtra);
        posicao = horas.indexOf(".");
        minuto = horas.substring(posicao+1);
        hora = horas.substring(0,posicao-1);

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
}


