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
		render(comentarios, noticia);
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
		String senhaDigitada = params.get("senha");

		System.out.println("Senha digitada : " + senhaDigitada);
		System.out.println("Senha criptografada esperada: " + comentario.senha);

		if (senhaDigitada == null || !comentario.senha.equals(CriptografiaUtils.criptografarMD5(senhaDigitada))) {
			flash.error("Senha incorreta. Comentário não removido.");
			System.out.println("estou dentro do if");
			listar(comentario.noticia.id);
			return;
		}

		comentario.status = Status.INATIVO;
		comentario.save();
		flash.success("Comentário removido com sucesso.");
		System.out.println("Deu certo");
		listar(comentario.noticia.id);
	}

	public static void editar(Long id) {
		Comentario comentario = Comentario.findById(id);

		Noticia noticia = comentario.noticia;
		renderTemplate("Comentarios/form.html", comentario, noticia);
	}

	
}
