#!/bin/bash

./gradlew uninstallAll
./gradlew connectedDebugAndroidTest

rm -rf ui_fsm_coverage
mkdir ui_fsm_coverage
mv ./app/build/generated/ksp/debug/resources/ru/kontur/mobile/visualfsm/sample_android/feature/auth/fsm/* ./ui_fsm_coverage

/home/g0rd1/Android/Sdk/platform-tools/adb pull /data/user/0/ru.kontur.mobile.visualfsm.sample_android/files/ui_fsm_coverage

python3 ui_fsm_coverage.py -input ui_fsm_coverage -output ui_fsm_coverage_results