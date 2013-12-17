package kuvaldis.makeup.lib.data.domain

/**
 * @author Kuvaldis
 * Create date: 15.12.13 15:01
 */
enum CreateUserResult {

    SUCCESS('Success'),
    LOGIN_EXISTS('Login already exists'),
    USERNAME_EXISTS('Username already exists')

    private String description

    CreateUserResult(String description) {
        this.description = description
    }
}
