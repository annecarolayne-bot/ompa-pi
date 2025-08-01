package controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

import models.Assunto;

import models.Noticia;

import models.Status;
import play.Play;
import play.mvc.Controller;

public class Noticias extends Controller{

	public static void form() {
		List<Assunto> assuntos = Assunto.findAll();
		render(assuntos);
	}
	
	public static void listar(String termo) {
	    List<Noticia> noticias;

	    if (termo == null || termo.trim().isEmpty()) {
	        noticias = Noticia.find("status = ?1 order by dataPublicacao desc", Status.ATIVO).fetch();
	    } else {
	        String termoBusca = "%" + termo.toLowerCase() + "%";
	        noticias = Noticia.find(
	            "(lower(titulo) like ?1 or lower(autor) like ?1 or lower(assunto) like ?1) and status = ?2 order by dataPublicacao desc",
	            termoBusca,
	            Status.ATIVO
	        ).fetch();
	    }
	    
	    render(noticias, termo);
	}
	
	public static void salvar(Noticia noticia, File imagemCapa) {
		 if (noticia.dataPublicacao == null) {
		        noticia.dataPublicacao = new Date();
		  }
		 
		 if (imagemCapa != null) {
		        // Crie um nome único para a imagem
		        String nomeArquivo = System.currentTimeMillis() + "_" + imagemCapa.getName();
		        
		        // Salve a imagem em uma pasta pública do projeto, por exemplo /public/imagens/
		        File destino = new File(Play.getFile("public/images"), nomeArquivo);
		        imagemCapa.renameTo(destino);

		        // Salve o caminho da imagem na notícia
		        noticia.caminhoImagem = "/public/images/" + nomeArquivo;
		    }
		 
		noticia.save();
		listar(null);
	}
	
	public static void remover(Long id) {
		Noticia noticia = Noticia.findById(id);
		noticia.status = Status.INATIVO;
		noticia.save();
		listar(null);
	}
	
	public static void editar(Long id) {
		Noticia n = Noticia.findById(id);
		List<Assunto> assuntos = Assunto.findAll();
		
		renderTemplate("Noticias/form.html", n, assuntos);
	}
	
	
	
}
