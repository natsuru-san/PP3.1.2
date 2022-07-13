package ru.natsuru.mvcboot.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ru.natsuru.mvcboot.model.Role;
import ru.natsuru.mvcboot.model.User;
import java.util.List;
import java.util.Set;

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
        Query query = manager.createQuery("DELETE FROM User User WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
    @Override
    public void updateUser(User user) {
        manager.merge(user);
    }

    @Override
    public User pullUser(long id) {
        return manager.find(User.class, id);
    }

    @Override
    public Set<Role> pullRolesFromUser(long id) {
        return pullUser(id).getRoles();
    }

    @Override
    public User pullUserByName(String login) {
        TypedQuery<User> query = manager.createQuery("FROM User user WHERE user.name=:name", User.class);
        query.setParameter("name", login);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
