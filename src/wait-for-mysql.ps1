# wait-for-mysql.ps1
param (
    [string]$host,
    [string]$port
)

# Define a function to check if MySQL is ready
function Test-MySQL {
    $result = Test-NetConnection -ComputerName $host -Port $port
    return $result.TcpTestSucceeded
}

# Wait for MySQL to be ready
$timeout = 60
$stopwatch = [System.Diagnostics.Stopwatch]::StartNew()

while (-not (Test-MySQL) -and $stopwatch.Elapsed.TotalSeconds -lt $timeout) {
    Start-Sleep -Seconds 1
}

if (Test-MySQL) {
    Write-Host "MySQL is ready!"
    exit 0
} else {
    Write-Host "MySQL did not become ready within the timeout period."
    exit 1
}
