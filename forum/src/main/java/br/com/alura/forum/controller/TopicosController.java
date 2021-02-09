package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoFom;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	//UTILIZANDO PAGINAÇÃO 
	@GetMapping
	public Page<TopicoDTO> listar (@RequestParam(required = false) String nomeCurso, @RequestParam int pagina, @RequestParam int qtd){
				
		Pageable paginacao = PageRequest.of(pagina,qtd);
				
		if(nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDTO.converter(topicos);
		} else {
			Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
			return TopicoDTO.converter(topicos);
		}

	}
	
	
	// TopicoForm É UM DTO DE ENTRADA DE DADOS.
	// CADASTRANDO UM NOVO TOPICO
		@PostMapping
		@Transactional
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoFom form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
		
	// DETALHANDO OS DADOS DO TOPICO
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDTO> detalhar(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
		return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));
		}
		return ResponseEntity.notFound().build();
		}

	
	// ATUALIZANDO OS DADOS DO TOPICO
		@PutMapping("/{id}")	
		@Transactional        // -> uTILIZADO O @TRANSACTIONAL PARA O SPRING EFETUAR O UPDATE NO BANCO
		public ResponseEntity<TopicoDTO>  atualizar( @PathVariable Long id, @RequestBody @Valid  AtualizacaoTopicoForm  form) {
			Optional<Topico> optional = topicoRepository.findById(id);
			if(optional.isPresent()) {
				Topico topico = form.atualizar(id, topicoRepository);
				return ResponseEntity.ok(new TopicoDTO(topico));
			}
				return ResponseEntity.notFound().build();
			}
		
	
	
		// DELETANDO OS DADOS DO TOPICO	
		@DeleteMapping("/{id}")
		@Transactional
		public ResponseEntity<?> remover(@PathVariable Long id){
			Optional<Topico> optional = topicoRepository.findById(id);
				if(optional.isPresent()) {
					topicoRepository.deleteById(id);
					return ResponseEntity.ok().build();
				}
				return ResponseEntity.notFound().build();
				}
		}
