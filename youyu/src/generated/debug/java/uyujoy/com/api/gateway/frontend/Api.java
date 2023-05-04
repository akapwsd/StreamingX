// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: api.proto

package uyujoy.com.api.gateway.frontend;

public final class Api {
  private Api() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface pingOrBuilder extends
      // @@protoc_insertion_point(interface_extends:uyujoy.com.api.gateway.frontend.ping)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional string channelId = 1;</code>
     */
    java.lang.String getChannelId();
    /**
     * <code>optional string channelId = 1;</code>
     */
    com.google.protobuf.ByteString
        getChannelIdBytes();
  }
  /**
   * Protobuf type {@code uyujoy.com.api.gateway.frontend.ping}
   */
  public  static final class ping extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:uyujoy.com.api.gateway.frontend.ping)
      pingOrBuilder {
    // Use ping.newBuilder() to construct.
    private ping(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ping() {
      channelId_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private ping(
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

              channelId_ = s;
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
      return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_ping_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_ping_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              uyujoy.com.api.gateway.frontend.Api.ping.class, uyujoy.com.api.gateway.frontend.Api.ping.Builder.class);
    }

    public static final int CHANNELID_FIELD_NUMBER = 1;
    private volatile java.lang.Object channelId_;
    /**
     * <code>optional string channelId = 1;</code>
     */
    public java.lang.String getChannelId() {
      java.lang.Object ref = channelId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        channelId_ = s;
        return s;
      }
    }
    /**
     * <code>optional string channelId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getChannelIdBytes() {
      java.lang.Object ref = channelId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        channelId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (!getChannelIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, channelId_);
      }
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getChannelIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, channelId_);
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
      if (!(obj instanceof uyujoy.com.api.gateway.frontend.Api.ping)) {
        return super.equals(obj);
      }
      uyujoy.com.api.gateway.frontend.Api.ping other = (uyujoy.com.api.gateway.frontend.Api.ping) obj;

      boolean result = true;
      result = result && getChannelId()
          .equals(other.getChannelId());
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (37 * hash) + CHANNELID_FIELD_NUMBER;
      hash = (53 * hash) + getChannelId().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static uyujoy.com.api.gateway.frontend.Api.ping parseFrom(
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
    public static Builder newBuilder(uyujoy.com.api.gateway.frontend.Api.ping prototype) {
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
     * Protobuf type {@code uyujoy.com.api.gateway.frontend.ping}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:uyujoy.com.api.gateway.frontend.ping)
        uyujoy.com.api.gateway.frontend.Api.pingOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_ping_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_ping_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                uyujoy.com.api.gateway.frontend.Api.ping.class, uyujoy.com.api.gateway.frontend.Api.ping.Builder.class);
      }

      // Construct using uyujoy.com.api.gateway.frontend.Api.ping.newBuilder()
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
        channelId_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_ping_descriptor;
      }

      public uyujoy.com.api.gateway.frontend.Api.ping getDefaultInstanceForType() {
        return uyujoy.com.api.gateway.frontend.Api.ping.getDefaultInstance();
      }

      public uyujoy.com.api.gateway.frontend.Api.ping build() {
        uyujoy.com.api.gateway.frontend.Api.ping result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public uyujoy.com.api.gateway.frontend.Api.ping buildPartial() {
        uyujoy.com.api.gateway.frontend.Api.ping result = new uyujoy.com.api.gateway.frontend.Api.ping(this);
        result.channelId_ = channelId_;
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
        if (other instanceof uyujoy.com.api.gateway.frontend.Api.ping) {
          return mergeFrom((uyujoy.com.api.gateway.frontend.Api.ping)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(uyujoy.com.api.gateway.frontend.Api.ping other) {
        if (other == uyujoy.com.api.gateway.frontend.Api.ping.getDefaultInstance()) return this;
        if (!other.getChannelId().isEmpty()) {
          channelId_ = other.channelId_;
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
        uyujoy.com.api.gateway.frontend.Api.ping parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (uyujoy.com.api.gateway.frontend.Api.ping) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object channelId_ = "";
      /**
       * <code>optional string channelId = 1;</code>
       */
      public java.lang.String getChannelId() {
        java.lang.Object ref = channelId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          channelId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string channelId = 1;</code>
       */
      public com.google.protobuf.ByteString
          getChannelIdBytes() {
        java.lang.Object ref = channelId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          channelId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string channelId = 1;</code>
       */
      public Builder setChannelId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        channelId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string channelId = 1;</code>
       */
      public Builder clearChannelId() {
        
        channelId_ = getDefaultInstance().getChannelId();
        onChanged();
        return this;
      }
      /**
       * <code>optional string channelId = 1;</code>
       */
      public Builder setChannelIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        channelId_ = value;
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


      // @@protoc_insertion_point(builder_scope:uyujoy.com.api.gateway.frontend.ping)
    }

    // @@protoc_insertion_point(class_scope:uyujoy.com.api.gateway.frontend.ping)
    private static final uyujoy.com.api.gateway.frontend.Api.ping DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new uyujoy.com.api.gateway.frontend.Api.ping();
    }

    public static uyujoy.com.api.gateway.frontend.Api.ping getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ping>
        PARSER = new com.google.protobuf.AbstractParser<ping>() {
      public ping parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ping(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ping> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ping> getParserForType() {
      return PARSER;
    }

    public uyujoy.com.api.gateway.frontend.Api.ping getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface pongOrBuilder extends
      // @@protoc_insertion_point(interface_extends:uyujoy.com.api.gateway.frontend.pong)
      com.google.protobuf.MessageOrBuilder {
  }
  /**
   * Protobuf type {@code uyujoy.com.api.gateway.frontend.pong}
   */
  public  static final class pong extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:uyujoy.com.api.gateway.frontend.pong)
      pongOrBuilder {
    // Use pong.newBuilder() to construct.
    private pong(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private pong() {
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }
    private pong(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
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
      return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_pong_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_pong_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              uyujoy.com.api.gateway.frontend.Api.pong.class, uyujoy.com.api.gateway.frontend.Api.pong.Builder.class);
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
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof uyujoy.com.api.gateway.frontend.Api.pong)) {
        return super.equals(obj);
      }
      uyujoy.com.api.gateway.frontend.Api.pong other = (uyujoy.com.api.gateway.frontend.Api.pong) obj;

      boolean result = true;
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static uyujoy.com.api.gateway.frontend.Api.pong parseFrom(
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
    public static Builder newBuilder(uyujoy.com.api.gateway.frontend.Api.pong prototype) {
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
     * Protobuf type {@code uyujoy.com.api.gateway.frontend.pong}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:uyujoy.com.api.gateway.frontend.pong)
        uyujoy.com.api.gateway.frontend.Api.pongOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_pong_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_pong_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                uyujoy.com.api.gateway.frontend.Api.pong.class, uyujoy.com.api.gateway.frontend.Api.pong.Builder.class);
      }

      // Construct using uyujoy.com.api.gateway.frontend.Api.pong.newBuilder()
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
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return uyujoy.com.api.gateway.frontend.Api.internal_static_uyujoy_com_api_gateway_frontend_pong_descriptor;
      }

      public uyujoy.com.api.gateway.frontend.Api.pong getDefaultInstanceForType() {
        return uyujoy.com.api.gateway.frontend.Api.pong.getDefaultInstance();
      }

      public uyujoy.com.api.gateway.frontend.Api.pong build() {
        uyujoy.com.api.gateway.frontend.Api.pong result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public uyujoy.com.api.gateway.frontend.Api.pong buildPartial() {
        uyujoy.com.api.gateway.frontend.Api.pong result = new uyujoy.com.api.gateway.frontend.Api.pong(this);
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
        if (other instanceof uyujoy.com.api.gateway.frontend.Api.pong) {
          return mergeFrom((uyujoy.com.api.gateway.frontend.Api.pong)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(uyujoy.com.api.gateway.frontend.Api.pong other) {
        if (other == uyujoy.com.api.gateway.frontend.Api.pong.getDefaultInstance()) return this;
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
        uyujoy.com.api.gateway.frontend.Api.pong parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (uyujoy.com.api.gateway.frontend.Api.pong) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
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


      // @@protoc_insertion_point(builder_scope:uyujoy.com.api.gateway.frontend.pong)
    }

    // @@protoc_insertion_point(class_scope:uyujoy.com.api.gateway.frontend.pong)
    private static final uyujoy.com.api.gateway.frontend.Api.pong DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new uyujoy.com.api.gateway.frontend.Api.pong();
    }

    public static uyujoy.com.api.gateway.frontend.Api.pong getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<pong>
        PARSER = new com.google.protobuf.AbstractParser<pong>() {
      public pong parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new pong(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<pong> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<pong> getParserForType() {
      return PARSER;
    }

    public uyujoy.com.api.gateway.frontend.Api.pong getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_uyujoy_com_api_gateway_frontend_ping_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_uyujoy_com_api_gateway_frontend_ping_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_uyujoy_com_api_gateway_frontend_pong_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_uyujoy_com_api_gateway_frontend_pong_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tapi.proto\022\037uyujoy.com.api.gateway.fron" +
      "tend\"\031\n\004ping\022\021\n\tchannelId\030\001 \001(\t\"\006\n\004pongB" +
      "*Z(uyujoy.com/pass/protogen/gateway;gate" +
      "wayb\006proto3"
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
    internal_static_uyujoy_com_api_gateway_frontend_ping_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_uyujoy_com_api_gateway_frontend_ping_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_uyujoy_com_api_gateway_frontend_ping_descriptor,
        new java.lang.String[] { "ChannelId", });
    internal_static_uyujoy_com_api_gateway_frontend_pong_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_uyujoy_com_api_gateway_frontend_pong_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_uyujoy_com_api_gateway_frontend_pong_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
