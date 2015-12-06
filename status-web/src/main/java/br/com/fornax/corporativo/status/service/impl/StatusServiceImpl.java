package br.com.fornax.corporativo.status.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.fornax.corporativo.status.model.Rede;
import br.com.fornax.corporativo.status.service.MailService;
import br.com.fornax.corporativo.status.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {
	private static Rede vpn;
	private static Rede internet;
	
	@Inject
	private MailService mailService;

	public StatusServiceImpl() {
		vpn = new Rede();
		vpn.setNome("PORTO");
		vpn.setUrl("http://u28/index.html");
		vpn.setQtdErros(0);
		vpn.setEnviarEmail(true);

		internet = new Rede();
		internet.setNome("INTERNET");
		internet.setUrl("http://www.fornax.com.br");
		internet.setQtdErros(0);
		internet.setEnviarEmail(false);
	}

	@Override
	public Rede statusConexao(String servico) {
		if (vpn.getNome().equals(servico)) {
			return vpn;
		} else if (internet.getNome().equals(servico)) {
			return internet;
		}
		return null;
	}

	private String verificaConexao(Rede servico) {
		HttpURLConnection conexao = null;
		try {
			conexao = (HttpURLConnection) new URL(servico.getUrl())
					.openConnection();
			servico.setStatus(Integer.valueOf(conexao.getResponseCode())
					.toString());
		} catch (Exception e) {
			servico.setStatus("0");
		}
		verificaStarted(servico);
		return servico.getStatus();
	}

	private void verificaStarted(Rede servico) {

		// Se o serviço não estiver em execução e o status do serviço igual
		// 200(quando o serviço for iniciado) grava a data e
		// hora de início e exibe uma msg de serviço iniciado.
		if (!servico.getStarted() && servico.getStatus().equals("200")) {
			servico.setStarted(Boolean.TRUE);
			servico.setDataInicio(Calendar.getInstance());

			System.out.println("Serviço: " + servico.getNome()
					+ " iniciado em " + servico.getDataInicio().getTime());
			
			if(servico.getEnviarEmail() == true){
				mailService.enviaEmailConexaoEstabelecida(servico);
			}

			// Se o serviço estiver iniciado e o status do serviço for diferente
			// de 200(quando o serviço "cair") grava a data e hora de término e
			// exibe uma msg mostrando a duração do serviço, exibe uma msg de
			// serviço terminado.
		} else if (servico.getStarted() && !servico.getStatus().equals("200")) {
			if (servico.getQtdErros() == 3) {
				servico.setDataFim(Calendar.getInstance());
				 if(servico.getEnviarEmail() == true){
					 System.out.println("Enviando e-mail...");
					 mailService.enviaEmailConexaoFora(servico);
				 }

				servico.setStarted(Boolean.FALSE);
				
				servico.setTempoDeConexao((servico.getDataFim()
						.getTimeInMillis() - servico.getDataInicio()
						.getTimeInMillis()) / 1000);
				servico.setQtdErros(0);

				System.out.println("Serviço: " + servico.getNome()
						+ " finalizado em " + servico.getDataFim().getTime());
				System.out.println("Tempo Total de Conexão: " + servico.getTempoDeConexao());
				// Enquanto a quantidade de erros(tentativas de reconexão) for
				// menor que 3 soma + 1 na variável qtdErros.
			} else {
				servico.setQtdErros(servico.getQtdErros() + 1);
			}

		}
	}

	// Executa o metodo a cada 10seg.
	@Scheduled(fixedDelay = 10000)
	public void verificaConexao() {
		verificaConexao(internet);
		verificaConexao(vpn);
	}
}