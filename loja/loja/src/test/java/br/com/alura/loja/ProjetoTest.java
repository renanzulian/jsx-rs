package br.com.alura.loja;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ProjetoTest {

	public void deveriaRetornarUmFormatoXML() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String content = target.path("/projetos").request().get(String.class);
		assertTrue(content.contains("<nome>Minha loja</nome>"));
	}
}
