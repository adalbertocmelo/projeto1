// JavaScript Document

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);


	function menuonoff()
	{
		if (LayerMenu.style.visibility == 'visible')
		{
			LayerMenu.style.visibility = 'hidden';
		}
		else
		{
			LayerMenu.style.visibility = 'visible';	
		}
	}

	function Aguarde()
	{
		LayerAguarde.style.visibility = 'visible';	
	}
	/*
	function Menu()
	{
		location.href = "CSCSistema.php";
	}
	
	function MenuAssociado()
	{
		location.href = "CSCSistemaAreaAssociado.php";
	}

	function Limpar(ele)
	{
		ele.value = "";
	}
	
	function checar()
	{
		var quant = document.Frm.elements.length;
		for (var i = 0; i < quant; i++)
		{
			var ele = document.Frm.elements[i];
			if (ele.getAttribute('obrig') == 'true')
			{
				if (ele.value == '')
				{
					var nom = ele.getAttribute('nome');
					window.alert('O campo ' + nom + ' não pode ficar vazio');
					ele.focus();
					LayerAguarde.style.visibility = 'hidden';
					return false;
					break;
				}
			}
			if (ele.getAttribute('senhacartaocredito') == 'true')
			{
				if (ele.value.length < 8)
				{
					window.alert('A senha do Cartão do Associado deve possuir 8 digitos.');
					ele.focus();
					LayerAguarde.style.visibility = 'hidden';
					return false;
					break;
				}
			}		
		}
	}

	function focar()
	{
		var quant = document.Frm.elements.length;
		for (var i = 0; i < quant; i++)
		{
			var ele = document.Frm.elements[i];
			if (ele.getAttribute('foco') == 'true')
			{
				ele.focus();
				return false;
				break;
			}		
		}
	}

	function achakey()
   	{
    	var tecla = window.event.keyCode;
		alert (tecla);
	} 
*/
	function somentenumeros()
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla != 8)&&(tecla !=46)&&(tecla !=9))
			{
				if((tecla < 48)||(tecla > 57))
				{
					if((tecla < 96)||(tecla > 105))
					{
						event.returnValue = false;
					} 
				}
			}
		}
	} 

	function numerosformatomoeda(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var valor = ele.value;
			if (isNaN(valor) == true)
			{
				valor = '0.00';
			}
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: valor = valor.substr(0,valor.length - 1); break;
			}
	
			valor = valor + stecla;
			valor = valor.replace('.','');
			var numero = valor.substr(0,valor.length - 2);
			var decimal = valor.substr(valor.length - 2,2);
			if (isNaN(parseFloat(numero)))
			{
				numero = '0';
			}
			else
			{
				numero = parseFloat(numero);
			}
			decimal = '.00' + decimal;
			decimal = decimal.substr(decimal.length -2,2);				
			valor = numero + '.' + decimal;
			ele.value = valor;
		}
	}
	
	function numerosformatomoedaD5(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var valor = ele.value;
			if (isNaN(valor) == true)
			{
				valor = '0.00';
			}
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: valor = valor.substr(0,valor.length - 1); break;
			}
	
			valor = valor + stecla;
			valor = valor.replace('.','');
			var numero = valor.substr(0,valor.length - 5);
			var decimal = valor.substr(valor.length - 5,5);
			if (isNaN(parseFloat(numero)))
			{
				numero = '0';
			}
			else
			{
				numero = parseFloat(numero);
			}
			decimal = '.00000' + decimal;
			decimal = decimal.substr(decimal.length -5,5);				
			valor = numero + '.' + decimal;
			ele.value = valor;
		}
	}	

	function numerosformatomoedaComVirgula(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var vvalor = ele.value;
			vvalor = vvalor.replace(',','.');
			if (isNaN(vvalor) == true)
			{
				vvalor = '0.00';
			}
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: vvalor = vvalor.substr(0,vvalor.length - 1); break;
			}
	
			vvalor = vvalor + stecla;
			vvalor = vvalor.replace('.','');
			var vnumero = vvalor.substr(0,vvalor.length - 2);
			var vdecimal = vvalor.substr(vvalor.length - 2,2);
			if (isNaN(parseFloat(vnumero)))
			{
				vnumero = '0';
			}
			else
			{
				vnumero = parseFloat(vnumero);
			}
			vdecimal = '.00' + vdecimal;
			vdecimal = vdecimal.substr(vdecimal.length -2,2);		
			vvalor = vnumero + ',' + vdecimal;
			ele.value = vvalor;
		}
	}

	function numerosformatomoedaComVirgulaPercentual(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var vvalor = ele.value;
			vvalor = vvalor.replace(',','.');
			if (isNaN(vvalor) == true)
			{
				vvalor = '0.00';
			}
			
	
			
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: vvalor = vvalor.substr(0,vvalor.length - 1); break;
			}
	
			vvalor = vvalor + stecla;
			vvalor = vvalor.replace('.','');
			var vnumero = vvalor.substr(0,vvalor.length - 2);
			var vdecimal = vvalor.substr(vvalor.length - 2,2);
			if (isNaN(parseFloat(vnumero)))
			{
				vnumero = '0';
			}
			else
			{
				vnumero = parseFloat(vnumero);
			}
			vdecimal = '.00' + vdecimal;
			vdecimal = vdecimal.substr(vdecimal.length -2,2);		
			vvalor = vnumero + ',' + vdecimal;
			if (parseFloat(vvalor) > parseFloat('100.00'))
			{
				event.returnValue = false;
			}
			else
			{
				ele.value = vvalor;
			}			
		}
	}	
	
	function numerosformatomoedaD5ComVirgula(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var valor = ele.value;
			valor = valor.replace(',','.');		
			if (isNaN(valor) == true)
			{
				valor = '0.00000';
			}
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: valor = valor.substr(0,valor.length - 1); break;
			}
	
			valor = valor + stecla;
			valor = valor.replace('.','');
			var numero = valor.substr(0,valor.length - 5);
			var decimal = valor.substr(valor.length - 5,5);
			if (isNaN(parseFloat(numero)))
			{
				numero = '0';
			}
			else
			{
				numero = parseFloat(numero);
			}
			decimal = '.00000' + decimal;
			decimal = decimal.substr(decimal.length -5,5);
			
			valor = numero + ',' + decimal;
			ele.value = valor;
		}
	}	

	function numerosformatomoedaD1ComVirgula(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var valor = ele.value;
			valor = valor.replace(',','.');		
			if (isNaN(valor) == true)
			{
				valor = '0.0';
			}
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: valor = valor.substr(0,valor.length - 1); break;
			}
	
			valor = valor + stecla;
			valor = valor.replace('.','');
			var numero = valor.substr(0,valor.length - 1);
			var decimal = valor.substr(valor.length - 1,1);
			if (isNaN(parseFloat(numero)))
			{
				numero = '0';
			}
			else
			{
				numero = parseFloat(numero);
			}
			decimal = '.0' + decimal;
			decimal = decimal.substr(decimal.length -1,1);
			
			valor = numero + '.' + decimal;
			ele.value = valor;
		}
	}	

	function numerosformatomoedaD4ComVirgula(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{		
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var valor = ele.value;
			valor = valor.replace(',','.');		
			if (isNaN(valor) == true)
			{
				valor = '0.0000';
			}
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: valor = valor.substr(0,valor.length - 1); break;
			}
	
			valor = valor + stecla;
			valor = valor.replace('.','');
			var numero = valor.substr(0,valor.length - 4);
			var decimal = valor.substr(valor.length - 4,4);
			if (isNaN(parseFloat(numero)))
			{
				numero = '0';
			}
			else
			{
				numero = parseFloat(numero);
			}
			decimal = '.0000' + decimal;
			decimal = decimal.substr(decimal.length -4,4);
			
			valor = numero + ',' + decimal;
			ele.value = valor;
		}
	}		

	function numerosformatodata(ele)
   	{
    	var tecla = window.event.keyCode;
    	if (tecla == 72)
    	{
    		ele.value = oSistema.getDataHoje();
    	}
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			if (tecla == 46)
			{
				ele.value = '';
			}
			else
			{
				var stecla = '';
				var valor = ele.value;
				valor = valor.replace('/','');
				valor = valor.replace('/','');		
				switch(tecla)
				{
					case 48: stecla = '0'; break;
					case 49: stecla = '1'; break;
					case 50: stecla = '2'; break;
					case 51: stecla = '3'; break;
					case 52: stecla = '4'; break;
					case 53: stecla = '5'; break;
					case 54: stecla = '6'; break;
					case 55: stecla = '7'; break;
					case 56: stecla = '8'; break;
					case 57: stecla = '9'; break;
		
					case 96: stecla = '0'; break;
					case 97: stecla = '1'; break;
					case 98: stecla = '2'; break;
					case 99: stecla = '3'; break;
					case 100: stecla = '4'; break;
					case 101: stecla = '5'; break;
					case 102: stecla = '6'; break;
					case 103: stecla = '7'; break;
					case 104: stecla = '8'; break;
					case 105: stecla = '9'; break;
					case 8: valor = valor.substr(0,valor.length - 1); break;
				}
		
				valor = valor + stecla;
				var dia = valor.substr(0,2);
				var mes = valor.substr(2,2);
				var ano = valor.substr(4,4);
				if (parseInt(dia)>=32)
				{
					dia = '01';
				}
				
				if (parseInt(mes)>=13)
				{
					mes = '12';
				}
				
				if (valor.length > 2)
				{
					if (valor.length > 4)
					{
						valor = dia + '/' + mes + '/' + valor.substr(4,4);
					}
					else
					{
						if (valor.length > 3)
						{
							valor = dia + '/' + mes + '/';
						}
						else
						{
							valor = dia + '/' + mes;
						}
					}
				}
				else
				{
					if (valor.length > 1)
					{
						valor = dia + '/';
					}
					else
					{
						valor = dia;
					}
				}
				ele.value = valor;
			}
		}
	}


	function numerosformatodatahora(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
					
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var valor = ele.value;
			valor = valor.replace('/','');
			valor = valor.replace('/','');	
			valor = valor.replace(':','');
			valor = valor.replace(' ','');		
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: valor = valor.substr(0,valor.length - 1); break;
			}
	
			valor = valor + stecla;
			var dia = valor.substr(0,2);
			var mes = valor.substr(2,2);
			var ano = valor.substr(4,4);
			var hora = valor.substr(8,2);
			var minuto = valor.substr(10,2);		
			if (parseInt(dia)>=32)
			{
				dia = '01';
			}
			
			if (parseInt(mes)>=13)
			{
				mes = '12';
			}
	
			if (parseInt(hora)>=24)
			{
				hora = '00';
			}
	
			if (parseInt(minuto)>=60)
			{
				hora = '00';
			}
			
			if (valor.length > 2)
			{
				if (valor.length > 4)
				{
					if (valor.length > 8)
					{
						if (valor.length > 9)
						{
							valor = dia + '/' + mes + '/' + valor.substr(4,4) + ' ' + hora + ':' + minuto;
						}
						else
						{
							valor = dia + '/' + mes + '/' + valor.substr(4,4) + ' ' + hora;
						}					
					}
					else
					{
						valor = dia + '/' + mes + '/' + valor.substr(4,4);
					}
				}
				else
				{
					if (valor.length > 3)
					{
						valor = dia + '/' + mes + '/';
					}
					else
					{
						valor = dia + '/' + mes;
					}
				}
			}
			else
			{
				if (valor.length > 1)
				{
					valor = dia + '/';
				}
				else
				{
					valor = dia;
				}
			}
			ele.value = valor;
		}
	}

	function numerosformatocartaocredito(ele)
   	{
    	var tecla = window.event.keyCode;
		if (tecla != 13)
		{
			if((tecla !=46)&&(tecla !=9))
			{
				event.returnValue = false;
			}
			var stecla = '';
			var valor = ele.value;
			valor = valor.replace('.','');
			valor = valor.replace('.','');
			valor = valor.replace('.','');
			switch(tecla)
			{
				case 48: stecla = '0'; break;
				case 49: stecla = '1'; break;
				case 50: stecla = '2'; break;
				case 51: stecla = '3'; break;
				case 52: stecla = '4'; break;
				case 53: stecla = '5'; break;
				case 54: stecla = '6'; break;
				case 55: stecla = '7'; break;
				case 56: stecla = '8'; break;
				case 57: stecla = '9'; break;
	
				case 96: stecla = '0'; break;
				case 97: stecla = '1'; break;
				case 98: stecla = '2'; break;
				case 99: stecla = '3'; break;
				case 100: stecla = '4'; break;
				case 101: stecla = '5'; break;
				case 102: stecla = '6'; break;
				case 103: stecla = '7'; break;
				case 104: stecla = '8'; break;
				case 105: stecla = '9'; break;
				case 8: valor = valor.substr(0,valor.length - 1); break;
			}
			valor = valor + stecla;
			if (valor.length > 4)
			{
				if (valor.length > 8)
				{
					if (valor.length > 12)
					{
						valor = valor.substr(0,4) + '.' + valor.substr(4,4) + '.' + valor.substr(8,4) + '.' + valor.substr(12,4);
					}
					else
					{
						if (valor.length > 11)
						{
							valor = valor.substr(0,4) + '.' + valor.substr(4,4) + '.' + valor.substr(8,4) + '.';
						}
						else
						{
							valor = valor.substr(0,4) + '.' + valor.substr(4,4) + '.' + valor.substr(8,4);
						}
					}			
				}
				else
				{
					if (valor.length > 7)
					{
						valor = valor.substr(0,4) + '.' + valor.substr(4,4) + '.';
					}
					else
					{
						valor = valor.substr(0,4) + '.' + valor.substr(4,4);
					}
				}
			}
			else
			{
				if (valor.length > 3)
				{
					valor = valor.substr(0,4) + '.';
				}
				else
				{
					valor = valor;
				}
			}
			ele.value = valor;
		}
	}

	function Checked(ele)
   	{
		if(ele.value == "0")
		{
			ele.value = "1";
		}
		else
		{
			ele.value = "0";			
		}
	}
/*
	function ContrasteTecladoVirtual(Nivel)
   	{
		if(Nivel == 0)
		{
			document.styleSheets(1).href = 'TecVir0.css';
		}
		if(Nivel == 1)
		{
			document.styleSheets(1).href = 'TecVir1.css';
		}
		if(Nivel == 2)
		{
			document.styleSheets(1).href = 'TecVir2.css';
		}
	}
*/
	function maxlengthTextArea(textArea,maxlen)
   	{
		var valor = textArea.value;
		if (valor.length > maxlen-1)
		{
			event.returnValue = false;
			if (valor.length > maxlen)
			{
				textArea.value = valor.substr(0,maxlen);
			}		
		}
	}		
	
	function formatomoedaComVirgula(ele)
   	{
		var vvalor = ''+ ele;
		vvalor = vvalor.replace(',','.');
		if (isNaN(vvalor) == true)
		{
			vvalor = '0.00';
		}
		
		if (vvalor.indexOf('.') != -1)
		{
			vvalor = vvalor + '00';
			var vnumero = vvalor.substr(0,vvalor.indexOf('.'));
			var vdecimal = vvalor.substr(vvalor.indexOf('.') + 1,2);
		}
		else
		{
			var vnumero = vvalor;
			var vdecimal = '00';			
		}
		
		vvalor = vnumero + ',' + vdecimal;
		return vvalor;
	}	
	
	function focusNext(form, elemName, evt) {
		evt = (evt) ? evt : event;
		var charCode = (evt.charCode) ? evt.charCode :
			((evt.which) ? evt.which : evt.keyCode);
		if (charCode == 13) {
			if (form == '0')
			{
				document.getElementById(elemName).focus();
			}
			else
			{
				form.elements[elemName].focus();
			}
			return false;
		}
		return true;
	}
	
	function dataBanco(data)
	{
		var dataR = "";
		if (data!="")
		{
			var dataD = data.substring(0, 2);
			var dataM = data.substring(3, 5);
			var dataA = data.substring(6);	
			dataR = dataNull(dataA+"-"+dataM+"-"+dataD);
			dataR = dataR.replace("\"","'");
		}
		else
		{
			dataR = dataNull(data);
		}
		return dataR;
	}
	
	function dataNull(data)
	{
		var dataTemp = data;
		dataTemp = dataTemp.replace("/","");
		dataTemp = dataTemp.replace("-","");
		if(dataTemp == "")
		{
			data = "''";
		}
		else
		{
			data = "'"+data+"'";		
		}
		return data;
	}	
//-->
