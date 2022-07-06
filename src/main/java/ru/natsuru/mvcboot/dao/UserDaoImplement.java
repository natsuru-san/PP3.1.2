package ru.natsuru.mvcboot.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.natsuru.mvcboot.model.User;
import java.util.List;

@Component
@Transactional
public class UserDaoImplement implements UserDao {
    @PersistenceContext
    private EntityManager manager;

    public UserDaoImplement() {}

    @Override
    public List<User> pullListUsers() {
        return manager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void putUser(String name, String surName, int socialNumber) {
        putUser(new User(name, surName, socialNumber));
    }

    @Override
    @Transactional
    public void putUser(User user) {
        manager.merge(user);
    }

    @Override
    public void removeUser(long id) {
        manager.remove(pullUser(id));
    }

    @Override
    public void updateUser(User user) {
        manager.merge(user);
    }

    @Override
    public User pullUser(long id) {
        return manager.find(User.class, id);
    }
}
