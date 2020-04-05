#!/bin/bash
export PROJECT_ID=da-pms-integration-prototype
export PROJECT_NAME=pms-integration-prototype
export BACKEND_IMAGE=gcr.io/da-pms-integration-prototype/pms-integration-backend:1.0
export FRONTEND_IMAGE=gcr.io/da-pms-integration-prototype/pms-integration-frontend:1.0

gcloud projects create $PROJECT_ID --name=$PROJECT_NAME --set-as-default
gcloud config set project $PROJECT_ID

gcloud services enable compute
gcloud services enable deploymentmanager
gcloud services enable containerregistry.googleapis.com
gcloud services enable cloudbuild.googleapis.com

gcloud compute instance-templates create-with-container backend-it --container-image $BACKEND_IMAGE

gcloud deployment-manager deployments create backend-deployment --config dm/backend.yaml