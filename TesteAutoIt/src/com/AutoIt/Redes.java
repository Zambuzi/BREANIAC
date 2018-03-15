package com.AutoIt;

import autoitx4java.AutoItX;

public class Redes {
	
	public void efetuaLoginRedes(String aplicacao, String usuario, String senha, AutoItX x) {
		
		String tela = "EXTRA! X-treme";
	       
        System.out.println("instanciou as variaveis");
        
        
     
        x.run("C:\\Program Files (x86)\\Attachmate\\EXTRA!\\EXTRA.exe", "Z:\\EXTRA!-v9.2.0\\", AutoItX.SW_RESTORE);
        //x.run("C:\\Windows\\System32\\notepad.exe");
        System.out.println("Executou o notepad");
        //x.winActivate();;
        System.out.println("colocou foco no notepad");
        x.winWaitActive(tela);
        System.out.println("Aguarda ativação do notepad");
        x.send("{ENTER}", false);
        x.sleep(7000);
        
        
        
        
        x.send(aplicacao);
        
        x.send("{ENTER}", false);
        x.sleep(3000);
        System.out.println("abriu a aplicacao: "+ aplicacao);
        
        x.send(usuario);
        x.sleep(1000);
        x.send("{ENTER}", false);
        x.sleep(3000);
        
        
        x.send(senha);
        x.sleep(3000);
        x.send("{ENTER}", false);
        //x.sleep(3000);
		
	}

	
	public void acessaG120(AutoItX x) {
		x.send("{ENTER}", false);
		x.sleep(2000);
		x.send("k100");
		x.send("{ENTER}", false);
		x.sleep(2000);
		x.send("{ENTER}", false);
		x.sleep(2000);
		
		
		x.send("G120");
		x.send("{ENTER}", false);
		x.sleep(2000);
		
		
	}
	
	public void preencheDadosG120(AutoItX x, String estabelecimento) {
		x.send("X");
		x.send(estabelecimento);
		x.send("{ENTER}", false);
		x.sleep(3000);
		x.send("POS");
		x.send("{ENTER}", false);
		x.sleep(3000);
	}
	
	
	public void validaBandeira(String bandeira, AutoItX x) {
		x.mouseClickDrag("left", 9, 325, 1352, 692);
		x.sleep(1000);
		x.send("{CTRLDOWN}c", false);
		String textoMainframe;
		x.sleep(1000);
		textoMainframe = x.clipGet();
		x.send("{CTRLUP}", false);
		System.out.println(textoMainframe);
		
		boolean encontrou_bandeira = false;
	    boolean ultima_pagina = false;
	    boolean encontrou_produto = false;
	   // boolean fim_produto = false;
	    
	    while(!encontrou_bandeira && !ultima_pagina && !encontrou_produto) {
	    	System.out.println(bandeira);
	    	System.out.println(textoMainframe.contains(bandeira));
	    	System.out.println(textoMainframe);
	    	if(!textoMainframe.contains(bandeira)) {
	    		x.send("{F8}", false);
	    		x.sleep(4000);
	    		if(textoMainframe.contains("NAO HA PAGINA POSTERIOR")) {
	    			ultima_pagina = true;
	    			System.out.println("Bandeira não Encontrada!");
	    		}else {
	    			x.sleep(3000);
	    			x.mouseClickDrag("left", 9, 325, 1352, 692);
	    			x.sleep(2000);
	    			
	    			x.send("{CTRLDOWN}c", false);
	    			x.sleep(1000);
	    			textoMainframe = x.clipGet();
	    			x.send("{CTRLUP}", false);
	    			x.sleep(1000);
	    		}
	    	}else {
	    		System.out.println("Encontrou a Bandeira!");
	    		encontrou_bandeira = true;
	    	}
	    
		
	    }
	
	
	}
	
	
	public void validaTipoPagamento(String bandeira, String habilitado, String tipoPagamento, AutoItX x) {
		boolean encontrou_bandeira = false;
		boolean encontrou_produto = false;
		boolean encontrou_outra_bandeira = false;
		String textoMainframe;
		
		while(!encontrou_produto){
			x.mouseClickDrag("left", 9, 325, 1352, 692);
			x.send("{CTRLDOWN}c", false);
			textoMainframe = x.clipGet();
			x.send("{CTRLUP}", false);
			
			String vetorTela[] = textoMainframe.split("\n");
			System.out.println(vetorTela.length);
			
			for(int i = 1; i < vetorTela.length; i++) {
				System.out.println(vetorTela[i]);
				if(vetorTela[i].contains(bandeira)) {
					encontrou_bandeira = true;
				}else if (encontrou_bandeira && vetorTela[i].contains("0000")) {
					encontrou_outra_bandeira = true;
					System.out.println("Produto nao encontrado!");
					return;
				}else if (encontrou_bandeira && vetorTela[i].contains(tipoPagamento)) {
					encontrou_produto = true;
					System.out.println("Encontrou produto!");
					if(habilitado == "SIM") {
						if(vetorTela[i].contains("SIM")) {
							System.out.println("O status do produto bate com o pesquisado");
						}else {
							System.out.println("O status do produto não bate com o pesquisado!");
						}
					}else {
						if(!vetorTela[i].contains("SIM")) {
							System.out.println("O status do produto bate com o pesquisado");
						}else {
							System.out.println("O status do produto não bate com o pesquisado!");
						}
					}
					return;
				}
			}
			if(encontrou_outra_bandeira || encontrou_produto) {
				return;
			}
			
			if(!encontrou_produto) {
				x.send("{F8}", false);
				x.sleep(2000);
			}
		}
	}
	
	
	
	
}
