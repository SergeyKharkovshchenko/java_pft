package ru.stqa.pft.mantis.model;

/**
 * Created by SK on 08.06.2016.
 */
public class Project {
    private int id;

    public String getName() {
        return name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public Project withId(int id) {
        this.id = id;
        return this;
    }

    private String name;

}
