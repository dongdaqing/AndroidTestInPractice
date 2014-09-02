EmmaDemo
=====================

## Overview
Android Code Coverage Test Demo Based on Emma

## Execution Steps

1.copy emma.jar,emma_ant.jar,emma_device.jar to android-sdk-macosx/tools/lib/ directory:

* emma.jar,emma_ant.jar:http://sourceforge.net/projects/emma/files/emma-release/2.0.5312/
* emma_device.jar:https://code.google.com/p/apk-view-tracer/source/browse/tools/lib/emma_device.jar?r=f6309001f2b417edac8e9f33ed3b913d97b0a356

2.enter EmmaDemo, and execute:

	android update project -p .

3.enter EmmaDemoTest, and execute:

	android update test-project -m ../EmmaDemo -p .
	
4.under EmmaDemoTest, and execute:

	ant clean emma debug install test
	
5.check test report in EmmaDemoTest/bin/coverage.html
