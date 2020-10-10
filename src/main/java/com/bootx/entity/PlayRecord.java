
package com.bootx.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class PlayRecord extends BaseEntity<Long> {

    @NotNull
    @JoinColumn(nullable = false,updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    @Column(nullable = false,updatable = false)
    private Long videoId;


    @NotNull
    @Column(nullable = false,updatable = false)
    private String playUrlKey;

    @NotNull
    @Column(nullable = false,updatable = false)
    @JsonView({ViewView.class})
    private Date start;

    @NotNull
    @Column(nullable = false,updatable = false)
    @JsonView({ViewView.class})
    private Date end;

    @NotNull
    @Column(nullable = false,updatable = false)
    @JsonView({ViewView.class})
    private Long seconds;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getPlayUrlKey() {
        return playUrlKey;
    }

    public void setPlayUrlKey(String playUrlKey) {
        this.playUrlKey = playUrlKey;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }
}