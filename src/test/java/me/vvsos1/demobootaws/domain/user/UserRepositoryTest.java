package me.vvsos1.demobootaws.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaAuditing
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void save_test() {
        //given
        String email = "email";
        String name = "name";
        String picture = "picture";
        Role role = Role.USER;

        User user = User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .role(role)
                .build();

        //when
        userRepository.save(user);

        //then
        List<User> all = userRepository.findAll();

        assertThat(all).hasSize(1);
        User target = all.get(0);

        assertThat(target.getId())
                .isGreaterThan(0L);

        assertThat(target.getEmail())
                .isEqualTo(email);
        assertThat(target.getName())
                .isEqualTo(name);
        assertThat(target.getPicture())
                .isEqualTo(picture);
        assertThat(target.getRole())
                .isEqualTo(role);


    }

    @Test
    public void findByEmail_test() {
        //given
        String email = "email";
        String name = "name";
        String picture = "picture";
        Role role = Role.USER;

        User user = User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .role(role)
                .build();
        userRepository.save(user);

        //when
        User target = userRepository.findByEmail(email).orElseThrow(() -> new AssertionError("error : no such user; email:" + email));

        //then

        assertThat(target.getEmail())
                .isEqualTo(email);
        assertThat(target.getName())
                .isEqualTo(name);
        assertThat(target.getPicture())
                .isEqualTo(picture);
        assertThat(target.getRole())
                .isEqualTo(role);

    }

    @Test
    public void created_time_test() {
        //given
        String email = "email";
        String name = "name";
        String picture = "picture";
        Role role = Role.USER;

        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .role(role)
                .build();

        //when
        userRepository.save(user);

        //then
        List<User> all = userRepository.findAll();

        User target = all.get(0);


        assertThat(target.getCreatedDate())
                .isAfterOrEqualTo(now);

    }

    @Test
    public void modified_time_test() {
        //given
        String email = "email";
        String name = "name";
        String picture = "picture";
        Role role = Role.USER;


        User user = userRepository.save(User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .role(role)
                .build());

        //when
        LocalDateTime now = LocalDateTime.now();
        user.update("name2","picture2");


        //then
        List<User> all = userRepository.findAll();

        User target = all.get(0);


        assertThat(target.getModifiedDate())
                .isAfterOrEqualTo(now);

    }

}