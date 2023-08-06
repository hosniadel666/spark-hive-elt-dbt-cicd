{{ config(materialized='table') }}

SELECT *
FROM events e
WHERE YEAR(eventdate) == 2019