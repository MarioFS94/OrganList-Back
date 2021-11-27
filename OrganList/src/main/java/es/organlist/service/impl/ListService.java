package es.organlist.service.impl;

import es.organlist.mappers.OrganListMapper;
import es.organlist.model.dto.ListDTO;
import es.organlist.model.dto.ProductDTO;
import es.organlist.model.entity.ListEntity;
import es.organlist.repository.ListRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListService {

    private static OrganListMapper mapper = Mappers.getMapper(OrganListMapper.class);

    private final ListRepository listRepository;

    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public List<ListDTO> getLists() {
        List<ListEntity> listEntities = listRepository.findAll();
        return listEntities.stream().map(listEntity -> mapper.toListDTO(listEntity)).collect(Collectors.toList());
    }

    public ResponseEntity insertList(ListDTO list) {
        ListEntity entityList = mapper.toListEntity(list);
        listRepository.save(entityList);
        return new ResponseEntity("Insertado!", HttpStatus.CREATED);
    }

    public ResponseEntity deleteList(Integer listId) {
        try {
            listRepository.deleteById(listId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + listId + "\n" + e.getMessage());
        }
        return new ResponseEntity("Borrado!", HttpStatus.CREATED);
    }

    public ListEntity updateList(ListDTO listDTO) {
        ListEntity listEntity = mapper.toListEntity(listDTO);
        ListEntity updatedList;
        try {
            updatedList = listRepository.save(listEntity);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + listEntity.getId() + "\n" + e.getMessage());
        }
        return updatedList;
    }

    public ResponseEntity updateFavoriteList(Integer listId) {
        Optional<ListEntity> listEntity = listRepository.findById(listId);
        //Comprobamos si hay resultado
        if (listEntity.isPresent()) {
            listEntity.get().setFav(Boolean.TRUE);
        } else {
            throw new NotFoundException("No existe el producto con id " + listId);
        }
        try {
            listRepository.save(listEntity.get());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No existe el producto con id " + listId + "\n" + e.getMessage());
        }
        return new ResponseEntity("Modificado el valor de favorito!", HttpStatus.OK);
    }
}
