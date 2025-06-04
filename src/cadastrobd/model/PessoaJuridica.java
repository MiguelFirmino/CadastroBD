/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Miguel
 */
public class PessoaJuridica extends Pessoa {
    protected String cnpj;
    
    public PessoaJuridica(int id, 
            String nome, 
            String logradouro,
            String cidade, 
            String estado,
            String telefone,
            String email,
            String cnpj) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }
    
    public String getCnpj() {
        return this.cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
                           CNPJ: %s
                           ------------------------
                           """.formatted(
                                   id,
                                   nome,
                                   logradouro,
                                   cidade,
                                   estado,
                                   telefone,
                                   email,
                                   cnpj
                           ));
    }
}
