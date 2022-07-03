package br.com.lambda.dto;

public class Usuario {
	
	private String nome;
	private int pontos;
	private boolean moderador;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nome, int pontos) {
		this.pontos = pontos;
		this.nome = nome;
		this.moderador = false;
	}
	
	
	public Usuario(String nome, int pontos, boolean moderador) {
		super();
		this.nome = nome;
		this.pontos = pontos;
		this.moderador = moderador;
	}
	
	
	public Usuario(String nome) {
		super();
		this.nome = nome;
	}
	
	
	
	public String getNome() {
		return nome;
	}
	public int getPontos() {
		return pontos;
	}
	public void tornaModerador() {
		this.moderador = true;
	}
	public boolean isModerador() {
		return moderador;
	}
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", pontos=" + pontos + ", moderador=" + moderador + "]";
	}
	
}
