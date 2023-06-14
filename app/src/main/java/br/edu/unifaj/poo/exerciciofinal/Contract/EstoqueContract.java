package br.edu.unifaj.poo.exerciciofinal.Contract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.edu.unifaj.poo.exerciciofinal.Model.Produto;

public interface EstoqueContract {
    interface View {
        void mostrarProdutos(List<Produto> produtos);
        void showMessage(String mensagem);
        void navigateToLoginScreen();

        void limparTextoEstoque();

        void mostrarTextoEstoqueVazio();

        void mostrarTextoEstoqueErro();
    }

    interface Presenter {
        void carregarEstoque(String login, String ip);
        Produto converterProdutoJson(JSONObject jsonObj) throws JSONException;
    }
}
