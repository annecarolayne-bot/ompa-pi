package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
	
	public String nome;
	public String email;
	
	@Required
	public String senha;
	
	@Enumerated(EnumType.STRING)
	public Perfil perfil;
	
	public Usuario() {
		this.perfil = Perfil.ALUNO;	
	}
	
	
	
	
	
	
	
	
	
	
	

}
