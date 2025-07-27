package controllers;

import java.util.List;

import models.Noticia;
import models.Status;
import play.mvc.Controller;

public class Noticias extends Controller{

	public static void form() {
		render();
	}
	
	public static void listar() {
		List <Noticia> noticias = Noticia.find("status = ?1", Status.ATIVO).fetch();
		render(noticias);
	}
	
	public static void salvar(Noticia noticia) {
		noticia.save();
		listar();
	}
	
	public static void remover(Long id) {
		Noticia noticia = Noticia.findById(id);
		noticia.status = Status.INATIVO;
		noticia.save();
		listar();
	}
	
	public static void editar(Long id) {
		Noticia n = Noticia.findById(id);
		renderTemplate("Noticias/form.html", n);
	}
	
	
	
}
