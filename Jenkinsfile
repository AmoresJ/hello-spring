pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                withGradle {
                    sh './gradlew clean test'
                }
            }
        }
        stage('Jacoco') {
            steps {
                jacoco()
            }
        }
    }
    post {
        always {
            junit 'build/test-results/test/*.xml'
        }
    }
}