package com.gbcom.gwifi.domain;

import com.j256.ormlite.field.DatabaseField;
import java.p456io.Serializable;

public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 4582678925625907068L;
    @DatabaseField(generatedId = true)

    /* renamed from: id */
    private Long f9152id;

    public Long getId() {
        return this.f9152id;
    }

    public void setId(Long l) {
        this.f9152id = l;
    }
}
