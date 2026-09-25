setup-dev:
	@docker compose up -d database mailpit

down:
	@docker compose down

grpc-ui-docker:
	docker run --rm -it --network host \
		fullstorydev/grpcui \
		-plaintext -port 8081 \
		localhost:9090