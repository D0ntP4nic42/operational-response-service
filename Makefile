app-dev-up: setup-dev app-build-image app-container-up grpc-ui-docker

app-build-image:
	@./gradlew bootBuildImage

app-container-up:
	@docker compose up -d app

app-container-down:
	@docker compose down app

setup-dev:
	@docker compose up -d database mailpit

down-setup-dev:
	@docker compose down

grpc-ui-docker:
	docker run --rm -it --network host \
		fullstorydev/grpcui \
		-plaintext -port 8080 \
		localhost:9090