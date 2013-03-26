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

public class relFechamentoBrieOrcaPorEstado extends HttpServlet {
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
            
            String relJasper = path + barra + "relFechamentoBrieOrcaPorEstado.jasper";
            //String subrelDir = path + barra;
            
            Map parameters = new HashMap();
		
            parameters.put("PESSIDEMPR", request.getParameter("pessidempr"));
            parameters.put("DOCUIDBRIE", request.getParameter("docuidbrie"));
                       
            //parameters.put("SUBREPORT_DIR", request.getParameter("caminhorelatorios"));

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
