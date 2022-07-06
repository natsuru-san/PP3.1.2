package ru.natsuru.mvcboot.service;

import ru.natsuru.mvcboot.model.User;
import java.util.List;

public interface UserService {
    List<User> getListUsers();
    void addUser(String name, String surName, int socialNumber);
    void addUser(User user);
    void deleteUser(long id);
    void changeUser(User user);
    User getUser(long id);
}
