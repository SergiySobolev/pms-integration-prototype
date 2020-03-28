package org.dataart.pmsintegration.dao;

import java.time.Instant

class AthenaHealthDao constructor(var accessToken: String,
                                  var accessTokenRetrieveTime: Instant
) {

    fun isAccessTokenValid(): Boolean {
        //todo check for token validity time
        return true
    }

}
