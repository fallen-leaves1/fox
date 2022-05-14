package com.hsy.creatorservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    public String id;

    public String title;

    private List<TwoSubject> children = new ArrayList<>();
}
