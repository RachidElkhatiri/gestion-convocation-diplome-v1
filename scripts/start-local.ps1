Write-Host "Starting all services with Docker Compose..."
docker compose up -d --build

Write-Host ""
Write-Host "Services started. Useful URLs:"
Write-Host "- Oracle XE      : localhost:1521"
Write-Host "- Eureka Server  : http://localhost:8761"
Write-Host "- Config Server  : http://localhost:8888"
Write-Host "- API Gateway    : http://localhost:8080"
Write-Host "- Angular Front  : http://localhost:4200"
Write-Host ""
Write-Host "To view logs: docker compose logs -f"
