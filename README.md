### Raven

A simiple JMeter + Java based utility to generate bulk(100k , 50 k etcc..) of XML files in any specified format.

#### Problem Statement 

Any middleware systems(Ex: Mule ESB , Apache Camel etc..) that process and trasnforms different formats of XML's (Ex:Distribuition Orders , ASN's , PO's , Invoice etc.. ) needs a systematic way of performating load testing.

Ex: There is often a requirement to **Stress test** a Mule appllication with different combinations of Distribution Orders etc..

Inorder to stress test the system we need to generate bulk(50K-100k) of these XML's with different combinations of child nodes and different field values and feed to the system.

There are multiple combination of the XML files that needs to be generated to test different flows.

Manually generating these files is no tangible , so a solution where a simple configuration file with list of fields to be modified and a sample template file is all needed to generated however the number of files required.


#### Solution

A simple and elegant way of configuring an  [YML](https://github.com/sdontireddy/performance-tests/blob/master/src/main/resources/do-mapping.yaml) file with required configuration along with sample XML(Ex: [DO.xml](https://github.com/sdontireddy/performance-tests/blob/master/src/main/resources/DO_sample_under_resource.xml) ) file and feed to this tool.

#### Technology Used / Pre-Requisites
1. Java 8 +
2. JMeter - No need to install , pre-baked in to the source code using Jmeter-maven plugin
3. Maven 3.5+

#### TODO 
1. Docker container
2. Automatically push the generated files to SFTP

#### How to RUN the tool

``` mvn verify -DMAPPING_FILE=do-mapping.yaml ```

Note : do-mapping.yml is already available under resource folder configured to generate bulk of Distribution Order related XML's

In order to levarage JMeter to create bulk of orders , need to pass additional parameters

``` mvn verify -NUMBER_OF_THREADS=20 -DMAPPING_FILE=do-mapping.yaml ```

##### What are these arguments?

1. NUMBER_OF_THREADS = Controls the number of threads to be used for generating. Useful when we anted to genearate bulk of XML files for faster execution 
2. MAPPING_FILE = [YML](https://github.com/sdontireddy/performance-tests/blob/master/src/main/resources/do-mapping.yaml) configuration defines sample XML file to be used as a base XML , number of XML's to generate , child nodes that needs to be updated/different for each of the generated XML's
   

##### Where should i keep sample resource files?

Configuration files can be placed under **resource** folder or any other folder and provide the relavtive or absolute path as below.

``` mvn verify -DMAPPING_FILE=../configs/mapping.yaml ```

##### Where are files generated?
ALl the generated files will be under [folder](https://github.com/sdontireddy/performance-tests/blob/master/src/main/resources/do-mapping.yaml#L7) defined in the configuration file , each file appended with a sequence number.

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

#### Define the configuration file 

```
bulkloadconfig:
	samplefile                -- This is the file name which needs to be created and updated
	numberoffiles             -- Number of files needs to be generated
	childnode                 -- The Child nodes which needs to be created
	numberofmainnodes         -- Number of main nodes present in the samplefile
	numberofchildnodes        -- Number of childnodes present within the main nodes
	numberofsubchildnodes     -- Number of subchildnodes present within the childnodes
	resultsfolderwithpath     -- The path where the generated files needs to be generated
```
Ex: To create 5000 xmls from a sample ASN.xml(placed under resource folder). And ASN has ASNDetail and LPN as child nodes 

Generated 5000 ASN's will have different number of of ASNDetail Nodes

```
  samplefile : ASN.xml # Sample XML base file 
  numberoffiles: 5000 # How many files to generate
  childnode: ASNDetail,LPN # CHild nodes within the sample file that needs to be different in each of the XML's
  numberofmainnodes: 2        #Optional, only if main nodes needs to be updated.
  numberofchildnodes: 2       #Optional, only if childNode needs to be generated and updated
  numberofsubchildnodes: 2    #Optional, only if subChildNode needs to be updated.
  resultsfolderwithpath: "../results/" # result generated files folder
  
 ```

#### How to update individual xml nodes

Individual xml nodes can be defined with current XPath and ValueType so that a ranndom value will be generated

  Ex  : Below configuration updates  **DistributionOrderId** field at the given xpath "/DistributionOrderId" with a random string appended with given suffix and prefix
    
    ```   
    name: DistributionOrderId
    path: //DistributionOrderId
    value:
      valueType: randomString
      prefix: AIML1125202SET
      suffix: 0615
      
    ```
 Ex 2 : Update CertificationName node at given path with one of the values from defined list

```
   name: CertificationName
    path: /tXML/Message/Student/Certifications[$$]/Certification[$$$]/CertificationName
    value:
      valueType: randomFromList
      list: Java,Python,Ruby,Apache,Spring,UI,js
	
	where,
	$$  -->  childNode
	$$$ -->  subChildNode
```

###### Required fields :
name : Name of the field from XML that needs to be updated
path : Xpath of the node to be updated
value : Value configuration
valueType : Various preconfigured ENUMS which generates different values

##### Available valueType ENUMS to update fields

1. **randomString**
   
   Random String will be generated.
   
    Optional "prefix" and "suffix" can be provided.
```
    value:
      valueType: randomString
      prefix: AIML1125202SET
      suffix: 0615
``` 
2.**randomNumber**

   Generates a random number between 1000 to 9999 by default.
    
```   
    value:
      valueType: randomNumber
```
3.**randomFromList**

    Picks the random value from the list
    Need to provide subnode "list" with comma separated list of values
```    
   value:
    valueType: randomFromList
    list: 1234,42323,94545,57834,4534
```
4. **dateTime**

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
5. **randomIntegerNumber**
    
	Generate Random integer number with the given startRange and endRange
```
    value:
      valueType: randomIntegerNumber
      startRange: 1
      endRange: 10
```
6. **randomDecimalNumber**
    
	Generate Random decimal number with the given startRange and endRange
```
    value:
      valueType: randomDecimalNumber
      startRange: 1
      endRange: 10
```
7. **boolean**
    
	Generate boolean value from the given booleanList
```
    value:
      valueType: boolean
      booleanList: yes,no
```
8. **static**
    
	Replaces the given static value
```
    value:
      valuetype: static
      staticValue: Black
```
9. **integer**
    
	Generate integer/decimal value which keep on incrementing/decrementing between the startRange and endRange with the given stepValue
```
    value:
      valuetype: integer       #integer or decimal value which needs to be generated.
      startrange: 1
      endrange: 5
      stepType: increment      #Optional. By default it will be decrement. It can be increment also.
      stepValue: 1             #The value by which the given number needs to be incremented/decremented.
```
10. **stringCounter**
    
	Generate concatinated string of staticString and stepValue. where stepValue keeps on incrementing.
```	  
	value:
      valuetype: stringCounter
      staticString: UB
      stepValue: 1
```


