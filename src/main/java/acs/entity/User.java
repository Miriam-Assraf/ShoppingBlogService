package acs.entity;

public class User {
    private String email;

    User(){}

    User(String email){
        this.email=email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
