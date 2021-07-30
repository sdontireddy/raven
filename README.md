### Performance Scipts for UCBOS

Jmeter based scripts to generate bulk of the test files to perform load testing on the UCBOS system.

Currently configured to generate bulk  Distribution Orders with random number of line items and random number of order quantity.

Idea is to use the same script for generating different types XML's and perform load tests.


#### How to RUN

```mvn install -D NUMBER_OF_ORDERS=1000 -DNUMBER_OF_THREADS=3 -DNUMBER_OF_LINEITEMS=2```
```mvn clean install -DMAPPING_FILE=mapping.yaml```

##### What are these arguments?

1. NUMBER_OF_LINEITEMS = Number of line items for each pf the orders
1. NUMBER_OF_THREADS = Controls the number of threads to be used for generating. Useful when we anted to genearate bulk of XML files for faster execution
1. NUMBER_OF_ORDERS = Number of orders to be genearated
   
2. MAPPING_FILE = This is the sample DO file
   1. Note : Please make sure to include the sample file under data folder

##### Where should i keep sample files?

Sample files used for generating bulk of files should be placed under **data** folder.

##### Where are files generated?
ALl the generated files will be under **results** folder , each file appended with a sequence number
```
/performance-tests/target/jmeter/results
```

##### Flow

**mvn clean install** command makes use of the **Jmeter-maven-plugin** defined in the pom.xml

to trigger the Jmeter and corresponding configured scripts automtically.

So flow will be

```
Maven --> pom.xml ---> LoadOrders.jmx ---> LoadOrders.java ---XMLDataGenerator.java

```

#### Project Structures


**performance-tests** : Maven - JMeter plugin based project , which is a glue between Jmeter scrtips and the actual code which generates the XML files.
 
 ##### Important files 

1. **pom.xml** : Maven depenecy and default configurations
2. **LoadOrders.jmx** : Jmeter configuration
3. **LoadOrders.java** : Glue between Jmeter and the actual scripts that generates XML files
4.  **XMLDataGenerator.java** : Script that reads the configurations and generates bulk of XML files
5.  **mapping.yaml** : Lists all our configurations , including the fields to be updated , corresponding XPATH and what should be the value
    
    Ex  : Below configuration updates  **DistributionOrderId** field at the given xpath "/DistributionOrderId" with a random string appended with given suffix and prefix
    ```   
    name: DistributionOrderId
    path: //DistributionOrderId
    value:
      valueType: randomString
      prefix: AIML1125202SET
      suffix: 0615
    ```
	```
	name: CertificationName
    path: /tXML/Message/Student/Certifications[$$]/Certification[$$$]/CertificationName
    value:
      valueType: randomFromList
      list: Java,Python,Ruby,Apache,Spring,UI,js
	```
	where,
	$$  -->  childNode
	$$$ -->  subChildNode
	
	
```````
bulkloadconfig:
  samplefile : ASN.xml
  numberoffiles: 5
  childnode: ASNDetail,LPN
  numberofmainnodes: 2        #Optional, only if main nodes needs to be updated.
  numberofchildnodes: 2       #Optional, only if childNode needs to be generated and updated
  numberofsubchildnodes: 2    #Optional, only if subChildNode needs to be updated.
  resultsfolderwithpath: "C:/performance-tests-master/target/jmeter/results/"


samplefile                -- This is the file name which needs to be created and updated
numberoffiles             -- Number of files needs to be generated
childnode                 -- The Child nodes which needs to be created
numberofmainnodes         -- Number of main nodes present in the samplefile
numberofchildnodes        -- Number of childnodes present within the main nodes
numberofsubchildnodes     -- Number of subchildnodes present within the childnodes
resultsfolderwithpath     -- The path where the generated files needs to be generated
```````	  
	  
##### How to Configure Mapping.yaml

###### Required fields :
name : Name of the field from XML that needs to be updated
path : Xpath of the node to be updated
value : Value configuration
valueType : Various preconfigured ENUMS which generates different values

####### Available valueType ENUMS

1. randomString 
   
   Random String will be generated.
   
    Optional "prefix" and "suffix" can be provided.
```
    value:
      valueType: randomString
      prefix: AIML1125202SET
      suffix: 0615
``` 
2.randomNumber

   Generates a random number between 1000 to 9999 by default.
    
```   
    value:
      valueType: randomNumber
```
3.randomFromList

    Picks the random value from the list
    Need to provide subnode "list" with comma separated list of values
```    
   value:
    valueType: randomFromList
    list: 1234,42323,94545,57834,4534
```
4. dateTime

    Generate dateTime in the given format
	days, minutes are optional
	days can be negative or positive based on the date which needed.
	minutes can be negative or positive based on the time which needed.
```
    value:
      valueType: dateTime
      days : 10
	  minutes: 10
      format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
```
5. randomIntegerNumber
    
	Generate Random integer number with the given startRange and endRange
```
    value:
      valueType: randomIntegerNumber
      startRange: 1
      endRange: 10
```
6. randomDecimalNumber
    
	Generate Random decimal number with the given startRange and endRange
```
    value:
      valueType: randomDecimalNumber
      startRange: 1
      endRange: 10
```
7. boolean
    
	Generate boolean value from the given booleanList
```
    value:
      valueType: boolean
      booleanList: yes,no
```
8. static
    
	Replaces the given static value
```
    value:
      valuetype: static
      staticValue: Black
```
9. integer
    
	Generate integer/decimal value which keep on incrementing/decrementing between the startRange and endRange with the given stepValue
```
    value:
      valuetype: integer       #integer or decimal value which needs to be generated.
      startrange: 1
      endrange: 5
      stepType: increment      #Optional. By default it will be decrement. It can be increment also.
      stepValue: 1             #The value by which the given number needs to be incremented/decremented.
```
10. stringCounter
    
	Generate concatinated string of staticString and stepValue. where stepValue keeps on incrementing.
```	  
	value:
      valuetype: stringCounter
      staticString: UB
      stepValue: 1
```
	  
**Note** : Dependency on the below common-utils was removed

**[common-utils](https://github.com/sdontireddy/common-utils)** : A dependent project and more of a library with common utils parse XML's , JSON etc..


