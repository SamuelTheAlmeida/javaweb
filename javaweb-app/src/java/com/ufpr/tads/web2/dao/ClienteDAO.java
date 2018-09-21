/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SAMUEL
 */
public class ClienteDAO {
    public List<Cliente> listarClientes() {
        ArrayList <Cliente> clientes = new ArrayList<Cliente>();
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM tb_cliente";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setCpf(rs.getString(2));
                cliente.setNome(rs.getString(3));
                cliente.setEmail(rs.getString(4));
                cliente.setData(rs.getDate(5));
                cliente.setRua(rs.getString(6));
                cliente.setNumero(rs.getInt(7));
                cliente.setCep(rs.getString(8));
                cliente.setCidade(rs.getString(9));
                cliente.setUf(rs.getString(10));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
    
    
    public boolean inserirCliente(Cliente cliente) {
        boolean result = false;
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO tb_cliente(cpf_cliente, nome_cliente, email_cliente, rua_cliente, "
                    + "nr_cliente, cep_cliente, cidade_cliente, uf_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getRua());
            st.setInt(5, cliente.getNumero());
            st.setString(6, cliente.getCep());
            st.setString(7, cliente.getCidade());
            st.setString(8, cliente.getUf());
            st.execute();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    } 
    
     public Cliente consultarCliente(int id) {
        Cliente cliente = null;
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM tb_cliente WHERE id_cliente = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setCpf(rs.getString(2));
                cliente.setNome(rs.getString(3));
                cliente.setEmail(rs.getString(4));
                cliente.setData(rs.getDate(5));
                cliente.setRua(rs.getString(6));
                cliente.setNumero(rs.getInt(7));
                cliente.setCep(rs.getString(8));
                cliente.setCidade(rs.getString(9));
                cliente.setUf(rs.getString(10));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cliente;
    } 
     
    public boolean removerCliente(int id) {
        boolean result = false;
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "DELETE FROM tb_cliente WHERE id_cliente = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public boolean alterarCliente(Cliente cliente) {
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE tb_cliente SET cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, "
                    + "rua_cliente = ?, nr_cliente = ?, cep_cliente = ?, cidade_cliente = ?, "
                    + "uf_cliente = ? WHERE id_cliente = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getRua());
            st.setInt(5, cliente.getNumero());
            st.setString(6, cliente.getCep());
            st.setString(7, cliente.getCidade());
            st.setString(8, cliente.getUf());
            st.setInt(9, cliente.getId());
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;  
        }
    }
    
}
