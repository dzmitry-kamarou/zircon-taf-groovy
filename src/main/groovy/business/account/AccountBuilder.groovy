package business.account

class AccountBuilder {

    private final Account account

    AccountBuilder() {
        account = AccountFactory.randomAccount()
    }

    AccountBuilder setEmail(String email) {
        account.email = email
        this
    }

    AccountBuilder setPassword(String password) {
        account.password = password
        this
    }

    Account build() {
        account
    }
}
