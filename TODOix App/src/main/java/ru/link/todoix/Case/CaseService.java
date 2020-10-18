package ru.link.todoix.Case;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {

    @Autowired
    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository){
        this.caseRepository = caseRepository;
    }

    public void createCase(CaseItem caseItem){
        caseRepository.save(caseItem);
    }

    public List<CaseItem> findAllByName(String caseName){
        //return caseRepository.findAllByName(caseName);
        return null;
    }
}
