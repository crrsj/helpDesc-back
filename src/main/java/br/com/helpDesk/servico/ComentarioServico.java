package br.com.helpDesk.servico;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.helpDesk.DTO.AtualizarComentarioDTO;
import br.com.helpDesk.DTO.BuscarComentariosDTO;
import br.com.helpDesk.DTO.CriarComentarioDTO;
import br.com.helpDesk.entidade.Comentario;
import br.com.helpDesk.repositorio.ComentarioRepositorio;
import br.com.helpDesk.repositorio.TicketRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioServico {

	private final ModelMapper modelMapper;
	private final ComentarioRepositorio comentarioRepositorio;
	private final TicketRepositorio ticketRepositorio;
	
	 public Comentario criarComentario (CriarComentarioDTO criarComentarioDTO,Long ticketId) {
		 var ticket = ticketRepositorio.findById(ticketId).orElseThrow();
		 var comentario = modelMapper.map(ticket, Comentario.class);
		 comentario.setTicket(ticket);
	     return comentarioRepositorio.save(comentario);
	}
	 
	 public Page<BuscarComentariosDTO>buscarComentarios(Pageable pageable){
		 return comentarioRepositorio.findAll(pageable).
				 map(comentario -> modelMapper.map(comentario, BuscarComentariosDTO.class));
	 }
	 
	public Comentario buscarPorId(Long id) {
		Optional<Comentario> buscar = comentarioRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	
	public Comentario atualizarComentarios(AtualizarComentarioDTO atualizarComentarioDTO,Long id) {
		atualizarComentarioDTO.setId(id);
		return comentarioRepositorio.save(modelMapper.map(atualizarComentarioDTO, Comentario.class));
	}
	
	public void excluirComentario(Long id) {
		comentarioRepositorio.deleteById(id);
		
	}
}
