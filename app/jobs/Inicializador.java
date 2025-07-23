package jobs;

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
		n1.save();
		
		Noticia n2 = new Noticia("Oficina de Dobraduras", "Conteúdo", "José");
		n2.save();
		
		Comentario c1 = new Comentario("Muito legal, recomendo!", 120, n1);
		c1.save();
		}	
	}
	
	
	
}
