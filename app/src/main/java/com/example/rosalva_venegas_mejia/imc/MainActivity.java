package com.example.rosalva_venegas_mejia.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;


public class MainActivity extends AppCompatActivity {
    private EditText mEditPeso;
    private EditText mEditEstatura;
    private Button mButtonCalcular;
    private TextView mTextViewImc;
    private Button mButtonLimpiar;
    private TextView mTextViewClassification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditPeso = (EditText) findViewById(R.id.edit_text_peso);
        mEditEstatura = (EditText) findViewById(R.id.edit_text_estatura);
        mButtonCalcular = (Button) findViewById(R.id.button_calcular);
        mTextViewImc = (TextView) findViewById(R.id.text_view_imc);
        mTextViewClassification = (TextView) findViewById(R.id.text_view_classification);
        mButtonLimpiar = (Button) findViewById(R.id.button_limpiar);

        //ActionPerformed para el boton calcular
        mButtonCalcular.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                try {
                    String s = mEditPeso.getText().toString();
                    double peso = Double.parseDouble(s);
                    s = mEditEstatura.getText().toString();
                    double estatura = Double.parseDouble(s);
                    double imc = peso / (estatura * estatura);

                    //If para Clasificar el IMC
                    if (imc >= 30) {
                        mTextViewClassification.setText("Obesidad");
                    } else if ((imc < 30) && (imc >= 25)) {
                        mTextViewClassification.setText("Sobrepeso");
                    } else if ((imc < 24.9) && (imc >= 18.5)) {
                        mTextViewClassification.setText("Normal");
                    } else if (imc <= 18.4) {
                        mTextViewClassification.setText("Bajo Peso");
                    }
                    //Se Muestra el Resultado en el text_view_imc
                    DecimalFormat fd = new DecimalFormat("#0.###");
                    try {
                        imc = fd.parse(fd.format(imc)).doubleValue();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    mTextViewImc.setText(Double.toString(imc));
                }catch(NumberFormatException nfc){
                    mTextViewImc.setText("Solo se admiten numeros");
                    mEditEstatura.setText("");
                    mEditPeso.setText("");
                    mTextViewClassification.setText("");
                }

            }

        });
        //Se limpian todos los EditText
        //ActionPerformed para el boton Limpiar
        mButtonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditEstatura.setText("");
                mEditPeso.setText("");
                mTextViewImc.setText("0");
                mTextViewClassification.setText("");

            }
        });
    }
}