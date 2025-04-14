package br.com.helpDesk.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.helpDesk.entidade.Comentario;
import br.com.helpDesk.entidade.Usuario;
import br.com.helpDesk.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriarTicketDTO {

	private int codigo;
	private String titulo;
    private String descricao;
    private Status status;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate criadoEm = LocalDate.now();
    private List<Comentario> comentarios = new ArrayList<>();
    private Usuario usuario;
}
