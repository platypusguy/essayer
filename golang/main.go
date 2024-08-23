package main

import (
	"fmt"
	"github.com/ebitengine/purego"
	"log"
	"os"
	"runtime"
)

var DirLibs string
var DirJvmLib string
var LibExt string
var LibListEnvKey string
var LibListValue string
var PathStringSep = string(os.PathSeparator)
var PathListSep = string(os.PathListSeparator)

func the_end(arg string) {
	fmt.Printf("THE_END %s\n", arg)
	os.Exit(0)
}

func setup() {

	// Set up library file extension and library path string as a function of O/S.
	operSys := runtime.GOOS
	if operSys == "windows" {
		LibExt = "dll"
		LibListEnvKey = "PATH"
	} else {
		LibExt = "so"
		LibListEnvKey = "LD_LIBRARY_PATH"
	}

	// Get Java home.
	javaHome := os.Getenv("JAVA_HOME")
	if javaHome == "" {
		the_end("*** Environment variable JAVA_HOME missing but is required. Exiting.")
	}

	// Get the library path (list).
	LibListValue = os.Getenv(LibListEnvKey)
	if javaHome == "" {
		errMsg := fmt.Sprintf("*** Environment variable %s missing but is required. Exiting.\n", LibListEnvKey)
		the_end(errMsg)
	}

	// Calculate the path of the Java lib directory.
	DirLibs = javaHome + PathStringSep + "lib"

	// Amend the library path to include the server JVM library.
	if len(LibListValue) > 0 {
		LibListValue += PathListSep + DirLibs + PathStringSep + "server"
	} else {
		LibListValue = DirLibs + PathStringSep + "server"
	}
	err := os.Setenv(LibListEnvKey, LibListValue)
	if err != nil {
		errMsg := fmt.Sprintf("*** os.Setenv(%s, %s) failed, reason: [%s]", LibListEnvKey, LibListValue, err.Error())
		the_end(errMsg)
	}

}

func zippy() {

	// Construct the path of the zip library and open it.
	zipLibPath := DirLibs + PathStringSep + "libzip." + LibExt
	zipLibHandle, err := purego.Dlopen(zipLibPath, purego.RTLD_NOW|purego.RTLD_GLOBAL)
	if err != nil {
		errMsg := fmt.Sprintf("***** ERROR opening [%s], reason: [%s]", zipLibPath, err.Error())
		the_end(errMsg)
	}

	// Close it.
	err = purego.Dlclose(zipLibHandle)
	if err != nil {
		errMsg := fmt.Sprintf("***** ERROR closing handle for [%s], reason: [%s]", zipLibPath, err.Error())
		the_end(errMsg)
	}

}

func main() {
	setup()
	fmt.Printf("main: os.Getenv(%s) = %s\n", LibListEnvKey, os.Getenv(LibListEnvKey))
	zippy()
	log.Print("main: Bye-bye")
}
