package br.com.futuroDev.APISustentavel.Repository;

import br.com.futuroDev.APISustentavel.Model.Entity.AcaoSustentavel;
import br.com.futuroDev.APISustentavel.Model.Enum.CategoriaAcaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {
    List<AcaoSustentavel> findByCategoria(CategoriaAcaoEnum categoriaAcaoEnum);
}
