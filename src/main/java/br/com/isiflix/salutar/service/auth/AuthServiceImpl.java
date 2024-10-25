package br.com.isiflix.salutar.service.auth;

import br.com.isiflix.salutar.dao.UsuarioDAO;
import br.com.isiflix.salutar.model.Usuario;
import br.com.isiflix.salutar.security.SalutarToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private UsuarioDAO dao;

    @Override
    public Usuario criar(Usuario novo) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        novo.setSenha(encoder.encode(novo.getSenha()));
        return dao.save(novo);
    }

    @Override
    public SalutarToken login(Usuario dadosLogin) {

        Usuario res = dao.findByLogin(dadosLogin.getLogin());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (res != null) {
            if (encoder.matches(dadosLogin.getSenha(), res.getSenha())) {
                return  new SalutarToken("*isidro123");
            }
        }
        return null;
    }
}
