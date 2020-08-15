package br.com.alura.loja.modelo;

import com.thoughtworks.xstream.XStream;

public class Projeto {
	
	private String nome;
	private long id;
	private int anoDeInicio;
	
	public Projeto() {
		
	}
	
	public Projeto(long id, String nome, int anoDeInicio) {
		this.id = id;
		this.nome = nome;
		this.anoDeInicio = anoDeInicio;
	}
	
	public String getNome() {
		return nome;
	}

	public long getId() {
		return id;
	}
	
	public int getAnoDeInicio() {
		return anoDeInicio;
	}	
	
	public void setId(long id) {
		this.id = id;
	}

	public String toXML() {
		return new XStream().toXML(this);
	}
	
	
}
