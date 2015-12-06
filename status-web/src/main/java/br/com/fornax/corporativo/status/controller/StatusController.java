package br.com.fornax.corporativo.status.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fornax.corporativo.status.model.Rede;
import br.com.fornax.corporativo.status.service.StatusService;

@Controller
public class StatusController {

	@Inject
	private StatusService statusService;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		Rede portoRede = statusService.statusConexao("PORTO");
		Rede internet = statusService.statusConexao("INTERNET");
		
		System.out.println(portoRede.getNome() + " " + portoRede.getStatus());
		System.out.println(internet.getNome() + " " + internet.getStatus());

		mav.setViewName("index");
		mav.addObject("internet", internet);
		mav.addObject("vpn", portoRede);
		return mav;
	}
}
