#!/usr/bin/env bash
BRANCH=${1:-main}
WORKFLOW=${2:-maven.yml}
REPO=${3:-voidtess/My-QA-Projects}

echo "Triggering GitHub Actions workflow '$WORKFLOW' on ref '$BRANCH' for repo '$REPO'..."

if ! command -v gh >/dev/null 2>&1; then
  echo "gh CLI not found. Install from https://cli.github.com/ and authenticate with 'gh auth login' before running this script." >&2
  exit 1
fi

gh workflow run "$WORKFLOW" --ref "$BRANCH" --repo "$REPO"
if [ $? -ne 0 ]; then
  echo "Failed to dispatch workflow" >&2
  exit 2
fi

echo "Workflow dispatched. Use 'gh run list --repo $REPO' and 'gh run view <run-id> --repo $REPO' to follow progress and download artifacts."