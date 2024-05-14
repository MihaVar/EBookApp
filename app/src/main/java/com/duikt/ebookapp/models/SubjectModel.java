package com.duikt.ebookapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class SubjectModel {
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    private String subjectName;

    public SubjectModel(String subjectName) {
        this.subjectName = subjectName;
    }
}
