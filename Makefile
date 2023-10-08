all: help
default: help
#TODO: Get automatically from pom.xml
APP_NAME:=spring-boot-api-template
DOCKER_IMAGE_NAME=${DOCKER_USER}/${APP_NAME}
KUBE_CONTEXT=docker-desktop

CG=\033[0;32m
NC=\033[0m


.PHONY: help docker build  run-pod destroy-docker destroy-pod

help:  ## Show help messages for make targets
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(firstword $(MAKEFILE_LIST)) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[32m%-30s\033[0m %s\n", $$1, $$2}'

build: ## Maven Build
	@echo "$(CG)Performing Maven Build${NC}"
	@mvn clean install

docker-build: build ## Build docker image
	@echo "${CG}Building docker image..${NC}"
	
	@docker build --pull  --build-arg NAME=${APP_NAME} --build-arg artificatid=${APP_NAME}  -t ${DOCKER_IMAGE_NAME}:latest -t ${APP_NAME}:latest -f Dockerfile-dev  .

docker-publish: ## Publish docker image to dockerhub

	@echo "${CG}Publish Docker image ${DOCKER_IMAGE_NAME} ${NC}"
	@docker push ${DOCKER_IMAGE_NAME}:latest

run: ## Run as docker container
	@echo "${CG} Run local docker container.. ${NC}"
	@docker run --rm -it ${DOCKER_IMAGE_NAME}:latest


pod-run: docker-build ## Run as k8s deployment
	@echo "${CG}Run as k8s deployment ${APP_NAME} on context ${KUBE_CONTEXT}${NC}"
	@kubectl --context ${KUBE_CONTEXT} apply -f ./k8s/
	@kubectl --context ${KUBE_CONTEXT} rollout restart deployment ${APP_NAME}
	@kubectl --context ${KUBE_CONTEXT} wait pod -l app.kubernetes.io/name=${APP_NAME} --for=condition=Ready --timeout=720s

pod-destroy: ## Delete k8s resources
	@kubectl --context ${KUBE_CONTEXT} delete --ignore-not-found=true -f ./k8s/

docker-destroy: ## Prune Docker 
	@docker system prune -f --filter "label=name=${APP_NAME}"
	
