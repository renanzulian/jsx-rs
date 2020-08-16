package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {
	
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void inicializaServidor() {
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		this.server = Servidor.inicializarServidor();
		this.client = ClientBuilder.newClient(config);
		this.target = this.client.target("http://localhost:8080");
	}
	
	@After
	public void encerraServidor() {
		this.server.stop();
	}
	
	@Test
	public void buscarUmCarrinhoDeveRetornarUmValido() {
		String content = this.target.path("/carrinhos/1").request().get(String.class);
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
        
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

        Response response = this.target.path("/carrinhos").request().post(entity);
        assertEquals(201, response.getStatus());
	}
}
