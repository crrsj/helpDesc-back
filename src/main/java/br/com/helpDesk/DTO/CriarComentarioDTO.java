package br.com.helpDesk.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.helpDesk.entidade.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriarComentarioDTO {

	 private String mensagem;
	 @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
	 private LocalDate criadoEm = LocalDate.now();
	 private Ticket ticket;
}
