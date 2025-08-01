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
public class Noticia extends Model{

	public String titulo;
	public String conteudo;
	public String autor;
	public String senha;
	
	@ManyToOne
	public Assunto assunto;

	@Temporal(TemporalType.DATE)
	public Date dataPublicacao;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public String caminhoImagem;
	
	public Noticia() {
		this.status = Status.ATIVO;
	}
	
	public Noticia(String titulo, String conteudo, String autor) {
		this.status = Status.ATIVO;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.autor = autor;
	}
	
	@Override
	public String toString() {
		return " ("+ titulo + ")"+ conteudo  + " -"+ autor+ "- "+ assunto+ " -"+ dataPublicacao ;
	}
	
	
	
}
