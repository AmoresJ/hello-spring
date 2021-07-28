pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Construyendo...'
                withGradle {
                    sh './gradlew assemble'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'build/libs/*.jar'
                }
            }
        }
        stage('Deploy') {
            echo 'Desplegando...'
            withGradle {
                sh './gradlew bootRun'
            }
        }
    }
}