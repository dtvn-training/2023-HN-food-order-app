package com.dtvn.foodorderbackend.mapper;

import com.dtvn.foodorderbackend.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BaseMapper {
    final ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(BaseMapper.class);
    public <S, T> T map(S entity, Class<T> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
