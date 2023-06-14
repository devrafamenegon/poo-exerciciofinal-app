package br.edu.unifaj.poo.exerciciofinal.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.edu.unifaj.poo.exerciciofinal.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button cancelarBtn = findViewById(R.id.cadastro_cancelar_button);
        cancelarBtn.setOnClickListener(v -> navegarTelaLogin());

        Button cadastrarBtn = findViewById(R.id.cadastro_cadastrar_button);
        cadastrarBtn.setOnClickListener(v -> cadastrar());
    }

    private void navegarTelaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void cadastrar() {
        EditText edtTextLogin = findViewById(R.id.cadastro_login_edtText);
        EditText edtTextPassword = findViewById(R.id.cadastro_senha_edtText);
        EditText edtTextIp = findViewById(R.id.cadastro_ip_edtText);

        String login = edtTextLogin.getText().toString();
        String password = edtTextPassword.getText().toString();
        String ip = edtTextIp.getText().toString();

        String API_URL = "http://" + ip + ":8081/cadastrarLogin";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject request = new JSONObject();
        try {
            request.put("login", login);
            request.put("senha", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest =
            new JsonObjectRequest(Request.Method.POST, API_URL, request,
                response -> {
                    try {
                        String status = response.getString("status");
                        String error = response.optString("error");

                        if (status.equals("OK")) {
                            Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                            navegarTelaLogin();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Erro: " + error, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    try {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 409) {
                            Toast.makeText(CadastroActivity.this, "Usuário já existe!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CadastroActivity.this, "Erro na requisição", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }

}
