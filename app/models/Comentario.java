package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comentario extends Model{

	public String conteudo;
	public Integer curtidas;
	
	@ManyToOne
	public Noticia noticia;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Comentario() {
		this.status = Status.ATIVO;
	}
	

	public Comentario(String conteudo, Integer curtidas, Noticia noticia) {
		
		this.conteudo = conteudo;
		this.curtidas = curtidas;
		this.noticia = noticia;
		
	}
	
	
	
	
}


//++Detalhar de noticia
//Formulario de comentario