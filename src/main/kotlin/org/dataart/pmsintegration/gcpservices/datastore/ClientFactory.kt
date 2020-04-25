package org.dataart.pmsintegration.gcpservices.datastore

import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.DatastoreOptions


class ClientFactory {

    companion object {

        var datastore: Datastore = DatastoreOptions.getDefaultInstance().getService()

        fun get() : Datastore {
            return datastore
        }

    }


}