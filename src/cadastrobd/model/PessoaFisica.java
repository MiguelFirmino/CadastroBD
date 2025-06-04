/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Miguel
 */
public class PessoaFisica extends Pessoa {
    protected String cpf;
    
    public PessoaFisica(int id, 
            String nome, 
            String logradouro,
            String cidade, 
            String estado,
            String telefone,
            String email,
            String cpf) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }
    
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Override
    public void exibir() {
        System.out.println("""
                           ------------------------
                           Id: %d
                           Nome: %s
                           Logradouro: %s
                           Cidade: %s
                           Estado: %s
                           Telefone: %s
                           Email: %s
                           CPF: %s
                           ------------------------
                           """.formatted(
                                   id,
                                   nome,
                                   logradouro,
                                   cidade,
                                   estado,
                                   telefone,
                                   email,
                                   cpf
                           ));
    }
}
