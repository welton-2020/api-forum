package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
	
	
	// FILTRANDO POR PARAMETRO NO CASO UTILIZANDO A ENTIDADE CURSO ATRAVÉS DO RELACIONAMENTO E BUSCANDO O NOME DO CURSO
	// A UTILIZAÇÃO DO " _" É PARA NÃO TER ERRO DE AMBIGUIDADE 
	List<Topico> findByCurso_Nome(String nomeCurso);
	
	
	// CASO NÃO QUEIRA SEGUIR O PADRÃO FINDBY DO JPA VOCÊ DEVERÁ DIGITAR SUA QUERY UTILIZANDO JPQL CONFORME DESEJADO E EXEMPLO ABAIXO:
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
	
}
