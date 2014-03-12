sample-ninja
============

It's shows @UnitOfWork often fails.

One Category has many Data(s).

Each Data has 3 DataNote(s) , but it is not directly written in @OneToMany in Data.

DataDto is a DTO object which links Data and DataNotes altogether.

The diagram is as following:

<img src="http://i.imgur.com/qqct3UJ.png">

Run mvn ninja:run , and ninja will first insert some fake Category/Data/DataNotes to DB.
And browse http://localhost:8080/category .
First it will show correctly, but if you refresh browser (just a little quick) , 
There will be 400 - Bad Request thrown.

    java.lang.IllegalStateException: Work already begun on this thread. Looks like you have called UnitOfWork.begin() twice   without a balancing call to end() in between.
    	at com.google.inject.internal.util.$Preconditions.checkState(Preconditions.java:142) ~[guice-3.0.jar:na]
    	at com.google.inject.persist.jpa.JpaPersistService.begin(JpaPersistService.java:66) ~[guice-persist-3.0.jar:na]
    	at ninja.jpa.UnitOfWorkInterceptor.invoke(UnitOfWorkInterceptor.java:53) ~[ninja-core-3.1.1.jar:na]
	

If change App.category() from @UnitOfWork to @Transactional , it will work well.
