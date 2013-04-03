// JavaScript Document
function tAjax(objServer)
{
	objServer = ((objServer != undefined)? objServer : '');
	this.objServer = objServer;
	
	this.campos = new Array( );
	this.retorno = '';
	this.receber = '';
	this.url = '';
	this.asyncrono = false;
	
	this.constroiUrl = function()
	{
		this.url= this.objServer;
		if (this.objServer.indexOf("?")== -1)
		{
			this.url= this.url+"?sid="+Math.random();
		}
		else
		{
			this.url= this.url+"&sid="+Math.random();
		}
		for( var i = 0; i < this.campos.length; i++)
		{
			this.url = this.url+'&'+this.campos[i].nome+'='+this.campos[i].valor;					
		}
		//alert(this.url);
	}
	
	this.urlEncode = function URLEncode(plaintext)
	{
		// The Javascript escape and unescape functions do not correspond
		// with what browsers actually do...
		var SAFECHARS = "0123456789" +					// Numeric
						"ABCDEFGHIJKLMNOPQRSTUVWXYZ" +	// Alphabetic
						"abcdefghijklmnopqrstuvwxyz" +
						"-_.!~*'()";					// RFC2396 Mark characters
		var HEX = "0123456789ABCDEF";
	
		var encoded = "";
		for (var i = 0; i < plaintext.length; i++ ) {
			var ch = plaintext.charAt(i);
			if (ch == " ") {
				encoded += "+";				// x-www-urlencoded, rather than %20
			} else if (SAFECHARS.indexOf(ch) != -1) {
				encoded += ch;
			} else {
				var charCode = ch.charCodeAt(0);
				if (charCode > 255) {
					alert( "Unicode Character '" 
							+ ch 
							+ "' cannot be encoded using standard URL encoding.\n" +
							  "(URL encoding only supports 8-bit characters.)\n" +
							  "A space (+) will be substituted." );
					encoded += "+";
				} else {
					encoded += "%";
					encoded += HEX.charAt((charCode >> 4) & 0xF);
					encoded += HEX.charAt(charCode & 0xF);
				}
			}
		} // for
	
		return encoded;
		
	}	
	
	this.addCampo = function(vnome,vval)
	{
		var i = this.campos.length;
		this.campos[i] = new Object();
		this.campos[i].nome = vnome;
		this.campos[i].valor = this.urlEncode(vval);
	}
	
	this.addCampos = function(lstCampos)
	{
		for(x=0;x<lstCampos.length;x++)
		{
			//alert(lstCampos[x]);
			var str = document.getElementById(lstCampos[x]).value;
			str = str.replace('”','"');
			str = str.replace('“','"');
			str = str.replace('–','-');
			this.addCampo(lstCampos[x],str);
		}
	}	

	this.limpaCampos = function()
	{
		this.campos = new Array( );
	}

	this.enviar = function(NoExec)
	{
		var xmlHttp = false;
		try
		{    // Firefox, Opera 8.0+, Safari
			xmlHttp=new XMLHttpRequest();
			if (xmlHttp.overrideMimeType) 
			{
				xmlHttp.overrideMimeType('text/xml');
				// See note below about this line
			}			
		}
		catch(e)
		{    // Internet Explorer    
			try
			{      
				xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
			}
			catch(e)
			{
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch(e)
				{
					alert("Seu navegador não suporta AJAX!");
					return false;
				}
			}
		}
		
		if (!this.asyncrono)
		{
			xmlHttp.onreadystatechange=function()
			{
				if(xmlHttp.readyState==4)
				{
					if (xmlHttp.status == 200) 
					{				
					}
					else
					{
						alert(this.url+' :Problemas de Conexão, tente novamente.');
					}
				}
			}
		}
		else
		{
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState==4)
				{
					if (xmlHttp.status == 200) 
					{
						alert('4');
					    if (window.execScript) 
					    {
					    	window.execScript(this.receber,'javascript');
							alert('5:'+this.receber);
					    } 
					    else 
					    {
					        var tag = document.createElement("script");
					        tag.type = "text/javascript";
					        tag.text = this.receber;
					        document.getElementsByTagName("head").item(0).appendChild(tag);
					    }					
					
					}
					else
					{
						alert(this.url+' :Problemas de Conexão, tente novamente.');
					}
				}
			}
		}
		this.constroiUrl();
		//alert(this.url);
		xmlHttp.open("POST",this.url,this.asyncrono);
		xmlHttp.send(null);
		
		if (!this.asyncrono)
		{
			this.retorno = xmlHttp.responseText;
			if (NoExec == undefined)
			{
				if (this.retorno != '')
				{
			        if (window.execScript) {        // Internet Explorer and Google Chrome
			            execScript (this.retorno, "JavaScript");
			        }
			        else {
			            if (window.eval) {
			                // the script language  is always JavaScript for the eval method
			                eval (this.retorno);
			            }
			        }
				}
			}
		}
	}
	/*
	this.receber = function(xmlHttp)
	{
		if(xmlHttp.readyState==4)
		{
			if (xmlHttp.status == 200) 
			{				
				this.retorno = xmlHttp.responseText;		
			}
			else
			{
				alert(this.url+' :Problemas de Conexão, tente novamente.');
			}
		}
	}
	*/
}
