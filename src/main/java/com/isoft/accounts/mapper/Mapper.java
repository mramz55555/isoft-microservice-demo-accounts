package com.isoft.accounts.mapper;

import com.isoft.accounts.dto.BaseDTO;
import com.isoft.accounts.entity.BaseEntity;
import lombok.NonNull;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
public abstract class Mapper<T extends BaseEntity, S extends BaseDTO> {
    private Set<String> ignoredFields = new HashSet<>();

    public Mapper() {
    }

    public void mergeEntityWithDTO(S dto, T entity) {
        if (Objects.isNull(dto) ^ Objects.isNull(entity)) {
            throw new NullPointerException("null input(s)");
        } else {
            if (Objects.nonNull(dto)) {
                this.mapFields(entity, dto, true);
            }

        }
    }

    protected S entityToDTO(@NonNull T entity, @NonNull Class<S> dtoClass) {
        S dto;
        try {
            dto = dtoClass.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException var5) {
            throw new RuntimeException(var5);
        }

        this.mapFields(entity, dto, false);
        return dto;
    }

    protected T dTOToEntity(S dto, Class<T> entityClass) {
        T entity;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException var5) {
            throw new RuntimeException(var5);
        }

        this.mapFields(entity, dto, true);
        return entity;
    }

    private void mapFields(T source, S target, boolean isReverse) {
        Class<?> sourceClass = isReverse ? target.getClass() : source.getClass();
        Class<?> targetClass = isReverse ? source.getClass() : target.getClass();

        do {
            Field[] sourceFields = sourceClass.getDeclaredFields();
            Field[] targetFields = targetClass.getDeclaredFields();

            for (int i = 0; i < Math.min(sourceFields.length, targetFields.length); ++i) {
                try {
                    sourceFields[i].setAccessible(true);
                    Object object = sourceFields[i].get(isReverse ? target : source);
                    if (!this.ignoredFields.contains(sourceFields[i].getName()) && Objects.nonNull(object)) {
                        targetFields[i].setAccessible(true);
                        targetFields[i].set(isReverse ? source : target, object);
                    }
                } catch (IllegalAccessException var10) {
                    throw new RuntimeException(var10);
                }
            }

            sourceClass = sourceClass.getSuperclass();
            targetClass = targetClass.getSuperclass();
        } while (sourceClass != Object.class);

    }
}
