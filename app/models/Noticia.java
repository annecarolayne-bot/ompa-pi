package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Noticia extends Model{

	@Required
	public String titulo;
	
	public String nomeAutor;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	@Required
	public String conteudo;

	@Temporal(TemporalType.DATE)
	public Date dataPublicacao;

	@Temporal(TemporalType.DATE)
	public Date dataEdicao;
	
	@Enumerated(EnumType.STRING)
	@Required
	public Status status;
	
	@ManyToOne
	@Required
	public Assunto assunto;
	
	@ManyToOne
	public Usuario autor;
	
	public String caminhoImagem;
	
	public Noticia() {
		this.status = Status.ATIVO;
	}
	
	
	@Override
	public String toString() {
		return " ("+ titulo + ")"+ conteudo  + " -"+ autor+ "- "+ assunto+ " -"+ dataPublicacao ;
	}
	
	
	
}
