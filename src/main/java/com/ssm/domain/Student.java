package com.ssm.domain;

public class Student {
 
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", math=" + math + ", chinese=" + chinese + ", english="
                + english + "]";
    }
    private String id;
    private String name;
    private String math;
    private String chinese;
    private String english;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMath() {
        return math;
    }
    public void setMath(String math) {
        this.math = math;
    }
    public String getChinese() {
        return chinese;
    }
    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
    public String getEnglish() {
        return english;
    }
    public void setEnglish(String english) {
        this.english = english;
    }
    
    
}
