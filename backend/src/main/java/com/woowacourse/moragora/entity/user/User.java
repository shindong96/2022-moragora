package com.woowacourse.moragora.entity.user;

import static com.woowacourse.moragora.entity.Provider.CHECKMATE;

import com.woowacourse.auth.exception.AuthenticationFailureException;
import com.woowacourse.moragora.entity.Provider;
import com.woowacourse.moragora.exception.InvalidFormatException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    private static final String REGEX_EMAIL = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    private static final int NICKNAME_LENGTH_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private EncodedPassword password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Builder
    public User(final Long id, final String email, final EncodedPassword password,
                final String nickname, final Provider provider) {
        validateEmail(email);
        validateNickname(nickname);
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.provider = provider;
    }

    public User(final String email, final EncodedPassword password, final String nickname) {
        this(null, email, password, nickname, CHECKMATE);
    }

    public User(final String email, final String nickname, final Provider provider) {
        this(null, email, null, nickname, provider);
    }

    public void checkPassword(final RawPassword rawPassword) {
        if (!password.isSamePassword(rawPassword)) {
            throw new AuthenticationFailureException();
        }
    }

    public void updateNickname(final String nickname) {
        validateNickname(nickname);
        this.nickname = nickname;
    }

    public void updatePassword(final EncodedPassword newPassword) {
        this.password = newPassword;
    }

    private void validateEmail(final String email) {
        final Matcher matcher = PATTERN_EMAIL.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidFormatException();
        }
    }

    private void validateNickname(final String nickname) {
        if (nickname.length() > NICKNAME_LENGTH_LENGTH) {
            throw new InvalidFormatException();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
