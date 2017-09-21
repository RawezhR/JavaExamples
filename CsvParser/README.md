# CsvParser

This program parses a CSV (comma-separated values) file and stores the information in two ArrayList objects. The first ArrayList stores the column names (if present). The second ArrayList stores the records. This program takes two parameters: [full path filename] [true|false header present]. A sample input file, called dat/addresses.csv, is included.

Here is a sample run:

```
c:\Temp>java CsvParser addresses.csv true
Column names:
Name,Street,City,State,Zip,Phone

Data:
John Smith,21 Pine Street,Detroit,Michigan,48126,111-222-3333
Mary Jones,11 Red Avenue,Orlando,Florida,32801,222-444-6666
Paul James,18 Main Street,Los Angeles,California,90004,555-222-1111
Sara Roberts,32 Bourbon Street,Jackson,Mississipi,39201,999-222-7777

c:\Temp>
```
