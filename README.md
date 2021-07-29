### Performance Scipts for UCBOS

Jmeter based scripts to generate bulk of the test files to perform load testing on the UCBOS system.

Currently configured to generate bulk  Distribution Orders with random number of line items and random number of order quantity.

Idea is to use the same script for generating different types XML's and perform load tests.


#### How to RUN

```mvn clean install -DMAPPING_FILE=do-mapping.yaml```

##### What are these arguments?

1. SAMPLE_FILE = This is the sample DO yaml file 
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
2.number

   Generates a random number between 0 to 999 by default.
    However optional **startRange** and **endRange** can also be provided
    
```   
    value:
      valueType: randomNumber
      startRange: 2
      endRange: 5
```
3.randomFromList

Need to provide subnode "list" with comma separated list of values
```    
   value:
    valueType: randomFromList
    list: 1234,42323,94545,57834,4534
```
4. dateTime
    Generate date in the given format
```
    value:
      valueType: date
      days : 10 #optional  - Generates a future date with additional 10 days to the current
      format: yyyy-MM-dd'T'HH:mm:ss.SSSZ
```
**Note** : Dependency on the below common-utils was removed

**[common-utils](https://github.com/sdontireddy/common-utils)** : A dependent project and more of a library with common utils parse XML's , JSON etc..


