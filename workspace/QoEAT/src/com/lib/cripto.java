package com.lib;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class cripto {
	public static String md5(String senha){
		String retornaSenha = "";
		try{  
			MessageDigest md = MessageDigest.getInstance( "MD5" );  
			md.update( senha.getBytes() );  
			BigInteger hash = new BigInteger( 1, md.digest() );  
			retornaSenha = hash.toString( 16 ); 
		}catch(NoSuchAlgorithmException ns){  
			ns.printStackTrace();  
		}
		return retornaSenha; 			
	}
}
