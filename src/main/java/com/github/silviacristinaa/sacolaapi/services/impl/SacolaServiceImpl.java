package com.github.silviacristinaa.sacolaapi.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.silviacristinaa.sacolaapi.enums.FormaPagamento;
import com.github.silviacristinaa.sacolaapi.models.Item;
import com.github.silviacristinaa.sacolaapi.models.Restaurante;
import com.github.silviacristinaa.sacolaapi.models.Sacola;
import com.github.silviacristinaa.sacolaapi.repositories.ProdutoRepository;
import com.github.silviacristinaa.sacolaapi.repositories.SacolaRepository;
import com.github.silviacristinaa.sacolaapi.resources.dtos.ItemDto;
import com.github.silviacristinaa.sacolaapi.services.SacolaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

	private final SacolaRepository sacolaRepository;
	private final ProdutoRepository produtoRepository;

	@Override
	public Item incluirItemNaSacola(ItemDto itemDto) {
		Sacola sacola = verSacola(itemDto.getIdSacola());

		if (sacola.isFechada()) {
			throw new RuntimeException("Esta sacola está fechada.");
		}

		Item itemParaSerInserido = Item.builder().quantidade(itemDto.getQuantidade()).sacola(sacola)
				.produto(produtoRepository.findById(itemDto.getIdProduto()).orElseThrow(() -> {
					throw new RuntimeException("Essa sacola não existe!");
				})).build();

		List<Item> itensDaSacola = sacola.getItens();
		if (itensDaSacola.isEmpty()) {
			itensDaSacola.add(itemParaSerInserido);
		} else {
			Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
			Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
			if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) {
				itensDaSacola.add(itemParaSerInserido);
			} else {
				throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes. Feche a sacola ou esvazie.");
			}
		}
		
		List<BigDecimal> valorDosItens = new ArrayList<>();
		for (Item itemDaSacola: itensDaSacola) { 
			BigDecimal valorTotalItem = 
					itemDaSacola.getProduto().getValorUnitario().multiply(BigDecimal.valueOf(itemDaSacola.getQuantidade()));
			valorDosItens.add(valorTotalItem);
		}
		
		BigDecimal valorTotalSacola = valorDosItens.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		
		sacola.setValorTotal(valorTotalSacola);
		sacolaRepository.save(sacola);
		return itemParaSerInserido;
	}

	@Override
	public Sacola verSacola(Long id) {
		return sacolaRepository.findById(id).orElseThrow(() -> {
			throw new RuntimeException("Essa sacola não existe!");
		});
	}

	@Override
	public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
		Sacola sacola = verSacola(id);
		if (sacola.getItens().isEmpty()) {
			throw new RuntimeException("Inclua itens na sacola!");
		}
		FormaPagamento formaPagamento = numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
		sacola.setFormaPagamento(formaPagamento);
		sacola.setFechada(true);
		return sacolaRepository.save(sacola);
	}

}
