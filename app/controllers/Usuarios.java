package controllers;

import models.Status;
import models.Usuario;
import play.mvc.Controller;

public class Usuarios extends Controller{
	
	public static void salvar(Usuario usuario) {
		usuario.save();
		flash.success("Usuário cadastrado com sucesso. Logue no sistema!");
		Logins.form();
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.status = Status.INATIVO;
		usuario.save();
	}
	
	public static void editar(Long id) {
		Usuario u = Usuario.findById(id);
		renderTemplate("Logins/form.html", u);
	}

}
