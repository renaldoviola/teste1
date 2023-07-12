package br.com.agricopel.gerasdcvobc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agricopel.gerasdcvobc.controller.dto.PlanilhaSdcvDto;
import br.com.agricopel.gerasdcvobc.exception.TratarException;
import br.com.agricopel.gerasdcvobc.service.CompradorService;
import br.com.agricopel.gerasdcvobc.service.FilialService;
import br.com.agricopel.gerasdcvobc.service.SdcvObcService;

@Controller
public class PlanilhaSdcvController {

	@Autowired
	private SdcvObcService sdcvObcService;

	@Autowired
	private CompradorService compradorService;

	@Autowired
	private FilialService filialService;

	@GetMapping("/")
	public String formPlanilha(Model model) throws IOException {

		PlanilhaSdcvDto planilhaSdcvDto = new PlanilhaSdcvDto();
		planilhaSdcvDto.setCompradores(compradorService.findAll());
		planilhaSdcvDto.setFiliais(filialService.findAll());

		model.addAttribute("planilhaSdcvDto", planilhaSdcvDto);

		return "uploadForm";
	}

	@PostMapping("/")
	public String procPlanilhaSdcv(@RequestParam(name = "idComprador") Integer idComprador,
			@RequestParam(name = "idFilial") Integer idFilial,
			@RequestParam(name = "planilhaExcel") MultipartFile planilhaExcel,
			@RequestParam(name = "email") String email, RedirectAttributes redirectAttributes) {

		try {
			sdcvObcService.gerarSDCV(planilhaExcel, idComprador, idFilial, email);

			redirectAttributes.addFlashAttribute("mensagem",
					"Processando arquivo enviado, resultado será enviado por e-mail!");
		} catch (Exception e) {
			TratarException.tratarExcecao(e);

			redirectAttributes.addFlashAttribute("erro", "Falha ao processar arquivo Excel!"
					.concat(planilhaExcel.getOriginalFilename()).concat(" - Erro: ").concat(e.getMessage()));
		}

		return "redirect:/";
	}
}