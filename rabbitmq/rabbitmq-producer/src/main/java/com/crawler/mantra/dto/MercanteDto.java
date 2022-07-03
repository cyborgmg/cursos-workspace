package com.crawler.mantra.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class MercanteDto {
	private Long corporationId;
	private String numeroBL;
	private String numeroCeMercante;
	private String dataOperacao;
	private String portoDeOrigem;
	private String portoDeDestino;
	private String agenteDeCarga;
	private String importador;
	private List<String> containers;
}
