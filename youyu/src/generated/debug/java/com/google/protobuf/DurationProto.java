// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/protobuf/duration.proto

package com.google.protobuf;

public final class DurationProto {
  private DurationProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_protobuf_Duration_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_protobuf_Duration_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036google/protobuf/duration.proto\022\017google" +
      ".protobuf\"*\n\010Duration\022\017\n\007seconds\030\001 \001(\003\022\r" +
      "\n\005nanos\030\002 \001(\005B\203\001\n\023com.google.protobufB\rD" +
      "urationProtoP\001Z1google.golang.org/protob" +
      "uf/types/known/durationpb\370\001\001\242\002\003GPB\252\002\036Goo" +
      "gle.Protobuf.WellKnownTypesb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_google_protobuf_Duration_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_google_protobuf_Duration_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_protobuf_Duration_descriptor,
        new java.lang.String[] { "Seconds", "Nanos", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
