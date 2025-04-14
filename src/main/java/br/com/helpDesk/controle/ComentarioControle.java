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

import br.com.helpDesk.DTO.AtualizarComentarioDTO;
import br.com.helpDesk.DTO.BuscarComentariosDTO;
import br.com.helpDesk.DTO.CriarComentarioDTO;
import br.com.helpDesk.servico.ComentarioServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ComentarioControle {

	private final ModelMapper  modelMapper;
	private final ComentarioServico comentarioServico;
	
	
	@PostMapping("/{comentarioId}")
	@Operation(summary = "Endpoint responsável por criar comentários pelo id.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<CriarComentarioDTO>criarComentario(@RequestBody CriarComentarioDTO criarComentarioDTO,
			                                                 @PathVariable Long comentarioId){
		var criar = comentarioServico.criarComentario(criarComentarioDTO, comentarioId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, CriarComentarioDTO.class));
	}
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar comentários.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<BuscarComentariosDTO>>buscarComentarios(
			                                                     @RequestParam(defaultValue = "0")int pagina,
			                                                     @RequestParam(defaultValue = "5")int registros){
		var paginacao = PageRequest.of(pagina, registros);
		List<BuscarComentariosDTO> listar = comentarioServico.buscarComentarios(paginacao).getContent();
		return ResponseEntity.ok(listar);
	}
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar comentário pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<BuscarComentariosDTO>buscarPorId(@PathVariable Long id){
		var buscar = comentarioServico.buscarPorId(id);
		return ResponseEntity.ok(modelMapper.map(buscar, BuscarComentariosDTO.class));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar comentário pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<AtualizarComentarioDTO>atualizarComentarios(@PathVariable Long id,
			                                                          @RequestBody AtualizarComentarioDTO dto){
		var atualizar = comentarioServico.atualizarComentarios(dto, id);
		return ResponseEntity.ok(modelMapper.map(atualizar, AtualizarComentarioDTO.class));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar comentário pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		comentarioServico.excluirComentario(id);
		return ResponseEntity.noContent().build();
	}
	
}
