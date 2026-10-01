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

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_NAME = "pizzaria_prefs";
    private static final String KEY_USER_NAME = "user_name";

    private EditText edtNome;
    private Button btnEntrar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Se já salvou nome antes, vai direto para a MainActivity
        String nomeSalvo = preferences.getString(KEY_USER_NAME, null);
        if (nomeSalvo != null && !nomeSalvo.isEmpty()) {
            irParaMain(nomeSalvo);
            return;
        }

        setContentView(R.layout.activity_login);

        edtNome = findViewById(R.id.edtNome);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString().trim();
            if (!TextUtils.isEmpty(nome)) {
                // Salva diretamente no SharedPreferences
                preferences.edit().putString(KEY_USER_NAME, nome).apply();
                irParaMain(nome);
            } else {
                Toast.makeText(LoginActivity.this, "Por favor, digite seu nome!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irParaMain(String nome) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("EXTRA_NOME_USUARIO", nome);
        startActivity(intent);
        finish();
    }
}