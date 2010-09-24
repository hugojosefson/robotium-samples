#!/bin/bash

set -e

ORIGINAL_APK="$(readlink --canonicalize $1)"
CLEAN_SIGNED_APK="$(mktemp -t robotium.clean.apk.XXXXXXXXXXXXXX)"
UNPACK_DIRECTORY="$(mktemp --directory -t robotium.unpack.XXXXXXXXXXXXXX)"
ZIPALIGNED_APK="$(readlink --canonicalize $2)"
PWD="$(pwd)"

if [ -e "$ORIGINAL_APK" ]; then
    if [ "$2" == "" ]; then
        echo "Second argument must be the output filename for the signed apk."
        exit 2
    fi
    
    rm "$CLEAN_SIGNED_APK" 2>/dev/null || true
    rm "$ZIPALIGNED_APK" 2>/dev/null || true
    
    cd "$UNPACK_DIRECTORY"
    jar xf "$ORIGINAL_APK"
    rm META-INF/CERT.*  2>/dev/null || true
    jar cf "$CLEAN_SIGNED_APK" .
    jarsigner -keystore ~/.android/debug.keystore -storepass android -keypass android "$CLEAN_SIGNED_APK" androiddebugkey
    zipalign 4 "$CLEAN_SIGNED_APK" "$ZIPALIGNED_APK"

else
    echo "First argument must be an apk file."
    exit 1
fi
