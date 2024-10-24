package br.com.isiflix.salutar.service.ficha;

import br.com.isiflix.salutar.dao.FichaPacienteDAO;
import br.com.isiflix.salutar.model.FichaPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FichaServiceImpl implements IFichaService{

    @Autowired
    private FichaPacienteDAO dao;

    @Override
    public FichaPaciente cadastrar(FichaPaciente nova) {
        nova.setAtivo(1);
        nova.setUuid(UUID.randomUUID().toString());
        return dao.save(nova);
    }

    @Override
    public FichaPaciente alterar(FichaPaciente fichaPaciente) {
        return dao.save(fichaPaciente);
    }

    @Override
    public List<FichaPaciente> buscarPorNome(String nome) {
        return dao.findAllByNomeContaining(nome);
    }

    @Override
    public FichaPaciente recuperarPorId(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public boolean excluir(Integer id) {
        FichaPaciente res = recuperarPorId(id);
        if (res != null) {
            res.setAtivo(0);
            dao.save(res);
            return true;
        }
        return false;
    }
}
