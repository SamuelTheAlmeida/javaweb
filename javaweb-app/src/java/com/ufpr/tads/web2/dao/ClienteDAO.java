/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author SAMUEL
 */
public class ClienteDAO {
    public List<Cliente> listar() {
        ArrayList <Cliente> clientes = new ArrayList<Cliente>();
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM tb_cliente C, tb_estado E, tb_cidade CD WHERE C.id_cidade = CD.id_cidade AND CD.id_estado = E.id_estado;";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                Estado estado = new Estado();
                Cidade cidade = new Cidade();
                cliente.setId(rs.getInt(1));
                cliente.setCpf(rs.getString(2));
                cliente.setNome(rs.getString(3));
                cliente.setEmail(rs.getString(4));
                cliente.setData(rs.getDate(5));
                cliente.setRua(rs.getString(6));
                cliente.setNumero(rs.getInt(7));
                cliente.setCep(rs.getString(8));
                cidade.setId(rs.getInt(9));
                estado.setId(rs.getInt(10));
                estado.setNome(rs.getString(11));
                estado.setSigla(rs.getString(12));
                cidade.setNome(rs.getString(14));
                cidade.setEstado(estado);
                cliente.setCidade(cidade);
                clientes.add(cliente);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
    
    
    public boolean inserirCliente(Cliente cliente) throws SQLException {
        boolean result = false;
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "INSERT INTO tb_cliente(cpf_cliente, nome_cliente, email_cliente, data_cliente, rua_cliente, "
                    + "nr_cliente, cep_cliente, id_cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEmail());
            st.setDate(4, new java.sql.Date(cliente.getData().getTime()) );
            System.out.println(cliente.getData());
            st.setString(5, cliente.getRua());
            st.setInt(6, cliente.getNumero());
            st.setString(7, cliente.getCep());
            st.setInt(8, cliente.getCidade().getId());
            st.execute();
            result = true;
        } catch (SQLException e) {
            throw e;
        }
        return result;
    } 
    
     public Cliente consultarCliente(int id) {
        Cliente cliente = null;
        Estado estado = null;
        Cidade cidade = null;
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "SELECT * FROM tb_cliente C, tb_cidade CD, tb_estado E WHERE id_cliente = ? AND C.id_cidade = CD.id_cidade AND CD.id_estado = E.id_estado;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
                cidade = new Cidade();
                estado = new Estado();
                cliente.setId(rs.getInt(1));
                cliente.setCpf(rs.getString(2));
                cliente.setNome(rs.getString(3));
                cliente.setEmail(rs.getString(4));
                cliente.setData(rs.getDate(5));
                cliente.setRua(rs.getString(6));
                cliente.setNumero(rs.getInt(7));
                cliente.setCep(rs.getString(8));
                cidade.setId(rs.getInt(9));
                cidade.setNome(rs.getString(11));
                estado.setId(rs.getInt(12));
                estado.setNome(rs.getString(14));
                estado.setSigla(rs.getString(15));
                cidade.setEstado(estado);
                cliente.setCidade(cidade);
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
    
    public boolean alterarCliente(Cliente cliente) throws Exception {
        Connection con = ConnectionFactory.getConnection();
        try {
            String sql = "UPDATE tb_cliente SET cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, "
                    + "data_cliente = ?, rua_cliente = ?, nr_cliente = ?, cep_cliente = ?, id_cidade = ? "
                    + "WHERE id_cliente = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cliente.getCpf());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getEmail());
            st.setDate(4, new java.sql.Date(cliente.getData().getTime()));
            st.setString(5, cliente.getRua());
            st.setInt(6, cliente.getNumero());
            st.setString(7, cliente.getCep());
            st.setInt(8, cliente.getCidade().getId());
            st.setInt(9, cliente.getId());
            st.executeUpdate();
            return true;
        } catch (Exception e) {
            throw e;  
        }
    }
    
}
