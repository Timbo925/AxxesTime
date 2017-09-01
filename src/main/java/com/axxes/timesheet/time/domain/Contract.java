package com.axxes.timesheet.time.domain;

import javax.persistence.*;

@Entity
public class Contract {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String client;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client='" + client + '\'' +
                ", type=" + type +
                '}';
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
}
