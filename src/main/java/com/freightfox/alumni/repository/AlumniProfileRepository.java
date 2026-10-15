package com.freightfox.alumni.repository;

import com.freightfox.alumni.entity.AlumniProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {
    List<AlumniProfile> findByUniversityContainingIgnoreCaseAndCurrentRoleContainingIgnoreCase(
            String university, String currentRole);
}
