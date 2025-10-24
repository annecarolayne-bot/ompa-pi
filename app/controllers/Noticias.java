package controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

import models.Assunto;

import models.Noticia;

import models.Status;
import models.Usuario;
import play.Play;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;
import security.Administrador;
import util.CriptografiaUtils;

@With(Seguranca.class)
public class Noticias extends Controller{

	@Administrador
	public static void form() {
		List<Assunto> assuntos = Assunto.findAll();
		render(assuntos);
	}
	
	public static void listar(String termo) {
	    List<Noticia> noticias = null;

	    if (termo == null || termo.trim().isEmpty()) {
	        noticias = Noticia.find("status = ?1", Status.ATIVO).fetch();
	    } else {
	        noticias = Noticia.find("(lower(titulo) like ?1 or lower(nomeAutor) like ?1 ) and status = ?2 ",
	        		"%" + termo.toLowerCase() + "%",
	            Status.ATIVO
	        ).fetch();
	    }
	    
	    render(noticias, termo);
	}
	

	public static void salvar(@Valid Noticia noticia, File imagemCapa) {
		
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			Noticias.form();
		}
		
		 if (imagemCapa != null) {
		        String nomeArquivo = System.currentTimeMillis() + "_" + imagemCapa.getName();
		        
		        File destino = new File(Play.getFile("public/images"), nomeArquivo);
		        imagemCapa.renameTo(destino);

		        noticia.caminhoImagem = "/public/images/" + nomeArquivo;
		 }else {
		    	noticia.caminhoImagem = "/public/images/home-bg.jpg";;
		 }
		 
		 noticia.autor = Usuario.find("lower(email) like ?1" , session.get("usuarioLogado").toLowerCase()).first();
		  
		 if(noticia.id == null) {
			noticia.nomeAutor = noticia.autor.nome;
			
			 if (noticia.dataPublicacao == null) {
			        noticia.dataPublicacao = new Date();
			 }
		 }else {
			 if (noticia.dataEdicao == null) {
			        noticia.dataEdicao = new Date();
			 }
		 }
		 
		noticia.save();
		listar(null);
	}
	
	@Administrador
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
