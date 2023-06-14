package br.edu.unifaj.poo.exerciciofinal.Presenter;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.edu.unifaj.poo.exerciciofinal.Contract.EstoqueContract;
import br.edu.unifaj.poo.exerciciofinal.Contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void login(String login, String password, String ip) {
        String API_URL = "http://" + ip + ":8081/login";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

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
                            view.showMessage("Logado com sucesso!");
                            view.navigateToEstoqueScreen(login, ip);
                        } else {
                            view.showMessage("Erro: " + error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    try {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 400) {
                            view.showMessage("Usuário e/ou Senha inválidos!");
                        } else {
                            view.showMessage("Erro na requisição");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            ) {
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
