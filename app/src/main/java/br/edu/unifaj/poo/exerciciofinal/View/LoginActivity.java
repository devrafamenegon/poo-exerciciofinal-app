package br.edu.unifaj.poo.exerciciofinal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.unifaj.poo.exerciciofinal.Contract.LoginContract;
import br.edu.unifaj.poo.exerciciofinal.Presenter.LoginPresenter;
import br.edu.unifaj.poo.exerciciofinal.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this, this);

        Button btnLogin = findViewById(R.id.login_login_button);
        btnLogin.setOnClickListener(v -> {
            String login = getLogin();
            String password = getPassword();
            String ip = getIp();
            presenter.login(login, password, ip);
        });

        Button btnCadastro = findViewById(R.id.login_cadastrar_button);
        btnCadastro.setOnClickListener(v -> navigateToCadastroScreen());
    }

    @Override
    public void navigateToEstoqueScreen(String login, String ip) {
        Intent intent = new Intent(this, EstoqueActivity.class);
        intent.putExtra("login", login);
        intent.putExtra("ip", ip);
        startActivity(intent);
    }

    @Override
    public void navigateToCadastroScreen() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getLogin() {
        EditText edtTxtLogin = findViewById(R.id.login_login_edtText);
        return edtTxtLogin.getText().toString();
    }

    private String getPassword() {
        EditText edtTxtPassword = findViewById(R.id.login_senha_edtText);
        return edtTxtPassword.getText().toString();
    }

    private String getIp() {
        EditText edtTxtIp = findViewById(R.id.login_ip_edtText);
        return edtTxtIp.getText().toString();
    }
}
