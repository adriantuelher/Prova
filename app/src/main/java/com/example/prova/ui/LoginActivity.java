package com.example.prova.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prova.R;

public class LoginActivity extends AppCompatActivity {

    // Constantes para persistência local
    private static final String PREF_NAME = "pizzaria_prefs";
    private static final String KEY_USER_NAME = "user_name";

    // Elementos de interface e persistência
    private EditText edtNome;
    private Button btnEntrar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializa o SharedPreferences em modo privado
        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Auto-login: avança direto se já houver sessão salva
        String nomeSalvo = preferences.getString(KEY_USER_NAME, null);
        if (nomeSalvo != null && !nomeSalvo.isEmpty()) {
            irParaMain(nomeSalvo);
            return;
        }

        setContentView(R.layout.activity_login);

        // Mapeamento dos componentes do layout
        edtNome = findViewById(R.id.edtNome);
        btnEntrar = findViewById(R.id.btnEntrar);

        // Ação do botão de entrada
        btnEntrar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString().trim();

            // Valida se o campo não está vazio
            if (!TextUtils.isEmpty(nome)) {
                // Grava o nome no SharedPreferences
                preferences.edit().putString(KEY_USER_NAME, nome).apply();
                irParaMain(nome);
            } else {
                Toast.makeText(LoginActivity.this, "Por favor, digite seu nome!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Transição de tela passando o nome do usuário via Intent
    private void irParaMain(String nome) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("EXTRA_NOME_USUARIO", nome);
        startActivity(intent);
        // Finaliza o login para não voltar na pilha ao pressionar voltar
        finish();
    }
}