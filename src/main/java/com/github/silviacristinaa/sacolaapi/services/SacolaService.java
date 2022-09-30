package com.github.silviacristinaa.sacolaapi.services;

import com.github.silviacristinaa.sacolaapi.models.Item;
import com.github.silviacristinaa.sacolaapi.models.Sacola;
import com.github.silviacristinaa.sacolaapi.resources.dtos.ItemDto;

public interface SacolaService {
	Item incluirItemNaSacola(ItemDto itemDto);
	Sacola verSacola(Long id);
	Sacola fecharSacola(Long id, int formaPagamento);

}
