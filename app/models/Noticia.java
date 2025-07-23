package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Noticia extends Model{

	public String titulo;
	public String conteudo;
	public String autor;
	
	public Noticia() {
		
	}
	
	public Noticia(String titulo, String conteudo, String autor) {
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.autor = autor;
	}
	
	@Override
	public String toString() {
		return " ("+ titulo + ")"+ conteudo  + " -"+ autor+ "- ";
	}
	
	
	
}
