## Liquibase

- 通过数据库生成liquibase changelog
```bash
liquibase --changelog-file=./dbchangelog.xml --driverPropertiesFile ./liquibase.properties generate-changelog
```
