EmmaDemo
=====================

## Overview
Android Code Coverage Test Based on Emma

## Execution Steps

1.copy emma.jar,emma_ant.jar,emma_device.jar to android-sdk-macosx/tools/lib/ directory:

2.enter EmmaDemo, and execute:

	android update project -p .

3.enter EmmaDemoTest, and execute:

	android update test-project -m ../EmmaDemo -p .
	
4.under EmmaDemoTest, and execute:

	ant clean emma debug install test
	
5.check test report in EmmaDemoTest/bin/coverage.html