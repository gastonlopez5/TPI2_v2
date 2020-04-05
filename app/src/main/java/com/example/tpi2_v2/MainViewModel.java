package com.example.tpi2_v2;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.DecimalFormat;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> rbDolar; //LiveData son tipos de variables que la vista observa continuamente si cambian
    private MutableLiveData<Boolean> rbPesoArgentino;
    private MutableLiveData<String> resultado;
    private MutableLiveData<String> etDolar;
    private MutableLiveData<String> etPesoArgentino;
    private double valordolar = 64.93;
    DecimalFormat df = new DecimalFormat("#.00");

    public LiveData<String> getResultado(){
        if (resultado == null){
            resultado = new MutableLiveData<>();
        }
        return resultado;
    }

    public LiveData<Boolean> getRbDolar(){
        if (rbDolar == null){
            rbDolar = new MutableLiveData<>();
        }
        return rbDolar;
    }

    public LiveData<Boolean> getRbPesoArgentino(){
        if (rbPesoArgentino == null){
            rbPesoArgentino = new MutableLiveData<>();
        }
        return rbPesoArgentino;
    }

    public void cambiarEstadoRbDolar(){
        rbDolar.setValue(false);
    }

    public void cambiarEstadoRbPesoArgentino(){
        rbPesoArgentino.setValue(false);
    }

    public void cambiar(String monto, String opcion) {

        double n, respuesta=0;
        n = Double.parseDouble(monto);

        switch (opcion){
            case "1":
            {
                respuesta = n * valordolar;
                break;
            }
            case "2":
            {
                respuesta = n / valordolar;
            }
        }

        resultado.setValue(df.format(respuesta));
    }
}