// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bin.proto

package u1467085.feedbins;

/**
 * Protobuf type {@code feedbins.StuffAmount}
 */
public  final class StuffAmount extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:feedbins.StuffAmount)
    StuffAmountOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StuffAmount.newBuilder() to construct.
  private StuffAmount(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StuffAmount() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StuffAmount(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            stuffAmount_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return u1467085.feedbins.FeedBin.internal_static_feedbins_StuffAmount_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return u1467085.feedbins.FeedBin.internal_static_feedbins_StuffAmount_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            u1467085.feedbins.StuffAmount.class, u1467085.feedbins.StuffAmount.Builder.class);
  }

  public static final int STUFFAMOUNT_FIELD_NUMBER = 1;
  private int stuffAmount_;
  /**
   * <code>int32 stuffAmount = 1;</code>
   */
  public int getStuffAmount() {
    return stuffAmount_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (stuffAmount_ != 0) {
      output.writeInt32(1, stuffAmount_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (stuffAmount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, stuffAmount_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof u1467085.feedbins.StuffAmount)) {
      return super.equals(obj);
    }
    u1467085.feedbins.StuffAmount other = (u1467085.feedbins.StuffAmount) obj;

    if (getStuffAmount()
        != other.getStuffAmount()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STUFFAMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getStuffAmount();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static u1467085.feedbins.StuffAmount parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static u1467085.feedbins.StuffAmount parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static u1467085.feedbins.StuffAmount parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static u1467085.feedbins.StuffAmount parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(u1467085.feedbins.StuffAmount prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
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
   * Protobuf type {@code feedbins.StuffAmount}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:feedbins.StuffAmount)
      u1467085.feedbins.StuffAmountOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return u1467085.feedbins.FeedBin.internal_static_feedbins_StuffAmount_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return u1467085.feedbins.FeedBin.internal_static_feedbins_StuffAmount_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              u1467085.feedbins.StuffAmount.class, u1467085.feedbins.StuffAmount.Builder.class);
    }

    // Construct using u1467085.feedbins.StuffAmount.newBuilder()
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
    @java.lang.Override
    public Builder clear() {
      super.clear();
      stuffAmount_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return u1467085.feedbins.FeedBin.internal_static_feedbins_StuffAmount_descriptor;
    }

    @java.lang.Override
    public u1467085.feedbins.StuffAmount getDefaultInstanceForType() {
      return u1467085.feedbins.StuffAmount.getDefaultInstance();
    }

    @java.lang.Override
    public u1467085.feedbins.StuffAmount build() {
      u1467085.feedbins.StuffAmount result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public u1467085.feedbins.StuffAmount buildPartial() {
      u1467085.feedbins.StuffAmount result = new u1467085.feedbins.StuffAmount(this);
      result.stuffAmount_ = stuffAmount_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof u1467085.feedbins.StuffAmount) {
        return mergeFrom((u1467085.feedbins.StuffAmount)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(u1467085.feedbins.StuffAmount other) {
      if (other == u1467085.feedbins.StuffAmount.getDefaultInstance()) return this;
      if (other.getStuffAmount() != 0) {
        setStuffAmount(other.getStuffAmount());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      u1467085.feedbins.StuffAmount parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (u1467085.feedbins.StuffAmount) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int stuffAmount_ ;
    /**
     * <code>int32 stuffAmount = 1;</code>
     */
    public int getStuffAmount() {
      return stuffAmount_;
    }
    /**
     * <code>int32 stuffAmount = 1;</code>
     */
    public Builder setStuffAmount(int value) {
      
      stuffAmount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 stuffAmount = 1;</code>
     */
    public Builder clearStuffAmount() {
      
      stuffAmount_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:feedbins.StuffAmount)
  }

  // @@protoc_insertion_point(class_scope:feedbins.StuffAmount)
  private static final u1467085.feedbins.StuffAmount DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new u1467085.feedbins.StuffAmount();
  }

  public static u1467085.feedbins.StuffAmount getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StuffAmount>
      PARSER = new com.google.protobuf.AbstractParser<StuffAmount>() {
    @java.lang.Override
    public StuffAmount parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StuffAmount(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StuffAmount> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StuffAmount> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public u1467085.feedbins.StuffAmount getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

