package by.emaptc.LibraryProject.dao;

import by.emaptc.LibraryProject.exceptions.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {

    public Connection connection = ConnectionBuilder.getConnection();

    public abstract void delete() throws DaoException;

    public abstract void deleteById(long id) throws DaoException, SQLException;

    public abstract T insert(T entity) throws DaoException;

    public abstract void update(T entity) throws DaoException;

    public T check(String username, String password) throws DaoException {
        return null;
    }
    public abstract List<T> select() throws DaoException;

    public List<T> selectByClientId(long id) throws DaoException {
        return null;
    }

    public abstract T selectById(long id) throws DaoException;



}
