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

public class relAcompanhamentoTrabalhoPorFiltro extends HttpServlet {
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
            
            String relJasper = path + barra + "relAcompanhamentoTrabalhoPorFiltro.jasper";
            //String subrelDir = path + barra;

            Map parameters = new HashMap();

            parameters.put("UNORID", "");
            if (request.getParameter("unorid") != null)
			{
            	parameters.put("UNORID", request.getParameter("unorid"));
			}            
            
            parameters.put("SITUPENDENTE"," ");
            if (request.getParameter("situpendente").equals("true"))
            {
            	parameters.put("SITUPENDENTE", " and     sita.concluitarefa <> " + request.getParameter("situpendente"));
            }
            
            //parameters.put("TAREID","");
            if (!request.getParameter("tareid").equals(""))
    		{
    			if (!request.getParameter("tareid").equals("null"))
    			{
    				parameters.put("TAREID", " and     tare.id = 0"+request.getParameter("tareid"));
    			}
    		}

            //parameters.put("SITUID","");
            if (!request.getParameter("situid").equals(""))
    		{
    			if (!request.getParameter("situid").equals("null"))
    			{
    				parameters.put("SITUID", " and     extr.situid = 0"+request.getParameter("situid"));
    			}
    		}
            
            //parameters.put("USUAID","");
            if (!request.getParameter("usuaid").equals(""))
    		{
    			if (!request.getParameter("usuaid").equals("null"))
    			{
    				parameters.put("USUAID", " and     extr.usuaid = 0"+request.getParameter("usuaid")+" ");
    			}
    		}            
            parameters.put("FILTROSQL", "0=0 ");
            if (!request.getParameter("filtrosql").equals(""))
    		{
    			if (!request.getParameter("filtrosql").equals("null"))
    			{
    				parameters.put("FILTROSQL", request.getParameter("filtrosql")+" ");
    			}
    		}
            
            parameters.put("UNORDESCR", request.getParameter("unordescr"));
            parameters.put("SITUPENDENTEDESCR", request.getParameter("situpendentedescr"));
            parameters.put("TAREDESCR", request.getParameter("taredescr"));
            parameters.put("SITUDESCR", request.getParameter("situdescr"));
            parameters.put("USUADESCR", request.getParameter("usuadescr"));
            parameters.put("FILTRODESCR", request.getParameter("filtrodescr"));           
            
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
