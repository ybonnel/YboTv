package fr.ybo.services;

import java.util.List;

public abstract class DataService<T> {

    public abstract List<T> getAll() throws ServiceExeption;

    public abstract T getById(String id) throws ServiceExeption;

    public abstract List<T> getBy(String parameterName, String parameterValue) throws ServiceExeption;

    public abstract List<T> get(String...parameters) throws ServiceExeption;

}
