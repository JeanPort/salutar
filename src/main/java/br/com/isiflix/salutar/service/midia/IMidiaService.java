package br.com.isiflix.salutar.service.midia;

import br.com.isiflix.salutar.model.Midia;

public interface IMidiaService {

    Midia cadastrar(Midia nova);
    Midia alterar(Midia midia);
    boolean excluir(Integer id);
    Midia recuperarPorId(Integer id);
}
