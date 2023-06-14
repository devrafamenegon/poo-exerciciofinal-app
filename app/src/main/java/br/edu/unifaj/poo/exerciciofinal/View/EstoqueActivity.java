package br.edu.unifaj.poo.exerciciofinal.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.List;

import br.edu.unifaj.poo.exerciciofinal.Contract.EstoqueContract;
import br.edu.unifaj.poo.exerciciofinal.Model.Produto;
import br.edu.unifaj.poo.exerciciofinal.Presenter.EstoquePresenter;
import br.edu.unifaj.poo.exerciciofinal.R;

public class EstoqueActivity extends AppCompatActivity implements EstoqueContract.View {

    private EstoqueContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        presenter = new EstoquePresenter(this, this);

        Button fecharBtn = findViewById(R.id.estoque_fechar_button);
        fecharBtn.setOnClickListener(v -> fechar());

        carregarEstoque();
    }

    private void fechar() {
        finish();
    }

    private void carregarEstoque() {
        try {
            String login = getIntent().getStringExtra("login");
            String ip = getIntent().getStringExtra("ip");
            presenter.carregarEstoque(login, ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mostrarProdutos(List<Produto> produtos) {
        ListView listView = findViewById(R.id.estoque_listView);
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_checked,
                produtos
        );

        listView.setAdapter(adapter);
    }

    @Override
    public void navigateToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void limparTextoEstoque() {
        TextView textoEstoque = getTextoEstoque();
        textoEstoque.setText("");
    }

    @Override
    public void mostrarTextoEstoqueVazio() {
        TextView textoEstoque = getTextoEstoque();
        textoEstoque.setText("Estoque vazio!");
    }

    @Override
    public void mostrarTextoEstoqueErro() {
        TextView textoEstoque = getTextoEstoque();
        textoEstoque.setText("Erro ao buscar estoque!");
    }

    @Override
    public void showMessage(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public TextView getTextoEstoque() {
        TextView textoEstoque = findViewById(R.id.estoque_auxiliar_txtView);
        return textoEstoque;
    }

}
