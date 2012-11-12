package fr.ybo.tv.server.modele;

import com.google.appengine.api.datastore.Blob;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Programme implements Serializable  {

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
