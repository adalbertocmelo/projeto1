var oOpco = new tCad();

oOpco.constroi = function()
{
	this.arqServer = '../acesso?objServer=cadOpco';
	this.lstExcluir = '';
	
	this.nome = 'Opco';
	this.objFocus = 'nome';
	
	this.cmpNome  = new Array('id','opcoidpai','nome','link','ordem','imagem','descricao','topcid');
	this.cmpValor = new Array('','-1','','','','','','');	
	this.cmpFiltro = new Array();
	this.aoConstruir();
}

oOpco.adicionarFilho = function(vid)
{		
	svid = ','+vid;
	var oAjax = new tAjax(this.arqServer);
	oAjax.addCampo('acao','telaCadastro');
	oAjax.addCampo('opcoidpai',svid.substr(1));	
	oAjax.enviar('NoExec');
	if (oAjax.retorno == '1')
	{
		alert('Erro um');
	}
	else
	{
		document.getElementById(this.divFrm).innerHTML = oAjax.retorno;
		setTimeout("ordem.focus();", 500); 
	}		
}

oOpco.atualizarCodigoHierarquico = function()
{		
	var oAjax = new tAjax(this.arqServer);
	oAjax.addCampo('acao','atualizarCodigoHierarquico');
	oAjax.enviar('NoExec');
	if (oAjax.retorno != '')
	{
		alert(oAjax.retorno);
	}
	else
	{
		this.atualizarTab();
	}
}

oOpco.constroi();