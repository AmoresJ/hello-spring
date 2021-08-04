#!/usr/bin/env groovy
pipeline {
    agent any
    options {
        ansiColor('xterm')
    }
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
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube local') { // Will pick the global server connection you have configured
                    sh './gradlew sonarqube'
                }
            }
        }
        stage('QA') {
            steps {
                withGradle {
                    sh './gradlew check'
                    sh './gradlew pitest'
                }
            }
            post {
                always {
                    recordIssues(
                            tools:
                                    [
                                            pmdParser(pattern: 'build/reports/pmd/*.xml'),
                                            spotBugs(pattern: 'build/reports/spotbugs/*.xml', useRankAsPriority: true),
                                            pit(pattern: 'build/reports/pitest/**/*.xml')
                                    ]
                    )
                }
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t hellospring:main-1.0.${BUILD_NUMBER}-${GIT_COMMIT} .'
            }
        }
        stage('Security') {
            steps {
                sh 'trivy image --format=json --output=trivy-image.json hellospring:main-1.0.${BUILD_NUMBER}-${GIT_COMMIT}'
            }
            post {
                always {
                    recordIssues(
                            tools:
                                    [
                                            trivy(pattern: '*.json')
                                    ]
                    )
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}
