ApiBonitasoft


This software is created with the purpose of facilitating the connection process with the Bonitasoft API.

Anyone can make use of this software making the respective mentions of copyright, no support is provided on the application.


The system settings are:


server.port=8083
#should not be modified
application.header.token=X-Bonita-API-Token
application.header.tenant=bonita.tenant
application.header.bos=BOS_Locale
application.header.jsessionid=JSESSIONID
#User credentials
bonita.username=walter.bates
bonita.password=bpm
#Bonita login url
bonita.url.login=http://localhost:8080/bonita/loginservice
#Name of process
bonita.processName=VentasPublicaciones
#process search url
bonita.url.searchProcess=http://localhost:8080/bonita/API/bpm/process?s=
#process initiation url
bonita.url.createProcess.part1=http://localhost:8080/bonita/API/bpm/process/
bonita.url.createProcess.part2=/instanti