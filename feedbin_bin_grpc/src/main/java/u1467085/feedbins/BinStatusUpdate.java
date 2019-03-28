// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bin.proto

package u1467085.feedbins;

/**
 * Protobuf type {@code feedbins.BinStatusUpdate}
 */
public  final class BinStatusUpdate extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:feedbins.BinStatusUpdate)
    BinStatusUpdateOrBuilder {
private static final long serialVersionUID = 0L;
  // Use BinStatusUpdate.newBuilder() to construct.
  private BinStatusUpdate(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BinStatusUpdate() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BinStatusUpdate(
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
          case 10: {
            u1467085.feedbins.Stuff.Builder subBuilder = null;
            if (stuff_ != null) {
              subBuilder = stuff_.toBuilder();
            }
            stuff_ = input.readMessage(u1467085.feedbins.Stuff.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(stuff_);
              stuff_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            u1467085.feedbins.StuffAmount.Builder subBuilder = null;
            if (amount_ != null) {
              subBuilder = amount_.toBuilder();
            }
            amount_ = input.readMessage(u1467085.feedbins.StuffAmount.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(amount_);
              amount_ = subBuilder.buildPartial();
            }

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
    return u1467085.feedbins.FeedBinController.internal_static_feedbins_BinStatusUpdate_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return u1467085.feedbins.FeedBinController.internal_static_feedbins_BinStatusUpdate_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            u1467085.feedbins.BinStatusUpdate.class, u1467085.feedbins.BinStatusUpdate.Builder.class);
  }

  public static final int STUFF_FIELD_NUMBER = 1;
  private u1467085.feedbins.Stuff stuff_;
  /**
   * <code>.feedbins.Stuff stuff = 1;</code>
   */
  public boolean hasStuff() {
    return stuff_ != null;
  }
  /**
   * <code>.feedbins.Stuff stuff = 1;</code>
   */
  public u1467085.feedbins.Stuff getStuff() {
    return stuff_ == null ? u1467085.feedbins.Stuff.getDefaultInstance() : stuff_;
  }
  /**
   * <code>.feedbins.Stuff stuff = 1;</code>
   */
  public u1467085.feedbins.StuffOrBuilder getStuffOrBuilder() {
    return getStuff();
  }

  public static final int AMOUNT_FIELD_NUMBER = 2;
  private u1467085.feedbins.StuffAmount amount_;
  /**
   * <code>.feedbins.StuffAmount amount = 2;</code>
   */
  public boolean hasAmount() {
    return amount_ != null;
  }
  /**
   * <code>.feedbins.StuffAmount amount = 2;</code>
   */
  public u1467085.feedbins.StuffAmount getAmount() {
    return amount_ == null ? u1467085.feedbins.StuffAmount.getDefaultInstance() : amount_;
  }
  /**
   * <code>.feedbins.StuffAmount amount = 2;</code>
   */
  public u1467085.feedbins.StuffAmountOrBuilder getAmountOrBuilder() {
    return getAmount();
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
    if (stuff_ != null) {
      output.writeMessage(1, getStuff());
    }
    if (amount_ != null) {
      output.writeMessage(2, getAmount());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (stuff_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getStuff());
    }
    if (amount_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAmount());
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
    if (!(obj instanceof u1467085.feedbins.BinStatusUpdate)) {
      return super.equals(obj);
    }
    u1467085.feedbins.BinStatusUpdate other = (u1467085.feedbins.BinStatusUpdate) obj;

    if (hasStuff() != other.hasStuff()) return false;
    if (hasStuff()) {
      if (!getStuff()
          .equals(other.getStuff())) return false;
    }
    if (hasAmount() != other.hasAmount()) return false;
    if (hasAmount()) {
      if (!getAmount()
          .equals(other.getAmount())) return false;
    }
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
    if (hasStuff()) {
      hash = (37 * hash) + STUFF_FIELD_NUMBER;
      hash = (53 * hash) + getStuff().hashCode();
    }
    if (hasAmount()) {
      hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
      hash = (53 * hash) + getAmount().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static u1467085.feedbins.BinStatusUpdate parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static u1467085.feedbins.BinStatusUpdate parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static u1467085.feedbins.BinStatusUpdate parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static u1467085.feedbins.BinStatusUpdate parseFrom(
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
  public static Builder newBuilder(u1467085.feedbins.BinStatusUpdate prototype) {
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
   * Protobuf type {@code feedbins.BinStatusUpdate}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:feedbins.BinStatusUpdate)
      u1467085.feedbins.BinStatusUpdateOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return u1467085.feedbins.FeedBinController.internal_static_feedbins_BinStatusUpdate_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return u1467085.feedbins.FeedBinController.internal_static_feedbins_BinStatusUpdate_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              u1467085.feedbins.BinStatusUpdate.class, u1467085.feedbins.BinStatusUpdate.Builder.class);
    }

    // Construct using u1467085.feedbins.BinStatusUpdate.newBuilder()
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
      if (stuffBuilder_ == null) {
        stuff_ = null;
      } else {
        stuff_ = null;
        stuffBuilder_ = null;
      }
      if (amountBuilder_ == null) {
        amount_ = null;
      } else {
        amount_ = null;
        amountBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return u1467085.feedbins.FeedBinController.internal_static_feedbins_BinStatusUpdate_descriptor;
    }

    @java.lang.Override
    public u1467085.feedbins.BinStatusUpdate getDefaultInstanceForType() {
      return u1467085.feedbins.BinStatusUpdate.getDefaultInstance();
    }

    @java.lang.Override
    public u1467085.feedbins.BinStatusUpdate build() {
      u1467085.feedbins.BinStatusUpdate result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public u1467085.feedbins.BinStatusUpdate buildPartial() {
      u1467085.feedbins.BinStatusUpdate result = new u1467085.feedbins.BinStatusUpdate(this);
      if (stuffBuilder_ == null) {
        result.stuff_ = stuff_;
      } else {
        result.stuff_ = stuffBuilder_.build();
      }
      if (amountBuilder_ == null) {
        result.amount_ = amount_;
      } else {
        result.amount_ = amountBuilder_.build();
      }
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
      if (other instanceof u1467085.feedbins.BinStatusUpdate) {
        return mergeFrom((u1467085.feedbins.BinStatusUpdate)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(u1467085.feedbins.BinStatusUpdate other) {
      if (other == u1467085.feedbins.BinStatusUpdate.getDefaultInstance()) return this;
      if (other.hasStuff()) {
        mergeStuff(other.getStuff());
      }
      if (other.hasAmount()) {
        mergeAmount(other.getAmount());
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
      u1467085.feedbins.BinStatusUpdate parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (u1467085.feedbins.BinStatusUpdate) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private u1467085.feedbins.Stuff stuff_;
    private com.google.protobuf.SingleFieldBuilderV3<
        u1467085.feedbins.Stuff, u1467085.feedbins.Stuff.Builder, u1467085.feedbins.StuffOrBuilder> stuffBuilder_;
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public boolean hasStuff() {
      return stuffBuilder_ != null || stuff_ != null;
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public u1467085.feedbins.Stuff getStuff() {
      if (stuffBuilder_ == null) {
        return stuff_ == null ? u1467085.feedbins.Stuff.getDefaultInstance() : stuff_;
      } else {
        return stuffBuilder_.getMessage();
      }
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public Builder setStuff(u1467085.feedbins.Stuff value) {
      if (stuffBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        stuff_ = value;
        onChanged();
      } else {
        stuffBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public Builder setStuff(
        u1467085.feedbins.Stuff.Builder builderForValue) {
      if (stuffBuilder_ == null) {
        stuff_ = builderForValue.build();
        onChanged();
      } else {
        stuffBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public Builder mergeStuff(u1467085.feedbins.Stuff value) {
      if (stuffBuilder_ == null) {
        if (stuff_ != null) {
          stuff_ =
            u1467085.feedbins.Stuff.newBuilder(stuff_).mergeFrom(value).buildPartial();
        } else {
          stuff_ = value;
        }
        onChanged();
      } else {
        stuffBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public Builder clearStuff() {
      if (stuffBuilder_ == null) {
        stuff_ = null;
        onChanged();
      } else {
        stuff_ = null;
        stuffBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public u1467085.feedbins.Stuff.Builder getStuffBuilder() {
      
      onChanged();
      return getStuffFieldBuilder().getBuilder();
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    public u1467085.feedbins.StuffOrBuilder getStuffOrBuilder() {
      if (stuffBuilder_ != null) {
        return stuffBuilder_.getMessageOrBuilder();
      } else {
        return stuff_ == null ?
            u1467085.feedbins.Stuff.getDefaultInstance() : stuff_;
      }
    }
    /**
     * <code>.feedbins.Stuff stuff = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        u1467085.feedbins.Stuff, u1467085.feedbins.Stuff.Builder, u1467085.feedbins.StuffOrBuilder> 
        getStuffFieldBuilder() {
      if (stuffBuilder_ == null) {
        stuffBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            u1467085.feedbins.Stuff, u1467085.feedbins.Stuff.Builder, u1467085.feedbins.StuffOrBuilder>(
                getStuff(),
                getParentForChildren(),
                isClean());
        stuff_ = null;
      }
      return stuffBuilder_;
    }

    private u1467085.feedbins.StuffAmount amount_;
    private com.google.protobuf.SingleFieldBuilderV3<
        u1467085.feedbins.StuffAmount, u1467085.feedbins.StuffAmount.Builder, u1467085.feedbins.StuffAmountOrBuilder> amountBuilder_;
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public boolean hasAmount() {
      return amountBuilder_ != null || amount_ != null;
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public u1467085.feedbins.StuffAmount getAmount() {
      if (amountBuilder_ == null) {
        return amount_ == null ? u1467085.feedbins.StuffAmount.getDefaultInstance() : amount_;
      } else {
        return amountBuilder_.getMessage();
      }
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public Builder setAmount(u1467085.feedbins.StuffAmount value) {
      if (amountBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        amount_ = value;
        onChanged();
      } else {
        amountBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public Builder setAmount(
        u1467085.feedbins.StuffAmount.Builder builderForValue) {
      if (amountBuilder_ == null) {
        amount_ = builderForValue.build();
        onChanged();
      } else {
        amountBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public Builder mergeAmount(u1467085.feedbins.StuffAmount value) {
      if (amountBuilder_ == null) {
        if (amount_ != null) {
          amount_ =
            u1467085.feedbins.StuffAmount.newBuilder(amount_).mergeFrom(value).buildPartial();
        } else {
          amount_ = value;
        }
        onChanged();
      } else {
        amountBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public Builder clearAmount() {
      if (amountBuilder_ == null) {
        amount_ = null;
        onChanged();
      } else {
        amount_ = null;
        amountBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public u1467085.feedbins.StuffAmount.Builder getAmountBuilder() {
      
      onChanged();
      return getAmountFieldBuilder().getBuilder();
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    public u1467085.feedbins.StuffAmountOrBuilder getAmountOrBuilder() {
      if (amountBuilder_ != null) {
        return amountBuilder_.getMessageOrBuilder();
      } else {
        return amount_ == null ?
            u1467085.feedbins.StuffAmount.getDefaultInstance() : amount_;
      }
    }
    /**
     * <code>.feedbins.StuffAmount amount = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        u1467085.feedbins.StuffAmount, u1467085.feedbins.StuffAmount.Builder, u1467085.feedbins.StuffAmountOrBuilder> 
        getAmountFieldBuilder() {
      if (amountBuilder_ == null) {
        amountBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            u1467085.feedbins.StuffAmount, u1467085.feedbins.StuffAmount.Builder, u1467085.feedbins.StuffAmountOrBuilder>(
                getAmount(),
                getParentForChildren(),
                isClean());
        amount_ = null;
      }
      return amountBuilder_;
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


    // @@protoc_insertion_point(builder_scope:feedbins.BinStatusUpdate)
  }

  // @@protoc_insertion_point(class_scope:feedbins.BinStatusUpdate)
  private static final u1467085.feedbins.BinStatusUpdate DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new u1467085.feedbins.BinStatusUpdate();
  }

  public static u1467085.feedbins.BinStatusUpdate getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BinStatusUpdate>
      PARSER = new com.google.protobuf.AbstractParser<BinStatusUpdate>() {
    @java.lang.Override
    public BinStatusUpdate parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new BinStatusUpdate(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BinStatusUpdate> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BinStatusUpdate> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public u1467085.feedbins.BinStatusUpdate getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

