package br.com.helpDesk.DTO;

import java.time.LocalDate;

import br.com.helpDesk.entidade.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarComentarioDTO {
	 private Long id;
	 private LocalDate criadoEm = LocalDate.now();
	 private Ticket ticket;
}
