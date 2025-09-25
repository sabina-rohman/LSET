package librarymanagement.uk.lset;

public class Member {
    private final String memberId;
    private String name;
    private String email;

    public Member(String memberId, String name, String email){
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Member[" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ']';
    }
}
