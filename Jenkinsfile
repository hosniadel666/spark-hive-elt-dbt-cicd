pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                // Build your project
                sh 'mvn clean package'
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
                sh 'rm $AIRFLOW_HOME/jars/*'
                sh 'mv target/elt.jar $AIRFLOW_HOME/jars'
                sh 'rm -rf $AIRFLOW_HOME/dbt'
                sh 'mv dbt $AIRFLOW_HOME'
                sh 'mv airflow_dags/* $AIRFLOW_HOME/dags'
            }
        }
    }
    post {
        success {
            mail to: "hosni.adel666@gmail.com",
            subject: "Jenkins Build Successful: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
            body: """
            Hello,
                \tThe Jenkins build for ${env.JOB_NAME} [${env.BUILD_NUMBER}] was successful.
                \tYou can view the build details and console output at ${env.BUILD_URL}
            Thank you,
            Jenkins
            """
        }

        failure {
            mail to: "hosni.adel666@gmail.com",
            subject: "Jenkins Build Failed: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
            body: """
            Hello,
                \tThe Jenkins build for ${env.JOB_NAME} [${env.BUILD_NUMBER}] has failed.
                \tYou can view the build details and console output at ${env.BUILD_URL}
            Thank you,
            Jenkins
            """
        }
    }
}