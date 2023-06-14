package br.edu.unifaj.poo.exerciciofinal.Presenter;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unifaj.poo.exerciciofinal.Contract.EstoqueContract;
import br.edu.unifaj.poo.exerciciofinal.Model.Produto;

public class EstoquePresenter implements EstoqueContract.Presenter {

    private final EstoqueContract.View view;
    private final Context context;

    public EstoquePresenter(EstoqueContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public Produto converterProdutoJson(JSONObject jsonObj) throws JSONException {
        int id = jsonObj.getInt("id");
        int contaId = jsonObj.getInt("conta_id");
        String nome = jsonObj.getString("nome");
        String quantidade = jsonObj.getString("quantidade");
        Double valor = jsonObj.getDouble("valor");
        return new Produto(id, nome, quantidade, valor, contaId);
    }

    @Override
    public void carregarEstoque(String login, String ip) {
        try {
            String API_URL = "http://" + ip + ":8081/produto/conta/login/" + login;

            RequestQueue requestQueue = Volley.newRequestQueue(context);

            JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, API_URL, null,
                    response -> {
                        try {
                            String status = response.getString("status");
                            JSONArray produtosJsonArray = response.getJSONArray("produtos");
                            String error = response.optString("error");

                            List<Produto> produtos = new ArrayList();

                            for (int i = 0; i < produtosJsonArray.length(); i++) {
                                Produto produtoConvertido = converterProdutoJson(produtosJsonArray.getJSONObject(i));
                                produtos.add(produtoConvertido);
                            }

                            if (status.equals("OK")) {
                                view.showMessage( "Produtos do usuário encontrados!");
                                view.mostrarProdutos(produtos);
                                view.limparTextoEstoque();

                                if (produtos.size() == 0) {
                                    view.mostrarTextoEstoqueVazio();
                                }
                            } else {
                                view.showMessage( "Erro: " + error);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        try {
                            NetworkResponse networkResponse = error.networkResponse;
                            if (networkResponse != null && networkResponse.statusCode == 400) {
                                view.showMessage( "Usuário não encontrado!");
                                view.mostrarTextoEstoqueErro();
                            } else {
                                view.showMessage("Erro na requisição");
                                view.mostrarTextoEstoqueErro();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}