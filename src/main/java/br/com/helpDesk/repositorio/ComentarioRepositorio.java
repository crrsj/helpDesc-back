package br.com.helpDesk.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpDesk.entidade.Comentario;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {

}
