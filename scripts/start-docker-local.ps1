param(
    [switch]$NoBuild,
    [switch]$SkipPrecheck,
    [int]$TimeoutSec = 360
)

$ErrorActionPreference = "Stop"

function Wait-HttpOk {
    param(
        [Parameter(Mandatory = $true)][string]$Url,
        [Parameter(Mandatory = $true)][string]$Label,
        [int]$TimeoutSeconds = 180
    )

    $start = Get-Date
    while (((Get-Date) - $start).TotalSeconds -lt $TimeoutSeconds) {
        try {
            $response = Invoke-WebRequest -Uri $Url -UseBasicParsing -TimeoutSec 10
            if ($response.StatusCode -ge 200 -and $response.StatusCode -lt 300) {
                Write-Host "OK: $Label ($Url)"
                return
            }
        } catch {
            Start-Sleep -Seconds 4
            continue
        }

        Start-Sleep -Seconds 2
    }

    throw "Timeout readiness: $Label ($Url)"
}

function Wait-ContainerHealthy {
    param(
        [Parameter(Mandatory = $true)][string]$ContainerName,
        [int]$TimeoutSeconds = 240
    )

    $start = Get-Date
    while (((Get-Date) - $start).TotalSeconds -lt $TimeoutSeconds) {
        $status = docker inspect --format "{{if .State.Health}}{{.State.Health.Status}}{{else}}{{.State.Status}}{{end}}" $ContainerName 2>$null
        if ($LASTEXITCODE -eq 0) {
            if ($status -eq "healthy" -or $status -eq "running") {
                Write-Host "OK: $ContainerName is $status"
                return
            }
        }

        Start-Sleep -Seconds 4
    }

    throw "Timeout readiness: container $ContainerName not healthy/running"
}

function Start-OneService {
    param(
        [Parameter(Mandatory = $true)][string]$Service
    )

    if ($NoBuild) {
        Write-Host "==> Lancement de $Service (sans rebuild)..."
        docker compose up -d --no-build --no-deps $Service
    } else {
        Write-Host "==> Build + lancement de $Service..."
        docker compose up -d --build --no-deps $Service
    }

    if ($LASTEXITCODE -ne 0) {
        throw "Echec docker compose up pour $Service"
    }
}

function Show-ServiceLogsAndFail {
    param(
        [Parameter(Mandatory = $true)][string]$Service
    )

    Write-Host ""
    Write-Host "==> Logs de $Service (dernieres lignes)"
    docker compose logs --tail 120 $Service
    throw "Service non pret: $Service"
}

Write-Host "==> Demarrage de l'application en Docker (local)"

if (-not $SkipPrecheck) {
    & "$PSScriptRoot/precheck-docker-local.ps1"
}

Start-OneService "oracle-db"
try {
    Wait-ContainerHealthy -ContainerName "gcd-oracle-db" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "oracle-db"
}

Start-OneService "eureka-server"
try {
    Wait-HttpOk -Label "Eureka" -Url "http://localhost:8761" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "eureka-server"
}

Start-OneService "config-server"
try {
    Wait-HttpOk -Label "Config Server" -Url "http://localhost:8888/actuator/health" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "config-server"
}

Start-OneService "geo-service"
try {
    Wait-HttpOk -Label "Geo Service" -Url "http://localhost:8082/actuator/health" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "geo-service"
}

Start-OneService "candidat-service"
try {
    Wait-HttpOk -Label "Candidat Service" -Url "http://localhost:8081/actuator/health" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "candidat-service"
}

Start-OneService "attribution-service"
try {
    Wait-HttpOk -Label "Attribution Service" -Url "http://localhost:8083/actuator/health" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "attribution-service"
}

Start-OneService "api-gateway"
try {
    Wait-HttpOk -Label "API Gateway" -Url "http://localhost:8080/actuator/health" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "api-gateway"
}

Start-OneService "frontend-angular"
try {
    Wait-HttpOk -Label "Frontend" -Url "http://localhost:4200" -TimeoutSeconds $TimeoutSec
} catch {
    Show-ServiceLogsAndFail -Service "frontend-angular"
}

Write-Host ""
Write-Host "==> Etat des conteneurs"
docker compose ps

Write-Host ""
Write-Host "==> URLs utiles"
Write-Host "- Frontend       : http://localhost:4200"
Write-Host "- API Gateway    : http://localhost:8080"
Write-Host "- Eureka Server  : http://localhost:8761"
Write-Host "- Config Server  : http://localhost:8888"
Write-Host "- Oracle XE      : localhost:1521"
Write-Host ""
Write-Host "Commandes utiles:"
Write-Host "- Logs:   docker compose logs -f"
Write-Host "- Stop:   docker compose down"
