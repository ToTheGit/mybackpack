package kr.ac.gachon.shop.service;

import kr.ac.gachon.shop.entity.Member;
import kr.ac.gachon.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service //bean
@Transactional //디비가 바뀌는거를 에러생겼을때 롤백해준다.
@RequiredArgsConstructor //bean 주입방법 생성자 final member, @NonNull member 생성자 생성함
//모든 멤버에 해당하는 생성자를 생성하라
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        this.validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다."); // 예외 처리
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //로그인폼포스트로 돌아온다면 여기로 온다.
        //이메일 기준으로 멤버찾아서 값은 이메일에 값이 있다면 그다음 로그인 로직으로
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                //로그인할때 요 로직을 타는데, 이 세가지 정보만 받는다.
                //패스워드 정보도 사실 넣으면 안된다.
                //암호화되어있는 패스워드가 프론트쪽에서 알필요없음
                .build();
    }
}