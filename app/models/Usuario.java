package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
	
	@Required
	public String nome;
	
	@Required
	@Email
	public String email;
	
	@Required
	public String senha;
	
	@Enumerated(EnumType.STRING)
	@Required
	public Perfil perfil;
	
	public Usuario() {
		this.perfil = Perfil.ALUNO;	
	}
	
	
	
	
	
	
	
	
	
	
	

}
