-- Найстарший працівник
SELECT NAME,
       BIRTHDAY,
       EXTRACT(YEAR FROM AGE(CURRENT_DATE, BIRTHDAY)) AS AGE
FROM worker
ORDER BY BIRTHDAY ASC
LIMIT 1;

-- Наймолодший працівник
SELECT NAME,
       BIRTHDAY,
       EXTRACT(YEAR FROM AGE(CURRENT_DATE, BIRTHDAY)) AS AGE
FROM worker
ORDER BY BIRTHDAY DESC
LIMIT 1;


-- Об'єднано для отримання найстаршого та наймолодшого працівника
WITH RankedWorkers AS (SELECT NAME,
                              BIRTHDAY,
                              RANK() OVER (ORDER BY BIRTHDAY ASC)  AS YoungestRank,
                              RANK() OVER (ORDER BY BIRTHDAY DESC) AS OldestRank
                       FROM worker)
SELECT CASE WHEN YoungestRank = 1 THEN 'OLDEST' ELSE 'YOUNGEST' END AS TYPE,
       NAME,
       BIRTHDAY
FROM RankedWorkers
WHERE YoungestRank = 1
   OR OldestRank = 1;