from datetime import datetime
from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.empty import EmptyOperator

with DAG(
        dag_id='ETL_Pipeline',
        schedule='* * * * *',
        start_date=datetime(2023, 7, 2),
        catchup=False,
) as dag:
    before_running = EmptyOperator(
        task_id="preparing_enviroment",
    )

    while_running = BashOperator(
        task_id="run_jar",
        bash_command="java -jar $AIRFLOW_HOME/jars/template-jar-with-dependencies.jar",
    )

    after_running = EmptyOperator(
        task_id="Bye",
    )

    before_running >> while_running >> after_running
