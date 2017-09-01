package com.axxes.timesheet.time.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Contract {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String client;
    @Enumerated(EnumType.STRING)
    private Type type;
    private double amountOfHours;
    private LocalDateTime defaultStart;
    private LocalDateTime defaultEnd;
    @OneToMany
    private List<Entry> entries;

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client='" + client + '\'' +
                ", type=" + type +
                '}';
    }

    public LocalDateTime getDefaultStart() {
        return defaultStart;
    }

    public void setDefaultStart(LocalDateTime defaultStart) {
        this.defaultStart = defaultStart;
    }

    public LocalDateTime getDefaultEnd() {
        return defaultEnd;
    }

    public void setDefaultEnd(LocalDateTime defaultEnd) {
        this.defaultEnd = defaultEnd;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contract contract = (Contract) o;

        if (id != null ? !id.equals(contract.id) : contract.id != null) {
            return false;
        }
        if (name != null ? !name.equals(contract.name) : contract.name != null) {
            return false;
        }
        if (client != null ? !client.equals(contract.client) : contract.client != null) {
            return false;
        }
        return type == contract.type;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public double getAmountOfHours() {
        return amountOfHours;
    }

    public void setAmountOfHours(double amountOfHours) {
        this.amountOfHours = amountOfHours;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
