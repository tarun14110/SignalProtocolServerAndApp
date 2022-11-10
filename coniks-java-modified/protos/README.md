#CONIKS Protos

Copyright (C) 2015-16 Princeton University.

http://coniks.org

##Introduction
These are protobuf source files that define the message format for all communication between the CONIKS test client and the basic server. They are based on [Google Protobufs](https://github.com/google/protobuf) (proto2) and require you to have the protocol buffer compiler protoc installed.

##Using the Protobufs
### Editing
Extensive documentation on developing protocol buffers can be found [here](https://developers.google.com/protocol-buffers/).

A few additional quick tips:
- A .proto file can define multiple protobuf messages. In our case, *c2s.proto* defines all messages specific to communication between CONIKS servers and clients, and *util.proto* defines messages included in several different c2s protos.
- All fields of a protobuf should be optional or repeated (but never required). The official Protocol buffer documentation explains:

> **Required Is Forever** You should be very careful about marking fields as required. If at some point you wish to stop writing or sending a required field, it will be problematic to change the field to an optional field – old readers will consider messages without this field to be incomplete and may reject or drop them unintentionally. You should consider writing application-specific custom validation routines for your buffers instead. Some engineers at Google have come to the conclusion that using required does more harm than good; they prefer to use only optional and repeated. However, this view is not universal. 

- All .proto files for CONIKS should use **org.coniks.coniks_common** as their java_package, should use the same package, and reside within the same directory. This will make compilation a lot easier and avoid certain bugs.

### Compiling into Java
-Prerequisites:
You'll need to install protoc 2.6.1. Follow these [instructions](https://github.com/google/protobuf#protocol-compiler-installation) to install protoc. If you've cloned this repo, the protoc Java Runtime is already included in the *coniks_common* directory, so you don't need to install it again.
-Compiling:
Run the following command from this directory for each .proto file you want to  compile into Java:
```
protoc --proto_path=. --java_out=../coniks_common/src <file>.proto
```
This command will automatically place the generated Java code in the appropriate package hierarchy in coniks_common/src.

## Disclaimer
Please keep in mind that this CONIKS Java implementation is under active development. The repository may contain experimental features that aren't fully tested. We recommend using a [tagged release](https://github.com/coniks-sys/coniks-java/releases).

##Documentation
[Read the official Google Protobuf documentation](https://developers.google.com/protocol-buffers/)
