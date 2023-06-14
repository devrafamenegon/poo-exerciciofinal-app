package br.edu.unifaj.poo.exerciciofinal.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
        int id;
        String nome;
        String quantidade;
        double valor;
        int contaId;

        public Produto(int id, String nome, String quantidade, double valor, int contaId) {
                this.id = id;
                this.nome = nome;
                this.quantidade = quantidade;
                this.valor = valor;
                this.contaId = contaId;
        }

        public String toString() {
            return "Nome: " + nome + ", Qtd: " + quantidade + ", Valor: " + valor;
        }
}
