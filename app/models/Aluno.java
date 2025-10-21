package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Aluno extends Model{

	public String matricula;
	public String curso;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	@OneToOne
	public Usuario usuario;
	
	public Aluno() {
		this.status = Status.ATIVO;
	}
	
	
}
