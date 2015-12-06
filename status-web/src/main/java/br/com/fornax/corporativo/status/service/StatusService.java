package br.com.fornax.corporativo.status.service;

import br.com.fornax.corporativo.status.model.Rede;

public interface StatusService {
	Rede statusConexao(String servico);
}
