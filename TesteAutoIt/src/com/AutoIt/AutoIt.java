package com.AutoIt;

import java.io.File;
import java.io.IOException;

import com.jacob.com.LibraryLoader;

import autoitx4java.AutoItX;

public class AutoIt {
	 public static Redes redes;
	 
	 public static void main(String[] args){
		 
		 File file = new File("lib", "jacob-1.18-x64.dll"); //path to the jacob dll
	        System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());
	        System.out.println("setou a propriedate!");
	        AutoItX x = new AutoItX();
	        System.out.println("Instanciou o autoit");
	        
	        redes = new Redes();
	        redes.efetuaLoginRedes("CICH0C1", "T1502F", "cielo343", x);
	        redes.acessaG120(x);
	        redes.preencheDadosG120(x, "2004211371");
	        redes.validaBandeira("ELO",x);
	        redes.validaTipoPagamento("ELO", "", "CREDITO A VISTA", x);
	        
		 
	 }
	 
}
