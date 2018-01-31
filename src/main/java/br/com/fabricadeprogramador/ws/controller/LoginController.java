package br.com.fabricadeprogramador.ws.controller;


import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricadeprogramador.ws.model.Usuario;
import br.com.fabricadeprogramador.ws.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/autenticar", consumes=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		if(usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e Senha sao obrigatorios");
		}
		
		//Consulta no banco
		Usuario usuarioAutenticado = usuarioService.buscarPorNome(usuario.getNome());
		
		if(usuarioAutenticado == null) {
			throw new ServletException("Usuario nao encontrado");
		}
		
		if(!usuarioAutenticado.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Usuario ou senha invalida");
		}
		
		//TOKEN
		String token = Jwts.builder()
				.setSubject(usuarioAutenticado.getNome())
				.signWith(SignatureAlgorithm.HS512, "root")
				.compact();
		      //.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
		
		return new LoginResponse(token);
	}
	
	private class LoginResponse{
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
		
		
	}
}
