package app.main;

import app.repo.Repository;

import java.util.List;

public class Service {

    private final Repository consoleRepo;

    public Service(Repository consoleRepo) {
        this.consoleRepo = consoleRepo;
    }

    public List<WiseSaying> getList() {
        return consoleRepo.getList();
    }

    public int getLastId() {
        return consoleRepo.getLastId();
    }

    public WiseSaying getWiseSayingById(int id) {
        return consoleRepo.getById(id);
    }

    public int addWiseSaying(String content, String author) {
        consoleRepo.add(content, author);
        return consoleRepo.getLastId();
    }

    public boolean removeWiseSaying(int number) {
        return consoleRepo.remove(number);
    }


    public boolean updateWiseSaying(int number, String newAuthor, String newContent) {
        return consoleRepo.update(number, newAuthor, newContent);
    }
}
