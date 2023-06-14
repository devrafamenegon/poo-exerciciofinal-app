package br.edu.unifaj.poo.exerciciofinal.Model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    String id;
    String login;
    String senha;
}
