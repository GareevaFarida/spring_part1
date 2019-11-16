package ru.geekbrains.controller.repr;

import ru.geekbrains.persistence.entity.Authority;

public class AuthorityUsage {
    private Authority authority;
    private boolean usage;

    public AuthorityUsage(Authority authority, boolean usage) {
        this.authority = authority;
        this.usage = usage;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public boolean isUsage() {
        return usage;
    }

    public void setUsage(boolean usage) {
        this.usage = usage;
    }
}
