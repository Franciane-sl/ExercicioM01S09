package br.com.futuroDev.APISustentavel.Controller;

import br.com.futuroDev.APISustentavel.Model.Dtos.AcaoSustentavelRequestDTO;
import br.com.futuroDev.APISustentavel.Model.Dtos.AcaoSustentavelResponseDTO;
import br.com.futuroDev.APISustentavel.Model.Entity.AcaoSustentavel;
import br.com.futuroDev.APISustentavel.Model.Enum.CategoriaAcaoEnum;
import br.com.futuroDev.APISustentavel.Service.AcaoSustentavelService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acoesSustentaveis")
public class AcaoSustentavelController {

    @Autowired
    private AcaoSustentavelService acaoSustentavelService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AcaoSustentavelResponseDTO>> list(){
        List<AcaoSustentavelResponseDTO> acoesSustentaveis = this.acaoSustentavelService.findAllAcaoSustentavel().stream()
                .map(acaoSustentavel -> modelMapper.map(acaoSustentavel, AcaoSustentavelResponseDTO.class)).collect(Collectors.toList());

        return acoesSustentaveis.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(acoesSustentaveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponseDTO> getByid (@PathVariable Long id) {
        AcaoSustentavel acaoSustentavel = acaoSustentavelService.findAcaoSustentavelById(id);
        AcaoSustentavelResponseDTO acaoSustentavelDTO = modelMapper.map(acaoSustentavel, AcaoSustentavelResponseDTO.class);
        return ResponseEntity.ok(acaoSustentavelDTO);
    }

    @PostMapping
    public ResponseEntity<AcaoSustentavelResponseDTO> create(@RequestBody @Valid AcaoSustentavelRequestDTO acaoSustentavelDTO) throws Exception {
        AcaoSustentavel acaoSustentavel = modelMapper.map(acaoSustentavelDTO, AcaoSustentavel.class);
        AcaoSustentavel createdAcaoSustentavel = acaoSustentavelService.create(acaoSustentavel);
        AcaoSustentavelResponseDTO createdAcaoSustentavelDTO = modelMapper.map(createdAcaoSustentavel, AcaoSustentavelResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAcaoSustentavelDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponseDTO> update(@PathVariable Long id, @RequestBody AcaoSustentavelRequestDTO acaoSustentavelDTO) throws Exception{
        AcaoSustentavel acaoSustentavel = modelMapper.map(acaoSustentavelDTO, AcaoSustentavel.class);
        AcaoSustentavel acaoSustentavelUpdate = this.acaoSustentavelService.update(id, acaoSustentavel);
        AcaoSustentavelResponseDTO acaoSustentavelUpdateDTO = modelMapper.map(acaoSustentavelUpdate, AcaoSustentavelResponseDTO.class);

        return ResponseEntity.ok(acaoSustentavelUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.acaoSustentavelService.delete(id);

        return ResponseEntity.ok("A ação foi deletada com sucesso.");
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<AcaoSustentavelResponseDTO>> buscarPorCategoria(@RequestParam String tipo) {
        try {
            CategoriaAcaoEnum categoria = CategoriaAcaoEnum.valueOf(tipo.toUpperCase());
            List<AcaoSustentavelResponseDTO> resultado = acaoSustentavelService.findByCategoria(categoria)
                    .stream()
                    .map(acao -> modelMapper.map(acao, AcaoSustentavelResponseDTO.class))
                    .collect(Collectors.toList());

            return resultado.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(resultado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
