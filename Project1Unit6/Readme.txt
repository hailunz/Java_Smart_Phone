hailunz_Project1Unit6

folder directory:

- Readme.txt
- Class Diagram.pdf
- 

server:
- CarConfiguration
    - src : source code
		- driver (package)
			- Driver.java (test code)
		- model(package)
			- Automobile.java
			- OptionSet.java
		- util (package)
			- FileIO.java
			- Properties.java
		- exception (package)
			- NoOptionSetException.java
			- NoOptionException.java
			- AutoException.java
			- Fix0to4.java
		- adapter (package)
			- BuildAuto.java
			- CreateAuto.java (interface)
			- UpdateAuto.java (interface)
			- FixAuto.java  (interface)
			- proxyAutomobile.java (abstract class)
		- scale (package)
			- EditOptions.java 
			- EditThread.java (interface)
		- server (package)
			- AutoServer
			- BuildCarModelOptions
			- DefaultSocketClientHandler
			- DefaultSocketClientServer
			- SocketClientConstants
			- SocketClientInterface
		- database(package)
			- JDBCAdapter
			- AddModel
			- CreateTable
			- DeleteModel
			- UpdateModel
	
    - test.txt 
    - test1.txt 
    - test2.txt 
    - dbSetting.txt 
    - tables.txt
    - log.txt  - Exception log output file
    - auto.ser


client:
- CarConfiguration
    - src : source code
		- driver (package)
			- Driver.java (test code)
		- model(package)
			- Automobile.java
			- OptionSet.java
		- util (package)
			- FileIO.java
			- Properties.java
		- exception (package)
			- NoOptionSetException.java
			- NoOptionException.java
			- AutoException.java
			- Fix0to4.java
		- adapter (package)
			- BuildAuto.java
			- CreateAuto.java (interface)
			- UpdateAuto.java (interface)
			- FixAuto.java  (interface)
			- proxyAutomobile.java (abstract class)
		- scale (package)
			- EditOptions.java 
			- EditThread.java (interface)
		- client (package)
			- CarModelOptionsIO
			- DefaultSocketClient
			- SelectCarOption
			- SocketClientConstants
			- SocketClientInterface
		- servlet (package)
			- getAvailableModels
			- getModel


    - test.txt 
    - test1.txt 
    - test2.txt 
    - test3.txt 
    - test4.txt - test Exception
    - log.txt  - Exception log output file
    - auto.ser



- test.txt format

[parameter name]=[parameter value]
Autoname=Focus Wagon ZTW [first line: autoname]
Baseprice=18 [second line: baseprice]

[OptionSet name]=[Option name@price,…]
Color=red@0,blue@10

Option[OptionSetName]=option name
Option[Color]=Fort Knox Gold Clearcoat Metallic


