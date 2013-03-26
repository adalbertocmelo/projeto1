package com.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class tHtml {
	public static String caminho = "";
	public String arquivo = "";
	public String conteudo = "";
	
	public tHtml() 
	{
	}
	
	public tHtml(String sArquivo) 
	{
		this.setArquivo(sArquivo);
		try{
			BufferedReader reader = new BufferedReader(new FileReader(tHtml.caminho.concat("/"+sArquivo)));  
			//StringBuffer buffer = new StringBuffer();  
			String line = reader.readLine();  
			while (line!=null) 
			{  
		        conteudo = conteudo.concat(line);
			    line = reader.readLine(); 
			}
		}catch(IOException ex){
			conteudo = "ocorreu um erro";
			conteudo += ex.getMessage();
		}
	}
	
	public void escrever(String texto) 
	{
		escrever(texto,false);
	}
	
	public void escrever(String texto, boolean limpar) 
	{
        BufferedWriter bufferedWriter = null;
        
        try {
            
            //Construct the BufferedWriter object
            bufferedWriter = new BufferedWriter(new FileWriter(tHtml.caminho.concat("/"+this.arquivo),!limpar));
            
            //Start writing to the output stream
            bufferedWriter.write(texto); 
            bufferedWriter.newLine();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedWriter
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}	
	
	public void setArquivo(String sArquivo)
	{
		arquivo = sArquivo;
	}
}
