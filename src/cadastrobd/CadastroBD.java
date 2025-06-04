/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import cadastrobd.model.Pessoa;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;

/**
 *
 * @author Miguel
 */
public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    
    private static Scanner scanner = null;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        boolean estaRodando = true;
        
        while (estaRodando) {
            int escolha = tratarEscolha();
            
            switch (escolha) {
                case 1: { // Incluir Pessoa
                    System.out.println("Insira o nome da pessoa: ");
                    String nome = scanner.nextLine();
                    
                    System.out.println("Insira o logradouro da pessoa: ");
                    String logradouro = scanner.nextLine();
                    
                    System.out.println("Insira a cidade da pessoa: ");
                    String cidade = scanner.nextLine();
                    
                    System.out.println("Insira o estado da pessoa: ");
                    String estado = scanner.nextLine();
                    
                    System.out.println("Insira o telefone da pessoa: ");
                    String telefone = scanner.nextLine();
                    
                    System.out.println("Insira o email da pessoa: ");
                    String email = scanner.nextLine();
                    
                    String tipoPessoa = tratarTipoPessoa();
                    if (tipoPessoa.equals("F")) {
                        System.out.println("Insira o cpf da pessoa: ");
                        String cpf = scanner.nextLine();
                        
                        PessoaFisica novaPessoa = new PessoaFisica(
                                0, 
                                nome,
                                logradouro,
                                cidade,
                                estado,
                                telefone,
                                email,
                                cpf
                        );
                        System.out.println("Inserindo pessoa física...");
                        PessoaFisicaDAO.incluir(novaPessoa);
                        System.out.println("Pessoa inserida com sucesso!");
                        
                    } else if (tipoPessoa.equals("J")) {
                        System.out.println("Insira o cnpj da pessoa: ");
                        String cnpj = scanner.nextLine();
                        
                        PessoaJuridica novaPessoa = new PessoaJuridica(
                                0, 
                                nome,
                                logradouro,
                                cidade,
                                estado,
                                telefone,
                                email,
                                cnpj
                        );
                        System.out.println("Inserindo pessoa jurídica...");
                        PessoaJuridicaDAO.incluir(novaPessoa);
                        System.out.println("Pessoa inserida com sucesso!");
                    }
                    break;
                }
                case 2: { // Alterar Pessoa
                    String tipoPessoa = tratarTipoPessoa();
                    
                    System.out.println("Insira o Id da Pessoa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (tipoPessoa.equals("F")) {
                        System.out.println("Dados atuais: ");
                        PessoaFisica pessoa = PessoaFisicaDAO.getPessoa(id);
                        pessoa.exibir();
                        
                        System.out.println("Insira o nome da pessoa: ");
                        pessoa.setNome(scanner.nextLine());
                    
                        System.out.println("Insira o logradouro da pessoa: ");
                        pessoa.setLogradouro(scanner.nextLine());
                    
                        System.out.println("Insira a cidade da pessoa: ");
                        pessoa.setCidade(scanner.nextLine());
                    
                        System.out.println("Insira o estado da pessoa: ");
                        pessoa.setEstado(scanner.nextLine());
                    
                        System.out.println("Insira o telefone da pessoa: ");
                        pessoa.setTelefone(scanner.nextLine());
                    
                        System.out.println("Insira o email da pessoa: ");
                        pessoa.setEmail(scanner.nextLine());
                        
                        System.out.println("Insira o cpf da pessoa: ");
                        pessoa.setCpf(scanner.nextLine());
                        
                        // registrar novos valores
                        System.out.println("Alterando pessoa física...");
                        PessoaFisicaDAO.alterar(pessoa);
                        System.out.println("Pessoa alterada com sucesso!");
                    } else if (tipoPessoa.equals("J")) {
                        System.out.println("Dados atuais: ");
                        PessoaJuridica pessoa = PessoaJuridicaDAO.getPessoa(id);
                        pessoa.exibir();
                        
                        System.out.println("Insira o nome da pessoa: ");
                        pessoa.setNome(scanner.nextLine());
                    
                        System.out.println("Insira o logradouro da pessoa: ");
                        pessoa.setLogradouro(scanner.nextLine());
                    
                        System.out.println("Insira a cidade da pessoa: ");
                        pessoa.setCidade(scanner.nextLine());
                    
                        System.out.println("Insira o estado da pessoa: ");
                        pessoa.setEstado(scanner.nextLine());
                    
                        System.out.println("Insira o telefone da pessoa: ");
                        pessoa.setTelefone(scanner.nextLine());
                    
                        System.out.println("Insira o email da pessoa: ");
                        pessoa.setEmail(scanner.nextLine());
                        
                        System.out.println("Insira o cnpj da pessoa: ");
                        pessoa.setCnpj(scanner.nextLine());
                        
                        // registrar novos valores
                        System.out.println("Alterando pessoa jurídica...");
                        PessoaJuridicaDAO.alterar(pessoa);
                        System.out.println("Pessoa alterada com sucesso!");
                    }
                    break;
                }
                case 3: { // Exluir Pessoa
                    String tipoPessoa = tratarTipoPessoa();
                    
                    System.out.println("Insira Id da pessoa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (tipoPessoa.equals("F")) {
                        PessoaFisica pessoa = PessoaFisicaDAO.getPessoa(id);
                        
                        System.out.println("Exluindo pessoa jurídica...");
                        PessoaFisicaDAO.excluir(pessoa);
                        System.out.println("Pessoa excluída com sucesso!");
                    } else if (tipoPessoa.equals("J")) {
                        PessoaJuridica pessoa = PessoaJuridicaDAO.getPessoa(id);
                        
                        System.out.println("Exluindo pessoa jurídica...");
                        PessoaJuridicaDAO.excluir(pessoa);
                        System.out.println("Pessoa excluída com sucesso!");
                    }
                    break;
                }
                case 4: { // Buscar Pelo Id
                    String tipoPessoa = tratarTipoPessoa();
                    
                    System.out.println("Insira Id da pessoa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    Pessoa pessoa = null;
                    
                    if (tipoPessoa.equals("F")) {
                        pessoa = PessoaFisicaDAO.getPessoa(id);
                    } else if (tipoPessoa.equals("J")) {
                        pessoa = PessoaJuridicaDAO.getPessoa(id);
                    }
                    
                    if (pessoa != null) {
                        System.out.println("Buscando pessoa...");
                        System.out.println("Dados de %s: "
                                .formatted(pessoa.getNome()));
                        pessoa.exibir();
                    } else {
                        System.out.println("Id %d não existente."
                                .formatted(id));
                    }
                    break;
                }
                case 5: { // Exibir Todos
                    String tipoPessoa = tratarTipoPessoa();
                    
                    if (tipoPessoa.equals("F")) {
                        List<PessoaFisica> pessoasFisicas = PessoaFisicaDAO.getPessoas();

                        pessoasFisicas.stream().forEach(x -> {
                            System.out.println("Pessoa Física:");
                            x.exibir();
                        });
                    } else if (tipoPessoa.equals("J")) {
                        List<PessoaJuridica> pessoasJuridicas = PessoaJuridicaDAO.getPessoas();

                        pessoasJuridicas.stream().forEach(x -> {
                            System.out.println("Pessoa Jurídica:");
                            x.exibir();
                        });
                    }
                    break;
                }
                case 0: { // Finalizar Programa
                    System.out.println("Programa Finalizado.");
                    estaRodando = false;
                    break;
                }
            }
        }
        
        scanner.close();
    }

    public static String tratarTipoPessoa() {
        // método copiado da prática anterior
        String tipoPessoa;
        
        System.out.println("Escolha o tipo de pessoa: ");
        while(true) {
            System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
            tipoPessoa = scanner.nextLine();
            
            if (tipoPessoa.equals("F") || tipoPessoa.equals("J")) {
                break;
            }
            
            System.out.println("Escolha uma opção válida(F ou J): ");
        }
        
        return tipoPessoa;
    }
    
    public static int tratarEscolha() {
        int escolha;
        
        // Opções do Usuário
        System.out.println("""
        ========================
        1 - Incluir Pessoa
        2 - Alterar Pessoa
        3 - Excluir Pessoa
        4 - Buscar pelo Id
        5 - Exibir Todos
        0 - Finalizar Programa
        ========================
        Escolha uma Opção: 
        """);
        while(true) {
            try {
                escolha = scanner.nextInt();
                scanner.nextLine();
                
                if (escolha > 5) {
                    System.out.println("Insira um número válido(0 a 5): ");
                    continue;
                }
                
                break;
            } catch(InputMismatchException  e) {
                System.out.println("Insira um número inteiro: ");
            }
        }
        
        return escolha;
    }
}
