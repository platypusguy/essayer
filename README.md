@platypusguy

I am trying to use the ```purego``` package with ```golang/main.go``` on Linux, Windows, and MacOS.

It turns out that purego.Dlopen of $JAVA_HOME/lib/libzip.so causes an implicit attempt to open $JAVA_HOME/lib/server/libjvm.so.
On Linux, 
* Before running, if I export LD_LIBRARY_PATH=$JAVA_HOME/lib/server/libjvm.so, all is well. It works.
* But if I do not do that, it fails with error text "libjvm.so: cannot open shared object file: No such file or directory".

When you look at the code, you will see that I successfully amend the LD_LIBRARY_PATH environment variable in the current shell with the directory holding the libjvm.so file.

Same (equivalent) on Windows and MacOS (not confident in that yet).

Clearly, I am missing something. Feel free to try it yourself and tell me how I screwed up!  (:
