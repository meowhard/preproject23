package ru.meowhard.preproject23.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "VkID")
    private String vkId;

    @Column(name = "Name", nullable = false)
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "Email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Request request;

    public User(String name, LocalDate dateOfBirth, String email) {
        this.username = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getRolesToString() {
        return this.roles.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    public boolean getRequest() {
        return this.request.getRequestStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}