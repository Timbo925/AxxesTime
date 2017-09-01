package domain;

import org.apache.catalina.User;

import java.time.LocalDateTime;

public class Entry {

    private Long id;
    private LocalDateTime from;
    private LocalDateTime to;
    private String dag;
    private Project project;
    private Percentage percantage;
    private User user;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entry entry = (Entry) o;

        if (getId() != null ? !getId().equals(entry.getId()) : entry.getId() != null) {
            return false;
        }
        if (getFrom() != null ? !getFrom().equals(entry.getFrom()) : entry.getFrom() != null) {
            return false;
        }
        if (getTo() != null ? !getTo().equals(entry.getTo()) : entry.getTo() != null) {
            return false;
        }
        if (getDag() != null ? !getDag().equals(entry.getDag()) : entry.getDag() != null) {
            return false;
        }
        if (getProject() != null ? !getProject().equals(entry.getProject()) : entry.getProject() != null) {
            return false;
        }
        if (getPercantage() != entry.getPercantage()) {
            return false;
        }
        if (getUser() != null ? !getUser().equals(entry.getUser()) : entry.getUser() != null) {
            return false;
        }
        return getStatus() == entry.getStatus();
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        result = 31 * result + (getDag() != null ? getDag().hashCode() : 0);
        result = 31 * result + (getProject() != null ? getProject().hashCode() : 0);
        result = 31 * result + (getPercantage() != null ? getPercantage().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", dag='" + dag + '\'' +
                ", project=" + project +
                ", percantage=" + percantage +
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

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public String getDag() {
        return dag;
    }

    public void setDag(String dag) {
        this.dag = dag;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Percentage getPercantage() {
        return percantage;
    }

    public void setPercantage(Percentage percantage) {
        this.percantage = percantage;
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
}
