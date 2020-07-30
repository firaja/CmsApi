package com.app.cms.dto.converter;


public interface ObjectConverter<E,D> {
    D toDto(E entity);
    E toEntity(D dto);
}
