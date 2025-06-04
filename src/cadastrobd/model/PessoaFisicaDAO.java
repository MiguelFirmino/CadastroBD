/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class PessoaFisicaDAO {
    public static PessoaFisica getPessoa(int id) {
        String sql = """
                     SELECT pessoa.*, pessoa_fisica.cpf
                     FROM pessoa
                     INNER JOIN pessoa_fisica ON pessoa_fisica.idPessoaFisica = pessoa.idPessoa
                     WHERE idPessoa = ?
                     """;
        
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PessoaFisica pessoa = null;
        
        try {
            conexao = ConectorBD.getConnection();
            ps = ConectorBD.getPrepared(sql);
            ps.setInt(1, id);
            
            rs = ConectorBD.getSelected(ps);
            if (rs.next()) { // se o query obter resultado
                PessoaFisica pessoaObtida = new PessoaFisica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
                
                pessoa = pessoaObtida;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(ps);
            ConectorBD.close(conexao);
        }
        
        return pessoa;
    }
    
    public static List<PessoaFisica> getPessoas() {
        String sql = """
                     SELECT pessoa.*, pessoa_fisica.cpf
                     FROM pessoa
                     INNER JOIN pessoa_fisica ON pessoa_fisica.idPessoaFisica = pessoa.idPessoa
                     """;
        
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PessoaFisica> pessoas = new ArrayList<>();
        
        try {
            conexao = ConectorBD.getConnection();
            ps = ConectorBD.getPrepared(sql);
            
            rs = ConectorBD.getSelected(ps);     
            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
                
                pessoas.add(pessoa);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(ps);
            ConectorBD.close(conexao);
        }
        
        return pessoas;
    }
    
    public static void incluir(PessoaFisica pessoa) {
        long novoId = SequenceManager.getValue("sPessoa"); 
        pessoa.setId((int)novoId); // atribue o valor de id conforme a sequence
        
        String sqlPessoa = """
                           INSERT INTO pessoa
                           VALUES (?, ?, ?, ?, ?, ?, ?)
                           """;
        
        String sqlPessoaFisica = """
                                 INSERT INTO pessoa_fisica
                                 VALUES (?, ?)
                                 """;
        
        Connection conexao = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;
        
        try {
            conexao = ConectorBD.getConnection();
            
            psPessoa = ConectorBD.getPrepared(sqlPessoa);
            psPessoa.setInt(1, pessoa.id);
            psPessoa.setString(2, pessoa.nome);
            psPessoa.setString(3, pessoa.logradouro);
            psPessoa.setString(4, pessoa.cidade);
            psPessoa.setString(5, pessoa.estado);
            psPessoa.setString(6, pessoa.telefone);
            psPessoa.setString(7, pessoa.email);
            psPessoa.executeUpdate();
            
            psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica);
            psPessoaFisica.setInt(1, pessoa.id);
            psPessoaFisica.setString(2, pessoa.cpf);
            psPessoaFisica.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaFisica);
            ConectorBD.close(conexao);
        }
    }
    
    public static void alterar(PessoaFisica pessoa) {
        String sqlPessoa = """
                           UPDATE pessoa
                           SET nome = ?, 
                           logradouro = ?, 
                           cidade = ?, 
                           estado = ?, 
                           telefone = ?,
                           email = ?
                           WHERE idPessoa = ?
                           """;
        
        String sqlPessoaFisica = """
                                 UPDATE pessoa_fisica
                                 SET cpf = ?
                                 WHERE idPessoaFisica = ?
                                 """;
        
        Connection conexao = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;
        
        try {
            conexao = ConectorBD.getConnection();
            
            psPessoa = ConectorBD.getPrepared(sqlPessoa);
            psPessoa.setString(1, pessoa.nome);
            psPessoa.setString(2, pessoa.logradouro);
            psPessoa.setString(3, pessoa.cidade);
            psPessoa.setString(4, pessoa.estado);
            psPessoa.setString(5, pessoa.telefone);
            psPessoa.setString(6, pessoa.email);
            psPessoa.setInt(7, pessoa.id);
            psPessoa.executeUpdate();
            
            psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica);
            psPessoaFisica.setString(1, pessoa.cpf);
            psPessoaFisica.setInt(2, pessoa.id);
            psPessoaFisica.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaFisica);
            ConectorBD.close(conexao);
        }
    }
    
    public static void excluir(PessoaFisica pessoa) {
        String sqlPessoa = """
                     DELETE FROM pessoa
                     WHERE idPessoa = ? 
                     """;
        
        String sqlPessoaFisica = """
                     DELETE FROM pessoa_fisica
                     WHERE idPessoaFisica = ?
                     """;
        
        Connection conexao = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;
        
        try {
            conexao = ConectorBD.getConnection();
            
            psPessoa = ConectorBD.getPrepared(sqlPessoa);
            psPessoa.setInt(1, pessoa.id);
            psPessoa.executeUpdate();
            
            psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica);
            psPessoaFisica.setInt(1, pessoa.id);
            psPessoaFisica.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaFisica);
            ConectorBD.close(conexao);
        }
    }
}
