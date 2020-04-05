package com.example.tpi2_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbDolar;
    private RadioButton rbPesoArgentino;
    private EditText etDolar;
    private EditText etPesoArgentino;
    private EditText etRespuesta;


    private MainViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convertidor);

        iniciarVista();
        vm = ViewModelProviders.of(this).get(MainViewModel.class);
        vm.getRbDolar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                rbDolar.setChecked(aBoolean);
                etDolar.setEnabled(aBoolean);
                etDolar.setText("");
                etRespuesta.setText("");
                etPesoArgentino.setEnabled(!aBoolean);
            }
        });
        vm.getRbPesoArgentino().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                rbPesoArgentino.setChecked(aBoolean);
                etPesoArgentino.setEnabled(aBoolean);
                etPesoArgentino.setText("");
                etRespuesta.setText("");
                etDolar.setEnabled(!aBoolean);
            }
        });
        vm.getResultado().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etRespuesta.setText(s);
            }
        });
    }

    public void iniciarVista(){
        rbDolar = findViewById(R.id.rbDolar);
        rbPesoArgentino = findViewById(R.id.rbPesosArgentinos);
        etDolar = findViewById(R.id.etDolares);
        etPesoArgentino = findViewById(R.id.etPesosArgentinos);
        etRespuesta = findViewById(R.id.etResultado);
    }

    public void activarRbDolar(View view) {
        vm.cambiarEstadoRbPesoArgentino();
    }

    public void activarRbPesoArgentino(View view){
        vm.cambiarEstadoRbDolar();
    }

    public void convertir(View view){
        if (etDolar.getText().toString().length() == 0 && etPesoArgentino.getText().toString().length() == 0) {
            Toast.makeText(this, "Ingrese un valor", Toast.LENGTH_LONG).show();
        } else {
            if (rbDolar.isChecked() && Double.parseDouble(etDolar.getText().toString()) > 0){
                vm.cambiar(etDolar.getText().toString(), "1");
            }
            else if (rbPesoArgentino.isChecked() && Double.parseDouble(etPesoArgentino.getText().toString()) > 0) {
                vm.cambiar(etPesoArgentino.getText().toString(), "2");
            }
            else {
                Toast.makeText(this, "Ingrese numero mayor que cero", Toast.LENGTH_LONG).show();
            }
        }
    }
}
