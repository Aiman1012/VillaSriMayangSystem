package com.softwaredev.srimayangvilla.Service;

import com.softwaredev.srimayangvilla.Exception.UserNotFoundException;
import com.softwaredev.srimayangvilla.Model.Package;
import com.softwaredev.srimayangvilla.Model.Package;
import com.softwaredev.srimayangvilla.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    private PackageRepository pkgRepo;

    public List<Package> listAllPackage(){

        return pkgRepo.findAll() ;
    }

    public void savePackage(Package pkg){
        pkgRepo.save(pkg);
    }

    public Package getPackagebyId(Long packageId) throws UserNotFoundException {
        Optional<Package> resultPackage = pkgRepo.findById(packageId);
        if (resultPackage.isPresent()){
            return resultPackage.get();
        }
        throw new UserNotFoundException("Could not found any Package with ID " + packageId);
    }

    public void deletePackagebyId(Long packageId) throws UserNotFoundException {
        Long PackageCount = pkgRepo.countByPackageId(packageId);
        if(PackageCount== null || PackageCount == 0){
            throw new UserNotFoundException("Could not found any Package with ID " + packageId);
        }
        pkgRepo.deleteById(packageId);
    }
}
