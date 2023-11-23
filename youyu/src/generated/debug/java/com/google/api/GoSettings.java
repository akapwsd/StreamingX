// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/api/client.proto

package com.google.api;

/**
 * <pre>
 * Settings for Go client libraries.
 * </pre>
 *
 * Protobuf type {@code google.api.GoSettings}
 */
public  final class GoSettings extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.api.GoSettings)
    GoSettingsOrBuilder {
  // Use GoSettings.newBuilder() to construct.
  private GoSettings(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GoSettings() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GoSettings(
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
            com.google.api.CommonLanguageSettings.Builder subBuilder = null;
            if (common_ != null) {
              subBuilder = common_.toBuilder();
            }
            common_ = input.readMessage(com.google.api.CommonLanguageSettings.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(common_);
              common_ = subBuilder.buildPartial();
            }

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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.api.ClientProto.internal_static_google_api_GoSettings_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.api.ClientProto.internal_static_google_api_GoSettings_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.api.GoSettings.class, com.google.api.GoSettings.Builder.class);
  }

  public static final int COMMON_FIELD_NUMBER = 1;
  private com.google.api.CommonLanguageSettings common_;
  /**
   * <pre>
   * Some settings.
   * </pre>
   *
   * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
   */
  public boolean hasCommon() {
    return common_ != null;
  }
  /**
   * <pre>
   * Some settings.
   * </pre>
   *
   * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
   */
  public com.google.api.CommonLanguageSettings getCommon() {
    return common_ == null ? com.google.api.CommonLanguageSettings.getDefaultInstance() : common_;
  }
  /**
   * <pre>
   * Some settings.
   * </pre>
   *
   * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
   */
  public com.google.api.CommonLanguageSettingsOrBuilder getCommonOrBuilder() {
    return getCommon();
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
    if (common_ != null) {
      output.writeMessage(1, getCommon());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (common_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getCommon());
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
    if (!(obj instanceof com.google.api.GoSettings)) {
      return super.equals(obj);
    }
    com.google.api.GoSettings other = (com.google.api.GoSettings) obj;

    boolean result = true;
    result = result && (hasCommon() == other.hasCommon());
    if (hasCommon()) {
      result = result && getCommon()
          .equals(other.getCommon());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    if (hasCommon()) {
      hash = (37 * hash) + COMMON_FIELD_NUMBER;
      hash = (53 * hash) + getCommon().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.api.GoSettings parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.api.GoSettings parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.api.GoSettings parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.api.GoSettings parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.api.GoSettings parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.api.GoSettings parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.api.GoSettings parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.api.GoSettings parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.api.GoSettings parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.api.GoSettings parseFrom(
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
  public static Builder newBuilder(com.google.api.GoSettings prototype) {
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
   * Settings for Go client libraries.
   * </pre>
   *
   * Protobuf type {@code google.api.GoSettings}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.api.GoSettings)
      com.google.api.GoSettingsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.api.ClientProto.internal_static_google_api_GoSettings_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.api.ClientProto.internal_static_google_api_GoSettings_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.api.GoSettings.class, com.google.api.GoSettings.Builder.class);
    }

    // Construct using com.google.api.GoSettings.newBuilder()
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
      if (commonBuilder_ == null) {
        common_ = null;
      } else {
        common_ = null;
        commonBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.api.ClientProto.internal_static_google_api_GoSettings_descriptor;
    }

    public com.google.api.GoSettings getDefaultInstanceForType() {
      return com.google.api.GoSettings.getDefaultInstance();
    }

    public com.google.api.GoSettings build() {
      com.google.api.GoSettings result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.api.GoSettings buildPartial() {
      com.google.api.GoSettings result = new com.google.api.GoSettings(this);
      if (commonBuilder_ == null) {
        result.common_ = common_;
      } else {
        result.common_ = commonBuilder_.build();
      }
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
      if (other instanceof com.google.api.GoSettings) {
        return mergeFrom((com.google.api.GoSettings)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.api.GoSettings other) {
      if (other == com.google.api.GoSettings.getDefaultInstance()) return this;
      if (other.hasCommon()) {
        mergeCommon(other.getCommon());
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
      com.google.api.GoSettings parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.api.GoSettings) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.api.CommonLanguageSettings common_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.api.CommonLanguageSettings, com.google.api.CommonLanguageSettings.Builder, com.google.api.CommonLanguageSettingsOrBuilder> commonBuilder_;
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public boolean hasCommon() {
      return commonBuilder_ != null || common_ != null;
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public com.google.api.CommonLanguageSettings getCommon() {
      if (commonBuilder_ == null) {
        return common_ == null ? com.google.api.CommonLanguageSettings.getDefaultInstance() : common_;
      } else {
        return commonBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public Builder setCommon(com.google.api.CommonLanguageSettings value) {
      if (commonBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        common_ = value;
        onChanged();
      } else {
        commonBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public Builder setCommon(
        com.google.api.CommonLanguageSettings.Builder builderForValue) {
      if (commonBuilder_ == null) {
        common_ = builderForValue.build();
        onChanged();
      } else {
        commonBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public Builder mergeCommon(com.google.api.CommonLanguageSettings value) {
      if (commonBuilder_ == null) {
        if (common_ != null) {
          common_ =
            com.google.api.CommonLanguageSettings.newBuilder(common_).mergeFrom(value).buildPartial();
        } else {
          common_ = value;
        }
        onChanged();
      } else {
        commonBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public Builder clearCommon() {
      if (commonBuilder_ == null) {
        common_ = null;
        onChanged();
      } else {
        common_ = null;
        commonBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public com.google.api.CommonLanguageSettings.Builder getCommonBuilder() {
      
      onChanged();
      return getCommonFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    public com.google.api.CommonLanguageSettingsOrBuilder getCommonOrBuilder() {
      if (commonBuilder_ != null) {
        return commonBuilder_.getMessageOrBuilder();
      } else {
        return common_ == null ?
            com.google.api.CommonLanguageSettings.getDefaultInstance() : common_;
      }
    }
    /**
     * <pre>
     * Some settings.
     * </pre>
     *
     * <code>optional .google.api.CommonLanguageSettings common = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.api.CommonLanguageSettings, com.google.api.CommonLanguageSettings.Builder, com.google.api.CommonLanguageSettingsOrBuilder> 
        getCommonFieldBuilder() {
      if (commonBuilder_ == null) {
        commonBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.api.CommonLanguageSettings, com.google.api.CommonLanguageSettings.Builder, com.google.api.CommonLanguageSettingsOrBuilder>(
                getCommon(),
                getParentForChildren(),
                isClean());
        common_ = null;
      }
      return commonBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:google.api.GoSettings)
  }

  // @@protoc_insertion_point(class_scope:google.api.GoSettings)
  private static final com.google.api.GoSettings DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.api.GoSettings();
  }

  public static com.google.api.GoSettings getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GoSettings>
      PARSER = new com.google.protobuf.AbstractParser<GoSettings>() {
    public GoSettings parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GoSettings(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GoSettings> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GoSettings> getParserForType() {
    return PARSER;
  }

  public com.google.api.GoSettings getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

