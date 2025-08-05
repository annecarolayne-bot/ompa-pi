package jobs;

import java.util.Date;

import models.Assunto;
import models.Comentario;
import models.Noticia;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job{

	@Override
	public void doJob() throws Exception{
		if(Noticia.count()==0) {
		Noticia n1 = new Noticia("Nova Olimpíada de Matemática", "Assunto interessante", "Maria");
		n1.dataPublicacao = new Date();
		n1.save();
		
		Noticia n2 = new Noticia("Oficina de Dobraduras", "Conteúdo", "José");
		n2.dataPublicacao = new Date();
		n2.save();
		
		Comentario c1 = new Comentario("Muito legal, recomendo!","José", 120,0, n1);
		c1.save();
		
		Assunto a1 = new Assunto("Tecnologia","tecnologia");
		a1.save();
		
		Assunto a2 = new Assunto("Saúde","saude");
		a2.save();
		
		Assunto a3 = new Assunto("Educação","educacao");
		a3.save();
		
		Assunto a4 = new Assunto("Cultura","cultura");
		a4.save();
		
		}	
	}
	
	
	
}
