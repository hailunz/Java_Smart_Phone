hailunz_Project1Unit3

folder directory:

- Readme.txt
- Class Diagram.pdf
- test_output.txt

- CarConfiguration
    - src : source code
		- driver (package)
			- Driver.java (test code)
		- model(package)
			- Automobile.java
			- OptionSet.java
		- util (package)
			- FileIO.java
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
			- EditOptionThread.java (interface)
		- scale (package)
			- EditOptions.java 

	
    - test.txt 
    - test1.txt 
    - test2.txt 
    - test3.txt 
    - test4.txt - test Exception
    - log.txt  - Exception log output file
    - auto.ser

- before synchronize
	- CarConfiguration : to show that without synchronization, data is corrupted.

- test.txt format

[parameter name]=[parameter value]
Autoname=Focus Wagon ZTW [first line: autoname]
Baseprice=18 [second line: baseprice]

[OptionSet name]=[Option name@price,…]
Color=red@0,blue@10

Option[OptionSetName]=option name
Option[Color]=Fort Knox Gold Clearcoat Metallic


I have synchronized every method in the Automobile class.

