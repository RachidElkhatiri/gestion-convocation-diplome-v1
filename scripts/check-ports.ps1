$ports = @(4200, 8080, 8081, 8082, 8083, 8761, 8888, 1521)

Write-Host "Checking required ports..."
foreach ($port in $ports) {
    $conn = Get-NetTCPConnection -LocalPort $port -State Listen -ErrorAction SilentlyContinue
    if ($null -ne $conn) {
        Write-Host "Port $port is in use" -ForegroundColor Yellow
    } else {
        Write-Host "Port $port is free" -ForegroundColor Green
    }
}
