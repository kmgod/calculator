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
                bat "./gradlew test jacocoTestReport"
                publishHTML (target: [
                    reportDir: 'build/reports/jacoco/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
                bat "./gradlew test jacocoTestCoverageVerification"
            }
        }
    }
}
