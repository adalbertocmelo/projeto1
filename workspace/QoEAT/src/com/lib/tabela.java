package com.lib;

import java.sql.SQLException;
import java.util.ArrayList;

public class tabela 
{
	public String cmpSemHint = "1;U;";
	public ArrayList<String> cmpTitulo;
	public ArrayList<String> cmpLargura;
	public ArrayList<String> cmpAlinhamento;
	public String classTituloColuna = "label";
	public String classDetalheColuna = "detalheColunaClass";
	public String bgCorTitulo = "#AABDC8";
	public String bgCorDetalhe = "#FFFFFF";
		
	public String htmlColuna = "<div title=\"@hint@\" style=\"width:@largura@; text-align: @alinhamento@; background-color=@bgcor@;\" class=\"@class@\">@valor@</div>";
	public String htmlDiv = "<div class=\"@class@\">@conteudo@</div>";
	public String objPista = "pista";
	public String htmlFiltro = "";
	
	public String barraAnterior = "<span style=\"cursor:pointer\" onclick=\"@funcaoJs@('@nbarradepaginacao@','@pag@',pista.value);\"><U>Anterior</U> </span>";
	public String barraPagSel = "&nbsp;&nbsp;<span><b>@pag@</b></span>";
	public String barraPagNSel = "&nbsp;&nbsp;<span style=\"cursor:pointer\" onClick=\"@funcaoJs@('@nbarradepaginacao@','@pag@',pista.value);\"><U>@pag@</U></span>";
	public String barraProximo = "<span style=\"cursor:pointer\" onclick=\"@funcaoJs@('@nbarradepaginacao@','@pag@',pista.value);\"> <U>Próximo</U></span>";		
	
	public String grpTituloTabelaClass = "grpTituloTabelaClass";
	public String tituloClass = "tituloClass";
	public String filtroClass = "filtroClass";
	public String barraDePaginacaoClass = "barraDePaginacaoClass";

	public String grpDetalheClass = "grpDetalheClass";
	public String linhaDetalheClass = "linhaDetalheClass";
	public String grpTituloColunasClass = "grpTituloColunasClass";
	
	public ArrayList<String> cmpFiltro = null;
	
	public int qtdLinhas;
	public int offSet;
	
	public String pista = "";
	public String titulo = "";
	public String barraDePaginacao = ""; 

	public String sql = "";
	public String filtro = "";
	public String sqlConta = "";
	public String sqlPesquisa = "";
	public String sqlOrderBy = "";
	public String nPag = "";
	
	//String[][] cmpTabela;

	public String conteudo = "";
	public int qtdRegistrosResultado;
	public int qtdPagResultado;
	public String nBarraDePaginacao = "";
	public int qtdPagPBPag;
	public String pistaAntiga = "";
	public String funcaoJs = "";
	public int registroInicioPagina;
	public int registroFimPagina;
	
	
	public void constroi() throws SQLException
	{
		this.constroiTabela();
	}
	
	public String constroiDiv(String conteudo,String classStr)
	{
		String shtmlDiv = this.htmlDiv;
		shtmlDiv = shtmlDiv.replace("@conteudo@", conteudo);
		shtmlDiv = shtmlDiv.replace("@class@", classStr);
		return shtmlDiv;
	}
	
	public void setClassTituloDetalheBg(String classTituloColuna,String classDetalheColuna, String bgCorTitulo, String bgCorDetalhe)
	{
		this.classTituloColuna = classTituloColuna;
		this.classDetalheColuna = classDetalheColuna;
		this.bgCorTitulo = bgCorTitulo;
		this.bgCorDetalhe = bgCorDetalhe;		
	}
	
	public void constroiTabela() throws SQLException
	{
		this.filtro = db.filtroSql(this.pista, this.cmpFiltro);				
		this.htmlFiltro = "Pista: <input id=\""+this.objPista+"\" type=\"text\" value=\"@pista@\" size=\"20\" onKeyDown=\"if(window.event.keyCode == 13){@funcaoJs@('1','1',"+this.objPista+".value);}\"> <input id=\"filtrosql\" type=\"hidden\" value=\""+this.filtro+"\" > <input id=\"pesquisar\" type=\"button\" value=\"pesquisar\" onclick=\"@funcaoJs@('1','1',"+this.objPista+".value);\">";
		this.conteudo = "";
		// adiciona o Titulo da Tabela e a Barra de navegação
		this.constroiBarraDePaginacao();
		// constroi Titulo Tabela
		String htmlTitulo = this.constroiDiv(this.titulo,this.tituloClass);
		// constroi Barra de Paginação
		String shtmlBarra = this.constroiDiv(this.barraDePaginacao,this.barraDePaginacaoClass);
		String shtmlFiltro = "";
		if (this.cmpFiltro != null)
		{
			// constroi Pista Tabela
			shtmlFiltro = this.constroiDiv(this.htmlFiltro,this.filtroClass);
			shtmlFiltro = shtmlFiltro.replace("@funcaoJs@", this.funcaoJs);
			shtmlFiltro = shtmlFiltro.replace("@pista@", this.pista);
		}
		// constroi Grupo de Titulo Tabela
		String htmlGrpTituloTabela = this.constroiDiv(htmlTitulo + shtmlBarra + shtmlFiltro,this.grpTituloTabelaClass);

		String htmlDetalhe = "";
		String htmlGrpDetalhe ="";
		this.constroiSqlPesquisa();
		tQuery qry = new tQuery();
		qry.add(this.sqlPesquisa);
		
		// adiciona o titulo dos campos no array tabela
		String grpTituloColunas = "";
		String shtmlColuna = "";
		int col = 0;
		while (col < this.cmpTitulo.size())
		{
			shtmlColuna = this.htmlColuna;
			shtmlColuna = shtmlColuna.replace("@hint@","");
			shtmlColuna = shtmlColuna.replace("@valor@",util.toStr(this.cmpTitulo.get(col)));
			shtmlColuna = shtmlColuna.replace("@largura@",util.toStr(this.cmpLargura.get(col)));
			shtmlColuna = shtmlColuna.replace("@alinhamento@",util.toStr(this.cmpAlinhamento.get(col)));
			shtmlColuna = shtmlColuna.replace("@class@",this.classTituloColuna);
			shtmlColuna = shtmlColuna.replace("@bgcor@",this.bgCorTitulo);				
			grpTituloColunas   +=  shtmlColuna;
			col++;
		}
		// constroi Grupo de Titulo das Colunas
		String shtmlGrpTituloColunas = this.constroiDiv(grpTituloColunas,this.grpTituloColunasClass);
		String linhaDetalhe = "";
		String valor ="";
		if (qry.abrir())
		{
			while (qry.proximo())
			{
				linhaDetalhe = "";
				//col = 0;
				for (int x = 1; x <= qry.qtdColunas; x++)
				{
					//col++;
					shtmlColuna = this.htmlColuna;
					valor = qry.result.getString(qry.rsmd.getColumnName(x));
					//this.cmpTabela[row][col] = valor;
					if (this.cmpSemHint.equals(""))
					{	
						shtmlColuna = shtmlColuna.replace("@hint@",util.toStr(valor));
					}
					else
					{
						// se for a ultima coluna e se tem a letra U no cmpSemHint
						if ((x == qry.qtdColunas) && (this.cmpSemHint.indexOf("U;") >= 0))
						{
							//não mostra o hint
							shtmlColuna = shtmlColuna.replace("@hint@","");
						}
						else
						{
							//senão testa a posição
							if (this.cmpSemHint.indexOf(String.valueOf(x)+";") == -1)
							{
								// caso não tenha achado, mostra o hint
								shtmlColuna = shtmlColuna.replace("@hint@",util.toStr(valor));
							}
							else
							{
								// se achou, não mostra o hint
								shtmlColuna = shtmlColuna.replace("@hint@","");
							}
						}
					}
					shtmlColuna = shtmlColuna.replace("@valor@",util.toStr(valor));
					shtmlColuna = shtmlColuna.replace("@largura@",util.toStr(this.cmpLargura.get(x)));
					shtmlColuna = shtmlColuna.replace("@alinhamento@",util.toStr(this.cmpAlinhamento.get(x)));
					shtmlColuna = shtmlColuna.replace("@class@",this.classDetalheColuna);
					shtmlColuna = shtmlColuna.replace("@bgcor@",this.bgCorDetalhe);
					linhaDetalhe += shtmlColuna;
													
					//this.SubstituiBranco[$c] = '&nbsp;';
				}
				
				htmlDetalhe += this.constroiDiv(linhaDetalhe,this.linhaDetalheClass);						
			}
		}
		// constroi Grupo de Detalhe da Tabela
		htmlGrpDetalhe = this.constroiDiv(htmlDetalhe,this.grpDetalheClass);

		this.conteudo += htmlGrpTituloTabela + shtmlGrpTituloColunas + htmlGrpDetalhe;							
	}

	public void constroiSqlConta()
	{
		//String filtro = db.filtroSql(this.pista, this.cmpFiltro);
		if (!this.filtro.equals(""))
		{
			this.sqlConta  = " select count(*) as conta ";
			this.sqlConta += " from   ("+this.sql+") as sql";
			this.sqlConta  = this.sqlConta.replace("@Filtro",this.filtro);
		}
		else
		{
			this.sqlConta     = "select count(*) as conta from ("+this.sql+") as sql;";
			this.sqlConta     = this.sqlConta.replace("@Filtro","0 = 0");
		}
		//System.out.println("SQLConta:"+this.sqlConta);
	}		

	public void constroiSqlPesquisa()
	{
		//String filtro = db.filtroSql(this.pista, this.cmpFiltro);
		
		if (!this.filtro.equals(""))
		{
			this.sqlPesquisa  = this.sql+" "+this.sqlOrderBy;
			this.sqlPesquisa  = this.sqlPesquisa.replace("@Filtro",this.filtro);
			this.sqlPesquisa +=	" limit "+this.qtdLinhas+" offset "+this.offSet+";";
		}
		else
		{
			this.sqlPesquisa  = this.sql+" "+this.sqlOrderBy;
			this.sqlPesquisa  = this.sqlPesquisa.replace("@Filtro","0 = 0");
			this.sqlPesquisa +=	" limit "+this.qtdLinhas+" offset "+this.offSet+";";
		}
	}		

	public void constroiBarraDePaginacao() throws NumberFormatException, SQLException
	{
		this.constroiSqlConta();
		tQuery qry = new tQuery();
		qry.add(this.sqlConta);
		if (qry.abrir())
		{
			qry.proximo();
			this.qtdRegistrosResultado = qry.result.getInt("conta");
			if (this.qtdRegistrosResultado != 0)
			{
				//System.out.println("qtdRegistrosResultado:"+this.qtdRegistrosResultado);
				//System.out.println("qtdLinhas:"+this.qtdLinhas);
				int qtdPag =  (int) Math.ceil(qry.result.getDouble("conta") / this.qtdLinhas);
				this.qtdPagResultado = qtdPag;
				//System.out.println("qtdPagResultado:"+String.valueOf(this.qtdPagResultado));
				
				if (this.nPag.equals(""))
				{
					this.nPag = "1";
				}
				if (this.nPag.equals("@nPag@"))
				{
					this.nPag = "1";
				}		
				if (this.nPag.equals("0"))
				{
					this.nPag = "1";
				}								
				//System.out.println("nPag:"+this.nPag);
				//System.out.println("qtdPagPBPag:"+String.valueOf(this.qtdPagPBPag));
				this.nBarraDePaginacao = String.valueOf((int) Math.ceil(Double.parseDouble(this.nPag) / this.qtdPagPBPag));
				//System.out.println("nBarraDePaginacao:"+this.nBarraDePaginacao);
				if (this.nBarraDePaginacao.equals(""))
				{
					this.nBarraDePaginacao = "1";
				}
				//System.out.println("nBarraDePaginacao:"+this.nBarraDePaginacao);
				//System.out.println("Math.ceil(qtdPag / this.qtdPagPBPag):"+String.valueOf(Math.ceil(qtdPag / this.qtdPagPBPag)));
				if (Integer.parseInt(this.nBarraDePaginacao) > Math.ceil(Double.parseDouble(String.valueOf(qtdPag)) / this.qtdPagPBPag))
				{
					this.nBarraDePaginacao = String.valueOf(Math.ceil( Double.parseDouble(String.valueOf(qtdPag)) / this.qtdPagPBPag)) ;
					//System.out.println("Entrou:1");
				}				
				//System.out.println("nBarraDePaginacao:"+this.nBarraDePaginacao);
				
				int inicio = (int) ((Integer.parseInt(this.nBarraDePaginacao) * this.qtdPagPBPag) - (this.qtdPagPBPag -1));
				int fim    = (int) (Integer.parseInt(this.nBarraDePaginacao) * this.qtdPagPBPag);
				
				//System.out.println("inicio:"+String.valueOf(inicio));
				//System.out.println("fim:"+String.valueOf(fim));
				
				if (inicio > qtdPag)
				{
					inicio = 1;
				}
				if (fim > qtdPag)
				{
					fim = qtdPag;
				}
				
				if (fim < Integer.parseInt(this.nPag))
				{
					this.nPag = String.valueOf(fim);
				}
				//System.out.println("nPag:"+this.nPag);
				//System.out.println("inicio:"+String.valueOf(inicio));
				//System.out.println("fim:"+String.valueOf(fim));			
				if (this.pistaAntiga.equals(""))
				{
					this.pistaAntiga = this.pista;
				}
				else
				{				
					if (!this.pistaAntiga.equals(this.pista))
					{
						this.nPag = "1";
						this.pistaAntiga = this.pista;
					}
				}
				
				this.offSet = (Integer.parseInt(this.nPag) - 1) * this.qtdLinhas;				
				if (this.offSet < 1)
				{
					this.offSet = 0;
				}
				this.registroInicioPagina = this.offSet + 1;
				if (fim >= qtdPag)
				{
					//ultima barra de paginação
					if (fim == Integer.parseInt(this.nPag))
					{
						this.registroFimPagina = this.qtdRegistrosResultado;
					}
					else
					{
						this.registroFimPagina = this.offSet + this.qtdLinhas;
					}
				}
				else
				{
					// não tem proximo
					this.registroFimPagina = this.offSet + this.qtdLinhas;
				}
				
				this.barraDePaginacao = "";
				String sbarraAnterior = "";
				if (Integer.parseInt(this.nBarraDePaginacao) > 1)
				{
					sbarraAnterior = this.barraAnterior;
					sbarraAnterior = sbarraAnterior.replace("@funcaoJs@",this.funcaoJs);
					sbarraAnterior = sbarraAnterior.replace("@nbarradepaginacao@",String.valueOf((Integer.parseInt(this.nBarraDePaginacao) - 1)));
					sbarraAnterior = sbarraAnterior.replace("@pag@",String.valueOf(((Integer.parseInt(this.nBarraDePaginacao) - 1)  * this.qtdPagPBPag)));
					sbarraAnterior = sbarraAnterior.replace("@pista@",this.pista);
				}
				String tempBarraDePaginacao = "";
				String sbarraProximo = "";
				String sbarraPagSel = "";
				String sbarraPagNSel = "";
				if (qtdPag > 1)
				{
					for (int i = inicio; i <= fim ;i++)
					{
						if (Integer.parseInt(this.nPag) == i)
						{
							sbarraPagSel = this.barraPagSel;
							sbarraPagSel = sbarraPagSel.replace("@funcaoJs@",this.funcaoJs);
							sbarraPagSel = sbarraPagSel.replace("@nbarradepaginacao@",this.nBarraDePaginacao);
							sbarraPagSel = sbarraPagSel.replace("@pag@",String.valueOf(i));						
							sbarraPagSel = sbarraPagSel.replace("@pista@",this.pista);
							tempBarraDePaginacao += sbarraPagSel;						
						}
						else
						{
							sbarraPagNSel = this.barraPagNSel;
							sbarraPagNSel = sbarraPagNSel.replace("@funcaoJs@",this.funcaoJs);
							sbarraPagNSel = sbarraPagNSel.replace("@nbarradepaginacao@",this.nBarraDePaginacao);
							sbarraPagNSel = sbarraPagNSel.replace("@pag@",String.valueOf(i));						
							sbarraPagNSel = sbarraPagNSel.replace("@pista@",this.pista);
							tempBarraDePaginacao += sbarraPagNSel;						
						}
					}
				}
	
				if (fim >= qtdPag)
				{
	//				this.barraDePaginacao .= ' Próximo ';
				}
				else
				{
					sbarraProximo = this.barraProximo;
					sbarraProximo = sbarraProximo.replace("@funcaoJs@",this.funcaoJs);
					sbarraProximo = sbarraProximo.replace("@nbarradepaginacao@",String.valueOf( (int) (Double.parseDouble(this.nBarraDePaginacao) + 1)));
					sbarraProximo = sbarraProximo.replace("@pag@",String.valueOf( (int)(((Double.parseDouble(this.nBarraDePaginacao) + 1) * this.qtdPagPBPag) - (this.qtdPagPBPag -1))));
					sbarraProximo = sbarraProximo.replace("@pista@",this.pista);					
				}
				
				this.barraDePaginacao	= "";
				if (qtdPag > 1)
				{
					this.barraDePaginacao	= "";//"Páginas:&nbsp;&nbsp;";
				}
				this.barraDePaginacao	+= sbarraAnterior + tempBarraDePaginacao + sbarraProximo;
			}
			else
			{
				this.barraDePaginacao	= "";	
			}
		}
	}	
}
