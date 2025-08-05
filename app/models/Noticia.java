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

import play.db.jpa.Model;

@Entity
public class Noticia extends Model{

	public String titulo;
	
	public String autor;
	public String senha;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	public String conteudo;

	@Temporal(TemporalType.DATE)
	public Date dataPublicacao;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@ManyToOne
	public Assunto assunto;
	
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
