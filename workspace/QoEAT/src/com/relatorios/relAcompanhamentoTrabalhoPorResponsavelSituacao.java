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

public class relAcompanhamentoTrabalhoPorResponsavelSituacao extends HttpServlet {
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
            
            String relJasper = path + barra + "relAcompanhamentoTrabalhoPorResponsavelSituacao.jasper";
            //String subrelDir = path + barra;

            Map parameters = new HashMap();

            parameters.put("USUAID", "");
            parameters.put("SITUID", "");
            parameters.put("TAREID", "");
            parameters.put("FILTROSQL", "");
            parameters.put("FILTRODESCRICAO", request.getParameter("desfiltro") + "; TAREFA: "+ request.getParameter("destareid") + "; SITUAÇÃO: "+ request.getParameter("dessituid"));
           
            
            String unorid = "";
            
            if (request.getParameter("unorid") != null)
			{
            	unorid = request.getParameter("unorid");
			}
            else
            {
            	unorid = "";
            }
    		if (!request.getParameter("cmbsituid").equals(""))
    		{
    			if (!request.getParameter("cmbsituid").equals("null"))
    			{
    				parameters.put("SITUID", " and     situ.id = 0" + request.getParameter("cmbsituid"));
    			}
    		}
    		if (!request.getParameter("cmbtareid").equals(""))
    		{
    			if (!request.getParameter("cmbtareid").equals("null"))
    			{
    				parameters.put("TAREID", " and     tare.id = 0" + request.getParameter("cmbtareid"));
    			}
    		}
    		if (request.getParameter("cmbfiltro").equals("1"))
    		{
    			parameters.put("FILTROSQL",  " and     	((extr.usuaid is not null and extr.usuaid = 0" + request.getParameter("usuaid") + ")" +
    										" 	    	or (extr.usuaid is null and extr.unorid = 0" + unorid + "))");
    		}
    		if (request.getParameter("cmbfiltro").equals("2"))
    		{
    			parameters.put("FILTROSQL", " and     	exists (select 	* " +
    							"						from   	historicoexecucaotrabalho hetr " +
    					        "					 	where  	hetr.trabid = extr.trabid " +
    					        "						and		hetr.fltrid = extr.fltrid " +
    					        "						and		hetr.tareid = extr.tareid " +
    					        "						and     hetr.usuaid = 0" + request.getParameter("usuaid") + ")");
    		}
    		
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
