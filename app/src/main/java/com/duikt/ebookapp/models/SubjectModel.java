package com.duikt.ebookapp.models;

import androidx.constraintlayout.utils.widget.ImageFilterView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class SubjectModel {

    private String subjectName;
    private int subjectImage;

    public int getSubjectImage() {
        return subjectImage;
    }

    public void setSubjectImage(int subjectImage) {
        this.subjectImage = subjectImage;
    }
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public SubjectModel(String subjectName, int subjectImage) {
        this.subjectName = subjectName;
        this.subjectImage = subjectImage;
    }
}
