package com.example.gerenciamento_notas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextIdade, editTextDisciplina, editTextNota1, editTextNota2;
    private TextView textViewErro, textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota1 = findViewById(R.id.editTextNota1);
        editTextNota2 = findViewById(R.id.editTextNota2);


        Button botaoEnviar = findViewById(R.id.botaoEnviar);
        Button botaoLimpar = findViewById(R.id.botaoLimpar);

        textViewErro = findViewById(R.id.textViewErro);
        textViewResultado = findViewById(R.id.textViewResultado);


        botaoEnviar.setOnClickListener(v -> manipularEnvio());
        botaoLimpar.setOnClickListener(v -> limparFormulario());
    }

    private void manipularEnvio() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String idadeStr = editTextIdade.getText().toString().trim();
        String disciplina = editTextDisciplina.getText().toString().trim();
        String nota1Str = editTextNota1.getText().toString().trim();
        String nota2Str = editTextNota2.getText().toString().trim();

        textViewErro.setVisibility(View.GONE);
        textViewResultado.setVisibility(View.GONE);

        // Validações
        if (nome.isEmpty()) {
            textViewErro.setText("O campo de nome está vazio.");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textViewErro.setText("O email é inválido.");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        if (idadeStr.isEmpty()) {
            textViewErro.setText("O campo de idade está vazio.");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeStr);
            if (idade <= 0) {
                textViewErro.setText("A idade deve ser um número positivo.");
                textViewErro.setVisibility(View.VISIBLE);
                return;
            }
        } catch (NumberFormatException e) {
            textViewErro.setText("A idade deve ser um número válido.");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        if (disciplina.isEmpty()) {
            textViewErro.setText("O campo de disciplina está vazio.");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        float nota1, nota2;
        try {
            nota1 = Float.parseFloat(nota1Str);
            if (nota1 < 0 || nota1 > 10) {
                textViewErro.setText("Nota 1 deve estar entre 0 e 10.");
                textViewErro.setVisibility(View.VISIBLE);
                return;
            }
        } catch (NumberFormatException e) {
            textViewErro.setText("Nota 1 deve ser um número válido.");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        try {
            nota2 = Float.parseFloat(nota2Str);
            if (nota2 < 0 || nota2 > 10) {
                textViewErro.setText("Nota 2 deve estar entre 0 e 10.");
                textViewErro.setVisibility(View.VISIBLE);
                return;
            }
        } catch (NumberFormatException e) {
            textViewErro.setText("Nota 2 deve ser um número válido.");
            textViewErro.setVisibility(View.VISIBLE);
            return;
        }

        // Cálculo da média
        float media = (nota1 + nota2) / 2;
        String resultado = String.format(
                "Nome: %s\nEmail: %s\nIdade: %d\nDisciplina: %s\nNota 1: %.2f\nNota 2: %.2f\nMédia: %.2f\n",
                nome, email, idade, disciplina, nota1, nota2, media);
        resultado += media >= 6 ? "Status: Aprovado" : "Status: Reprovado";

        textViewResultado.setText(resultado);
        textViewResultado.setVisibility(View.VISIBLE);
    }


    private void limparFormulario() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextIdade.setText("");
        editTextDisciplina.setText("");
        editTextNota1.setText("");
        editTextNota2.setText("");
        textViewErro.setVisibility(View.GONE);
        textViewResultado.setVisibility(View.GONE);
    }
}
