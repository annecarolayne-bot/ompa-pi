package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Noticia extends Model{

	public String titulo;
	public String conteudo;
	public String autor;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
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
		return " ("+ titulo + ")"+ conteudo  + " -"+ autor+ "- ";
	}
	
	
	
}
