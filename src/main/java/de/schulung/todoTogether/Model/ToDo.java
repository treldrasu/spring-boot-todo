package de.schulung.todoTogether.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String beschreibung;

    private Boolean status;
    
    public ToDo(){}

    public ToDo(String beschreibung, Boolean status) {
        this.beschreibung = beschreibung;
        this.status = status;
    }
    public Long getId() {
        return id; 
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ToDo [id=" + id + ", beschreibung=" + beschreibung + ", status=" + status + "]";
    }
    

}
