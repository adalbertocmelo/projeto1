package com.acesso.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lib.tHtml;
import com.mvc.BusinessLogic;

public class acesso extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException
	{
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException
    {
		tHtml.caminho = getServletContext().getRealPath("/WEB-INF/classes/com/");

		String businessLogicClassName = "com.acesso.web."+request.getParameter("objServer");
		try {
			Class businessLogicClass = Class.forName(businessLogicClassName);
			if (!BusinessLogic.class.isAssignableFrom(businessLogicClass)) 
			{
				throw new ServletException("classe não implementa a interface: " + businessLogicClassName);	
			}
			BusinessLogic businessLogicObject = (BusinessLogic)	businessLogicClass.newInstance();
			businessLogicObject.putPaths(getServletContext().getRealPath("/acesso/imagens/"), getServletContext().getRealPath("/relatorios/"));			
			businessLogicObject.doGet(request, response);
		} catch (Exception e) {
			try {
				throw new ServletException("A lógica de negócios causou uma exceção", e);
			} catch (ServletException e1) {
				e1.printStackTrace();
			}
		}
	}
}
