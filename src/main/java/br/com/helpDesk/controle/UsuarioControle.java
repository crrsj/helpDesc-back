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

import br.com.helpDesk.DTO.BuscarUsuarioDTO;
import br.com.helpDesk.DTO.CriarUsuarioDTO;
import br.com.helpDesk.servico.AtualizarUsuarioDTO;
import br.com.helpDesk.servico.UsuarioServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioControle {

	private final UsuarioServico usuarioServico;
	private final ModelMapper modelMapper;
	
	@PostMapping
	@Operation(summary = "Endpoint responsável por cadastrar usuários pelo id.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<CriarUsuarioDTO>criarUsuario(@RequestBody CriarUsuarioDTO criarUsuarioDTO){
		var criar = usuarioServico.criarUsuario(criarUsuarioDTO);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(criar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(criar, CriarUsuarioDTO.class));
	}
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar usuários.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<List<BuscarUsuarioDTO>>buscarUsuarios(@RequestParam(defaultValue ="0")int pagina
	                                                           ,@RequestParam(defaultValue =  "5")int registro){
		var paginacao = PageRequest.of(pagina, registro);
		List<BuscarUsuarioDTO>listar = usuarioServico.buscarUsuarios(paginacao).getContent();
		return ResponseEntity.ok(listar);
	}
	
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar usuários pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<AtualizarUsuarioDTO> atualizarUsuarios(@RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO,
			                                                     @PathVariable Long id){
		var atualizar = usuarioServico.AtualizarUsuario(atualizarUsuarioDTO, id);
			return ResponseEntity.ok(modelMapper.map(atualizar, AtualizarUsuarioDTO.class));
		}
	
	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar usuários pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<BuscarUsuarioDTO>buscarPorId(@PathVariable Long id){
		var buscar = usuarioServico.buscarPorId(id);
		return ResponseEntity.ok(modelMapper.map(buscar, BuscarUsuarioDTO.class));
	}
	
	
	@GetMapping("/buscarCpf")
	@Operation(summary = "Endpoint responsável por buscar usuário pelo cpf.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<BuscarUsuarioDTO>buscarPorCpf(@PathVariable("cpf")String cpf){
		var buscar = usuarioServico.buscarPorCpf(cpf);
		return ResponseEntity.ok(modelMapper.map(buscar, BuscarUsuarioDTO.class));
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por deletar usuário pelo id.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<Void>excluirUsuarios(@PathVariable Long id){
		usuarioServico.excluirUsuario(id);
		return ResponseEntity.noContent().build();
	}
}
