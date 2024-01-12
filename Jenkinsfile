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
                sh "chmod +x gradlew && ./gradlew compileJava"
            }
        }
        stage('Unit test') {
            steps {
                sh "./gradlew test"
            }
        }
        stage('Code coverage') {
            steps {
                sh "./gradlew jacocoTestReport"
                publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage('Static code analysis') {
            steps {
                sh "./gradlew checkstyleMain"
                publishHTML (target: [
                    reportDir: 'build/reports/checkstyle/',
                    reportFiles: 'main.html',
                    reportName: "Checkstyle Report"
                ])
            }
        }
        stage('Package') {
            steps {
                sh "./gradlew build"
            }
        }
        stage('Docker build') {
            steps {
                sh "docker build -t 127.0.0.1:443/dockeruser/calculator ."
            }
        }
        stage('Docker push') {
            steps {
                sh "docker push 127.0.0.1:443/dockeruser/calculator"
            }
        }
        stage('Deploy to staging') {
            steps {
                sh "docker run -d --rm -p 8765:8090 --name calculator 127.0.0.1:443/dockeruser/calculator"
            }
        }
        stage('Acceptance test') {
            steps {
                sleep 10
                sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
            }
        }
    }
    post {
        always {
            sh "docker stop calculator"
        }
    }
}
