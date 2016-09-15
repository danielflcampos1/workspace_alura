package br.com.alura.loja;

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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;
import com.thoughtworks.xstream.XStream;

public class ClienteTest {

	 private HttpServer server;
	 private Client client;
	 private WebTarget target;;
	 
	 /*
	 * @test public void testaQueAConexaoComServidorFunciona() {
	 * 
	 * Client client = ClientBuilder.newClient(); WebTarget target =
	 * client.target("http://www.mocky.io"); String conteudo =
	 * target.path("/v2/52aaf5deee7ba8c70329fb7d").request() .get(String.class);
	 * System.out.println(conteudo);
	 * Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro")); }
	 */

	 
	@Before
	public void before(){
		this.server = Servidor.inicializaServidor();
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:8080");
	}
	
	@After
	public void mataServidor(){
		server.stop();
	}
	
	@Test
	public void testaQueBuscaUmCarrinhoTrazOCarrinhoEsperado() {
		Carrinho carrinho = this.target.path("/carrinhos/1").request().get(Carrinho.class);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
	
	@Test
	public void testaQueBuscaUmProjetoTrazOProjetoEsperado() {
		Projeto projeto = this.target.path("/projetos/1").request().get(Projeto.class);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}
	
//	@Test
//	public void testaQueSuportaNovosCarrinhos() {
//		Carrinho carrinho = new Carrinho();
//        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
//        carrinho.setRua("Rua Vergueiro");
//        carrinho.setCidade("Sao Paulo");
//        String xml = carrinho.toXml();
//        
//        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
//
//        Response response = this.target.path("/carrinhos").request().post(entity);
//        Assert.assertEquals(response.getStatus(), 201);
//        String location = response.getHeaderString("Location");
//        String conteudo = this.client.target(location).request().get(String.class);
//        System.out.println(conteudo);
//        Assert.assertTrue(conteudo.contains("Tablet"));
//        //Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
//	}
	
	@Test
	public void testaQueSuportaNovosCarrinhos() {
		Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        
        Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

        Response response = this.target.path("/carrinhos").request().post(entity);
        Assert.assertEquals(response.getStatus(), 201);
        String location = response.getHeaderString("Location");
        
        Carrinho carrinhoCarregado = this.client.target(location).request().get(Carrinho.class);
        Assert.assertEquals("Tablet", carrinhoCarregado.getProdutos().get(0).getNome());
	}
}
