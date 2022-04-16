docker pull noman637/oracle-19.3.0-ee:latest
docker run --name "bentbase-oracle-db" -p 1521:1521 -p 5500:5500 -e ORACLE_SID=BentBase -e ORACLE_PDB=BentBasePDB -e ORACLE_PWD=bentbase -v /opt/oracle/oradata noman637/oracle-19.3.0-ee
