package com.example.handschriftenportal.service;

import com.example.handschriftenportal.entity.Manuscript;

import java.util.List;

public interface ManuscriptService {

    Manuscript saveManuscript(Manuscript manuscript);

    List<Manuscript> getAllManuscripts();
}
