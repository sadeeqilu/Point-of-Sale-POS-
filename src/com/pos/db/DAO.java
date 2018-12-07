/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.db;

import java.util.List;

/**
 *
 * @author ASIYA
 * @param <T>
 */
public interface DAO<T> {
    public T getT(String id);
    public boolean add(T t);
    public boolean update(T t);
    public List<T> getAll();
}
