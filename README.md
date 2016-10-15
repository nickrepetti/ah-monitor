#WoW Auction House Monitor

This application polls the World of Warcraft auction house and collects the data for the current auctions.  Future work will include applications to run metrics on the data, as well as providing an interface for viewing, filtering, and sorting the data.

See https://dev.battle.net/ for more information on the World of Warcraft API.

*Usage:*

`gradle build`
`java -jar build/libs/ah-monitor-1.0.jar config.properties`

*Example config.properties (Stored in the project root directory):*

`endpoint=https://us.api.battle.net/wow/auction/data/sargeras?locale=en_US&apikey=APIKEYHERE
timeUnit=M
period=60
dbUrl=jdbc:mysql://MYSQLDATABASELOCATION:3306/DATABASENAMEHERE
dbUserName=USERNAMEHERE
dbPassword=PASSWORDHERE`
