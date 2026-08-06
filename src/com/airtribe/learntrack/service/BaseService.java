package com.airtribe.learntrack.service;

import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T> {
    protected final List<T> items;

    public BaseService() {
        this.items = new ArrayList<>();
    }

    protected T findById(List<T> list, int id, String entityName, IdProvider<T> idProvider) throws EntityNotFoundException {
        for (T item : list) {
            if (idProvider.getId(item) == id) {
                return item;
            }
        }
        throw new EntityNotFoundException(entityName + " with id " + id + " was not found.");
    }

    public List<T> listAll() {
        return items;
    }

    @FunctionalInterface
    public interface IdProvider<T> {
        int getId(T item);
    }
}
