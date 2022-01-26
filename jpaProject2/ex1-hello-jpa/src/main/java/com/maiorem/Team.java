package com.maiorem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //다대일 양방향 연관관계 중 다
    @OneToMany(mappedBy = "team")
    private List<Member2> members = new ArrayList<>();
    //연관관계의 주인에게 mappedBy가 있음.
    //외래키가 있는 쪽이 연관관계의 주인. (이 경우엔 Member에 team이 있으므로 Member.team 이 주인. 다대일 중 다 쪽이 주인.)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member2> getMembers() {
        return members;
    }

    public void setMembers(List<Member2> members) {
        this.members = members;
    }
}
