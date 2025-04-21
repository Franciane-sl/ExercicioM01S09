package br.com.futuroDev.APISustentavel.Model.Entity;

import br.com.futuroDev.APISustentavel.Model.Enum.CategoriaAcaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AcaoSustentavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column
    @Enumerated(EnumType.STRING)
    private CategoriaAcaoEnum categoria;
    @Column
    @Temporal(value = TemporalType.DATE)
    private LocalDate dataRealizacao;
}