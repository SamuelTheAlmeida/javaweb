/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;
import java.util.List;

/**
 *
 * @author SAMUEL
 */
public class ClientesFacade {
    public static void inserir(Cliente c) {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.inserirCliente(c);
    }
    public static void alterar(Cliente c) {
        ClienteDAO clienteDAO = new ClienteDAO();;
        clienteDAO.alterarCliente(c);
    }
    public static Cliente buscar(int id){
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.consultarCliente(id);
        return cliente;
    }
    public static List<Cliente> buscarTodos() {
        ClienteDAO clienteDAO = new ClienteDAO();
        List <Cliente> clientes = clienteDAO.listarClientes(); 
        return clientes;
    }
    
    public static void remover(int id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.removerCliente(id); 
    }


}
