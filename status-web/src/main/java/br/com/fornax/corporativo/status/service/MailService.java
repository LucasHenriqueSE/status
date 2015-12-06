package br.com.fornax.corporativo.status.service;

import br.com.fornax.corporativo.status.model.Rede;


public interface MailService {
	public void enviaEmailConexaoFora(Rede servico);
	public void enviaEmailConexaoEstabelecida(Rede servico);
}
