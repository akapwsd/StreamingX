// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/protobuf/struct.proto

package com.google.protobuf;

/**
 * <pre>
 * `NullValue` is a singleton enumeration to represent the null value for the
 * `Value` type union.
 *  The JSON representation for `NullValue` is JSON `null`.
 * </pre>
 *
 * Protobuf enum {@code google.protobuf.NullValue}
 */
public enum NullValue
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * Null value.
   * </pre>
   *
   * <code>NULL_VALUE = 0;</code>
   */
  NULL_VALUE(0),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * Null value.
   * </pre>
   *
   * <code>NULL_VALUE = 0;</code>
   */
  public static final int NULL_VALUE_VALUE = 0;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static NullValue valueOf(int value) {
    return forNumber(value);
  }

  public static NullValue forNumber(int value) {
    switch (value) {
      case 0: return NULL_VALUE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<NullValue>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      NullValue> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<NullValue>() {
          public NullValue findValueByNumber(int number) {
            return NullValue.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.google.protobuf.StructProto.getDescriptor()
        .getEnumTypes().get(0);
  }

  private static final NullValue[] VALUES = values();

  public static NullValue valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private NullValue(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:google.protobuf.NullValue)
}

