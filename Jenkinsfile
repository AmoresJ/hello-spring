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
                    sh './gradlew clean test pitest'
                }
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                }
            }
        }
        stage('Analysis') {
            failFast true
            parallel {
                stage('SonarQube analysis') {
                    when { expression { true } }
                    steps {
                        withSonarQubeEnv('SonarQube local') {
                            // Will pick the global server connection you have configured
                            sh './gradlew sonarqube'
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
                                                    spotBugs(pattern: 'build/reports/spotbugs/*.xml', useRankAsPriority: true),
                                                    pit(enabledForFailure: true, pattern: 'build/reports/pitest/**/*.xml')
                                            ]
                            )
                        }
                    }
                }
            }
        }
        stage('Build') {
            steps {
                sh 'docker build -t 10.250.5.19:5050/amoresj/hello-spring/hellospring:main-1.0.${BUILD_NUMBER}-${GIT_COMMIT} .'
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
                //sh 'docker-compose up -d'
                withDockerRegistry(credentialsId: 'gitlab-registry', url: '10.250.5.19:5050/amoresj/hello-spring') {
                    sh 'docker push 10.250.5.19:5050/amoresj/hello-spring/hellospring:main-1.0.${BUILD_NUMBER}-${GIT_COMMIT}'
                }
            }
        }
    }
}
