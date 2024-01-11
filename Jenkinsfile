pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git url: 'https://github.com/kmgod/calculator.git', 
                branch: 'main'
            }
        }
        stage('Compile') {
            steps {
                bat "./gradlew compileJava"
            }
        }
        stage('Unit test') {
            steps {
                bat "./gradlew test"
            }
        }
        stage('Code coverage') {
            steps {
                bat "./gradlew jacocoTestReport"
                publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
                bat "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage('Static code analysis') {
            steps {
                bat "./gradlew checkstyleMain"
                publishHTML (target: [
                    reportDir: 'build/reports/checkstyle/',
                    reportFiles: 'main.html',
                    reportName: "Checkstyle Report"
                ])
            }
        }
        stage('Package') {
            steps {
                bat "./gradlew build"
            }
        }
        stage('Docker build') {
            steps {
                bat "docker build -t dockeruser/claculator ."
            }
        }
    }
}
