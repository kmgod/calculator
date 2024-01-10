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
    }
}
