
package com.freightfox.alumni.service;

import com.freightfox.alumni.dto.SearchRequest;
import com.freightfox.alumni.entity.AlumniProfile;
import com.freightfox.alumni.repository.AlumniProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumniService {

    private final AlumniProfileRepository repository;
    private final PhantomBusterService phantomBusterService;

    @Value("${alumni.search.mock-mode:false}")
    private boolean mockMode;

    public AlumniService(
            AlumniProfileRepository repository,
            PhantomBusterService phantomBusterService) {
        this.repository = repository;
        this.phantomBusterService = phantomBusterService;
    }

    @Transactional
    public List<AlumniProfile> search(SearchRequest request) {

        if (mockMode) {
            return searchWithTestData(request);
        }

        List<AlumniProfile> fetched =
                phantomBusterService.search(request);

        List<AlumniProfile> saved = new ArrayList<>();

        for (AlumniProfile profile : fetched) {
            saved.add(repository.save(profile));
        }

        return saved;
    }

    private List<AlumniProfile> searchWithTestData(
            SearchRequest request) {

        List<AlumniProfile> results = new ArrayList<>();

        results.add(new AlumniProfile(
                "Sample Alumni One",
                request.designation(),
                request.university(),
                "India",
                "TEST DATA - Sample software professional",
                request.passoutYear(),
                "https://example.com/mock-alumni/1"
        ));

        results.add(new AlumniProfile(
                "Sample Alumni Two",
                request.designation(),
                request.university(),
                "India",
                "TEST DATA - Sample backend developer",
                request.passoutYear(),
                "https://example.com/mock-alumni/2"
        ));

        List<AlumniProfile> saved = new ArrayList<>();

        for (AlumniProfile profile : results) {
            saved.add(repository.save(profile));
        }

        return saved;
    }

    public List<AlumniProfile> all() {
        return repository.findAll();
    }
}
