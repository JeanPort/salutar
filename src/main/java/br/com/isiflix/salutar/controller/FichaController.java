package br.com.isiflix.salutar.controller;

import br.com.isiflix.salutar.model.FichaPaciente;
import br.com.isiflix.salutar.service.ficha.IFichaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FichaController {

    @Autowired
    private IFichaService service;

    @GetMapping(value = "/fichas/busca")
    public ResponseEntity<List<FichaPaciente>> recuperarTodos(@RequestParam(name = "nome") String nome) {
        List<FichaPaciente> res = service.buscarPorNome(nome);
        if (!res.isEmpty()){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(value = "/fichas/{id}")
    public ResponseEntity<FichaPaciente> buscarPorId(@PathVariable("id") Integer id) {
        FichaPaciente res = service.recuperarPorId(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/fichas")
    public ResponseEntity<FichaPaciente> cadastrar(@RequestBody FichaPaciente nova) throws URISyntaxException {
        FichaPaciente res = service.cadastrar(nova);
        if (res != null) {
            return ResponseEntity.created(new URI("/fichas/"+ res.getIdFicha())).body(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/fichas/{id}")
    public ResponseEntity<FichaPaciente> alterar(@PathVariable("id") Integer id, @RequestBody FichaPaciente fichaPaciente) {
        fichaPaciente.setIdFicha(id);
        FichaPaciente res = service.alterar(fichaPaciente);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/fichas/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Integer id) {
        boolean res = service.excluir(id);
        if (res) {
            return ResponseEntity.ok("Excluido com sucesso");
        }
        return ResponseEntity.notFound().build();
    }
}
