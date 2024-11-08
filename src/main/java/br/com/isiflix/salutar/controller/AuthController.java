package br.com.isiflix.salutar.controller;

import br.com.isiflix.salutar.model.Usuario;
import br.com.isiflix.salutar.security.SalutarToken;
import br.com.isiflix.salutar.service.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private IAuthService service;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario novo) throws URISyntaxException {
        Usuario res = service.criar(novo);
        if (res != null) {
            return ResponseEntity.created(new URI("/usuarios/"+res.getId())).body(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<SalutarToken> login(@RequestBody Usuario usuario) {
        SalutarToken res = service.login(usuario);
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(403).build();
    }
}
