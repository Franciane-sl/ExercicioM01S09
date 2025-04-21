package br.com.futuroDev.APISustentavel.Model.Dtos;

import br.com.futuroDev.APISustentavel.Model.Enum.CategoriaAcaoEnum;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcaoSustentavelRequestDTO {

    @NotBlank(message = "O titulo da ação é obrigatório")
    private String titulo;
    @NotBlank(message = "A descrição da ação é obrigatória")
    private String descricao;
    @NotNull
    private CategoriaAcaoEnum categoria;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "A data da realização da acão deve está no futuro")
    private LocalDate dataRealizacao;
}
