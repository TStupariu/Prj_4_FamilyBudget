import controller.MemberController;
import model.Entry;
import model.Member;
import org.junit.Test;
import repository.MemberRepository;
import ui.MemberUI;

import java.util.List;
import java.util.Scanner;

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

    @Test
    public void AddMemberRepoTest () {
        Member m = new Member("Snoop", "Dawg");
        boolean success = repo.addMember(m);
        assert(success);
    }

    @Test
    public void AddDuplicateMemberRepoTest () {
        Member m = new Member("Snoop", "Dawg");
        repo.addMember(m);
        boolean success2 = repo.addMember(m);
        assert(!success2);
    }


    @Test
    public void AddEntryTest () {
        Member m = new Member("Snoop", "9");
        Entry e = new Entry("cost", 300, "2");
        boolean ok = this.ctrl.addEntry(e);
        assert(ok);
    }

    @Test
    public void AddEntryRepoTest () {
        Member m = new Member("Snoop", "9");
        Entry e = new Entry("cost", 300, "2");
        boolean ok = this.repo.addEntry(e);
        assert(ok);
    }

    @Test
    public void AddEntryBadIdTest () {
        Member m = new Member("Snoop", "DAWG");
        Entry e = new Entry("cost", 300, "9");
        boolean ok = this.ctrl.addEntry(e);
        assert(!ok);
    }

    @Test
    public void AddEntryBadIdRepoTest () {
        Member m = new Member("Snoop", "dawg");
        Entry e = new Entry("cost", 300, "9");
        boolean ok = this.repo.addEntry(e);
        assert(!ok);
    }

    @Test
    public void UISetController () {
        MemberUI ui = new MemberUI(ctrl);
        MemberController mCtrl = new MemberController(new MemberRepository());
        ui.setCtrl(mCtrl);
        assert(ui.getCtrl() == mCtrl);
    }

    @Test
    public void UISetIn () {
        MemberUI ui = new MemberUI(ctrl);
        Scanner nsc = new Scanner(System.in);
        ui.setIn(nsc);
        assert(nsc == ui.getIn());
    }

    @Test
    public void DecisionFalse () {
        Entry e = new Entry("cost", 200, "10");
        boolean ok = repo.addEntry(e);
        assert(!ok);
    }

    @Test
    public void DecisionTrue () {
        Entry e = new Entry("cost", 200, "1");
        boolean ok = repo.addEntry(e);
        assert(ok);
    }

    @Test
    public void AddMemberTrue () {
        Member m = new Member("Name1", "id1");
        boolean ok = repo.addMember(m);
        assert(ok);
    }

    @Test
    public void AddMemberFalse () {
        Member m = new Member("Alex", "1");
        boolean ok = repo.addMember(m);
        assert(!ok);
    }

    @Test
    public void ToStringMember () {
        Member m = new Member("Alex", "1");
        boolean ok = "Alex 1".equals(m.toString());
        assert ok;
    }

    @Test
    public void ToStringEntry () {
        Entry e = new Entry("cost", 200, "1");
        boolean ok = "idM=1 cost 200".equals(e.toString());
        assert ok;
    }
}
