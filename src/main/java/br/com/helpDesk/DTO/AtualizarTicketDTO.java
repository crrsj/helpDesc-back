package br.com.helpDesk.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.helpDesk.entidade.Comentario;
import br.com.helpDesk.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarTicketDTO {
	private Long id;
	private int codigo;
	private String titulo;
    private String descricao;
    private Status status;   
    private LocalDate criadoEm = LocalDate.now();
    private List<Comentario> comentarios = new ArrayList<>();
}
