package com.maiorem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member2 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

//    //다대일 관계 중 일
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    //다대일 읽기전용 필드
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    //다대다 매핑 (실무사용X)
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    //다대다 현실적인 매핑
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

//    public Team getTeam() {
//        return team;
//    }
//
//    // 자바 관례상의 게터세터가 아니므로 메서드 이름을 바꾸는 것을 권장
//    public void setTeam(Team team) {
//        this.team = team;
//        //양방향 매핑
//        team.getMembers().add(this);
//    }
}
