### Performance Scipts for UCBOS

Jmeter based scripts to generate bulk of the test file to perform load testing on the UCBOS system.

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

Sample files used for generating bulk of files should be placed under **data** folder
You can place a new sample XML file and pass the file name with **SAMPLE_FILE = newfilename.xml**


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
Maven --> pom.xml ---> LoadOrders.jmx ---> LoadOrders.java ---OrderXMLGenerator.java

```

#### Project Structures

This project uses another dependency project 
**performance-tests** : Maven - JMeter plugin based project , which is a glue between Jmeter scrtips and the actual code which generates the XML files.
 
 ##### Important files 

**pom.xml** : Maven depenecy and default configurations
**LoadOrders.jmx** : Jmeter configuration
**LoadOrders.java** : Glue between Jmeter and the actual scripts that generates XML files
 **OrderXMLGenerator.java** : Script that reads the configurations and generates bulk of XML files
**config.properties** : Lists all our preconfigured default values/ list information 
Ex: List of items to be used while generating XML files

**fieldmapping.properties** : List the fields that we wanted to be generate in each of the XML's and their corresponding XPATH



**common-utils** : Another dependent project and more of a library with common utils parse XML's , JSON etc..


