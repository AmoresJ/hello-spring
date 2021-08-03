pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Testing...'
                withGradle {
                    sh './gradlew clean test'
                }
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                    jacoco execPattern:'build/jacoco/*.exec'
                }
            }
        }
        stage('QA') {
            steps {
                sh './gradlew check'
            }
        }
    }
}


/*
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                withGradle {
                    sh './gradlew clean test'
                }
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                }
            }
        }
        stage('QA') {
            sh './gradlew check'
        }
    }
}*/