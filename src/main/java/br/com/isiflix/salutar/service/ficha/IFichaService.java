package br.com.isiflix.salutar.service.ficha;

import br.com.isiflix.salutar.model.FichaPaciente;

import java.util.List;

public interface IFichaService {

    FichaPaciente cadastrar(FichaPaciente nova);
    FichaPaciente alterar(FichaPaciente fichaPaciente);
    List<FichaPaciente> buscarPorNome(String nome);
    FichaPaciente recuperarPorId(Integer id);
    boolean excluir(Integer id);

}
