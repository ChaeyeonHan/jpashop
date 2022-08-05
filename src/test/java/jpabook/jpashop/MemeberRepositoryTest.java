//package jpabook.jpashop;
//
//import jpabook.jpashop.domain.Member;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class MemeberRepositoryTest {
//
//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    @Transactional  // @Transactional이 test에 있으면 test가 끝난후 db를 롤백한다!
////    @Rollback(value = false)
//    public void testMember() throws Exception{
//        // given
//        Member member = new Member();
//        member.setUsername("memeberA");
//
//        // when
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
//
//        // then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member);
//
//        System.out.println("findMember == member: " + (findMember == member));
//        // true -> 같은 영속성 컨텍스트 안에서 id값이 동일하기에 동일한 entity로 식별
//
//    }
//}