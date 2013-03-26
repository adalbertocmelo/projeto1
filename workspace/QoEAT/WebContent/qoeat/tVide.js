var oVide = new tCad();

oVide.constroi = function()
{	
	this.arqServer = '../qoeat?objServer=cadVide';
	this.lstExcluir = '';
	
	this.nome = 'Vide';
	this.objFocus = 'nome';
	
	this.cmpNome  = new Array('id','nome','duracao','origem');
	this.cmpValor = new Array('','','','');	
	this.cmpFiltro = new Array();
	this.aoConstruir();
}

oVide.constroi();