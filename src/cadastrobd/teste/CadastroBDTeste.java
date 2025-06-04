/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.teste;

import java.util.ArrayList;
import java.util.List;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;

/**
 *
 * @author Miguel
 */
public class CadastroBDTeste {
    
    public static void main(String[] args) {
        // Instanciar uma pessoa física e persistir no banco de dados.
        PessoaFisica novaPessoaFisica = new PessoaFisica(
                0, // o id será reescrito
                "Margaret", 
                "Rua E, 555",
                "Cidade E",
                "E1",
                "55955555555",
                 "margaret@email.com",
                "55555555555"
        );
        
        PessoaFisicaDAO.incluir(novaPessoaFisica);
        
        // Alterar os dados da pessoa física no banco.
        novaPessoaFisica.setNome("Juliana");
        
        PessoaFisicaDAO.alterar(novaPessoaFisica);
        
        // Consultar todas as pessoas físicas do banco de dados e listá-las.
        List<PessoaFisica> pessoasFisicas = PessoaFisicaDAO.getPessoas();
        
        pessoasFisicas.stream().forEach(x -> {
            System.out.println("Pessoa Física:");
            x.exibir();
        });
        
        // Excluir a pessoa física criada anteriormente no banco.
        PessoaFisicaDAO.excluir(novaPessoaFisica);
        
        // Instanciar uma pessoa jurídica e persistir no banco de dados.
        PessoaJuridica novaPessoaJuridica = new PessoaJuridica(
                0, // o id será reescrito
                "Alypio", 
                "Rua F, 666",
                "Cidade F",
                "F1",
                "66966666666",
                 "alypio@email.com",
                "66666666666666"
        );
        PessoaJuridicaDAO.incluir(novaPessoaJuridica);
        
        // Alterar os dados da pessoa jurídica no banco.
        novaPessoaJuridica.setNome("Marcos");
        PessoaJuridicaDAO.alterar(novaPessoaJuridica);
        
        // Consultar todas as pessoas jurídicas do banco de dados e listá-las.
        List<PessoaJuridica> pessoasJuridicas = PessoaJuridicaDAO.getPessoas();
        
        pessoasJuridicas.stream().forEach(x -> {
            System.out.println("Pessoa Jurídica:");
            x.exibir();
        });
        
        // Excluir a pessoa jurídica criada anteriormente no banco.
        PessoaJuridicaDAO.excluir(novaPessoaJuridica);
    }
}
