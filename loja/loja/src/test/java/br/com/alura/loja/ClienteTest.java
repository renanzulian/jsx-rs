package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {
	
	private HttpServer server;
	
	@Before
	public void inicializaServidor() {
		new Servidor();
		this.server = Servidor.inicializarServidor();
	}
	
	@After
	public void encerraServidor() {
		this.server.stop();
	}
	
	@Test
	public void buscarUmCarrinhoDeveRetornarUmValido() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String content = target.path("/carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(content);
		assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	
	}
	
	@Test
	public void adicionaUmCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXML();
        
        Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
        
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        assertEquals(201, response.getStatus());
	}
}
