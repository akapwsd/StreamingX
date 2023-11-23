// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/protobuf/field_mask.proto

package com.google.protobuf;

/**
 * <pre>
 * `FieldMask` represents a set of symbolic field paths, for example:
 *     paths: "f.a"
 *     paths: "f.b.d"
 * Here `f` represents a field in some root message, `a` and `b`
 * fields in the message found in `f`, and `d` a field found in the
 * message in `f.b`.
 * Field masks are used to specify a subset of fields that should be
 * returned by a get operation or modified by an update operation.
 * Field masks also have a custom JSON encoding (see below).
 * # Field Masks in Projections
 * When used in the context of a projection, a response message or
 * sub-message is filtered by the API to only contain those fields as
 * specified in the mask. For example, if the mask in the previous
 * example is applied to a response message as follows:
 *     f {
 *       a : 22
 *       b {
 *         d : 1
 *         x : 2
 *       }
 *       y : 13
 *     }
 *     z: 8
 * The result will not contain specific values for fields x,y and z
 * (their value will be set to the default, and omitted in proto text
 * output):
 *     f {
 *       a : 22
 *       b {
 *         d : 1
 *       }
 *     }
 * A repeated field is not allowed except at the last position of a
 * paths string.
 * If a FieldMask object is not present in a get operation, the
 * operation applies to all fields (as if a FieldMask of all fields
 * had been specified).
 * Note that a field mask does not necessarily apply to the
 * top-level response message. In case of a REST get operation, the
 * field mask applies directly to the response, but in case of a REST
 * list operation, the mask instead applies to each individual message
 * in the returned resource list. In case of a REST custom method,
 * other definitions may be used. Where the mask applies will be
 * clearly documented together with its declaration in the API.  In
 * any case, the effect on the returned resource/resources is required
 * behavior for APIs.
 * # Field Masks in Update Operations
 * A field mask in update operations specifies which fields of the
 * targeted resource are going to be updated. The API is required
 * to only change the values of the fields as specified in the mask
 * and leave the others untouched. If a resource is passed in to
 * describe the updated values, the API ignores the values of all
 * fields not covered by the mask.
 * If a repeated field is specified for an update operation, new values will
 * be appended to the existing repeated field in the target resource. Note that
 * a repeated field is only allowed in the last position of a `paths` string.
 * If a sub-message is specified in the last position of the field mask for an
 * update operation, then new value will be merged into the existing sub-message
 * in the target resource.
 * For example, given the target message:
 *     f {
 *       b {
 *         d: 1
 *         x: 2
 *       }
 *       c: [1]
 *     }
 * And an update message:
 *     f {
 *       b {
 *         d: 10
 *       }
 *       c: [2]
 *     }
 * then if the field mask is:
 *  paths: ["f.b", "f.c"]
 * then the result will be:
 *     f {
 *       b {
 *         d: 10
 *         x: 2
 *       }
 *       c: [1, 2]
 *     }
 * An implementation may provide options to override this default behavior for
 * repeated and message fields.
 * In order to reset a field's value to the default, the field must
 * be in the mask and set to the default value in the provided resource.
 * Hence, in order to reset all fields of a resource, provide a default
 * instance of the resource and set all fields in the mask, or do
 * not provide a mask as described below.
 * If a field mask is not present on update, the operation applies to
 * all fields (as if a field mask of all fields has been specified).
 * Note that in the presence of schema evolution, this may mean that
 * fields the client does not know and has therefore not filled into
 * the request will be reset to their default. If this is unwanted
 * behavior, a specific service may require a client to always specify
 * a field mask, producing an error if not.
 * As with get operations, the location of the resource which
 * describes the updated values in the request message depends on the
 * operation kind. In any case, the effect of the field mask is
 * required to be honored by the API.
 * ## Considerations for HTTP REST
 * The HTTP kind of an update operation which uses a field mask must
 * be set to PATCH instead of PUT in order to satisfy HTTP semantics
 * (PUT must only be used for full updates).
 * # JSON Encoding of Field Masks
 * In JSON, a field mask is encoded as a single string where paths are
 * separated by a comma. Fields name in each path are converted
 * to/from lower-camel naming conventions.
 * As an example, consider the following message declarations:
 *     message Profile {
 *       User user = 1;
 *       Photo photo = 2;
 *     }
 *     message User {
 *       string display_name = 1;
 *       string address = 2;
 *     }
 * In proto a field mask for `Profile` may look as such:
 *     mask {
 *       paths: "user.display_name"
 *       paths: "photo"
 *     }
 * In JSON, the same mask is represented as below:
 *     {
 *       mask: "user.displayName,photo"
 *     }
 * # Field Masks and Oneof Fields
 * Field masks treat fields in oneofs just as regular fields. Consider the
 * following message:
 *     message SampleMessage {
 *       oneof test_oneof {
 *         string name = 4;
 *         SubMessage sub_message = 9;
 *       }
 *     }
 * The field mask can be:
 *     mask {
 *       paths: "name"
 *     }
 * Or:
 *     mask {
 *       paths: "sub_message"
 *     }
 * Note that oneof type names ("test_oneof" in this case) cannot be used in
 * paths.
 * ## Field Mask Verification
 * The implementation of any API method which has a FieldMask type field in the
 * request should verify the included field paths, and return an
 * `INVALID_ARGUMENT` error if any path is unmappable.
 * </pre>
 *
 * Protobuf type {@code google.protobuf.FieldMask}
 */
public  final class FieldMask extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.protobuf.FieldMask)
    FieldMaskOrBuilder {
  // Use FieldMask.newBuilder() to construct.
  private FieldMask(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FieldMask() {
    paths_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private FieldMask(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              paths_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            paths_.add(s);
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        paths_ = paths_.getUnmodifiableView();
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.protobuf.FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.protobuf.FieldMaskProto.internal_static_google_protobuf_FieldMask_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.protobuf.FieldMask.class, com.google.protobuf.FieldMask.Builder.class);
  }

  public static final int PATHS_FIELD_NUMBER = 1;
  private com.google.protobuf.LazyStringList paths_;
  /**
   * <pre>
   * The set of field mask paths.
   * </pre>
   *
   * <code>repeated string paths = 1;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getPathsList() {
    return paths_;
  }
  /**
   * <pre>
   * The set of field mask paths.
   * </pre>
   *
   * <code>repeated string paths = 1;</code>
   */
  public int getPathsCount() {
    return paths_.size();
  }
  /**
   * <pre>
   * The set of field mask paths.
   * </pre>
   *
   * <code>repeated string paths = 1;</code>
   */
  public java.lang.String getPaths(int index) {
    return paths_.get(index);
  }
  /**
   * <pre>
   * The set of field mask paths.
   * </pre>
   *
   * <code>repeated string paths = 1;</code>
   */
  public com.google.protobuf.ByteString
      getPathsBytes(int index) {
    return paths_.getByteString(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < paths_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, paths_.getRaw(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < paths_.size(); i++) {
        dataSize += computeStringSizeNoTag(paths_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getPathsList().size();
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.google.protobuf.FieldMask)) {
      return super.equals(obj);
    }
    com.google.protobuf.FieldMask other = (com.google.protobuf.FieldMask) obj;

    boolean result = true;
    result = result && getPathsList()
        .equals(other.getPathsList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    if (getPathsCount() > 0) {
      hash = (37 * hash) + PATHS_FIELD_NUMBER;
      hash = (53 * hash) + getPathsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.protobuf.FieldMask parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.protobuf.FieldMask parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.protobuf.FieldMask parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.protobuf.FieldMask parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.protobuf.FieldMask parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.protobuf.FieldMask parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.protobuf.FieldMask parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.protobuf.FieldMask parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.protobuf.FieldMask parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.protobuf.FieldMask parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.protobuf.FieldMask prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * `FieldMask` represents a set of symbolic field paths, for example:
   *     paths: "f.a"
   *     paths: "f.b.d"
   * Here `f` represents a field in some root message, `a` and `b`
   * fields in the message found in `f`, and `d` a field found in the
   * message in `f.b`.
   * Field masks are used to specify a subset of fields that should be
   * returned by a get operation or modified by an update operation.
   * Field masks also have a custom JSON encoding (see below).
   * # Field Masks in Projections
   * When used in the context of a projection, a response message or
   * sub-message is filtered by the API to only contain those fields as
   * specified in the mask. For example, if the mask in the previous
   * example is applied to a response message as follows:
   *     f {
   *       a : 22
   *       b {
   *         d : 1
   *         x : 2
   *       }
   *       y : 13
   *     }
   *     z: 8
   * The result will not contain specific values for fields x,y and z
   * (their value will be set to the default, and omitted in proto text
   * output):
   *     f {
   *       a : 22
   *       b {
   *         d : 1
   *       }
   *     }
   * A repeated field is not allowed except at the last position of a
   * paths string.
   * If a FieldMask object is not present in a get operation, the
   * operation applies to all fields (as if a FieldMask of all fields
   * had been specified).
   * Note that a field mask does not necessarily apply to the
   * top-level response message. In case of a REST get operation, the
   * field mask applies directly to the response, but in case of a REST
   * list operation, the mask instead applies to each individual message
   * in the returned resource list. In case of a REST custom method,
   * other definitions may be used. Where the mask applies will be
   * clearly documented together with its declaration in the API.  In
   * any case, the effect on the returned resource/resources is required
   * behavior for APIs.
   * # Field Masks in Update Operations
   * A field mask in update operations specifies which fields of the
   * targeted resource are going to be updated. The API is required
   * to only change the values of the fields as specified in the mask
   * and leave the others untouched. If a resource is passed in to
   * describe the updated values, the API ignores the values of all
   * fields not covered by the mask.
   * If a repeated field is specified for an update operation, new values will
   * be appended to the existing repeated field in the target resource. Note that
   * a repeated field is only allowed in the last position of a `paths` string.
   * If a sub-message is specified in the last position of the field mask for an
   * update operation, then new value will be merged into the existing sub-message
   * in the target resource.
   * For example, given the target message:
   *     f {
   *       b {
   *         d: 1
   *         x: 2
   *       }
   *       c: [1]
   *     }
   * And an update message:
   *     f {
   *       b {
   *         d: 10
   *       }
   *       c: [2]
   *     }
   * then if the field mask is:
   *  paths: ["f.b", "f.c"]
   * then the result will be:
   *     f {
   *       b {
   *         d: 10
   *         x: 2
   *       }
   *       c: [1, 2]
   *     }
   * An implementation may provide options to override this default behavior for
   * repeated and message fields.
   * In order to reset a field's value to the default, the field must
   * be in the mask and set to the default value in the provided resource.
   * Hence, in order to reset all fields of a resource, provide a default
   * instance of the resource and set all fields in the mask, or do
   * not provide a mask as described below.
   * If a field mask is not present on update, the operation applies to
   * all fields (as if a field mask of all fields has been specified).
   * Note that in the presence of schema evolution, this may mean that
   * fields the client does not know and has therefore not filled into
   * the request will be reset to their default. If this is unwanted
   * behavior, a specific service may require a client to always specify
   * a field mask, producing an error if not.
   * As with get operations, the location of the resource which
   * describes the updated values in the request message depends on the
   * operation kind. In any case, the effect of the field mask is
   * required to be honored by the API.
   * ## Considerations for HTTP REST
   * The HTTP kind of an update operation which uses a field mask must
   * be set to PATCH instead of PUT in order to satisfy HTTP semantics
   * (PUT must only be used for full updates).
   * # JSON Encoding of Field Masks
   * In JSON, a field mask is encoded as a single string where paths are
   * separated by a comma. Fields name in each path are converted
   * to/from lower-camel naming conventions.
   * As an example, consider the following message declarations:
   *     message Profile {
   *       User user = 1;
   *       Photo photo = 2;
   *     }
   *     message User {
   *       string display_name = 1;
   *       string address = 2;
   *     }
   * In proto a field mask for `Profile` may look as such:
   *     mask {
   *       paths: "user.display_name"
   *       paths: "photo"
   *     }
   * In JSON, the same mask is represented as below:
   *     {
   *       mask: "user.displayName,photo"
   *     }
   * # Field Masks and Oneof Fields
   * Field masks treat fields in oneofs just as regular fields. Consider the
   * following message:
   *     message SampleMessage {
   *       oneof test_oneof {
   *         string name = 4;
   *         SubMessage sub_message = 9;
   *       }
   *     }
   * The field mask can be:
   *     mask {
   *       paths: "name"
   *     }
   * Or:
   *     mask {
   *       paths: "sub_message"
   *     }
   * Note that oneof type names ("test_oneof" in this case) cannot be used in
   * paths.
   * ## Field Mask Verification
   * The implementation of any API method which has a FieldMask type field in the
   * request should verify the included field paths, and return an
   * `INVALID_ARGUMENT` error if any path is unmappable.
   * </pre>
   *
   * Protobuf type {@code google.protobuf.FieldMask}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.protobuf.FieldMask)
      com.google.protobuf.FieldMaskOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.protobuf.FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.protobuf.FieldMaskProto.internal_static_google_protobuf_FieldMask_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.protobuf.FieldMask.class, com.google.protobuf.FieldMask.Builder.class);
    }

    // Construct using com.google.protobuf.FieldMask.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      paths_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.protobuf.FieldMaskProto.internal_static_google_protobuf_FieldMask_descriptor;
    }

    public com.google.protobuf.FieldMask getDefaultInstanceForType() {
      return com.google.protobuf.FieldMask.getDefaultInstance();
    }

    public com.google.protobuf.FieldMask build() {
      com.google.protobuf.FieldMask result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.protobuf.FieldMask buildPartial() {
      com.google.protobuf.FieldMask result = new com.google.protobuf.FieldMask(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        paths_ = paths_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.paths_ = paths_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.protobuf.FieldMask) {
        return mergeFrom((com.google.protobuf.FieldMask)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.protobuf.FieldMask other) {
      if (other == com.google.protobuf.FieldMask.getDefaultInstance()) return this;
      if (!other.paths_.isEmpty()) {
        if (paths_.isEmpty()) {
          paths_ = other.paths_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensurePathsIsMutable();
          paths_.addAll(other.paths_);
        }
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.protobuf.FieldMask parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.protobuf.FieldMask) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.LazyStringList paths_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensurePathsIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        paths_ = new com.google.protobuf.LazyStringArrayList(paths_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getPathsList() {
      return paths_.getUnmodifiableView();
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public int getPathsCount() {
      return paths_.size();
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public java.lang.String getPaths(int index) {
      return paths_.get(index);
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public com.google.protobuf.ByteString
        getPathsBytes(int index) {
      return paths_.getByteString(index);
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public Builder setPaths(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensurePathsIsMutable();
      paths_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public Builder addPaths(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensurePathsIsMutable();
      paths_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public Builder addAllPaths(
        java.lang.Iterable<java.lang.String> values) {
      ensurePathsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, paths_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public Builder clearPaths() {
      paths_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The set of field mask paths.
     * </pre>
     *
     * <code>repeated string paths = 1;</code>
     */
    public Builder addPathsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensurePathsIsMutable();
      paths_.add(value);
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:google.protobuf.FieldMask)
  }

  // @@protoc_insertion_point(class_scope:google.protobuf.FieldMask)
  private static final com.google.protobuf.FieldMask DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.protobuf.FieldMask();
  }

  public static com.google.protobuf.FieldMask getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FieldMask>
      PARSER = new com.google.protobuf.AbstractParser<FieldMask>() {
    public FieldMask parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FieldMask(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FieldMask> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FieldMask> getParserForType() {
    return PARSER;
  }

  public com.google.protobuf.FieldMask getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

