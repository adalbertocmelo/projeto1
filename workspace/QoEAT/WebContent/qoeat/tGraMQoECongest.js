var oGraMQoECongest = new tCad();

oGraMQoECongest.constroi = function()
{	
	this.arqServer = '../qoeat?objServer=GraMQoECongest';
	this.lstExcluir = '';
	
	this.nome = 'GraMQoECongest';
	this.objFocus = 'nome';
	
	this.cmpNome  = new Array('id','nome','duracao','origem');
	this.cmpValor = new Array('','','','');	
	this.cmpFiltro = new Array();
	this.aoConstruir();
}

oGraMQoECongest.constroiGraMQoECongest = function()
{	
	selecionarMenuAtual = new Array(vlink, acao, execAoExibirTela);
	exibirMenu();
	var oAjax = new tAjax('../../QoEAT/'+vlink.substr(1).replace('/','?objServer='));
	oAjax.addCampo('acao',acao);
	oAjax.enviar('NoExec');
	corpo.innerHTML = oAjax.retorno;

	var oAjax1 = new tAjax('../../QoEAT/acesso/login');
	oAjax1.addCampo('acao','setTela');
	oAjax1.addCampo('telaAcao',acao);	
	oAjax1.addCampo('telaLink',vlink);
	oAjax1.enviar('NoExec');	
	
	if (execAoExibirTela == undefined)
	{
		execAoExibirTela = true;
	}
	
	if (execAoExibirTela)
	{
		setTimeout("executarAoExibirTela('"+vlink+"');", 500);
	}
}

oGraMQoECongest.constroi();