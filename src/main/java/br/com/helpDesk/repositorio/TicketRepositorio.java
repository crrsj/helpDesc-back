package br.com.helpDesk.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helpDesk.entidade.Ticket;

public interface TicketRepositorio extends JpaRepository<Ticket, Long> {

	Ticket findByCodigo(int codigo);

}
