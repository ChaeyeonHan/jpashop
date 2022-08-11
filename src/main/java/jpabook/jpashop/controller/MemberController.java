package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")  // 폼 화면을 보여준다
    public String createForm(Model model){
        // model -> 컨트롤러에서 뷰로 넘어갈때 데이터를 실어서 넘긴다
        model.addAttribute("memberForm", new MemberForm());
        // 엔티티로 주고받지X !! 폼객체(dto)를 만들어서 사용해준다.
        // 요구사항이 정말 단순하다면 괜찮겠지만 대부분은 단순하지 X.
        // 엔티티는 핵심 비즈니스 로직만 있어야 하고, 화면을 처리하기위한 로직이 있으면 X -> 화면때문에 엔티티가 지저분해지고, 유지보수가 어려워진다
        // 엔티티는 최대한 순순하게 유지하는 것이 좋다!!
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")  // submit버튼을 클릭하면 넘어온다 -> 데이터를 실제 등록
    public String create(@Valid MemberForm form, BindingResult result){

        // @Valid 다음에 BindingResult가 있으면 오류가 튕겨지지 않고 result에 담기고, 코드가 실행이 된다
        if (result.hasErrors()){  // 오류가 있으면 다시 폼입력 페이지로 돌아간다
            return "members/createMemberForm";  // 스프링이 BindingResult를 기본적으로 폼으로 끌고가서 사용할 수 있게 해준다
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";  // 메인페이지로 넘어가게 된다
    }

    @GetMapping("/members")  // 전체 회원목록 조회
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        // 이 부분도 뿌릴때 엔티티를 그대로 넘겨주지만, 사실 엔티티말고 dto로 바꿔서 해주는게 좋다.

        // api구현시 엔티티 절대로 외부로 반환하지 말것. => 엔티티에 필드가 추가되면 api 스펙이 변하게 된다


//       model.addAttribute("members", memberService.findMembers());  // 이렇게도 사용 가능함(리팩토링)
        return "members/memberList";
    }
}
