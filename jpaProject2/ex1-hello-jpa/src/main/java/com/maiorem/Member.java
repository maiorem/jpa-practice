package com.maiorem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", initialValue = 1, allocationSize = 50) //시퀀스 생성
public class Member {
// allocationsSize : 미리 디비에 갯수만큼 올려놓고 메모리에서 꺼내 씀
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //LocalDateTime 을 쓸 때는 필요 없음
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDateTime testLocalDate;

    //큰 텍스트
    @Lob
    private String description;

    //디비 저장 없이 메모리에서만 쓸때
    @Transient
    private int temp;

    public Member(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
