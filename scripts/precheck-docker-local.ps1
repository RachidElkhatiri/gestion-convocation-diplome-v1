param(
    [switch]$StrictPorts
)

$ErrorActionPreference = "Stop"

function Invoke-CheckedCommand {
    param(
        [Parameter(Mandatory = $true)][string]$Command
    )

    & powershell -NoProfile -Command $Command | Out-Null
    if ($LASTEXITCODE -ne 0) {
        throw "Commande en echec: $Command"
    }
}

Write-Host "==> Precheck Docker local"

if (-not (Test-Path "docker-compose.yml")) {
    Write-Error "docker-compose.yml introuvable a la racine du projet."
}

if (-not (Test-Path ".env")) {
    Write-Error "Fichier .env introuvable a la racine du projet."
}

$envFile = Get-Content ".env" -Raw
$requiredVars = @("ORACLE_PASSWORD", "APP_USER_PASSWORD")
foreach ($varName in $requiredVars) {
    if ($envFile -notmatch "(?m)^$varName=.+$") {
        Write-Error "Variable obligatoire manquante ou vide dans .env : $varName"
    }
}

Write-Host "- Verification Docker daemon..."
Invoke-CheckedCommand "docker version"

Write-Host "- Validation compose..."
Invoke-CheckedCommand "docker compose config"

if ($StrictPorts) {
    Write-Host "- Verification des ports locaux..."
    $ports = 1521,4200,8080,8081,8082,8083,8761,8888
    foreach ($port in $ports) {
        $listeners = Get-NetTCPConnection -State Listen -LocalPort $port -ErrorAction SilentlyContinue
        if ($listeners) {
            Write-Error "Port $port deja utilise localement. Libere le port ou modifie docker-compose.yml."
        }
    }
}

Write-Host "Precheck OK."
