package com.axxes.timesheet.time.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Entry {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime begin;
    private LocalDateTime to;
    @ManyToOne
    private Contract contract;
    @Enumerated(EnumType.STRING)
    private Percentage percentage;
    @Enumerated(EnumType.STRING)
    private Status status;


    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", begin=" + begin +
                ", to=" + to +
                ", contract=" + contract +
                ", percentage=" + percentage +
                ", status=" + status +
                '}';
    }


    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Percentage getPercentage() {
        return percentage;
    }

    public void setPercentage(Percentage percentage) {
        this.percentage = percentage;
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
        if (begin != null ? !begin.equals(entry.begin) : entry.begin != null) {
            return false;
        }
        if (to != null ? !to.equals(entry.to) : entry.to != null) {
            return false;
        }

        if (contract != null ? !contract.equals(entry.contract) : entry.contract != null) {
            return false;
        }
        if (percentage != entry.percentage) {
            return false;
        }
        return status == entry.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
