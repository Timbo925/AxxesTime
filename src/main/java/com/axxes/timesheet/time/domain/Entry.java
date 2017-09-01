package com.axxes.timesheet.time.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Entry {

    @Id
    private Long id;
    private LocalTime from;
    private LocalTime to;
    private LocalDateTime day;
    @ManyToOne
    private Project project;
    @Enumerated(EnumType.STRING)
    private Percentage percentage;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Entry(LocalTime from, LocalTime to, LocalDateTime day){
        this.from = from;
        this.to = to;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", day=" + day +
                ", project=" + project +
                ", percentage=" + percentage +
                ", user=" + user +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Percentage getPercentage() {
        return percentage;
    }

    public void setPercentage(Percentage percentage) {
        this.percentage = percentage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entry entry = (Entry) o;

        if (id != null ? !id.equals(entry.id) : entry.id != null) {
            return false;
        }
        if (from != null ? !from.equals(entry.from) : entry.from != null) {
            return false;
        }
        if (to != null ? !to.equals(entry.to) : entry.to != null) {
            return false;
        }
        if (day != null ? !day.equals(entry.day) : entry.day != null) {
            return false;
        }
        if (project != null ? !project.equals(entry.project) : entry.project != null) {
            return false;
        }
        if (percentage != entry.percentage) {
            return false;
        }
        if (user != null ? !user.equals(entry.user) : entry.user != null) {
            return false;
        }
        return status == entry.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
