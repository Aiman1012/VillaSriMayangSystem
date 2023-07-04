package com.softwaredev.srimayangvilla.Repository;

import com.softwaredev.srimayangvilla.Model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
    Long countByPackageId(Long packageId);
}
