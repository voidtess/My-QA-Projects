# NezamInteractions

Automated UI interaction tests (Selenium + TestNG) for learning and demo purposes.

[![CI](https://github.com/voidtess/My-QA-Projects/actions/workflows/maven.yml/badge.svg)](https://github.com/voidtess/My-QA-Projects/actions/workflows/maven.yml) [![Build Status](https://img.shields.io/badge/build-local-brightgreen)](https://github.com/)

## Summary
This Maven-based Java project contains UI tests that exercise common web interactions (alerts, dropdowns, drag-and-drop, file upload, iframes, tables, etc.). It is intended as a personal test automation collection and a starting point for a QA automation portfolio.

## Prerequisites
- JDK 26
- Maven (3.x)
- A modern browser (Chrome/Edge/Firefox) and the corresponding WebDriver on PATH, or use a driver manager (e.g. WebDriverManager)

## Quick start
1. Open the project in IntelliJ IDEA.
2. Build and run all tests:

   mvn test

3. Run a single TestNG test class (example):

   mvn -Dtest=Alerts test

If your tests require a specific driver, ensure the driver executable (chromedriver/geckodriver) is available on PATH or configured by your test utilities.

## Project structure
- pom.xml — Maven configuration and dependencies
- src/test/java — Test classes (Alerts, DragAndDrop, Dropdown, DynamicElements, FileUpload, Iframe(s), Tables, AutomationUtils)
- src/test/resources — test fixtures and sample HTML pages

## Committing & sharing
- Keep machine-specific IDE files out of Git (.idea/workspace.xml, *.iml local-only files). Shared metadata (encodings.xml, misc.xml, vcs.xml) may be committed.
- Use a descriptive commit message for test changes and data updates.

## Suggested visual improvements (to make the repository more presentable)
1. Add status badges in the README (build, test coverage, license) using shields.io.
2. Include a short animated GIF or screenshot of tests running or a sample test report in `/docs` and reference it in README.
3. Add a Table of Contents for easier navigation to sections.
4. Create a GitHub Actions workflow to run tests on push/PR; show the build badge in the README.
5. Add a CONTRIBUTING.md and a short LICENSE (MIT/Apache) to make onboarding and reuse clearer.
6. Add a neat header image or logo and a one-line project tagline for immediate context.

## Next steps (optional)
- Add GitHub Actions CI (run `mvn test`) and publish results/HTML reports.
- Add test reports (Surefire, ReportNG, Allure) and include a link or artifact.

## Running tests without installing Maven or IntelliJ
If you don't want to install anything locally, run tests using GitHub Actions (no local installs required):

1. Push your changes to GitHub (any branch) and open the repository page.
2. Go to the Actions tab → select the "CI" workflow → click "Run workflow" (or use the recent workflow run). The workflow uses JDK 26 and runs `mvn test` in GitHub's cloud.
3. After the run completes, open the workflow run and download the "surefire-reports" artifact to view test results and reports.

If you'd rather run tests locally without installing Maven, Docker can be used (requires Docker installed). I can add a Dockerfile and a small run script if you want.

## Triggering CI from your machine using GitHub CLI
Two helper scripts are included in /scripts to dispatch the CI workflow using GitHub CLI (gh). Install the GitHub CLI and authenticate once with `gh auth login`, then run either script:

PowerShell (Windows):

  .\scripts\trigger-workflow.ps1 -Branch main

Bash (Linux/macOS or WSL):

  ./scripts/trigger-workflow.sh main

The scripts default to repo voidtess/My-QA-Projects and workflow maven.yml. To change, pass branch/workflow/repo as arguments.

If you prefer a token-based curl approach instead of gh, create a Personal Access Token (PAT) and I can provide a short curl script.

## Contact
Repository owner: voidtess

---

If you'd like, I can add a GitHub Actions workflow and example badges, or commit this README for you and push to your remote. Which would you prefer?