package br.com.isiflix.salutar.controller;

import br.com.isiflix.salutar.model.Midia;
import br.com.isiflix.salutar.service.midia.IMidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin("*")
public class MidiaController {


    @Autowired
    private IMidiaService service;

    @GetMapping(value = "/midias/{id}")
    public ResponseEntity<Midia> recuperarPorId(@PathVariable("id") Integer id) {
        Midia res = service.recuperarPorId(id);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/midias")
    public ResponseEntity<Midia> cadastrar(@RequestBody Midia nova) throws URISyntaxException {
        Midia res = service.cadastrar(nova);
        if (res != null) {
            return ResponseEntity.created(new URI("/midias/"+ res.getNumSec())).body(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/mididas/{id}")
    public ResponseEntity<Midia> atualizar(@PathVariable("id") Integer id, @RequestBody Midia midia) {
        if (midia.getNumSec() == null) {
            midia.setNumSec(id);
        }
        Midia res = service.alterar(midia);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/midias/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean res = service.excluir(id);
        if (res) {
            return ResponseEntity.ok("Excluido com sucesso");
        }
        return ResponseEntity.notFound().build();
    }
}
