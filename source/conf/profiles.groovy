environments {
    nfadin {
        server {
            api {
                port = 8081
            }
            static_content {
                port = 8082
                path = 'c:\\Work\\AWS\\makeup\\source\\static\\src'
            }
        }
    }
    kuvaldis {
        server {
            static_content {
                path = 'g:\\Sources\\Projects\\makeup\\source\\static\\src\\'
            }
        }
    }
}