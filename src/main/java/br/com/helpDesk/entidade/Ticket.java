package br.com.helpDesk.entidade;




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.helpDesk.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tickets")
@Data
@NoArgsConstructor
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int codigo = new Random().nextInt(1000 + 1);
	private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status;    
    private LocalDate criadoEm = LocalDate.now();
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Comentario> comentarios = new ArrayList<>();
    @ManyToOne   
    private Usuario usuario;
}
