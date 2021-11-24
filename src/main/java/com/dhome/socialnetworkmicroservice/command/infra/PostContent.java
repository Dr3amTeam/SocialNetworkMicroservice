package com.dhome.socialnetworkmicroservice.command.infra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class PostContent {
    @Id
    private String postId;
    @NotNull
    private String videoUrl;
    @NotNull
    private String content;
    @NotNull
    private Date uploadDate;
    @NotNull
    private String employeeId;

    public PostContent(){

    }

    public PostContent(String postId, String videoUrl, String content, Date uploadDate, String employeeId){
        this.postId = postId;
        this.videoUrl = videoUrl;
        this.content = content;
        this.uploadDate = uploadDate;
        this.employeeId = employeeId;
    }
}
