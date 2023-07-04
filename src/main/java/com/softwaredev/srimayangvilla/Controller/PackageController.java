package com.softwaredev.srimayangvilla.Controller;

import com.softwaredev.srimayangvilla.Exception.UserNotFoundException;
import com.softwaredev.srimayangvilla.Model.Package;
import com.softwaredev.srimayangvilla.Repository.PackageRepository;
import com.softwaredev.srimayangvilla.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class PackageController {
    @Autowired
    private PackageService packageService;

    @Autowired
    private PackageRepository packageRepository;

    @GetMapping("/packageIndex")
    public String showPackage(Model model){
        List<Package> listPackages = packageService.listAllPackage();
        model.addAttribute("ListPackages", listPackages);

        return "packageIndex";
    }

    @GetMapping("/packageIndex/addpackage")
    public String addPackage(Model model){
        Package pkg = new Package();
        model.addAttribute("package", pkg);

        return "addPackage";

    }

    @PostMapping("/packageIndex/packageUpload")
    public String packageUpload(@RequestParam("packageImage")MultipartFile packageImage, @RequestParam("packageName") String packageName,
                             @RequestParam("packageDesc") String packageDesc, @RequestParam("packagePrice") double packagePrice, HttpSession httpSession){
        Package pkg = new Package();

        pkg.setPackageImage(packageImage.getOriginalFilename());
        pkg.setPackageName(packageName);
        pkg.setPackagePrice(packagePrice);
        pkg.setPackageDesc(packageDesc);

        Package packageSave = packageRepository.save(pkg);
        if(packageSave!=null){
            try{
                File saveFile = new ClassPathResource("static/packageImg").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + packageImage.getOriginalFilename());
                System.out.println(path);

                Files.copy(packageImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        httpSession.setAttribute("msg", "Package added successfully!");

        return "redirect:/packageIndex";
    }


    @GetMapping("/packageIndex/delete/{packageId}")
    public String deletePackage(@PathVariable("packageId") Long packageId, RedirectAttributes ra){
        try {
            packageService.deletePackagebyId(packageId);
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The package has been deleted");
        }
        return "redirect:/packageIndex";
    }

    @GetMapping("/packageIndex/edit/{packageId}")
    public String editPackage(@PathVariable("packageId") Long packageId, Model model, RedirectAttributes ra){
        try {
            Package pkg = packageService.getPackagebyId(packageId);
            model.addAttribute("package", pkg);
            return "editPackage";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/packageIndex";
    }


    @PostMapping("/packageIndex/update")
    public String updatePackage(@RequestParam("packageId") Long packageId, @RequestParam("packageImage")MultipartFile packageImage, @RequestParam("packageName") String packageName,
                                @RequestParam("packageDesc") String packageDesc, @RequestParam("packagePrice") double packagePrice, HttpSession httpSession) {
        try {
            Package pkg = packageService.getPackagebyId(packageId);

            pkg.setPackageName(packageName);
            pkg.setPackageDesc(packageDesc);
            pkg.setPackagePrice(packagePrice);

            if (!packageImage.isEmpty()) {
                pkg.setPackageImage(packageImage.getOriginalFilename());
//                room.setRoomImage(roomImg.getOriginalFilename());
                File saveFile = new ClassPathResource("static/packageImg").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + packageImage.getOriginalFilename());
                Files.copy(packageImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }

            packageService.savePackage(pkg);
            httpSession.setAttribute("msg", "Package updated successfully");
        } catch (UserNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/packageIndex";
    }
}

