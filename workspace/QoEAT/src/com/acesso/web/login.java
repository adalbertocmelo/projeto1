package com.acesso.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lib.tHtml;

public class login extends HttpServlet{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
		tHtml.caminho = getServletContext().getRealPath("/WEB-INF/classes/com/");
		tLogin objTela = new tLogin();
		try {
			objTela.iniciar(request);
			response.setContentType("text/html");
			response.setCharacterEncoding("ISO-8859-1");
			PrintWriter out;
			out = response.getWriter();
	        out.println(objTela.echo);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }	
}
