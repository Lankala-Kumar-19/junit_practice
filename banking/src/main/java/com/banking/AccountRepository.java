package com.banking;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findById(Long id);

    Account save(Account account);
}
