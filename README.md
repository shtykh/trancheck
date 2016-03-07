# trancheck
## task
There is a table in database of this structure:
```
 id  | amount  |      data       
 int | decimal |      json       
-----+---------+-----------------
 123 |  100.05 | {"a":1,"b":2}
 124 |  150.75 | {"a":10,"b":20}
 125 | 1010.00 | {"a":20,"b":30}
 126 |   15.50 | {"a":30,"b":40}
```
Task is to check amounts in transactions given in file like that:
```
PID;PAMOUNT;PDATA;
123;94.7;20160101120000;
124;150.75;20160101120001;
125;1020.2;20160101120002;
126;15.5;20160101120003;
127;120.74;20160101120004;
TOTAL;5;
```
and store the result data in file near the input
## rest
There's another way to access the service: via rest api: once you installed all right post json like this
```
[
{"id":123, "amount":10.50},
{"id":124, "amount":150.75},
{"id":127, "amount":10.50},
{"id":126, "amount":10.50},
{"id":126, "amount":102.50},
{"id":126, "amount":101.50}
]
```
onto (http://localhost:8080/check) and get a json answer
(img )
## user manual
is coming soon
