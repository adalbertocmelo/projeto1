function tCad()
{
	this.constroi = function()
	{
		this.arqServer = ''; //'../cadOpco.php';
		this.lstExcluir = '';
		
		this.objFocus = ''; //'nome';
		this.nome = '';//'Opco';
		this.divTab = 'tab'+this.nome;
		this.divFrm = 'frm'+this.nome;
		this.comAbas = false; 
		this.abaInicial = 'liConsultar';
		this.abaAtual = '';
		
		this.cmpNome  = new Array('id','opcoidpai','nome','link','ordem','imagem');
		this.cmpValor = new Array('','-1','','','','');
		this.cmpFiltro = new Array();	
		this.exibirConsulta = true;
		this.pistaAnt = '';
		this.nBarraDePaginacaoAnt = '';
		this.nPagAnt = '';
	}
	
	this.aoConstruir = function()
	{	
		this.divTab = 'tab'+this.nome;
		this.divFrm = 'frm'+this.nome;
		this.divBarraBotoes = 'grp'+this.nome + 'BarraBotoes';
	}
	
	this.selecionarAbas = function(aba)
	{
		this.fecharAbas();
		this.abaAtual = aba;
		if (aba == 'liConsultar')
		{
			this.atualizarTab();
			document.getElementById(this.divTab).style.display = 'block';
			document.getElementById(this.divBarraBotoes).style.display = 'block';	
		}
		if (aba == 'liCadastrar')
		{
			document.getElementById(this.divFrm).style.display = 'block';
		}	
	}
	
	this.fecharAbas = function()
	{
		document.getElementById(this.divBarraBotoes).style.display = 'none';
		document.getElementById(this.divTab).style.display = 'none';
		document.getElementById(this.divFrm).style.display = 'none';
	}
	
	this.iniciaCampos = function()
	{
		for( x=0 ; x < this.cmpNome.length ; x++ )
		{
			if (this.cmpValor[x] != 'ignorar')
			{
				document.getElementById(this.cmpNome[x]).value = this.cmpValor[x];
			}
		}
		if(this.temSpanId == undefined)
		{			
			this.temSpanId = true;
		}
		if(this.temSpanId)
		{
			document.getElementById("spanId").innerHTML = '';
		}
		this.aposIniciaCampos();
	}
	
	this.aposIniciaCampos = function()	
	{
		
	}
	
	this.salvar = function()
	{		
		var oAjax = new tAjax(this.arqServer);		
		oAjax.addCampo('acao','salvar');
		oAjax.addCampos(this.cmpNome);
		oAjax.enviar('NoExec');

		if (oAjax.retorno == '')
		{
			this.iniciaCampos();
			this.atualizarTab();
		}
		else
		{
			alert(oAjax.retorno);
		}
	}
	
	this.excluir = function()
	{
		var oAjax = new tAjax(this.arqServer);
		oAjax.addCampo('acao','excluir');
		oAjax.addCampo('lstExcluir',this.lstExcluir.substr(1));
		oAjax.addCampos(this.cmpFiltro);
		oAjax.enviar('NoExec');
		if (oAjax.retorno == '')
		{
			this.atualizarTab();
		}
		else
		{
			alert(oAjax.retorno);
		}
	}

	this.atualizarTab = function(nBarraDePaginacao,nPag,pista)
	{
		if((this.exibirConsulta == true) || (this.exibirConsulta == undefined))
		{
			if ((nBarraDePaginacao != '') && (nBarraDePaginacao != undefined)) {this.nBarraDePaginacaoAnt = nBarraDePaginacao;}			
			if (nBarraDePaginacao == undefined)	{nBarraDePaginacao = this.nBarraDePaginacaoAnt;}
			nBarraDePaginacao = ((nBarraDePaginacao != undefined)? nBarraDePaginacao :'');
			
			if ((nPag != '') && (nPag != undefined)) {this.nPagAnt = nPag;}			
			if (nPag == undefined)	{nPag = this.nPagAnt;}
			nPag = ((nPag != undefined)? nPag : '');
			
			if ((pista != undefined)) {this.pistaAnt = pista;}			
			if (pista == undefined)	{pista = this.pistaAnt;}
			pista = ((pista != undefined)? pista : '');
			
			this.lstExcluir = '';
			//document.getElementById("aviso").runtimeStyle.visibility = 'visible';
			var oAjax = new tAjax(this.arqServer);
			oAjax.addCampo('acao','atualizarTab');
			oAjax.addCampos(this.cmpFiltro);
			oAjax.addCampo('nBarraDePaginacao',nBarraDePaginacao);
			oAjax.addCampo('nPag',nPag);
			oAjax.addCampo('pista',pista);
			oAjax.enviar('NoExec');

			
			document.getElementById(this.divTab).innerHTML = oAjax.retorno;
			if (pista != '')
			{
				setTimeout('document.getElementById("pista").focus()',500);
			}
		}
	}

	this.atualizaLst = function(vobj)
	{
		if (vobj.checked)
		{
			this.lstExcluir = this.lstExcluir + ',' + vobj.value;
		}
		else
		{
			this.lstExcluir = this.lstExcluir.replace(',' + vobj.value, '');
		}
	}

	this.telaCadastro = function(vid)
	{	
		if (this.comAbas)
		{
			this.selecionarAbas('liCadastrar');
		}
		svid = ','+vid;
		var oAjax = new tAjax(this.arqServer);
		oAjax.addCampo('acao','telaCadastro');
		this.chaveNome = ((this.chaveNome != undefined)? this.chaveNome :'');
		if (this.chaveNome != '')
		{
			oAjax.addCampo(this.chaveNome,svid.substr(1));
		}
		else
		{
			oAjax.addCampo('id',svid.substr(1));
		}
		oAjax.enviar('NoExec');
		if (oAjax.retorno == '1')
		{
			alert('Erro um');
		}
		else
		{
			document.getElementById(this.divFrm).innerHTML = oAjax.retorno;	
		}
		this.aoExibirTelaCadastro();
	}
	
	this.aoExibirTelaCadastro = function()
	{
		setTimeout('o'+this.nome+'.focar();',500);
		this.iniciarOutrasAbas();		
	}
	
	this.focar = function()
	{ 
		if(this.objFocus != '')
		{
			if (!document.getElementById(this.objFocus).disabled)
			{
				document.getElementById(this.objFocus).focus();
			}
		}		
	}
	
	this.iniciarOutrasAbas = function()
	{
	
	}
	
	this.aoExibirTela = function()
	{	
		this.focar();
		this.atualizarTab();
		if (this.comAbas)
		{
			this.selecionarAbas(this.abaInicial);
		}
	}
	
	this.addCombo = function(campoChave, proximoCampo, msgAlerta)
	{
		var nome = 'o'+this.nome;
		this.qtdCombos++;
		this.combo[this.qtdCombos] = new tComboPista();
		this.combo[this.qtdCombos].constroi(campoChave, proximoCampo, msgAlerta, nome, this.arqServer, this.qtdCombos);	
	}
	
	this.atualizarCombos = function()
	{
		for(var x = 1; x <= this.qtdCombos; x++)
		{
			this.combo[x].atualizarCampoChave();
		}		
	}
	
	this.marcarTudo = function(prefixo)
	{
		var elementos = document.getElementById(this.divTab).getElementsByTagName("input");
		for (var x = 0; x < elementos.length; x++)
		{
			var campo = elementos[x];
			if (campo.type == 'checkbox')
			{
				if (prefixo.indexOf(campo.id.substr(0,prefixo.length)) >= 0)
				{
					if (!campo.checked)
					{
						campo.click();
					}
				}
			}
		}		
	}
	
	this.desmarcarTudo = function(prefixo)
	{
		var elementos = document.getElementById(this.divTab).getElementsByTagName("input");
		for (var x = 0; x < elementos.length; x++)
		{
			var campo = elementos[x];
			if (campo.type == 'checkbox')
			{
				if (prefixo.indexOf(campo.id.substr(0,prefixo.length)) >= 0)
				{
					if (campo.checked)
					{
						campo.click();
					}
				}
			}
		}		
	}	
}

function tComboPista()
{
	this.constroi = function(campoChave, proximoCampo, msgAlerta, objTela, arqServer, cmbid)
	{
		this.campoChave = campoChave;
		this.objTela = objTela;
		this.proximoCampo = proximoCampo;	
		this.alerta = msgAlerta;	
		this.arqServer = arqServer;
		this.cmbid = cmbid;
		this.exibeBotNovo = true;
		this.largura = '600';
		
		this.div = 'edit'+this.campoChave;
		this.name = 'cmb'+this.campoChave;
		this.campoPista = 'pista'+this.name;
		this.acao = 'comboPistaPesquisar'+this.campoChave;	
	}	

	this.telaPesquisar = function()
	{
		var botNovo = '';
		if(this.exibeBotNovo)
		{
			botNovo = '<input name="botNovo'+this.name+'" type="button" value="Novo" onClick="'+this.objTela+'.combo['+this.cmbid+'].novo()">';
		}		
		document.getElementById(this.div).innerHTML = '<input id="'+this.campoPista+'" name="'+this.campoPista+'" type="text" size="40" value="" onKeyDown="'+this.objTela+'.combo['+this.cmbid+'].clickBotPesquisar()"><input name="botPesquisar'+this.name+'" type="button" value="Pesquisar" onClick="'+this.objTela+'.combo['+this.cmbid+'].pesquisar()">'+botNovo;	
		document.getElementById(this.campoPista).value = '';
		this.onTelaPesquisar();
	}
	
	this.alterar = function()
	{
		this.telaPesquisar();
		setTimeout("document.getElementById('"+this.campoPista+"').focus();", 100);  	
	}
	
	this.telaSelecionado = function()	
	{
		document.getElementById(this.div).innerHTML = document.getElementById(this.campoChave).descricao+'<input id="botTelaPesquisar'+this.name+'" name="botTelaPesquisar'+this.name+'" type="button" value="Alterar" onClick="'+this.objTela+'.combo['+this.cmbid+'].alterar()">';
	}
	
	this.clickBotPesquisar = function()
	{
		if (window.event.keyCode == 13)
		{
			this.pesquisar();
		}
	}
	
	this.pesquisar = function()
	{
		if (document.getElementById(this.campoPista).value != '')
		{
			var campoPista = '<input id="'+this.campoPista+'" name="'+this.campoPista+'" type="text" size="40" value="'+ document.getElementById(this.campoPista).value +'" onKeyDown="'+this.objTela+'.combo['+this.cmbid+'].clickBotPesquisar()"><input name="botPesquisar'+this.name+'" type="button" value="Pesquisar" onClick="'+this.objTela+'.combo['+this.cmbid+'].pesquisar()">';
			var	botFechar = '<input name="botFechar'+this.name+'" type="button" value="Fechar" onClick="'+this.objTela+'.combo['+this.cmbid+'].fechar()">';
			var botNovo = '';
			if(this.exibeBotNovo)
			{
				botNovo = '<input name="botNovo'+this.name+'" type="button" value="Novo" onClick="'+this.objTela+'.combo['+this.cmbid+'].novo()">';
			}
			var oAjax = new tAjax(this.arqServer);
			oAjax.retorno = '';
			oAjax.addCampo('acao',this.acao);
			this.addFiltro(oAjax);
			oAjax.addCampo(this.campoPista,document.getElementById(this.campoPista).value);
			oAjax.enviar('NoExec');		
			document.getElementById(this.div).innerHTML = campoPista+'<br> <div style="position:absolute; background-color:CCCCCC; border: 1px solid #999999; width:'+this.largura+'px;">'+ oAjax.retorno +'<div style="text-align:left; float:left; width:50%;">'+botNovo+'</div><div style="text-align:right; width:100%;">'+botFechar+'</div></div>';
			
			if (document.getElementById(this.name).length == 0)
			{
				document.getElementById(this.div).innerHTML = campoPista;
				if(this.exibeBotNovo)
				{
					if(window.confirm("Não foi encontrado resultado para sua pesquisa. Click em OK para cadastrar um novo."))
					{
						this.novo();
					}
				}
				else
				{
					alert("Não foi encontrado resultado para sua pesquisa.");
				}
				document.getElementById(this.campoPista).focus();
			}
			else
			{
				if (document.getElementById(this.name).length == 1)
				{
					document.getElementById(this.name).selectedIndex = 0;
					this.selecionar();
				}
				else
				{
					var strExecutar = this.objTela+'.combo['+this.cmbid+'].selecionar()';
					document.getElementById(this.name).ondblclick = function() { window.execScript(strExecutar,'javascript'); };
					var strEnter = this.objTela+'.combo['+this.cmbid+'].enter()';
					document.getElementById(this.name).onkeydown = function() { window.execScript(strEnter,'javascript'); };
					document.getElementById(this.name).selectedIndex = 0;
					document.getElementById(this.name).style.width = this.largura+'px';
					document.getElementById(this.name).style.overflow = 'scroll';
					setTimeout('document.getElementById("'+this.name+'").focus();', 500);
				}
			}
		}
		else
		{
			alert('Informe uma pista para pesquisar');
			document.getElementById(this.campoPista).focus();
		}
	}
	
	this.enter = function()
	{
		var key = window.event.keyCode;
		if(key == 13)
		{
			this.selecionar();	
		}
	}
	
	this.selecionar = function()
	{
		if (document.getElementById(this.name).value != '')
		{
			document.getElementById(this.campoChave).value = document.getElementById(this.name).value;
			document.getElementById(this.campoChave).descricao = document.getElementById(this.name).options[document.getElementById(this.name).selectedIndex].text;
			document.getElementById(this.div).innerHTML = document.getElementById(this.name).options[document.getElementById(this.name).selectedIndex].text+' <input name="botTelaPesquisar'+this.name+'" type="button" value="Alterar" onClick="'+this.objTela+'.combo['+this.cmbid+'].alterar()">';			
			this.onChange();
			setTimeout("document.getElementById('"+this.proximoCampo+"').focus();", 500); 		
		}
		else
		{
			alert(this.alerta);
			document.getElementById('botPesquisar'+this.name).focus();
		}
	}	

	this.onChange = function()
	{
		
	}

	this.onTelaPesquisar = function()
	{
		
	}
	
	this.novo = function()	
	{
		
	}	
	
	this.addFiltro = function(oAjax)	
	{
		
	}
	
	this.fechar = function()	
	{
		this.telaPesquisar();
		setTimeout("document.getElementById('"+this.campoPista+"').focus();", 500); 	
	}
	
	this.atualizarCampoChave = function()
	{
		//alert('*'+document.getElementById(this.campoChave).value+'*');
		if(document.getElementById(this.campoChave).value == '')
		{	
			this.telaPesquisar();
		}
		else
		{
			this.telaSelecionado();	
		}
	}
}