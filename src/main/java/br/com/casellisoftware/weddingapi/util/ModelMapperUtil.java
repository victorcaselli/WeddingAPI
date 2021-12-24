package br.com.casellisoftware.weddingapi.util;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class ModelMapperUtil {

    private final ModelMapper modelMapper;
    private static ModelMapper staticModelMapper;

    @PostConstruct
    private void initializer(){
        staticModelMapper = modelMapper;
    }

    public static <S,D> D map(S source,Class<D> destination){
        return staticModelMapper.map(source,destination);
    }

    public static <S,D> void map(S source, D destionation){
        staticModelMapper.map(source, destionation);
    }

}
