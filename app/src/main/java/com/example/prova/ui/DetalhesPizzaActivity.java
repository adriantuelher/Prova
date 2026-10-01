package com.example.prova.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prova.R;
import com.example.prova.model.Pizza;

import java.util.Locale;

public class DetalhesPizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pizza);

        ImageView imgPizza = findViewById(R.id.imgDetalhePizza);
        TextView txtNome = findViewById(R.id.txtDetalheNome);
        TextView txtPreco = findViewById(R.id.txtDetalhePreco);
        TextView txtIngredientes = findViewById(R.id.txtDetalheIngredientes);
        TextView txtTempoPreparo = findViewById(R.id.txtTempoPreparo);
        Button btnVoltar = findViewById(R.id.btnVoltar);
        Button btnConfirmar = findViewById(R.id.btnConfirmarPedido);

        Pizza pizza = (Pizza) getIntent().getSerializableExtra("EXTRA_PIZZA");

        if (pizza != null) {
            imgPizza.setImageResource(pizza.getImagemResId());
            txtNome.setText(pizza.getNome());
            txtPreco.setText(String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", pizza.getPreco()));
            txtIngredientes.setText(pizza.getIngredientes());
            txtTempoPreparo.setText("⏱ Tempo estimado: " + pizza.getTempoPreparo());
        }

        btnVoltar.setOnClickListener(v -> finish());

        btnConfirmar.setOnClickListener(v -> {
            if (pizza != null) {
                Toast.makeText(this, "Pedido de " + pizza.getNome() + " enviado para a cozinha!", Toast.LENGTH_LONG).show();
            }
            finish();
        });
    }
}