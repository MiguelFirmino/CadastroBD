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
public class PessoaJuridicaDAO {
    public static PessoaJuridica getPessoa(int id) {
        String sql = """
                     SELECT pessoa.*, pessoa_juridica.cnpj
                     FROM pessoa
                     INNER JOIN pessoa_juridica ON pessoa_juridica.idPessoaJuridica = pessoa.idPessoa
                     WHERE idPessoa = ?
                     """;
        
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PessoaJuridica pessoa = null;
        
        try {
            conexao = ConectorBD.getConnection();
            ps = ConectorBD.getPrepared(sql);
            ps.setInt(1, id);
            
            rs = ConectorBD.getSelected(ps);
            if (rs.next()) { // se o query obter resultado
                PessoaJuridica pessoaObtida = new PessoaJuridica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
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
    
    public static List<PessoaJuridica> getPessoas() {
        String sql = """
                     SELECT pessoa.*, pessoa_juridica.cnpj
                     FROM pessoa
                     INNER JOIN pessoa_juridica ON pessoa_juridica.idPessoaJuridica = pessoa.idPessoa
                     """;
        
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PessoaJuridica> pessoas = new ArrayList<>();
        
        try {
            conexao = ConectorBD.getConnection();
            ps = ConectorBD.getPrepared(sql);
            
            rs = ConectorBD.getSelected(ps);     
            while (rs.next()) {
                PessoaJuridica pessoa = new PessoaJuridica(
                        rs.getInt("idPessoa"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
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
    
    public static void incluir(PessoaJuridica pessoa) {
        long novoId = SequenceManager.getValue("sPessoa"); 
        pessoa.setId((int)novoId); // atribue o valor de id conforme a sequence
        
        String sqlPessoa = """
                           INSERT INTO pessoa
                           VALUES (?, ?, ?, ?, ?, ?, ?)
                           """;
        
        String sqlPessoaJuridica = """
                                 INSERT INTO pessoa_juridica
                                 VALUES (?, ?)
                                 """;
        
        Connection conexao = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;
        
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
            
            psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica);
            psPessoaJuridica.setInt(1, pessoa.id);
            psPessoaJuridica.setString(2, pessoa.cnpj);
            psPessoaJuridica.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaJuridica);
            ConectorBD.close(conexao);
        }
    }
    
    public static void alterar(PessoaJuridica pessoa) {
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
        
        String sqlPessoaJuridica = """
                                 UPDATE pessoa_juridica
                                 SET cnpj = ?
                                 WHERE idPessoaJuridica = ?
                                 """;
        
        Connection conexao = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;
        
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
            
            psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica);
            psPessoaJuridica.setString(1, pessoa.cnpj);
            psPessoaJuridica.setInt(2, pessoa.id);
            psPessoaJuridica.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaJuridica);
            ConectorBD.close(conexao);
        }
    }
    
    public static void excluir(PessoaJuridica pessoa) {
        String sqlPessoa = """
                     DELETE FROM pessoa
                     WHERE idPessoa = ? 
                     """;
        
        String sqlPessoaJuridica = """
                     DELETE FROM pessoa_juridica
                     WHERE idPessoaJuridica = ?
                     """;
        
        Connection conexao = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;
        
        try {
            conexao = ConectorBD.getConnection();
            
            psPessoa = ConectorBD.getPrepared(sqlPessoa);
            psPessoa.setInt(1, pessoa.id);
            psPessoa.executeUpdate();
            
            psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica);
            psPessoaJuridica.setInt(1, pessoa.id);
            psPessoaJuridica.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaJuridica);
            ConectorBD.close(conexao);
        }
    }
}
