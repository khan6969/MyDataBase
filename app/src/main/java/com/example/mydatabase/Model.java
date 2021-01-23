package com.example.mydatabase;

public class Model {

    private String name,age,qualification;
    int  id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model(String name, String age, String qualification, int id) {
        this.name = name;
        this.age = age;
        this.qualification = qualification;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Model(String name, String age, String qualification) {
        this.name = name;
        this.age = age;
        this.qualification = qualification;

    }
}
