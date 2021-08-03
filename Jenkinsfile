#!/usr/bin/env groovy
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
                                            spotBugs(pattern: 'build/reports/spotbugs/*.xml')
                                    ]
                    )
                }
            }
        }
    }
}