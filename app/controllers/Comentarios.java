package controllers;

import java.util.Date;
import java.util.List;

import models.Comentario;
import models.Noticia;
import models.Status;
import models.Usuario;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
import play.mvc.results.Result;
import util.CriptografiaUtils;

@With(Seguranca.class)
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
	public static void salvar(@Valid Comentario comentario) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			Comentarios.form(comentario.noticia.id);
		}
		
		if(comentario.id == null) {
		
		if (comentario.data == null) {
			comentario.data = new Date();
		}
		
		comentario.autor = Usuario.find("lower(email) like ?1" , session.get("usuarioLogado").toLowerCase()).first();
		comentario.save();	
		//salva o comentario e armazena a mensagem de sucesso no flash
		flash.success("Comentário salvo com sucesso!");
		
		} else {
			if(comentario.autor.email.equalsIgnoreCase(session.get("usuarioLogado"))) {
				if (comentario.data == null) {
					comentario.data = new Date();
				}
				
				comentario.save();
				flash.success("Comentário editado com sucesso!");	
			}else {
				flash.error("Impossível editar este comentário, você não é o autor.");
			}
		}
		
		//Vai para o listar que recebe um noticiaId
		listar(comentario.noticia.id);
	 }

	//Recebe o id do comentario a ser removido    
	public static void remover(Long id) {
		//Acha um comentario pelo id e realiza a exclusão lógica
		
		Comentario comentario = Comentario.findById(id);
		
       if(comentario.autor.email.equalsIgnoreCase(session.get("usuarioLogado"))){
    	   comentario.status = Status.INATIVO;
   		comentario.save();
   		flash.success("Comentário removido com sucesso.");
       }else {
    	   flash.error("Impossível excluir o comentário, você não é o autor.");
       }

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
