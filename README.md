# Filmster
#### Demo project

### Overview
Filmster is a demo (sample) project, which demonstrates REST handling
within Lazy-loading ListView

REST API is provided by mocked server which uses demo data (publicly available at imdb.com)


### Technical details
Movies are downloaded from REST API via asynchronous task with specific response listener.
Response listener takes care of creating Movie class instances (objects) from the list definition
(JSON). Project provides also a simple custom JSON parser.

Lazy loading list is implemented for universal adaptation to any class, so this project uses
MovieList implementation, which together with Movie Adapter creates a simple, but functional
UI filled with details of loaded movies. Lazy loading is invoked automatically when scrolling the
list to the point when last adapter item is visible.

Lazy loading will stop once there is no more items to load in the REST API, or when it reaches
defined maximal data length.

### Support
APIs supported are 10(GingerBread) to 21(Lollipop), anyway the app was not tested on all APIs.
It was tested both on simulator (API10, API16, API17, API19) and real devices (API10, API17, API19).

### Contact
In case on any questions, do not hesitate to send me an email. I will try to respond in reasonable time.

2014 Dusan Bartos
