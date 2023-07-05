package com.softwaredev.srimayangvilla.Model;


import javax.persistence.*;


@Entity

public class feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedid;
    @Column(nullable = false, length = 100)
    private String feedemail;
    @Column(length = 200, nullable = false)
    private String feedMessage;

    public String getFeedemail() {
        return feedemail;
    }

    public void setFeedemail(String feedemail) {
        this.feedemail = feedemail;
    }

    public String getFeedMessage() {
        return feedMessage;
    }

    public void setFeedMessage(String feedMessage) {
        this.feedMessage = feedMessage;
    }

    public Integer getFeedid() {
        return feedid;
    }

    public void setFeedid(Integer feedid) {
        this.feedid = feedid;
    }
}
