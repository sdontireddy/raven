### Performance Scipts for UCBOS

Jmeter based scripts to generate bulk of the test files to perform load testing on the UCBOS system.

Currently configured to generate bulk  Distribution Orders with random number of line items and random number of order quantity.

Idea is to use the same script for generating different types XML's and perform load tests.


#### How to RUN

```mvn install -D NUMBER_OF_ORDERS=1000 -DNUMBER_OF_THREADS=3 -DNUMBER_OF_LINEITEMS=2```

##### What are these arguments?

1. NUMBER_OF_ORDERS = number of distribution orders to be created
1. NUMBER_OF_LINEITEMS = Number of line items for each pf the orders
1. NUMBER_OF_THREADS = Controls the number of threads to be used for generating. Useful when we anted to genearate bulk of XML files for faster execution
2. SAMPLE_FILE = This is the sample DO file 
   1. Note : Please make sure to include the sample file under data folder

##### Where should i keep sample files?

Sample files used for generating bulk of files should be placed under **data** folder.
You can place a new sample XML file in the **data** folder and pass the file name with **SAMPLE_FILE = newfilename.xml** as an argument to the scripts.


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
      generatedvalue: randomstring
      prefix: AIML1125202SET
      suffix: 0615
    ```
##### How to Configure Mapping.yaml

###### Required fields :
name : Name of the field from XML that needs to be updated
path : Xpath of the node to be updated
value : Value configuration
generatedvalue : Various preconfigured ENUMS which generates different values

####### Available generatedvalue ENUMS

1. randomstring 
   
   Random String will be generated.
   
    Optional "prefix" and "suffix" can be provided.
```
    value:
      generatedvalue: randomstring
      prefix: AIML1125202SET
      suffix: 0615
  ``` 
2.number

   Generates a radom number betwen 0 to 999 by default.
    However optional **startrange** and **endRange** can also be provided
```   
    value:
      generatedvalue: number
      startrange: 2
      endrange: 5
```
3.randomfromlist

Need to provide subnode "list" with comma separated list of values
```    
   value:
    generatedvalue: randomfromlist
    list: 1234,42323,94545,57834,4534
```
4. date
    Generate date in the given format
```
    value:
      generatedvalue: date
      adddays : 10 #optional  - Generates a future date with additional 10 days to the current
      format: yyyy-MM-dd'T'HH:mm:ss.SSSZ
```


**[common-utils](https://github.com/sdontireddy/common-utils)** : A dependent project and more of a library with common utils parse XML's , JSON etc..


