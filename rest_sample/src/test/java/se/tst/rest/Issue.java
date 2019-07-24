package se.tst.rest;

import java.util.Objects;

public class Issue {

    private int id;
    private String subject;
    private String description;
    private String state_name;

    public String getState_name() {
        return state_name;
    }

    public Issue withState_name(String status) {
        this.state_name = status;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                Objects.equals(subject, issue.subject) &&
                Objects.equals(description, issue.description) &&
                Objects.equals(state_name, issue.state_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description, state_name);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", state_name='" + state_name + '\'' +
                '}';
    }
}
