package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alura.forum.modelo.Topico;

public class TopicoDTO {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	
	
	
	
	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	
	// AQUI ESTOU RECEBENDO UMA LISTA DE TOPICOS E CONVERTENDO EM UMA LISTA DE TOPICODTO UTILIZANDO API DE STREAM DO JAVA 8
	public static Page<TopicoDTO> converter(Page<Topico> topicos) {
		// TODO Auto-generated method stub
		return topicos.map(TopicoDTO::new);
	}
	
	
	
}
