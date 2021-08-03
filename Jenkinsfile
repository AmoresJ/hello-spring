#!/usr/bin/env groovy
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
            steps {
                withGradle {
                    sh './gradlew check'
                }
            }
            post {
                always {
                    recordIssues(
                            tools:
                                    [
                                            pmdParser(pattern: 'build/reports/pmd/*.xml'),
                                            spotBugs(pattern: 'build/reports/spotbugs/*.xml', useRankAsPriority: true)
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
                sh 'trivy --format=json --output=trivy-image.json image hellospring:main-1.0.${BUILD_NUMBER}-${GIT_COMMIT}'
            }
            post {
                always {
                    recordIssues(
                            tools:
                                    [
                                            trivy(pattern: 'trivy-image.json')
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