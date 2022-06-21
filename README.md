## Sample of usage VisualFSM for Android application - Kotlin Coroutines, Jetpack Compose

[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=Channel&color=0088CC)](https://t.me/visualfsm)
[![Telegram](https://img.shields.io/static/v1?label=Telegram&message=Chat&color=0088CC)](https://t.me/visualfsm_support)

ENG | [RUS](docs/README-RU.md)

[VisualFSM](https://github.com/Kontur-Mobile/VisualFSM) is a Kotlin library that implements an **MVI architecture**
(`Model-View-Intent`)[[1]](#what-is-mvi) and a set of tools for visualization and analysis of
**FSM**'s (`Finite-state machine`)[[2]](#what-is-fsm) diagram of states.

The graph is being built from source code of **FSM**'s implementation. There is no need of custom
written configurations for **FSM**, you can just create new State and Action classes, they would be
automatically added to the graph of States and Transitions.

Source code analysis and the graph built are being performed with reflection and declared as a
separate module that would allow it to be connected to testing environment.

### Authorization and registration process

<img src="docs/graph.png" alt="graph" width="1000"/>

Feature: [AuthFeature.kt](./app/src/main/java/ru/kontur/mobile/visualfsm/sample_android/feature/auth/fsm/AuthFeature.kt).

States: [AuthFSMState.kt](./app/src/main/java/ru/kontur/mobile/visualfsm/sample_android/feature/auth/fsm/AuthFSMState.kt).

Actions: [actions](./app/src/main/java/ru/kontur/mobile/visualfsm/sample_android/feature/auth/fsm/actions).

AsyncWorker: [AuthFSMAsyncWorker.kt](./app/src/main/java/ru/kontur/mobile/visualfsm/sample_android/feature/auth/fsm/AuthFSMAsyncWorker.kt).

States to Ui data models mapper: [ScreenDataMapper.kt](./app/src/main/java/ru/kontur/mobile/visualfsm/sample_android/ui/auth/ScreenDataMapper.kt).

Generate digraph and tests
sample: [AuthFSMTests.kt](./app/src/test/java/ru/kontur/mobile/visualfsm/sample_android/AuthFSMTests.kt).

For CI visualization use [graphviz](https://graphviz.org/doc/info/command.html), for the local visualization (on your
PC) use [webgraphviz](http://www.webgraphviz.com/).

### Screenshots

<style>
table th:first-of-type {
    width: 33%;
}
table th:nth-of-type(2) {
    width: 33%;
}
table th:nth-of-type(3) {
    width: 33%;
}
</style>

|            Login             |     Registration      |  ConfirmationRequested  |
|:----------------------------:|:---------------------:|:-----------------------:|
|    ![](./docs/login.png)     |  ![](./docs/reg.png)  | ![](./docs/confirm.png) |
|  AsyncWorkState.Registering  |  Login with snackbar  |     UserAuthorized      |
| ![](./docs/reg_progress.png) | ![](./docs/snack.png) | ![](./docs/welcome.png) |

### What is MVI

`MVI` stands for **Model-View-Intent**. It is an architectural pattern that utilizes _unidirectional
data flow_. The data circulates between `Model` and `View` only in one direction - from `Model`
to `View` and from `View` to `Model`.

[More on hannesdorfmann](http://hannesdorfmann.com/android/model-view-intent/)

### What is FSM

A `finite-state machine` (FSM) is an abstract machine that can be in exactly one of a finite number
of states at any given time. The `FSM` can change from one state to another in response to some
inputs.

[More on wikipedia](https://en.wikipedia.org/wiki/Finite-state_machine)