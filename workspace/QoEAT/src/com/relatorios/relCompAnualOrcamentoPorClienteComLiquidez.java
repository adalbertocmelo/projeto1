package com.relatorios;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.lib.db; 

public class relCompAnualOrcamentoPorClienteComLiquidez extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		processRequest(request, response);
	}
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String pathimg = getServletContext().getRealPath("/acesso/imagens/");
			String path = getServletContext().getRealPath("/relatorios/");
            String barra = "";
            
            if (path.charAt(0) == '/') {
            	//Barra do Linux
            	barra = "/";
            }
            else {
            	//Barra do Windows
            	barra = "\\";
            }
            
            String relJasper = path + barra + "relCompAnualOrcamentoPorClienteComLiquidez.jasper";
            //String subrelDir = path + barra;
            
            Map parameters = new HashMap();
		
            parameters.put("PESSIDEMPR", request.getParameter("pessidempr"));            
            parameters.put("USUAIDATENDENTE", request.getParameter("usuaidatendente"));            
            parameters.put("PESSIDCLIE", request.getParameter("pessidclie"));            
            parameters.put("ANO1", request.getParameter("ano1"));            
            parameters.put("ANO2", request.getParameter("ano2"));            
            JasperPrint report = null;
		
		try {
			report = JasperFillManager.fillReport(relJasper, parameters,db.getConnectionRel());
			response.setContentType("application/pdf");
			
			byte x1[] = JasperExportManager.exportReportToPdf(report);
			
			response.getOutputStream().write(x1);
		}
		catch (JRException e){
			System.out.print(e.getMessage());
		}
	}
}
