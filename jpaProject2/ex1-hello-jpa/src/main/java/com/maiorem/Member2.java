package com.maiorem;

import javax.persistence.*;

@Entity
public class Member2 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    //다대일 관계 중 일
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Team getTeam() {
        return team;
    }

    // 자바 관례상의 게터세터가 아니므로 메서드 이름을 바꾸는 것을 권장
    public void setTeam(Team team) {
        this.team = team;
        //양방향 매핑
        team.getMembers().add(this);
    }
}
