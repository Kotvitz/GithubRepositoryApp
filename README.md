# GitHub Repository App

This application provides a service for retrieving information about GitHub repositories and their branches, leveraging the GitHub API.



## Features

- **List User Repositories**: Retrieve a list of GitHub repositories owned by a specific user.

- **Repository Details**: For each repository, obtain details such as the repository name, owner login, branch list, and last commit SHA for each branch.

- **Error Handling**: Properly handle errors, such as when the user does not exist on GitHub.

  

## Technologies Used

- Java 17

- Spring WebFlux

- WebClient for HTTP requests

- GitHub REST API v3

  

## Getting Started

### Prerequisites

- Java 17 JDK installed on your machine
- GitHub Personal Access Token (optional, for increasing rate limit)

### Installation

1. Clone this repository to your local machine:

   ```
   git clone https://github.com/Kotvitz/GithubRepositoryApp.git
   ```

2. Navigate to the project directory:

   ```
   cd GithubRepository
   ```

### Usage

1. Build the application using Maven:

   ```
   mvn clean install
   ```

2. Run the application:

   ```
   java -jar target/github-repository-service.jar
   ```

3. Access the service endpoints using a REST client or browser.

### Endpoints

#### List User Repositories

- **Endpoint:** `/repositories/{username}`

- **Method:** GET

- **Description:** Retrieve a list of GitHub repositories owned by the specified username.

- Response:

  ```
  [
    {
      "name": "repository-name",
      "owner": {
      	"login": "owner-login",
      },
      "branches": [
        {
        	"name": "branch-name",
          "commit": {
          	"sha": "last-commit-sha"
          }
        }
      ],
      "fork": "false"
    }
  ]
  ```

#### Error Handling

- If the specified username does not exist on GitHub, a 404 Not Found error is returned with an appropriate message.

  

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests for any improvements or fixes.