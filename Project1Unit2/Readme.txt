hailunz_Project1Unit2

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

	
    - test.txt 
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


