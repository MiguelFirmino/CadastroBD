/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class SequenceManager {
    public static long getValue(String nomeSequence) {
        String sql = "SELECT NEXT VALUE FOR " + nomeSequence;
        long value = 0;
        
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conexao = ConectorBD.getConnection();
            ps = ConectorBD.getPrepared(sql);
            rs = ConectorBD.getSelected(ps);
            
            if (rs.next()) {
                value = rs.getLong(1);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(ps);
            ConectorBD.close(conexao);
        }
        
        return value;
    }
}
