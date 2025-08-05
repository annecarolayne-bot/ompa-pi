package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class Comentario extends Model{

	public String conteudo;
	public Integer curtidas;
	public Integer descurtidas;
	public String autor;
	public String senha;
	
	@Temporal(TemporalType.DATE)
	public Date data;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@ManyToOne
	public Noticia noticia;
	
	public Comentario() {
		this.status = Status.ATIVO;
	}

	public Comentario(String conteudo, String autor, Integer curtidas, Integer descurtidas, Noticia noticia) {
		this.status = Status.ATIVO;
		this.conteudo = conteudo;
		this.autor = autor;
		this.curtidas = curtidas;
		this.noticia = noticia;
		
	}
	
	
	
	
}


//++Detalhar de noticia
//Formulario de comentario