package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class Responsavel extends Model{
	
	@OneToOne
	public Usuario usuario;
	
	
	
}
