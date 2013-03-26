var oUsua = new tCad();

oUsua.constroi = function()
{
	this.arqServer = '../acesso?objServer=cadUsua';
	this.lstExcluir = '';
	
	this.nome = 'Usua';
	this.objFocus = 'nome';
	
	this.cmpNome  = new Array('id','login','nome','perfid','email');
	this.cmpValor = new Array('','','','ignorar','','ignorar');	
	this.cmpFiltro = new Array();
	this.aoConstruir();
}

oUsua.constroi();