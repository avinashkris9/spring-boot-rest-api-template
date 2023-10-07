all: help
default: help
#TODO: Get automatically from pom.xml
APP_NAME:=spring-boot-api-template
DOCKER_IMAGE_NAME=${DOCKER_USER}/${APP_NAME}
KUBE_CONTEXT=docker-desktop


.PHONY: help docker build  run
help:  ## Show help messages for make targets
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(firstword $(MAKEFILE_LIST)) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[32m%-30s\033[0m %s\n", $$1, $$2}'

publish-docker: ## Publish docker image to dockerhub
	docker push ${DOCKER_IMAGE_NAME}:latest

build: ## Build Locally
	@echo "Building..."
	mvn clean install

docker: ## Build docker image
	# docker build --pull  --build-arg artificatid=${APP_NAME} --build-arg "version=${APP_VERSION}" -t ${APP_NAME}:latest -t ${DOCKER_IMAGE_NAME}:latest .
	docker build --pull  --build-arg artificatid=${APP_NAME}  -t ${DOCKER_IMAGE_NAME}:latest -t ${APP_NAME}:latest  .


run: docker ## run local k8s

	kubectl --context ${KUBE_CONTEXT} apply -f ./k8s/
	kubectl --context ${KUBE_CONTEXT} wait pod -l app.kubernetes.io/name=${APP_NAME} --for=condition=Ready --timeout=720s

destroy: ## Destroy k8s resources

	kubectl --context ${KUBE_CONTEXT} delete --ignore-not-found=true -f ./k8s/