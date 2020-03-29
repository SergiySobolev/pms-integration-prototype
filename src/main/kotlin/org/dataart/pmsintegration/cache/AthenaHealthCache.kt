package org.dataart.pmsintegration.cache

import java.time.Instant

class AthenaHealthCache constructor(var accessToken: String,
                                    var accessTokenRetrieveTime: Instant
) {

    fun isAccessTokenValid(): Boolean {
        //todo check for token validity time
        return true
    }

}
