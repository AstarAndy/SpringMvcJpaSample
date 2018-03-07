#!/bin/bash

USE_PROFILE=$1

#
# if the command line parameter is ? help or -help then
# display some help
#
if [ "$USE_PROFILE" = "?" ] || [ "$USE_PROFILE" = "help" ] || [ "$USE_PROFILE" = "-help" ]; then
    echo "**********************"
    echo "*"
    echo "* If you just execute ./runApp.sh without parameters then "
    echo "* the default profile will be local."
    echo "* Any other value you enter will be used as the profile."
    echo "* If you use test then ./gradlew test will be execued to run"
    echo "* all the application tests"
    echo "*"
    echo "**********************"
    exit 0
fi

# Test if USE_PROFILE is empty or null and, if it is,
# default it to local
if [ -z $USE_PROFILE ]; then
    USE_PROFILE=local
fi

echo "Setting SPRING_PROFILES_ACTIVE=$USE_PROFILE"

export SPRING_PROFILES_ACTIVE=$USE_PROFILE

# Now, either run the app or execute all tests

if [ "$USE_PROFILE" = "test" ]; then
    ./gradlew test
else
    ./gradlew bootRun
fi
