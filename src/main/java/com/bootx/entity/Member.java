package com.bootx.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member extends BaseEntity<Long> {

    @NotNull
    @Column(nullable = false,updatable = false,unique = true)
    private String openId;

    @ManyToOne
    @NotNull
    @JoinColumn(updatable = false,nullable = false)
    private App app;

    @ManyToOne
    @NotNull
    @JoinColumn(nullable = false)
    private MemberRank memberRank;

    private String nickName;

    private String sessionKey;

    private String token;

    private String unionid;

    private Integer gender;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    private Boolean isAuth;

    /**
     * 积分
     */
    @Column(nullable = false)
    private Long point;

    /**
     * 余额
     */
    @Column(nullable = false, precision = 27, scale = 12)
    private BigDecimal balance;

    @Column(nullable = false, precision = 27, scale = 12)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member parent;

    @OneToMany(mappedBy = "parent",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Member> children = new HashSet<>();

    @Min(1)
    @NotNull
    @Column(nullable = false)
    private Integer grade;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public MemberRank getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(MemberRank memberRank) {
        this.memberRank = memberRank;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        this.isAuth = isAuth;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Member getParent() {
        return parent;
    }

    public void setParent(Member parent) {
        this.parent = parent;
    }

    public Set<Member> getChildren() {
        return children;
    }

    public void setChildren(Set<Member> children) {
        this.children = children;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
