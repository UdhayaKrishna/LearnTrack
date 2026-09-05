package com.airtribe.learntrack.service;

import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.Collections;
import java.util.List;

public abstract class BaseService<T> {
    protected final List<T> items;

    public BaseService(List<T> sharedBackingStore) {
        this.items = sharedBackingStore;
    }

    protected T findById(int id, String entityName, IdProvider<T> idProvider) throws EntityNotFoundException {
        for (T item : this.items) {
            if (idProvider.getId(item) == id) {
                return item;
            }
        }
        throw new EntityNotFoundException(entityName + " with id " + id + " was not found.");
    }

    /**
     * Returns an unmodifiable view of the internal list.
     * Prevents external callers from adding or removing items directly.
     */
    public List<T> listAll() {
        return Collections.unmodifiableList(items);
    }

    @FunctionalInterface
    public interface IdProvider<T> {
        int getId(T item);
    }
}
