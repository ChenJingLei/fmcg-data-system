package com.john.model;

/**
 * Created by cjl20 on 6/4/2017.
 */
public class UserAuthority {

    private User user;
    private Authority authority;

    public UserAuthority() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "user=" + user +
                ", authority=" + authority +
                '}';
    }
}
