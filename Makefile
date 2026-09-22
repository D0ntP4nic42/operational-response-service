setup-dev:
	@docker compose up -d database mailpit

down:
	@docker compose down