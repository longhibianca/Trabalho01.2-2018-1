package br.edu.iff.pooa20181.trabalho012_2018_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }
}
