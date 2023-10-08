pipeline {
  agent {
        kubernetes {
        yaml '''
            apiVersion: v1
            kind: Pod
            metadata:
              name: java-agent
            spec:
              containers:
              - name: maven
                image: maven:3.2-jdk-8
                tty: true
                command:
                - cat
              - name: docker
                image: docker:stable
                tty: true
                command:
                - cat
                volumeMounts:
                - mountPath: /var/run/docker.sock
                  name: dind-socket
              volumes:
              - hostPath:
                  path: /var/run/dind/docker.sock
                  type: Socket
                name: dind-socket
              '''.stripIndent()
      defaultContainer 'docker'
        }
  }
    stages {
      //   stage('Checkout') {
      // steps
      //       {
      //  // cleanWs()

      //   git branch: 'master', url: 'https://github.com/avinashkris9/spring-boot-rest-api-template.git'
      //       //   sh 'mvn -DskipTests package'
      //       }
      //   }
        stage('Build') {


            // environment {
            //     JAR_NAME = """${sh(
            //         returnStdout: true,
            //         script: './mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout'
            //     )}"""
            //     JAR_VERSION = """${sh(
            //             returnStdout: true,
            //             script: './mvnw help:evaluate -Dexpression=project.version -q -DforceStdout'
            //         )}"""

      // // Using returnStatus
      // }
      stages {
        stage('maven build') {
          steps {
            container('maven')
          {
              sh '''
              mvn clean install

            '''
          }
          }
          post {
            always {
              junit testResults: './target/**/*.xml' , allowEmptyResults: true
            }
          }
        }
      }
        }
      stage('dockerize'){
      environment {
        APP_NAME = 'spring-boot-rest-api-template'
        DOCKER_REPO     = credentials('DOCKER_REPO')
        DOCKER_TAG =  ${GIT_COMMIT}
      }
        stages{
                  stage('Docker build') {
          steps {
            sh """
                docker build --pull  --build-arg artificatid=${APP_NAME}  -t ${APP_NAME}:latest  -t "${DOCKER_REPO}/${APP_NAME}:${DOCKER_TAG}" .
                """
          }
        }
        stage('Push image') {
          steps {
            withDockerRegistry([url: '', credentialsId: 'dockerhub']) {
              sh  "docker push ${DOCKER_REPO}/${APP_NAME}:${DOCKER_TAG}"
            }
          }
        }
        }
      }

      stage('dev-deploy'){
        when {
          beforeAgent true
          anyOf {
            branch 'develop'
          }
        }
        steps {
          
          echo 'Deploy stuffs to test'
        }
      }
      stage('prod-deploy'){
        when {
          beforeAgent true
          anyOf {
            // Only works if you got multi branch pipeline
            branch 'master' 
          }
        }
        steps {
          
          echo 'Deploy stuffs to prod'
        }
      }
    }
}

