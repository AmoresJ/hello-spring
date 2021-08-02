pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh './gradlew test'
                //junit '**/test-results/*.xml'
            }
        }
        stage('Build') {
            steps {
                echo 'Construyendo...'
                // Alternativa con gradlew
                /*withGradle {
                    sh './gradlew assemble'
                }*/
                // Alternativa docker build - docker compose
                //sh 'docker build -t hellospring:latest .'
            }
            /*post {
                success {
                    archiveArtifacts artifacts: 'build/libs/*.jar'
                }
            }*/
        }
        stage('Deploy') {
            steps {
                echo 'Desplegando...'
                // Alternativa con gradlew
                /*withGradle {
                    sh './gradlew bootRun'
                }*/
                // Alternativa docker build - docker compose
                //sh 'docker-compose up -d'
            }
        }
    }
}