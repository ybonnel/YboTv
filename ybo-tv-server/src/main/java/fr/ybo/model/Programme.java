package fr.ybo.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

@Entity
public class Programme implements Serializable {

    @Id
    private Long id;

    private Date dateProgramme;

    private Blob file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateProgramme() {
        return dateProgramme;
    }

    public void setDateProgramme(Date dateProgramme) {
        this.dateProgramme = dateProgramme;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {

        this.file = file;
    }
}
