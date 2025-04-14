package br.com.helpDesk.servico;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.helpDesk.DTO.AtualizarTicketDTO;
import br.com.helpDesk.DTO.BuscarTicketDTO;
import br.com.helpDesk.DTO.CriarTicketDTO;
import br.com.helpDesk.entidade.Ticket;
import br.com.helpDesk.enums.Status;
import br.com.helpDesk.repositorio.TicketRepositorio;
import br.com.helpDesk.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServico {
	
	private final ModelMapper modelMapper;
	private final TicketRepositorio ticketRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    
	public Ticket criarTicket(CriarTicketDTO criarTicketDTO,Long usuarioId) {
	 var usuario  = usuarioRepositorio.findById(usuarioId).orElseThrow();
	 var ticket = modelMapper.map(criarTicketDTO,Ticket.class);
	     ticket.setUsuario(usuario);
	     ticket.setStatus(Status.ABERTO);	
	    return ticketRepositorio.save(ticket);
	}
	
	public Page<BuscarTicketDTO> buscarTickets(Pageable pageable) {
		return ticketRepositorio.findAll(pageable)
				.map(listar -> modelMapper.map(listar, BuscarTicketDTO.class));
	}
	
	public Ticket buscarPorId(Long id) {
	Optional<Ticket> buscar =  ticketRepositorio.findById(id);
	return buscar.orElseThrow();
	}
	
	public Ticket AtualizarTicket(AtualizarTicketDTO atualizarTicketDTO, Long id) {
		atualizarTicketDTO.setId(id);
		return ticketRepositorio.save(modelMapper.map(atualizarTicketDTO, Ticket.class));
	}
	
	public Ticket buscarPorCodigo(int codigo) {
		return ticketRepositorio.findByCodigo(codigo);
	}
	
	public void excluirTicket(Long id) {
		ticketRepositorio.deleteById(id);
	}
}
