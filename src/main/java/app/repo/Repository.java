package app.repo;

import app.main.WiseSaying;

import java.util.List;

public interface Repository {
    List<WiseSaying> getList();
    int getLastId();

    void add(String content, String author);

    boolean remove(int number);

    boolean update(int number, String newAuthor, String newContent);

    WiseSaying getById(int id);
}

