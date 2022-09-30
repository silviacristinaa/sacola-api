package com.github.silviacristinaa.sacolaapi.resources.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ItemDto {

	private Long idProduto;
	private int quantidade;
	private Long idSacola;
}