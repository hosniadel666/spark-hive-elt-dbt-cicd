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

       stage('Deploy') {
            steps {
                // Copy the archived JAR file to the desired location
                // For example, copying it to a 'deploy' directory
                dir('/home/hosniadel/jars') {
                    copyArtifacts filter: 'template-jar-with-dependencies.jar', fingerprintArtifacts: true, projectName: env.JOB_NAME, selector: lastSuccessful()
                }
            }
        }

        stage('Run JAR') {
            steps {
                sh 'java -jar /home/hosniadel/template-jar-with-dependencies.jar'
            }
        }

    }
}