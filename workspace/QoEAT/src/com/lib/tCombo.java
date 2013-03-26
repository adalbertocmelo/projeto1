package com.lib;

import java.sql.SQLException;
import java.util.ArrayList;

public class tCombo 
{
	public String sql = "";
	public String conteudo = "";
	public String objetoNome = "";
	public String indexTabulacao = "";
	public boolean funcaoAoAlterar = false;
	public boolean submeterAoAlterar = false;
	public String acao = "";
	public boolean obrig = false;
	public String nome = "";
	public String valorInicial = "";
	public ArrayList<String> valorInicialMultiplas = new ArrayList<String>();
	public boolean seBrancoSelPrimeiro = true;
	public String estilo = "caixa";
	public boolean multiplasLinhas = false;
	public String qtdLinhas = "";
	public String complemento = "";

	public void constroi() throws SQLException
	{		
		// inicia variaveis
		String obrigatorio = ""; 
		String multiplas = "";
		// Objetos Dinâmicos de Cadastro
		tQuery qry = new tQuery();
		qry.limpar();
		qry.add(this.sql);
		if (this.obrig)
		{
			obrigatorio = "obrig=\"true\" nome=\""+ this.nome+"\" ";
		}
		
		if (this.multiplasLinhas)
		{
			multiplas = "multiple size=\""+ this.qtdLinhas+"\" ";
		}
		
		if (this.submeterAoAlterar)
		{
			if (this.funcaoAoAlterar)
			{
				this.conteudo += "<select name=\""+ this.objetoNome+"\" id=\""+ this.objetoNome+"\" "+ multiplas+" tabindex=\""+this.indexTabulacao+"\" class=\""+ this.estilo+"\" "+ obrigatorio+" onchange=\""+ this.acao+"\" @complemento@ @value@> \r";
			}
			else
			{
				this.conteudo += "<select name=\""+this.objetoNome+"\" id=\""+ this.objetoNome+"\" " + multiplas +" tabindex=\""+ this.indexTabulacao+"\" class=\"" + this.estilo+"\" " + obrigatorio + " onchange=\"Frm.Acao.value ='" + this.acao+"';Frm.submit()\" @complemento@ @value@> \r";
			}
		}
		else
		{
			if (this.funcaoAoAlterar)
			{
				this.conteudo += "<select name=\""+ this.objetoNome+"\" id=\""+ this.objetoNome+"\" "+ multiplas+" tabindex=\""+ this.indexTabulacao+"\" class=\""+ this.estilo+"\" onchange=\""+ this.acao+"\" @complemento@ @value@> \r";
			}
			else
			{			
				this.conteudo += "<select name=\""+ this.objetoNome+"\" id=\""+ this.objetoNome+"\" "+ multiplas+" tabindex=\""+ this.indexTabulacao+"\" class=\""+ this.estilo+"\" @complemento@ @value@> \r";
			}
		}
		
		this.conteudo = this.conteudo.replace("@complemento@", this.complemento);
		
		boolean encontrou = false;
		boolean primeiro = true;
		if (qry.abrir())
		{
			while (qry.proximo())
			{		
				if (this.multiplasLinhas)
				{
					if (this.valorInicialMultiplas.size() > 0)
					{
						encontrou = false;
						for (int v = 0; v <= this.valorInicialMultiplas.size() - 1;v++)
						{
							if (qry.result.getString(1).equals(this.valorInicialMultiplas.get(v)))
							{
								this.conteudo += "<option value=\""+qry.result.getString(1)+"\" title=\""+qry.result.getString(2)+"\" selected>"+qry.result.getString(2)+"</option> \r ";
								encontrou = true;
							}
						}
						if (!encontrou)
						{
							 this.conteudo += "<option value=\""+qry.result.getString(1)+"\" title=\""+qry.result.getString(2)+"\" >"+qry.result.getString(2)+"</option> \r ";
						}
					}
					else
					{
						 this.conteudo += "<option value=\""+qry.result.getString(1)+"\" title=\""+qry.result.getString(2)+"\" >"+qry.result.getString(2)+"</option> \r ";
					}
					this.conteudo = this.conteudo.replace("@value@", "");
				}
				else
				{
					if ( this.valorInicial.equals(""))
					{
						// Sem valor Inicial
						if (primeiro)
						{
							this.conteudo = this.conteudo.replace("@value@", "value=\""+qry.result.getString(1)+"\"");
							this.conteudo += "<option value=\""+qry.result.getString(1)+"\"  title=\""+qry.result.getString(2)+"\" selected>"+qry.result.getString(2)+"</option> \r ";
							primeiro = false;
						}
						else
						{
							this.conteudo += "<option value=\""+qry.result.getString(1)+"\" title=\""+qry.result.getString(2)+"\" >"+qry.result.getString(2)+"</option> \r ";
						}
					}
					else
					{
						if (util.toStr(qry.result.getString(1)).equals(util.toStr(this.valorInicial)))
						{
							this.conteudo = this.conteudo.replace("@value@", "value=\""+qry.result.getString(1)+"\"");
							this.conteudo += "<option value=\""+qry.result.getString(1)+"\" title=\""+qry.result.getString(2)+"\" selected>"+qry.result.getString(2)+"</option> \r ";
						}
						else
						{
							 this.conteudo += "<option value=\""+qry.result.getString(1)+"\" title=\""+qry.result.getString(2)+"\" >"+qry.result.getString(2)+"</option> \r ";
						}
					}
				}
			}
		}
		this.conteudo = this.conteudo.replace("@value@", "");
		
		this.conteudo += "</select> \r";
	}
}
