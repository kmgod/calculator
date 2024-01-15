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
//         stage('Code coverage') {
//             steps {
//                 sh "./gradlew jacocoTestReport"
//                 publishHTML (target: [
//                     reportDir: 'build/reports/jacoco/test/html',
//                     reportFiles: 'index.html',
//                     reportName: "JaCoCo Report"
//                 ])
//                 sh "./gradlew jacocoTestCoverageVerification"
//             }
//         }
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
                sh "docker build -t 192.168.56.31:443/dockeruser/calculator:${BUILD_TIMESTAMP} ."
            }
        }
        stage('Docker push') {
            steps {
                sh "docker push 192.168.56.31:443/dockeruser/calculator:${BUILD_TIMESTAMP}"
            }
        }
        stage('Update version') {
            steps {
                sh "sed  -i 's/{{VERSION}}/${BUILD_TIMESTAMP}/g' deployment.yaml"
            }
        }
        stage('Deploy to staging') {
            steps {
                sh "kubectl apply -f hazelcast.yaml"
                sh "kubectl apply -f deployment.yaml"
                sh "kubectl apply -f service.yaml"
            }
        }
        stage('Acceptance test') {
            steps {
                sleep 10
                sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
            }
        }
        stage('Smoke test') {
            steps {
                sleep 10
                sh "chmod +x smoke_test.sh && ./smoke_test.sh"
            }
        }
    }
//     post {
//         always {
//             sh "docker stop calculator"
//         }
//     }
}
