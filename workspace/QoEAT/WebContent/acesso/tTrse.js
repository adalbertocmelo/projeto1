var oTrse = new tCad();

oTrse.constroi = function()
{
	this.arqServer = '../acesso?objServer=trocarSenha';
	this.lstExcluir = '';
	
	this.nome = 'Trse';
	this.objFocus = 'EdtNovaSenha';
	
	this.cmpNome  = new Array('EdtNovaSenha', 'EdtConfirmarNovaSenha');
	this.cmpValor = new Array('', '');	
	this.cmpFiltro = new Array();
	this.aoConstruir();
	this.temSpanId = false;
}

oTrse.aoExibirTela = function()
{	
	if(this.objFocus != '')
	{
		document.getElementById(this.objFocus).focus();
	}	
}

oTrse.trocar = function(nsenha, cnsenha)
{	
	this.nsenha = document.getElementById("EdtNovaSenha").value;
	
	this.cnsenha = document.getElementById("EdtConfirmarNovaSenha").value;
	var oAjax = new tAjax(this.arqServer);
	oAjax.addCampo('acao','trocar');
	oAjax.addCampo('EdtNovaSenha',this.nsenha);
	oAjax.addCampo('EdtConfirmarNovaSenha',this.cnsenha);
	oAjax.enviar('NoExec');
	alert(oAjax.retorno);
	this.iniciaCampos();
	this.aoExibirTela();
}

oTrse.constroi();