package com.freightfox.alumni.service;

import com.freightfox.alumni.dto.SearchRequest;
import com.freightfox.alumni.entity.AlumniProfile;
import com.freightfox.alumni.repository.AlumniProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AlumniService {
    private final AlumniProfileRepository repository;
    private final PhantomBusterService phantomBusterService;

    public AlumniService(AlumniProfileRepository repository, PhantomBusterService phantomBusterService) {
        this.repository = repository;
        this.phantomBusterService = phantomBusterService;
    }

    @Transactional
    public List<AlumniProfile> search(SearchRequest request) {
        List<AlumniProfile> fetched = phantomBusterService.search(request);
        for (AlumniProfile profile : fetched) {
            repository.findByUniversityContainingIgnoreCaseAndCurrentRoleContainingIgnoreCase(
                profile.getUniversity(), profile.getCurrentRole() == null ? "" : profile.getCurrentRole());
            repository.save(profile);
        }
        return fetched;
    }

    public List<AlumniProfile> all() { return repository.findAll(); }
}
