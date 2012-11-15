package fr.ybo.services;

import java.util.List;

public abstract class DataService<T> {

    public abstract List<T> getAll() throws ServiceExeption;

    public abstract T getById(String id) throws ServiceExeption;

}
