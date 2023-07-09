pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                // Build your project
                sh 'mvn clean install'
            }
        }

        stage('Generate JAR') {
            steps {
                // Archive the jar file as an artifact
                archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
            }
        }

        stage('Run JAR') {
            steps {
                sh 'java -jar /target/sample-1.0-SNAPSHOT-jar-with-dependencies.jar'
            }
        }
    }
}