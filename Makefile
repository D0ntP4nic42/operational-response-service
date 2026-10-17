app-dev-up: setup-dev app-build-image-api app-build-image-worker app-container-up grpc-ui-docker

app-build-image-api:
	@./gradlew bootBuildImage -PmainClass=br.petroedge.oprs.api.OprsApiApplication -PimageName=oprs-api:latest

app-build-image-worker:
	@./gradlew bootBuildImage -PmainClass=br.petroedge.oprs.worker.WorkerApplication -PimageName=oprs-worker:latest

app-container-up:
	@docker compose up -d app-api app-worker

app-container-down:
	@docker compose down app-api app-worker

setup-dev:
	@docker compose up -d database mailpit

down-setup-dev:
	@docker compose down

grpc-ui-docker:
	docker run --rm -it --network host \
		fullstorydev/grpcui \
		-plaintext -port 8081 \
		localhost:9090