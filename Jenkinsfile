pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'mhamed17/foyer:latest'  // Vérifiez que l'image correspond à votre dépôt Docker Hub
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials'  // L'ID des identifiants configuré dans Jenkins
    }
    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/abderrahmenzarrouk/5ARCTIC6-G5-FOYER'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker image..."
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    withDockerRegistry([credentialsId: DOCKER_HUB_CREDENTIALS, url: 'https://index.docker.io/v1/']) {
                        echo "Pushing image to Docker Hub..."
                        sh "docker push ${DOCKER_IMAGE}"
                    }
                }
            }
        }
    }
    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
