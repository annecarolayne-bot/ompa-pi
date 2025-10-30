package controllers;

import models.Status;
import models.Usuario;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Usuarios extends Controller{
	
	public static void salvar(@Valid Usuario usuario) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			Logins.form();
		}
		
		Usuario userDuplicado = Usuario.find("email = ?1",usuario.email).first();
		
		if(userDuplicado==null) {
			usuario.save();
			flash.success("Usuário cadastrado com sucesso. Logue no sistema!");
		}else {
			flash.error("Este email já está cadastrado no sistema.");
		}
		
		Logins.form();
	}
	
	public static void remover(Long id) {
		Usuario usuario = Usuario.findById(id);
		usuario.delete();
		flash.success("Usuário deletado com sucesso!");
	}
	
	public static void editar(Long id) {
		Usuario u = Usuario.findById(id);
		renderTemplate("Logins/form.html", u);
	}

}
