pipeline {

    agent any

    stages{
        stage('Build BackEnd'){
           steps {
                dir('tasks-backend') {
                    sh label: 'Maven - Build', script: 'mvn clean package -DskipeTests=true'
                } 
            }
        }
        stage('Unit Tests'){
           steps {
                dir('tasks-backend') {
                    sh label: 'Maven - Test', script: 'mvn test'
                }
            }
        }
        stage('Sonar Analise'){
            
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                dir('tasks-backend') {
                    withSonarQubeEnv('SONAR_LOCAL'){
                        sh label: 'Sonar - Scanner', script: "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://192.168.0.8:9000/ -Dsonar.login=94e712ccbc4ae692be9fb579c9eb332c0b386c4f -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java"
                    }
                }
            }
        }
        stage ("Quality Gate") {
            steps {
                dir('tasks-backend') {
                    sleep(20)
                    timeout(time: 1, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                    //script {
                    //    qualitygate = waitForQualityGate()
                    //    if (qualitygate.status != "OK") {
                    //        error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
                    //    }
                    //}
                }
            }
        }
        stage('Deploy Backend'){
           steps {
                dir('tasks-backend') {
                    deploy adapters: [tomcat8(credentialsId: 'admin', path: '', url: 'http://192.168.0.8:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
                }
            }
        }
        stage('Api Test'){
           steps {
                dir('tasks-api-test') {
                    //git branch: 'main', credentialsId: '621667b3-cd45-4d59-8e48-004141311fb3', url: 'https://github.com/cyborgmg/tasks-api-test.git'
                    sh label: 'Maven - Test Api', script: 'mvn clean package'
                }
            } 
        }
        stage('Deploy Fontend'){
           steps {
                dir('tasks-frontend') {
                    //git branch: 'master', credentialsId: '621667b3-cd45-4d59-8e48-004141311fb3', url: 'https://github.com/cyborgmg/tasks-frontend.git'
                    sh label: 'Maven - Frontend Clean Package', script: 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'admin', path: '', url: 'http://192.168.0.8:8001')], contextPath: 'tasks', war: 'target/tasks.war'
                }
            } 
        }
        stage('Publish Container'){
           steps {
                sh label: 'Maven - Test', script: "chmod 777 ./container.sh && ./container.sh"
            }
        }
    }
    
}

