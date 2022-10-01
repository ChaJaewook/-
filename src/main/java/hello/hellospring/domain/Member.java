package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리한다는 뜻
public class Member {
    @Id //pk키를 설정한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 알아서 생성해 주는 전략을 identity라고 한다.
    private Long id;
    //데이터 구분을 위한 id
    //@Column(name="username") 이렇게 쓰면 db의 username과 매핑된다.
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
