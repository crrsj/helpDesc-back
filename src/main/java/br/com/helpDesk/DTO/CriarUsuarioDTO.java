package br.com.helpDesk.DTO;

import java.util.List;

import br.com.helpDesk.entidade.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriarUsuarioDTO {
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private List<Ticket>tickets;
}
