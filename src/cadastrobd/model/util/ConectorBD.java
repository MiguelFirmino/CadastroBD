/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Miguel
 */
public class ConectorBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "loja";
    private static final String SENHA = "loja";
    
    private static Connection conexao = null;
    
    public static Connection getConnection() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return conexao;
    }
    
    public static PreparedStatement getPrepared(String sql) {
        try {
            if (conexao != null) {
                return conexao.prepareStatement(sql);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null; 
    }
    
    public static ResultSet getSelected(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static void close(Connection con) {
        if (con != null) {
            return;
        }
        try {
            if (!con.isClosed()) {
                con.close(); 
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement ps) {
        if (ps != null) {
            return;
        }
        try {
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(ResultSet rs) {
        if (rs != null) {
            return;
        }
        try {
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
