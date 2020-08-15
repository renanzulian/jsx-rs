package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String buscarProjetos(@PathParam("id") long id){
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto.toXML();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String adicionarProjeto(String dados) {
		Projeto projeto = (Projeto) new XStream().fromXML(dados);
		new ProjetoDAO().adiciona(projeto);
		return "<status>Sucesso</status>";
	}
}
