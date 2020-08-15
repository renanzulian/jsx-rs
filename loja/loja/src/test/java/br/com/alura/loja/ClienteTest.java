package br.com.alura.loja;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

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
}
