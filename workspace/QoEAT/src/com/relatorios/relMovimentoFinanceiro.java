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

public class relMovimentoFinanceiro extends HttpServlet {
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
            String relJasper = "";
            if (request.getParameter("versaoretrato").equals("false"))
            {
            	relJasper = path + barra + "relMovimentoFinanceiro.jasper";
            }
            else
            {
            	relJasper = path + barra + "relMovimentoFinanceiroVRetrato.jasper";
            }
            //String subrelDir = path + barra;
            
            Map parameters = new HashMap();

            parameters.put("PERIODO", request.getParameter("periodo"));
            parameters.put("PESSIDEMPR", request.getParameter("pessidempr"));
            parameters.put("RECEBIMENTO", request.getParameter("recebimento"));
            parameters.put("TITULORELATORIO", request.getParameter("titulorelatorio"));
            parameters.put("SQLDATA", request.getParameter("sqldata"));
            parameters.put("ORDEM", request.getParameter("ordem"));
            parameters.put("SQLORIG1", request.getParameter("sqlorig1"));
            parameters.put("SQLORIG2", request.getParameter("sqlorig2"));
            parameters.put("PESSID", request.getParameter("pessid"));
            parameters.put("SQLRELATORIO", request.getParameter("sqlrelatorio"));
            parameters.put("LBLDATAQUITACAO", request.getParameter("lbldataquitacao"));
            parameters.put("LBLCLIEVEICFORN", request.getParameter("lblclieveicforn"));

            parameters.put("SUBREPORT_DIR", path + barra);

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
