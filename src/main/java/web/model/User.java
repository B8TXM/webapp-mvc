package web.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column(nullable = false)
    private Integer age;

    public User() {}

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void setAge(Integer age) {
        if (age == null) {
            throw new IllegalArgumentException("Age cannot be null");
        }

        if (age < 0 || age > 127) {
            throw new IllegalArgumentException("Age must be between 0 and 127");
        }

        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

}

