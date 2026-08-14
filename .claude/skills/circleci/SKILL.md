---
name: CircleCI best practices
description: Conventions for using CircleCI and maintaining configuration files
---

## 1. Use the Latest CircleCI Version

Make sure to use the latest version of CircleCI configuration (currently 2.1).

## 2. Follow Best Practices

When updating or creating CircleCI configuration files in `.circleci/`, ensure the following:

1. **Orbs**: Use CircleCI Orbs where possible to simplify configuration (e.g., `circleci/maven`, `circleci/node`).
2. **Docker Images**: Use specific, versioned Docker images. Eclipse Temurin is preferred for Java projects.
3. **Java Version**: Always use the latest available Java version that is compatible with the project (check `pom.xml` or `build.gradle`).
4. **Caching**: Implement efficient caching strategies for dependencies (e.g., Maven, NPM) to speed up builds.
5. **Workflows**: Use workflows to manage job dependencies and parallel execution.
6. **Resource Class**: Specify appropriate `resource_class` for jobs if necessary.
7. **Clean Commands**: Ensure build commands are optimized and follow project standards (e.g., `mvn clean install`).
8. **Environment Variables**: Use context or project-level environment variables for sensitive data.
9. **Latest Versioning**: When applicable, use the latest versions for tools, orbs, and base images.
10. **Custom Commands**: For non-standard commands (e.g., `make`, shell scripts), do NOT use specialized orb jobs like `maven/test`. Instead, define a custom job, use a compatible executor (like `maven/default`), and include a `checkout` step followed by a `run` step for the command. This avoids issues with orb-internal logic and parameters.
11. **Simplicity**: Favor simplicity in job definitions. If an orb job requires multiple `pre-steps` or complex parameter overrides to run a custom command, a standard job with `run` steps is preferred.

## 3. References

Please use these sources for best practices:
1. https://circleci.com/docs/2.0/config-intro/
2. https://circleci.com/docs/2.0/best-practices/
3. https://circleci.com/developer/orbs
