package com.example.prova.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova.R;
import com.example.prova.adapter.PizzaAdapter;
import com.example.prova.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Chaves de acesso ao SharedPreferences
    private static final String PREF_NAME = "pizzaria_prefs";
    private static final String KEY_USER_NAME = "user_name";

    // Componentes visuais e de persistência
    private TextView txtBoasVindas;
    private ImageView btnSair;
    private RecyclerView recyclerPizzas;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajuste de margens para a barra de estado e navegação (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialização do SharedPreferences e Views
        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        txtBoasVindas = findViewById(R.id.txtBoasVindas);
        btnSair = findViewById(R.id.btnSair);
        recyclerPizzas = findViewById(R.id.recyclerPizzas);

        // Recupera o nome de utilizador via Intent ou da sessão guardada
        String nome = getIntent().getStringExtra("EXTRA_NOME_USUARIO");
        if (nome == null || nome.isEmpty()) {
            nome = preferences.getString(KEY_USER_NAME, "CLIENTE");
        }
        txtBoasVindas.setText("BEM-VINDO, " + nome.toUpperCase());

        // Encerramento de sessão: limpa preferências e regressa ao Login
        btnSair.setOnClickListener(v -> {
            preferences.edit().clear().apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Inicializa o cardápio
        configurarRecyclerView();
    }

    // Configura o RecyclerView com LayoutManager e Adapter
    private void configurarRecyclerView() {
        List<Pizza> pizzas = criarListaDePizzas();

        recyclerPizzas.setLayoutManager(new LinearLayoutManager(this));
        PizzaAdapter adapter = new PizzaAdapter(pizzas, new PizzaAdapter.OnPizzaClickListener() {
            @Override
            public void onPizzaClick(Pizza pizza) {
                // Abre a tela de detalhes enviando a pizza selecionada
                Intent intent = new Intent(MainActivity.this, DetalhesPizzaActivity.class);
                intent.putExtra("EXTRA_PIZZA", pizza);
                startActivity(intent);
            }

            @Override
            public void onSolicitarClick(Pizza pizza) {
                // Ação rápida ao carregar no botão Solicitar
                Toast.makeText(MainActivity.this, "Pedido de " + pizza.getNome() + " solicitado!", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerPizzas.setAdapter(adapter);
    }

    // Catálogo de dados combinado com a lista mínima de pizzas
    private List<Pizza> criarListaDePizzas() {
        List<Pizza> lista = new ArrayList<>();

        lista.add(new Pizza(1, "Pizza Pepperoni", "Pepperoni fatiado, mussarela e molho especial de tomate", 39.90, R.drawable.pizza1, "25-35 min"));
        lista.add(new Pizza(2, "Pizza Margherita", "Tomate fresco, manjericão e mussarela de búfala", 35.90, R.drawable.pizza2, "20-30 min"));
        lista.add(new Pizza(3, "Pizza Calabresa", "Calabresa artesanal, cebola roxa e mussarela", 37.50, R.drawable.pizza3, "25-30 min"));
        lista.add(new Pizza(4, "Pizza Quatro Queijos", "Mussarela, provolone, gorgonzola e catupiry", 44.00, R.drawable.pizza4, "30-40 min"));
        lista.add(new Pizza(5, "Pizza Frango c/ Catupiry", "Peito de frango desfiado, catupiry e milho verde", 41.90, R.drawable.pizza5, "25-35 min"));

        return lista;
    }
}