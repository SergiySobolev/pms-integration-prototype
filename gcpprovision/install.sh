#!/bin/bash
export PROJECT_ID=da-pms-integration-prototype
export PROJECT_NAME=pms-integration-prototype

gcloud projects create $PROJECT_ID --name=$PROJECT_NAME --set-as-default
gcloud config set project $PROJECT_ID

gcloud services enable compute
gcloud services enable deploymentmanager
gcloud services enable containerregistry.googleapis.com
gcloud services enable stackdriver.googleapis.com

gcloud deployment-manager deployments create pmsint --config dm/app.yaml