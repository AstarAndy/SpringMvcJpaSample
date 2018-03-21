pipeline {
  agent any

    stages {

        stage('Unit Test') {
            steps {
                echo 'Running gradle clear and test...'
                sh './gradlew --version'
                sh './gradlew clean'
                sh './runApp.sh test'
                echo 'Test output to: '
                sh 'ls -al ./build/test-results/test'
            }
        }

        stage('Build JAR') {
            steps {
                echo 'Building now'
                sh './gradlew --version'
                sh './gradlew bootRepackage'
                sh 'ls -al'
                echo ' **** The build output folder now contains the following *** '
                sh 'ls -al ./build/libs/'
            }
        }

        stage('Build Docker Container') {
            steps {
                echo 'Building Docker Container now'
                sh './buildDockerImage.sh'
                sh 'ls -al'
            }
        }

        stage('Deploying Docker Container') {
        steps {
          echo 'Deploying....'
        }
      }

    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/reports/**/*.xml'
        }
    }


}