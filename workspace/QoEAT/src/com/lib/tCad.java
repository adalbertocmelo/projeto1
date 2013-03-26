package com.lib;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.acesso.db.dbUsua;

public class tCad {	
	public int consQtdLinhas = 50;
	public int consQtdPagPBPag = 10;
	public String echo = "";
	public String htmlTela = "";
	public String htmlFrm = "";
	public String titulo = "";
	public String objJs = "";
	public Propriedades campo = new Propriedades();
	public ArrayList<String> consCmpTitulo;
	public ArrayList<String> consCmpLargura;
	public ArrayList<String> consCmpAlinhamento;
	public ArrayList<String> consCmpFiltro;
	public String consCmpSemHint = "1;U;";	
	public String consSql = "";
	public String consSqlOrderBy = "";
	public String consLinhaDetalheClass = "";
	public String consGrpDetalheClass = "";
	public String consGrpTituloColunasClass = "";
	public String consBarraDePaginacaoClass = "";
	public String consTituloClass = "";
	public String consGrpTituloTabelaClass = "";
	public String consTitulo = "";
	public String consPista = "";
	public HttpSession sessao;
	public dbUsua usuarioAtual = new dbUsua();
	public String link = "";
	public boolean impressaoPdf = false;
	public JasperPrint report = null;
	public String pathimg;
	public String path;
	public String nomeDoRelatorio;
	public Map parameters = null;
	
	public void setLink(String slink)
	{
		this.link = slink; 
	}
	public void putPaths(String pathimg, String path) throws IOException
    {
		this.pathimg = pathimg;
		this.path = path;
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {	
		// verifica se usuario tem acesso
		this.sessao = request.getSession(true);
		//if (this.sessao.isNew()) {}	else{}
		this.usuarioAtual = (dbUsua) this.sessao.getAttribute("usuarioAtual");
		
		String[] retorno = null;
		try 
		{
			retorno = dbUsua.validarAcesso(!(this.sessao.getAttribute("usuarioAtual") == null), this.link, this.usuarioAtual);
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		if (retorno == null)
		{
			/*
			tMensagem mens = new tMensagem();
			mens.titulo = "Aviso de Sistema";
			mens.texto = "retorno é nulo";
			mens.link = "";
			mens.constroi();
			this.echo = mens.conteudo;	
			*/
			this.echo = "retorno é nulo";
		}
		else
		{
			if (retorno[0].equals("FALSE"))
			{
				/*
				tMensagem mens = new tMensagem();
				mens.titulo = "Aviso de Sistema";
				mens.texto = retorno[1];
				mens.link = retorno[2];
				mens.constroi();
				this.echo = mens.conteudo;
				*/
				this.echo = retorno[1];
			}
			else
			{
				try 
				{
					this.iniciar(request);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		if (!this.impressaoPdf)
		{
			// depois de executar tudo, imprime
			response.setContentType("text/html");
			response.setCharacterEncoding("ISO-8859-1");
			PrintWriter out = response.getWriter();
			
	        out.print(this.echo);
		}
		else
		{
			String barra = "";
	        if (this.path.charAt(0) == '/') 
	        {
	        	//Barra do Linux
	        	barra = "/";
	        }
	        else 
	        {
	        	//Barra do Windows
	        	barra = "\\";
	        }
	        String relJasper = this.path + barra + this.nomeDoRelatorio;
	        
	        JasperPrint report = null;
	        
			try {      
				System.out.println("passei por aqui 0.2");
				report = JasperFillManager.fillReport(relJasper, this.parameters, db.getConnectionRel());
				System.out.println("passei por aqui 0.5");
				response.setContentType("application/pdf");
				System.out.println("passei por aqui 1");
				byte x1[] = JasperExportManager.exportReportToPdf(report);
				System.out.println("passei por aqui 2");				
				response.getOutputStream().write(x1);
				System.out.println("passei por aqui 3");
			}
			catch (JRException e){
				System.out.print(e.getMessage());
			}			
		}
    }	
	
	public void iniciar(HttpServletRequest request) throws SQLException 
	{
		
	}
	
	public int executar(String acao) throws SQLException 
	{
		int retorno = 0;
		if (acao.equals(""))
		{
			//echo = "Ação não informada.";
			retorno = 1;
		}
		else
		{
			//call_user_func(array($this,$acao));
	        // Get the method named sayHello.
	        //Method metodo = this.getClass().getMethod(acao, null);
	        //metodo.invoke(this, null);
	        //acaoMethod.
	        //System.out.println("Public method found: " + metodo.toString());
	    	this.exec(acao);
	        //System.out.println("Method either doesn't exist or is not public: " + ex.toString());
		}
		return retorno;
	}		
			
	public void putCampos(HttpServletRequest request)
	{
		Enumeration<Object> chaves = this.campo.keys();
		String chave = "";
		
		while (chaves.hasMoreElements())
		{
			chave = (String) chaves.nextElement();
			//System.out.println(chave);
			if (request.getParameter(chave) != null)
			{
				//System.out.println(request.getParameter(chave));
				this.campo.put(chave,request.getParameter(chave));
			}
		}
	}
	
	public void exec(String acao) throws SQLException
	{
		
	}
			
	public String getCampos(String str)
	{
		Enumeration<Object> chaves = this.campo.keys();
		String chave = "";
		
		while (chaves.hasMoreElements())
		{
			chave = (String) chaves.nextElement();
			str = str.replace("@"+chave+"@", this.campo.getProperty(chave));
			str = str.replace("@"+chave+"_html@", this.campo.gPHTML(chave));
		}		
		return str;
	}
			
	public String criarCombosTela(String str) throws SQLException
	{
		return str;
	}
		
	public String criarCombos(String str) throws SQLException
	{			
		return str;
	}		
	
	public void atualizarTab() throws SQLException
	{
		echo = this.constroiTab();
	}		
	
	public void exibirTela() throws SQLException
	{
		//imprimir a tela
		tHtml corpo = new tHtml(this.htmlTela);
		corpo.conteudo = this.criarCombosTela(corpo.conteudo);
		corpo.conteudo = corpo.conteudo.replace("@titulo@", this.titulo);
		corpo.conteudo = corpo.conteudo.replace("@frm@", this.telaFrm());
		echo = corpo.conteudo;		
	}
	
	public boolean comChaves()
	{
		return (!this.campo.getProperty("id").equals(""));			
	}
	
	public String telaFrm() throws SQLException
	{
		if (this.comChaves())
		{
			this.recuperar();
		}
		tHtml corpo = new tHtml(this.htmlFrm);
		corpo.conteudo = this.criarCombos(corpo.conteudo);
		corpo.conteudo = this.getCampos(corpo.conteudo);
		return corpo.conteudo;		
	}
	
	public void telaCadastro() throws SQLException
	{
	 	echo = this.telaFrm();
	}
	
	public void salvar(){}		

	public void excluir(){}			
	
	public void recuperar(){}

	public void aoExibirTela()
	{
		echo = this.objJs+".aoExibirTela();";
	}
	
	public String constroiTab() throws SQLException
	{
		tabela oTabela = new tabela();
		oTabela.titulo = this.consTitulo;
		oTabela.funcaoJs = this.objJs+".atualizarTab";
		oTabela.setClassTituloDetalheBg("tituloV2","detalheV2","#ECECEC","#FFFFFF");		
		if (!this.consGrpTituloTabelaClass.equals(""))
		{
			oTabela.grpTituloTabelaClass = this.consGrpTituloTabelaClass;
		}
		else
		{
			oTabela.grpTituloTabelaClass = "grpTituloTabelaClass";
		}
		if (!this.consTituloClass.equals(""))
		{
			oTabela.tituloClass = this.consTituloClass; 
		}
		else
		{
			oTabela.tituloClass = "tituloClass"; 
		}
		
		if (!this.consBarraDePaginacaoClass.equals(""))
		{
			oTabela.barraDePaginacaoClass = this.consBarraDePaginacaoClass; 
		}
		else
		{
			oTabela.barraDePaginacaoClass = "barraDePaginacaoClass"; 
		}
		
		if (!this.consGrpTituloColunasClass.equals(""))
		{
			oTabela.grpTituloColunasClass = this.consGrpTituloColunasClass; 
		}
		else
		{
			oTabela.grpTituloColunasClass = "grpTituloColunasClass"; 
		}
		
		if (!this.consGrpDetalheClass.equals(""))
		{
			oTabela.grpDetalheClass = this.consGrpDetalheClass;  
		}
		else
		{
			oTabela.grpDetalheClass = "grpDetalheClass"; 
		}
		
		if (!this.consLinhaDetalheClass.equals("")) 
		{
			oTabela.linhaDetalheClass = this.consLinhaDetalheClass; 
		}
		else
		{
			oTabela.linhaDetalheClass = "linhaDetalheClass"; 
		}
						
		oTabela.pista = this.consPista;
		oTabela.cmpSemHint = this.consCmpSemHint;
		oTabela.cmpFiltro = this.consCmpFiltro;
		oTabela.cmpTitulo = this.consCmpTitulo;
		oTabela.cmpLargura = this.consCmpLargura;
		oTabela.cmpAlinhamento = this.consCmpAlinhamento;				
		oTabela.sql = this.consSql;
		oTabela.sqlOrderBy = this.consSqlOrderBy;
		// atributos para exibição
		oTabela.qtdLinhas = this.consQtdLinhas;
		oTabela.qtdPagPBPag = this.consQtdPagPBPag; // Quantidade de Paginas a serem exibidas por Barra de Paginação
		oTabela.nBarraDePaginacao = util.toStr(this.campo.getProperty("nBarraDePaginacao")); // Número da Barra de Paginação a Exibir
		oTabela.nPag = util.toStr(this.campo.getProperty("nPag")); // Número da Pagina a exibir	
		oTabela.constroi();
		return oTabela.conteudo;
	}					
}
