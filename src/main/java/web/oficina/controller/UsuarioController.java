package web.oficina.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.oficina.model.Usuario;
import web.oficina.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastrar")
    public String cadastrar(Usuario usuario) {
        return "usuarios/cadastrar";
    }

    @PostMapping("/novo")
    public String novo(@Valid Usuario usuario, BindingResult resultado) {
        if (resultado.hasErrors()) return "usuarios/cadastrar";
        usuarioService.salvar(usuario);
        return "redirect:/usuarios/cadastrosucesso";
    }

}
