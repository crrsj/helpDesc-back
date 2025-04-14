package br.com.helpDesk.servico;

import java.util.List;

import br.com.helpDesk.entidade.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarUsuarioDTO {
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private List<Ticket>tickets;
}
