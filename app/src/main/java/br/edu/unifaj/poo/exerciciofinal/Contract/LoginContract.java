package br.edu.unifaj.poo.exerciciofinal.Contract;

public interface LoginContract {
    interface View {
        void navigateToEstoqueScreen(String login, String ip);
        void navigateToCadastroScreen();
        void showMessage(String message);
    }

    interface Presenter {
        void login(String login, String password, String ip);

    }
}
