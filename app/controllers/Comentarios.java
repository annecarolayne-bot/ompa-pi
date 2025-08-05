package controllers;

import java.util.Date;
import java.util.List;

import models.Comentario;
import models.Noticia;
import models.Status;

import play.mvc.Controller;
import play.mvc.results.Result;
import util.CriptografiaUtils;

public class Comentarios extends Controller {

	public static void form(Long noticiaId) {
		Noticia noticia = Noticia.findById(noticiaId);
		render(noticia);
	}

	public static void listar(Long noticiaId) {
		Noticia noticia = Noticia.findById(noticiaId);
		List<Comentario> comentarios = Comentario.find("noticia.id = ?1 AND status = ?2", noticiaId, Status.ATIVO)
				.fetch();
		
		 int totalComentarios = comentarios.size();
		
		render(comentarios, noticia, totalComentarios);
	}

	public static void salvar(Comentario comentario) {
		if (comentario.data == null) {
			comentario.data = new Date();
		}
		if (comentario.senha != null) {
			comentario.senha = CriptografiaUtils.criptografarMD5(comentario.senha);
		}
		flash.success("Comentário salvo com sucesso!");
		comentario.save();
		listar(comentario.noticia.id);
	 }

	    

	public static void remover(Long id) {
		Comentario comentario = Comentario.findById(id);

		comentario.status = Status.INATIVO;
		comentario.save();
		flash.success("Comentário removido com sucesso.");
		
		listar(comentario.noticia.id);
	}

	public static void editar(Long id) {
		Comentario comentario = Comentario.findById(id);

		Noticia noticia = comentario.noticia;
		renderTemplate("Comentarios/form.html", comentario, noticia);
	}

	public static void curtir(Long id) {
	    Comentario c = Comentario.findById(id);
	    if (c != null) {
	    	if (c.curtidas == null) {
	    	    c.curtidas = 0;
	    	}
	       c.curtidas = c.curtidas+1;
	       c.save(); // ou update()
	    }

	    Long noticiaId = Long.valueOf(params.get("noticiaId"));
	    listar(noticiaId);
	}

	public static void descurtir(Long id) {
	    Comentario c = Comentario.findById(id);
	    if (c != null) {
	    	if (c.descurtidas == null) {
	    	    c.descurtidas = 0;
	    	}
	        c.descurtidas = c.descurtidas+1;
	        c.save(); // ou update()
	    }

	    Long noticiaId = Long.valueOf(params.get("noticiaId"));
	    listar(noticiaId);
	}
	

	
}
