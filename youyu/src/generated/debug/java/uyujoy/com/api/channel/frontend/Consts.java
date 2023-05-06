// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: consts.proto

package uyujoy.com.api.channel.frontend;

public final class Consts {
  private Consts() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code uyujoy.com.api.channel.frontend.channelState}
   */
  public enum channelState
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>ChannelStateClosed = 0;</code>
     */
    ChannelStateClosed(0),
    /**
     * <code>ChannelStateFree = 1;</code>
     */
    ChannelStateFree(1),
    /**
     * <code>ChannelStateBusy = 2;</code>
     */
    ChannelStateBusy(2),
    /**
     * <code>ChannelStateInsufficientBalance = 999;</code>
     */
    ChannelStateInsufficientBalance(999),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>ChannelStateClosed = 0;</code>
     */
    public static final int ChannelStateClosed_VALUE = 0;
    /**
     * <code>ChannelStateFree = 1;</code>
     */
    public static final int ChannelStateFree_VALUE = 1;
    /**
     * <code>ChannelStateBusy = 2;</code>
     */
    public static final int ChannelStateBusy_VALUE = 2;
    /**
     * <code>ChannelStateInsufficientBalance = 999;</code>
     */
    public static final int ChannelStateInsufficientBalance_VALUE = 999;


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
    public static channelState valueOf(int value) {
      return forNumber(value);
    }

    public static channelState forNumber(int value) {
      switch (value) {
        case 0: return ChannelStateClosed;
        case 1: return ChannelStateFree;
        case 2: return ChannelStateBusy;
        case 999: return ChannelStateInsufficientBalance;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<channelState>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        channelState> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<channelState>() {
            public channelState findValueByNumber(int number) {
              return channelState.forNumber(number);
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
      return uyujoy.com.api.channel.frontend.Consts.getDescriptor().getEnumTypes().get(0);
    }

    private static final channelState[] VALUES = values();

    public static channelState valueOf(
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

    private channelState(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:uyujoy.com.api.channel.frontend.channelState)
  }

  /**
   * Protobuf enum {@code uyujoy.com.api.channel.frontend.channelStopReason}
   */
  public enum channelStopReason
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>ChannelStopReasonUnknown = 0;</code>
     */
    ChannelStopReasonUnknown(0),
    /**
     * <pre>
     *房间正常停止
     * </pre>
     *
     * <code>ChannelStopReasonNormal = 1;</code>
     */
    ChannelStopReasonNormal(1),
    /**
     * <code>ChannelStopReasonClosedBySystem = 2;</code>
     */
    ChannelStopReasonClosedBySystem(2),
    /**
     * <code>ChannelStopReasonError = 3;</code>
     */
    ChannelStopReasonError(3),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>ChannelStopReasonUnknown = 0;</code>
     */
    public static final int ChannelStopReasonUnknown_VALUE = 0;
    /**
     * <pre>
     *房间正常停止
     * </pre>
     *
     * <code>ChannelStopReasonNormal = 1;</code>
     */
    public static final int ChannelStopReasonNormal_VALUE = 1;
    /**
     * <code>ChannelStopReasonClosedBySystem = 2;</code>
     */
    public static final int ChannelStopReasonClosedBySystem_VALUE = 2;
    /**
     * <code>ChannelStopReasonError = 3;</code>
     */
    public static final int ChannelStopReasonError_VALUE = 3;


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
    public static channelStopReason valueOf(int value) {
      return forNumber(value);
    }

    public static channelStopReason forNumber(int value) {
      switch (value) {
        case 0: return ChannelStopReasonUnknown;
        case 1: return ChannelStopReasonNormal;
        case 2: return ChannelStopReasonClosedBySystem;
        case 3: return ChannelStopReasonError;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<channelStopReason>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        channelStopReason> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<channelStopReason>() {
            public channelStopReason findValueByNumber(int number) {
              return channelStopReason.forNumber(number);
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
      return uyujoy.com.api.channel.frontend.Consts.getDescriptor().getEnumTypes().get(1);
    }

    private static final channelStopReason[] VALUES = values();

    public static channelStopReason valueOf(
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

    private channelStopReason(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:uyujoy.com.api.channel.frontend.channelStopReason)
  }

  /**
   * Protobuf enum {@code uyujoy.com.api.channel.frontend.channelUserKickReason}
   */
  public enum channelUserKickReason
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     *没有被踢
     * </pre>
     *
     * <code>ChannelUserKickReasonNormal = 0;</code>
     */
    ChannelUserKickReasonNormal(0),
    /**
     * <code>ChannelUserKickReasonKickBySystem = 1;</code>
     */
    ChannelUserKickReasonKickBySystem(1),
    /**
     * <code>ChannelUserKickReasonError = 2;</code>
     */
    ChannelUserKickReasonError(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <pre>
     *没有被踢
     * </pre>
     *
     * <code>ChannelUserKickReasonNormal = 0;</code>
     */
    public static final int ChannelUserKickReasonNormal_VALUE = 0;
    /**
     * <code>ChannelUserKickReasonKickBySystem = 1;</code>
     */
    public static final int ChannelUserKickReasonKickBySystem_VALUE = 1;
    /**
     * <code>ChannelUserKickReasonError = 2;</code>
     */
    public static final int ChannelUserKickReasonError_VALUE = 2;


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
    public static channelUserKickReason valueOf(int value) {
      return forNumber(value);
    }

    public static channelUserKickReason forNumber(int value) {
      switch (value) {
        case 0: return ChannelUserKickReasonNormal;
        case 1: return ChannelUserKickReasonKickBySystem;
        case 2: return ChannelUserKickReasonError;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<channelUserKickReason>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        channelUserKickReason> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<channelUserKickReason>() {
            public channelUserKickReason findValueByNumber(int number) {
              return channelUserKickReason.forNumber(number);
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
      return uyujoy.com.api.channel.frontend.Consts.getDescriptor().getEnumTypes().get(2);
    }

    private static final channelUserKickReason[] VALUES = values();

    public static channelUserKickReason valueOf(
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

    private channelUserKickReason(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:uyujoy.com.api.channel.frontend.channelUserKickReason)
  }

  /**
   * Protobuf enum {@code uyujoy.com.api.channel.frontend.channelCategory}
   */
  public enum channelCategory
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>ChannelCategoryUnknown = 0;</code>
     */
    ChannelCategoryUnknown(0),
    /**
     * <code>ChannelCategorySingle = 1;</code>
     */
    ChannelCategorySingle(1),
    /**
     * <code>ChannelCategoryMultiple = 2;</code>
     */
    ChannelCategoryMultiple(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>ChannelCategoryUnknown = 0;</code>
     */
    public static final int ChannelCategoryUnknown_VALUE = 0;
    /**
     * <code>ChannelCategorySingle = 1;</code>
     */
    public static final int ChannelCategorySingle_VALUE = 1;
    /**
     * <code>ChannelCategoryMultiple = 2;</code>
     */
    public static final int ChannelCategoryMultiple_VALUE = 2;


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
    public static channelCategory valueOf(int value) {
      return forNumber(value);
    }

    public static channelCategory forNumber(int value) {
      switch (value) {
        case 0: return ChannelCategoryUnknown;
        case 1: return ChannelCategorySingle;
        case 2: return ChannelCategoryMultiple;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<channelCategory>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        channelCategory> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<channelCategory>() {
            public channelCategory findValueByNumber(int number) {
              return channelCategory.forNumber(number);
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
      return uyujoy.com.api.channel.frontend.Consts.getDescriptor().getEnumTypes().get(3);
    }

    private static final channelCategory[] VALUES = values();

    public static channelCategory valueOf(
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

    private channelCategory(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:uyujoy.com.api.channel.frontend.channelCategory)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014consts.proto\022\037uyujoy.com.api.channel.f" +
      "rontend*x\n\014channelState\022\026\n\022ChannelStateC" +
      "losed\020\000\022\024\n\020ChannelStateFree\020\001\022\024\n\020Channel" +
      "StateBusy\020\002\022$\n\037ChannelStateInsufficientB" +
      "alance\020\347\007*\217\001\n\021channelStopReason\022\034\n\030Chann" +
      "elStopReasonUnknown\020\000\022\033\n\027ChannelStopReas" +
      "onNormal\020\001\022#\n\037ChannelStopReasonClosedByS" +
      "ystem\020\002\022\032\n\026ChannelStopReasonError\020\003*\177\n\025c" +
      "hannelUserKickReason\022\037\n\033ChannelUserKickR" +
      "easonNormal\020\000\022%\n!ChannelUserKickReasonKi",
      "ckBySystem\020\001\022\036\n\032ChannelUserKickReasonErr" +
      "or\020\002*e\n\017channelCategory\022\032\n\026ChannelCatego" +
      "ryUnknown\020\000\022\031\n\025ChannelCategorySingle\020\001\022\033" +
      "\n\027ChannelCategoryMultiple\020\002B*Z(uyujoy.co" +
      "m/pass/protogen/channel;channelb\006proto3"
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
  }

  // @@protoc_insertion_point(outer_class_scope)
}