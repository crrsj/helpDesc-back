package br.com.helpDesk.controle;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helpDesk.DTO.AtualizarTicketDTO;
import br.com.helpDesk.DTO.BuscarTicketDTO;
import br.com.helpDesk.DTO.CriarTicketDTO;
import br.com.helpDesk.servico.TicketServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TicketControle {

	private final ModelMapper modelMapper;
	private final TicketServico ticketServico;
	
	@PostMapping("/{usuarioId}")
	@Operation(summary = "Endpoint responsável por criaar tickets.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<CriarTicketDTO>criarTicket(@RequestBody CriarTicketDTO criarTicketDTO,@PathVariable Long usuarioId){
		var criar = ticketServico.criarTicket(criarTicketDTO,usuarioId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, CriarTicketDTO.class));
	}
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar tickets.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<BuscarTicketDTO>>buscarTickets(
			                                                   @RequestParam(defaultValue = "0")int pagina,
		                                                       @RequestParam(defaultValue = "5")int registros){
		var paginacao = PageRequest.of(pagina, registros);
		List<BuscarTicketDTO> buscar = ticketServico.buscarTickets(paginacao).getContent();
		return ResponseEntity.ok(buscar);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar ticket por id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<BuscarTicketDTO>buscarPorId(@PathVariable Long id){
		var buscar = ticketServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarTicketDTO.class));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar ticket pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<AtualizarTicketDTO>atualizarTickets(
			                                                  @PathVariable Long id,
			                                                  @RequestBody AtualizarTicketDTO atualizarTicketDTO){
		var atualizar = ticketServico.AtualizarTicket(atualizarTicketDTO, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarTicketDTO.class));
	}
	
	
	@Operation(summary = "Endpoint responsável por buscar ticket por codigo.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<BuscarTicketDTO>buscarPorCodigo(@RequestParam int codigo){
		var buscar = ticketServico.buscarPorCodigo(codigo);
		return ResponseEntity.ok().body(modelMapper.map(buscar,BuscarTicketDTO.class));
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por excluir ticket pelo id.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<Void>excluirTicket(@PathVariable Long id){
		ticketServico.excluirTicket(id);
		return ResponseEntity.noContent().build();
	}
}
