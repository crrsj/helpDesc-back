package br.com.helpDesk.servico;



import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.helpDesk.DTO.BuscarUsuarioDTO;
import br.com.helpDesk.DTO.CriarUsuarioDTO;
import br.com.helpDesk.entidade.Usuario;
import br.com.helpDesk.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServico {

	private final ModelMapper modelMapper;
	private final UsuarioRepositorio usuarioRepositorio;
	
	public Usuario criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
		return usuarioRepositorio.save(modelMapper.map(criarUsuarioDTO, Usuario.class));
	}
	
	public Page<BuscarUsuarioDTO>buscarUsuarios(Pageable pageable){
		return usuarioRepositorio.findAll(pageable).map(listar
				-> modelMapper.map(listar, BuscarUsuarioDTO.class));
	}
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario>buscar = usuarioRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Usuario buscarPorCpf(String cpf) {
		Optional<Usuario> buscar = usuarioRepositorio.findByCpf(cpf);
		return buscar.orElseThrow();
	}
	
	public Usuario AtualizarUsuario(AtualizarUsuarioDTO atualizarUsuarioDTO,Long id) {
		atualizarUsuarioDTO.setId(id);
		return usuarioRepositorio.save(modelMapper.map(atualizarUsuarioDTO, Usuario.class));
		
	}
	
	public void excluirUsuario(Long id) {
		usuarioRepositorio.deleteById(id);
	}
}
