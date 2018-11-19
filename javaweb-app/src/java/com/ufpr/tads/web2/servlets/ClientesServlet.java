/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.*;
import com.ufpr.tads.web2.dao.*;
import com.ufpr.tads.web2.facade.ClientesFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, Exception {
        
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
            EstadoDAO estadoDAO = new EstadoDAO();
            CidadeDAO cidadeDAO = new CidadeDAO();
            List<Estado> estados = null;
            Estado estado = null;
            Cidade cidade = null;
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
                    estados = estadoDAO.listar();
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("estados", estados);
                    request.setAttribute("cidadeSelecionada", cliente.getCidade());
                    request.setAttribute("form", "alterar");
                    rd = ctx.getRequestDispatcher("/clientesForm.jsp");
                    rd.forward(request, response);
                }
                else if(action.equals("remove")) {
                    idCliente = Integer.parseInt(request.getParameter("id"));
                    ClientesFacade.remover(idCliente); 
                    response.sendRedirect("ClientesServlet");
                }
                else if (action.equals("update")) {
                    cliente = new Cliente();
                    estado = new Estado();
                    cidade = new Cidade();
                    cliente.setId( (Integer.parseInt(request.getParameter("idCliente"))) );
                    cliente.setCpf( request.getParameter("cpf"));
                    cliente.setNome( request.getParameter("nome"));
                    cliente.setEmail( request.getParameter("email"));
                    cliente.setRua( request.getParameter("rua"));
                    cliente.setCep( request.getParameter("cep"));
                    cliente.setNumero(  Integer.parseInt(request.getParameter("numero")) );
                    try {
                        cidade = cidadeDAO.obter(Integer.parseInt(request.getParameter("cidade")));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    cliente.setCidade(cidade);
                    ClientesFacade.alterar(cliente);
                    response.sendRedirect("ClientesServlet");
                }
                else if (action.equals("formNew")) {
                    rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
                    estados = estadoDAO.listar();
                    request.setAttribute("estados", estados);
                    rd.forward(request, response);
                }
                else if (action.equals("new")) {
                    cliente = new Cliente();
                    cliente.setCpf(request.getParameter("cpf").replaceAll("\\W", ""));
                    cliente.setNome(request.getParameter("nome"));
                    cliente.setEmail(request.getParameter("email"));
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    String str = request.getParameter("data");   // Data como String
                    Date data = null;
                            try {
                                    data = format.parse(str); 
                            } 
                         catch (ParseException e) {
                                    System.out.println("Data no formato errado");
                                    e.printStackTrace();
                            }
                    System.out.println("Data = " + data);

                    cliente.setData(data);
                    cliente.setRua(request.getParameter("rua"));
                    cliente.setCep(request.getParameter("cep").replaceAll("\\W", ""));
                    cliente.setNumero(Integer.parseInt(request.getParameter("numero")));
                    try {
                    cidade = cidadeDAO.obter(Integer.parseInt(request.getParameter("cidade")));
                    cliente.setCidade(cidade);
                    ClientesFacade.inserir(cliente);
                    response.sendRedirect("ClientesServlet");
                    } catch (ServletException e) {
                        throw new ServletException(e);
                    }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
       
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
