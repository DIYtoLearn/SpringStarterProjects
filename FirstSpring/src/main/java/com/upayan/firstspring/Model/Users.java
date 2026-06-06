package com.upayan.firstspring.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Users {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Please provide a valid email address")
    private String EmailAddress;

    @NotBlank(message = "Username cannot be blank")
    private String UserName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}