package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Assunto extends Model{
	
	public String titulo;
	public String descricao;
	
	public Assunto() {
		
	}
	
	public Assunto(String titulo, String descricao) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return titulo + "(" + descricao + ")";
	}
	
	

}
