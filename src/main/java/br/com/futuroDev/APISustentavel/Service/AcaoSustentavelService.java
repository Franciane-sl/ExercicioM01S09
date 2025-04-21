package br.com.futuroDev.APISustentavel.Service;

import br.com.futuroDev.APISustentavel.Model.Entity.AcaoSustentavel;
import br.com.futuroDev.APISustentavel.Model.Enum.CategoriaAcaoEnum;
import br.com.futuroDev.APISustentavel.Model.Exceptions.RecursoNaoEncontradoException;
import br.com.futuroDev.APISustentavel.Repository.AcaoSustentavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoSustentavelService {

    @Autowired
    AcaoSustentavelRepository acaoSustentavelRepository;

    public List<AcaoSustentavel> findAllAcaoSustentavel(){
        return acaoSustentavelRepository.findAll();
    }

    public AcaoSustentavel findAcaoSustentavelById(Long id){
        return acaoSustentavelRepository.findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("Ação não encontrada pelo id" + id)
        );

    }

    public AcaoSustentavel create(AcaoSustentavel acaoSustentavel){
        return acaoSustentavelRepository.save(acaoSustentavel);
    }

    public AcaoSustentavel update(Long id, AcaoSustentavel acaoSustentavelUpdate){
        AcaoSustentavel existingAcaoSustentavel = findAcaoSustentavelById(id);
        existingAcaoSustentavel.setTitulo(acaoSustentavelUpdate.getTitulo());
        existingAcaoSustentavel.setDescricao(acaoSustentavelUpdate.getDescricao());
        existingAcaoSustentavel.setCategoria(acaoSustentavelUpdate.getCategoria());
        existingAcaoSustentavel.setDataRealizacao(acaoSustentavelUpdate.getDataRealizacao());
        return acaoSustentavelRepository.save(existingAcaoSustentavel);
    }

    public void delete(Long id){
        AcaoSustentavel acaoSustentavel = findAcaoSustentavelById(id);
        acaoSustentavelRepository.delete(acaoSustentavel);
    }

    public List<AcaoSustentavel> findByCategoria(CategoriaAcaoEnum categoriaAcaoEnum) {
        return acaoSustentavelRepository.findByCategoria(categoriaAcaoEnum);
    }

}
