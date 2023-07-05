package com.softwaredev.srimayangvilla.Model;


import javax.persistence.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
//@Table(name = "Staff", schema="userdb")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffid;
    @Column(nullable = false, unique = true, length = 100)
    private String semail;
    @Column(length = 8, nullable = false)
    private String spassword;
    @Column(length = 100, nullable = false)
    private String sfirstname;
    @Column(length = 100, nullable = false)
    private String slastname;
    @Column(length = 11, nullable = false)
    private String sphoneno;



    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSfirstname() {
        return sfirstname;
    }

    public void setSfirstname(String sfirstname) {
        this.sfirstname = sfirstname;
    }

    public String getSlastname() {
        return slastname;
    }

    public void setSlastname(String slastname) {
        this.slastname = slastname;
    }

    public String getSphoneno() {
        return sphoneno;
    }

    public void setSphoneno(String sphoneno) {
        this.sphoneno = sphoneno;
    }

    public String toString() {
        return "Staff{" +
                "id=" + staffid +
                ", email='" + semail + '\'' +
                ", password='" + spassword + '\'' +
                ", firstname='" + sfirstname + '\'' +
                ", lastname='" + slastname + '\'' +
                ", phoneno='" + sphoneno + '\'' +
                '}';
    }
}

