package com.dtvn.foodorderbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseMapper {
    final ModelMapper modelMapper;

    public <T, D> D map(T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

}
