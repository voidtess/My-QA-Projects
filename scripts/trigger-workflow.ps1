param(
    [string]$Branch = "main",
    [string]$Workflow = "maven.yml",
    [string]$Repo = "voidtess/My-QA-Projects"
)

Write-Host "Triggering GitHub Actions workflow '$Workflow' on ref '$Branch' for repo '$Repo'..."

if (-not (Get-Command gh -ErrorAction SilentlyContinue)) {
    Write-Error "gh CLI not found. Install from https://cli.github.com/ and authenticate with 'gh auth login' before running this script."
    exit 1
}

# Dispatch the workflow
$rc = gh workflow run $Workflow --ref $Branch --repo $Repo

if ($LASTEXITCODE -ne 0) {
    Write-Error "Failed to dispatch workflow. gh exit code: $LASTEXITCODE"
    exit $LASTEXITCODE
}

Write-Host "Workflow dispatched. Use 'gh run list --repo $Repo' and 'gh run view <run-id> --repo $Repo' to follow progress and download artifacts."