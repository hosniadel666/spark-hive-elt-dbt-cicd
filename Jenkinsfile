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

        stage('Deploy to Airflow') {
            steps {
                sh 'mv target/template-jar-with-dependencies.jar $AIRFLOW_HOME/jars'
                sh 'mv dbt $AIRFLOW_HOME'
                sh 'mv dags/* $AIRFLOW_HOME/dags'

            }
        }


    }
}