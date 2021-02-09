package br.com.alura.forum.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.alura.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);
	
	
	
	// CASO NÃO QUEIRA SEGUIR O PADRÃO FINDBY DO JPA VOCÊ DEVERÁ DIGITAR SUA QUERY UTILIZANDO JPQL CONFORME DESEJADO E EXEMPLO ABAIXO:
	//	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	//	List<Curso> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
