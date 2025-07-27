package controllers;

import java.util.List;

import models.Comentario;
import models.Noticia;
import models.Status;

import play.mvc.Controller;

public class Comentarios extends Controller {
	
	public static void form(Long noticiaId) {
		Noticia noticia = Noticia.findById(noticiaId);
		render(noticia);
	}

	public static void listar(Long noticiaId) {
		Noticia noticia = Noticia.findById(noticiaId);
		List<Comentario> comentarios = Comentario.find("noticia.id = ?1 AND status = ?2", noticiaId, Status.ATIVO).fetch();
		render(comentarios, noticia);
	}
	
	public static void salvar(Comentario comentario) {
		comentario.save();
		listar(comentario.noticia.id);
	}

	public static void remover(Long id) {
		Comentario comentario = Comentario.findById(id);
		comentario.status = Status.INATIVO;
		comentario.save();
		listar(comentario.noticia.id);
	}

	public static void editar(Long id) {
		Comentario c = Comentario.findById(id);
		Noticia noticia = c.noticia;
		renderTemplate("Comentarios/form.html", c, noticia);
	}
}

