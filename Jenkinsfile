pipeline {
  agent {
    docker {
      image
      'gradle:3-alpine'
      args
      '-v /root/.m2:/root/.m2'
    }
  }
  stages {
    stage('Build') {
      steps {
        sh 'gradle clean test'
      }
    }
  }
}