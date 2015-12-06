package br.com.fornax.corporativo.status.model;

import java.util.Calendar;

public class Rede {
	private String nome;
	private String status;
	private String url;
	private Calendar dataInicio;
	private Calendar dataFim;
	private Boolean started = false;
	private long tempoDeConexao;
	private int qtdErros;
	private Boolean enviarEmail;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getStarted() {
		return started;
	}

	public void setStarted(Boolean started) {
		this.started = started;
	}

	public long getTempoDeConexao() {
		return tempoDeConexao;
	}

	public void setTempoDeConexao(long tempoDeConexao) {
		this.tempoDeConexao = tempoDeConexao;
	}

	public int getQtdErros() {
		return qtdErros;
	}

	public void setQtdErros(int qtdErros) {
		this.qtdErros = qtdErros;
	}

	public Boolean getEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(Boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}
}