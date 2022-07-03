package br.ce.wcaquino.entidades;

import java.util.Date;

public class NotaAluguel {

	private int preco;
	
	private Date dataEntrega;
	
	private int potuacao;

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public int getPotuacao() {
		return potuacao;
	}

	public void setPotuacao(int potuacao) {
		this.potuacao = potuacao;
	}
	
}
