# ConsumerApplication
Consumer app - redis subscriber and the message persister

The consumer application, tries to demonstrate the redis channel subscription and redis store persist storage.
And the implementation relies on the "localhost" redis service and uses the default redis port 6379.
The redis publish channel subscribed is "BROADCAST" and it writes all the data pushed  to the redis store.

Clone the repo to local machine with redis installed and service running.
Goto the ConsumerApplication/ path and execute 'mvn compile exec:java ' to start the consumer application.
Tested in windows and found to be working.

