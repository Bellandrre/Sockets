# Sockets
A Key/Value store, you can use your client to pre-populate the server with enough data (i.e, Key-Value store with data and a set of keys).The composition of the data is up to you in terms of what you want to store there. Once the key-value store is populated, client can do any operation (such as PUT(Key), GET(Key), DELETE(Key)).

The code determines the number of active cores in the CPU and distributes loads evenly amongst the cores for performance in the server.
