/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.ClienteDAO;
import com.ufpr.tads.web2.facade.ClientesFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SAMUEL
 */
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = null;
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();
        int id;
        try {
            id = ((LoginBean)session.getAttribute("loginBean")).getId();
        } catch (Exception e) {
            id = 0;
        }
        
        if (id == 0) {
            rd = getServletContext().getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuario precisa estar autenticado para acessar o sistema");
            rd.forward(request, response);
        } else {
            String action = (String)request.getParameter("action");
            int idCliente = 0;
            Cliente cliente = null;
            if (action == null || action.equals("list")) {
               List<Cliente> lista = ClientesFacade.buscarTodos();
               rd = getServletContext().getRequestDispatcher("/clientesListar.jsp");
               request.setAttribute("clientes", lista);
               rd.forward(request, response);               
            } else if (action.equals("show")){
                idCliente = Integer.parseInt(request.getParameter("id"));
                cliente = ClientesFacade.buscar(idCliente);
                if (cliente != null) {
                    rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
                    request.setAttribute("cliente", cliente);
                    rd.forward(request, response);
                }
            }
                else if (action.equals("formUpdate")) {
                    idCliente = Integer.parseInt(request.getParameter("id"));
                    cliente = ClientesFacade.buscar(idCliente);
                    request.setAttribute("cliente", cliente);
                    rd = ctx.getRequestDispatcher("/clientesAlterar.jsp");
                    rd.forward(request, response);
                }
                else if(action.equals("remove")) {
                    idCliente = Integer.parseInt(request.getParameter("id"));
                    ClientesFacade.remover(idCliente); 
                    response.sendRedirect("ClientesServlet");
                }
                else if (action.equals("update")) {
                    cliente = new Cliente();
                    cliente.setId( (Integer.parseInt(request.getParameter("idCliente"))) );
                    cliente.setCpf( request.getParameter("cpf"));
                    cliente.setNome( request.getParameter("nome"));
                    cliente.setEmail( request.getParameter("email"));
                    cliente.setRua( request.getParameter("rua"));
                    cliente.setCep( request.getParameter("cep"));
                    cliente.setNumero(  Integer.parseInt(request.getParameter("numero")) );
                    cliente.setCidade(request.getParameter("cidade"));
                    cliente.setUf(request.getParameter("uf"));
                    ClientesFacade.alterar(cliente);
                    response.sendRedirect("ClientesServlet");
                }
                else if (action.equals("formNew")) {
                    //response.sendRedirect("/clientesNovo.jsp");
                    rd = getServletContext().getRequestDispatcher("/clientesNovo.jsp");
                    rd.forward(request, response);
                }
                else if (action.equals("new")) {
                    cliente = new Cliente();
                    cliente.setCpf(request.getParameter("cpf"));
                    cliente.setNome(request.getParameter("nome"));
                    cliente.setEmail(request.getParameter("email"));
                    cliente.setRua(request.getParameter("rua"));
                    cliente.setCep(request.getParameter("cep"));
                    cliente.setNumero(Integer.parseInt(request.getParameter("numero")));
                    cliente.setCidade(request.getParameter("cidade"));
                    cliente.setUf((String)request.getParameter("uf"));
                    try {
                        ClientesFacade.inserir(cliente);
                        response.sendRedirect("ClientesServlet");
                    } catch (Exception e) {
                        throw e;
                    }
                    
                    
                    //rd = getServletContext().getRequestDispatcher("/ClientesServlet");
                    //rd.forward(request, response);
                }
            }
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
