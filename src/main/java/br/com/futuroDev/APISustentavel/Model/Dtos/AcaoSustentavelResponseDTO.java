package br.com.futuroDev.APISustentavel.Model.Dtos;

import br.com.futuroDev.APISustentavel.Model.Enum.CategoriaAcaoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcaoSustentavelResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private CategoriaAcaoEnum categoria;
    private LocalDate dataRealizacao;



}
