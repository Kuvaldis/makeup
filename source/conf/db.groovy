db {
    url = 'jdbc:h2:file:db/makeup'
    username = 'sa'
    password = 'sa'

    managementPassword = 'sa'

    encryption {
        key {
            password = 'passwordsecret~1'
        }
    }
}