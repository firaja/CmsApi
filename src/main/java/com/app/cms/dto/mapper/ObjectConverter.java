package com.app.cms.dto.mapper;


public interface ObjectConverter<E,D> {
    D toDto(E entity);
    E toEntity(D dto);
}
