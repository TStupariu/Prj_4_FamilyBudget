import controller.MemberController;
import model.Entry;
import model.Member;
import org.junit.Test;
import repository.MemberRepository;

import java.util.List;

public class TestCases {

    private final MemberRepository repo;
    private final MemberController ctrl;

    public TestCases() {
        this.repo = new MemberRepository();
        this.ctrl = new MemberController(repo);
    }

    @Test
    public void AddMemberTest () {
        Member m = new Member("Snoop", "Dawg");
        boolean success = ctrl.addMember(m);
        assert(success);
    }

    @Test
    public void AddDuplicateMemberTest () {
        Member m = new Member("Snoop", "Dawg");
        boolean success1 = ctrl.addMember(m);
        boolean success2 = ctrl.addMember(m);
        assert(!success2);
    }
}
