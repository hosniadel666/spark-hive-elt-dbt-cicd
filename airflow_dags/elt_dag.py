from datetime import datetime
from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.empty import EmptyOperator

with DAG(
        dag_id='ETL_Pipeline',
        schedule='0 * * * *',
        start_date=datetime(2023, 7, 2),
        catchup=False,
) as dag:
    before_running = EmptyOperator(
        task_id="preparing_enviroment",
    )

    while_running_etl = BashOperator(
        task_id="run_jar",
        bash_command="java -jar $AIRFLOW_HOME/jars/elt.jar",
    )

    while_running_dbt = BashOperator(
        task_id="run_dbt",
        bash_command=" dbt run --profiles-dir $AIRFLOW_HOME/dbt --project-dir $AIRFLOW_HOME/dbt/dbt_with_hive/",
    )
    after_running = EmptyOperator(
        task_id="Bye",
    )

    before_running >> while_running_etl >> while_running_dbt >> after_running
