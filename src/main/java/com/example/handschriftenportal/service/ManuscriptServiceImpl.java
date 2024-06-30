package com.example.handschriftenportal.service;

import com.example.handschriftenportal.entity.Manuscript;
import com.example.handschriftenportal.repository.ManuscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManuscriptServiceImpl implements ManuscriptService {

    private final ManuscriptRepository manuscriptRepository;

    @Autowired
    public ManuscriptServiceImpl(ManuscriptRepository manuscriptRepository) {
        this.manuscriptRepository = manuscriptRepository;
    }

    @Override
    public Manuscript saveManuscript(Manuscript manuscript) {
        return manuscriptRepository.save(manuscript);
    }

    @Override
    public List<Manuscript> getAllManuscripts() {
        return manuscriptRepository.findAll();
    }
}
