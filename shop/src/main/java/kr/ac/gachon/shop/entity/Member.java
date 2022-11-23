package kr.ac.gachon.shop.entity;

import kr.ac.gachon.shop.constant.Role;
import kr.ac.gachon.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
//entity는 setter를 쓰면 안된다는 얘기가있다.
//db필드에 직접 업데이트를 친다.

@ToString
public class Member //extends BaseEntity
    {
    //멤버가 가져야할 기본적인 사항을 baseentity
    //성별, 나이
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;
    //dto에 없는거 하나 더 받음

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}