package br.com.helpDesk.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpDesk.entidade.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

	Optional<Usuario>findByCpf(String cpf);

}
