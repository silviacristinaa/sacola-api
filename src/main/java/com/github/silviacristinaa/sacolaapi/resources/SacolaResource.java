package com.github.silviacristinaa.sacolaapi.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.silviacristinaa.sacolaapi.models.Item;
import com.github.silviacristinaa.sacolaapi.models.Sacola;
import com.github.silviacristinaa.sacolaapi.resources.dtos.ItemDto;
import com.github.silviacristinaa.sacolaapi.services.SacolaService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(value ="/ifood-devweek/sacolas")
@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaResource {

	private final SacolaService sacolaService;
	
	@PostMapping
	public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) {
		return sacolaService.incluirItemNaSacola(itemDto);
	}
	
	@GetMapping("/{id}")
	public Sacola verSacola(@PathVariable("id") Long id) { 
		return sacolaService.verSacola(id);
	}
	
	@PatchMapping("/fecharSacola/{idSacola}")
	public Sacola fecharSacola(@PathVariable("idSacola") Long idSacola, 
							   @RequestParam("formaPagamento") int formaPagamento) { 
		return sacolaService.fecharSacola(idSacola, formaPagamento);
	}
	
}