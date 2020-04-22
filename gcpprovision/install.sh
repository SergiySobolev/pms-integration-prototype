#!/bin/bash
export PROJECT_ID=da-pms-integration-prototype
export PROJECT_NAME=pms-integration-prototype
export SERVICE_ACCOUNT_NAME=logging-account
export KEY_FILE_NAME=logging-account-key-file
export GOOGLE_APPLICATION_CREDENTIALS="$HOME/workspace/pms-integration-prototype/gcpprovision/$KEY_FILE_NAME.json"

gcloud projects create $PROJECT_ID --name=$PROJECT_NAME --set-as-default
gcloud config set project $PROJECT_ID

gcloud services enable compute
gcloud services enable deploymentmanager
gcloud services enable containerregistry.googleapis.com
gcloud services enable stackdriver.googleapis.com

gcloud iam service-accounts create $SERVICE_ACCOUNT_NAME
gcloud projects add-iam-policy-binding $PROJECT_ID --member "serviceAccount:$SERVICE_ACCOUNT_NAME@$PROJECT_ID.iam.gserviceaccount.com" --role "roles/owner"
gcloud iam service-accounts keys create $KEY_FILE_NAME.json --iam-account $SERVICE_ACCOUNT_NAME@$PROJECT_ID.iam.gserviceaccount.com

gcloud deployment-manager deployments create pmsint --config dm/app.yaml