package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
//    @Autowired
//    private EntityManager em;  //스프링이 entitymanager를 만들어서 주입해준다

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);  // transaction이 commit되는 시점에 db에 반영된다(insert쿼리가 날아간다)
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)  // (JPQL, 반환타입)
                .getResultList();
        // SQL -> 테이블을 대상으로 쿼리
        // JPQL -> 엔티티를 대상으로 쿼리(Member)
    }

    public List<Member> findByName(String name){  // 이름으로 회원 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)  // 파라미터 바인딩
                .getResultList();
    }
}
