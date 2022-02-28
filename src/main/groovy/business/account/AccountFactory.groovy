package business.account

import business.BusinessConfig
import business.role.Role

class AccountFactory {

    private static final BusinessConfig CONFIG = BusinessConfig.config

    private AccountFactory() {
    }

    static Account registeredUser() {
        def account = new Account()
        account.id = Long.valueOf CONFIG.registeredUserId()
        account.email = CONFIG.registeredUserEmail()
        account.password = CONFIG.registeredUserPassword()
        account.role = Role.USER
        return account
    }
}
