package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("When POST request with Guest is made, gets Guest information with new ID")
    request {
        method 'POST'
        url '/guests'
        body(
                "name":"Jayant",
                "email":"jayant@gmail.com",
                "contactNumber":"123456789",
                "cards":[]
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 201
        body(

        )
        headers {
            contentType(applicationJson())
        }
    }
}

