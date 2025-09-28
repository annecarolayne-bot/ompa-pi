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

	//Recebe o Id da Noticia porque eu quero cadastrar o comentario visualizando a noticia encima
	public static void form(Long noticiaId) {
		Noticia noticia = Noticia.findById(noticiaId);
		render(noticia);
	}

	//Recebe o id da Noticia porque eu quero vê-la encima da lista de comentarios cadastrados como id dela
	public static void listar(Long noticiaId) {
		Noticia noticia = Noticia.findById(noticiaId);
		
		//Acha os comentários daquela noticia
		List<Comentario> comentarios = Comentario.find("noticia.id = ?1 AND status = ?2", noticiaId, Status.ATIVO)
				.fetch();
		
		//Armazena a quantidade de comentarios daquela noticia
		 int totalComentarios = comentarios.size();
		
		 //Manda para o template a noticia a ser apresentada, os comentarios dela e o número de comentarios
		render(comentarios, noticia, totalComentarios);
	}

	//Recebe o comentario a ser salvo
	public static void salvar(Comentario comentario) {
		//Se o comentario for novo, cadastra a data atual nele
		if (comentario.data == null) {
			comentario.data = new Date();
		}
		
		//Criptografa a senha recebida
		if (comentario.senha != null) {
			comentario.senha = CriptografiaUtils.criptografarMD5(comentario.senha);
		}
		
		//salva o comentario e armazena a mensagem de sucesso no flash
		flash.success("Comentário salvo com sucesso!");
		comentario.save();
		
		//Vai para o listar que recebe um noticiaId
		listar(comentario.noticia.id);
	 }

	//Recebe o id do comentario a ser removido    
	public static void remover(Long id) {
		//Acha esse comentario pelo id e realiza a exclusão lógica
		Comentario comentario = Comentario.findById(id);

		comentario.status = Status.INATIVO;
		comentario.save();
		flash.success("Comentário removido com sucesso.");
		
		//Vai para o listar que recebe um noticiaId
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
