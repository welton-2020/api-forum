package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class AtualizacaoTopicoForm {

	@NotNull @NotEmpty @NotBlank @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @NotBlank @Length(min = 10)
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	// METHODO QUE ATUALIZA O TOPICO
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		
		//BUSCADO O TOPICO PELO ID
		Topico topico = topicoRepository.getOne(id);
		
		//ATUALIZANDO O TITULO E A MENSAGEM DO TOPICO
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		
		return topico;
	}
	
	
}
