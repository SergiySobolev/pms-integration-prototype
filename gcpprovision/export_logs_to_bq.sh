#!/bin/bash

gcloud logging sinks create AthenaKtorClientLogsSink2BQ \
     bigquery.googleapis.com/projects/da-pms-integration-prototype/datasets/logs_dataset \
      --log-filter='labels.loggerName=org.dataart.pmsintegration.pmsclients.impl.AthenaKtorHealthClient'