package hello.hellospring.controller;

public class MemberForm {
    public String getName() {
        //createMemberForm의 id=name에 맞게 들어온다.

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}

