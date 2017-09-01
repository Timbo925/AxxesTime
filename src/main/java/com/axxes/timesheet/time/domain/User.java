package com.axxes.timesheet.time.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private Double vacation;
    private Double recup;
    @ManyToOne
    private Project currentProject;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                ", currentProject=" + currentProject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) {
            return false;
        }
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null) {
            return false;
        }
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null) {
            return false;
        }
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) {
            return false;
        }
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) : user.getPhone() != null) {
            return false;
        }
        return getUserType() == user.getUserType();
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getUserType() != null ? getUserType().hashCode() : 0);
        return result;
    }

    public Double getVacation() {
        return vacation;
    }

    public void setVacation(Double vacation) {
        this.vacation = vacation;
    }

    public Double getRecup() {
        return recup;
    }

    public void setRecup(Double recup) {
        this.recup = recup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
