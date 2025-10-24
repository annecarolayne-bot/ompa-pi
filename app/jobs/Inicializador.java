package jobs;

import java.util.Date;

import models.Aluno;
import models.Assunto;
import models.Comentario;
import models.Noticia;
import models.Perfil;
import models.Responsavel;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job{

	@Override
	public void doJob() throws Exception{
		if(Noticia.count()==0) {
	
		Usuario usuarioJoao = new Usuario();
		usuarioJoao.nome = "joao";
		usuarioJoao.email = "joao123@gmail.com";
		usuarioJoao.senha = "123";
		usuarioJoao.perfil = Perfil.RESPONSAVEL;
		usuarioJoao.save();
		
		Responsavel joao = new Responsavel();
		joao.usuario = usuarioJoao;
		joao.save();
		
		Usuario usuarioCarol = new Usuario();
		usuarioCarol.nome = "carol";
		usuarioCarol.email = "carol789@gmail.com";
		usuarioCarol.senha = "789";
		usuarioCarol.perfil = Perfil.RESPONSAVEL;
		usuarioCarol.save();
		
		Responsavel carol = new Responsavel();
		carol.usuario = usuarioCarol;
		carol.save();
		
		Usuario usuarioAna = new Usuario();
		usuarioAna.nome = "ana";
		usuarioAna.email = "ana456@gmail.com";
		usuarioAna.senha = "456";
		usuarioAna.perfil = Perfil.ALUNO;
		usuarioAna.save();
		
		Aluno ana = new Aluno();
		ana.usuario = usuarioAna;
		ana.curso = "Informática";
		ana.matricula = "20221064010008";
		ana.save();
		
		Assunto a1 = new Assunto("Tecnologia","tecnologia");
		a1.save();
		
		Assunto a2 = new Assunto("Saúde","saude");
		a2.save();
		
		Assunto a3 = new Assunto("Educação","educacao");
		a3.save();
		
		Assunto a4 = new Assunto("Cultura","cultura");
		a4.save();
		
		Noticia n1 = new Noticia();
		n1.assunto = a1;
		n1.autor = usuarioJoao;
		n1.titulo = "Nova Olímpiada de Matemática";
		n1.conteudo = "assunto interessante";
		n1.dataPublicacao = new Date();
		n1.nomeAutor = n1.autor.nome;
		n1.save();
		
		Noticia n2 = new Noticia();
		n2.titulo = "Oficina de Dobraduras";
		n2.conteudo = "conteúdo";
		n2.assunto = a3;
		n2.autor = usuarioCarol;
		n2.dataPublicacao = new Date();
		n2.nomeAutor = n2.autor.nome;
		n2.save();
		
		Comentario c1 = new Comentario("Muito legal, recomendo!", 120,0, n1);
		c1.autor = usuarioAna;
		c1.data = new Date();
		c1.save();
		
		
		//O responsavel deve conseguir remover comentários, mesmo que ele não seja o autor
	
		
		}	
	}
	
	
	
}
