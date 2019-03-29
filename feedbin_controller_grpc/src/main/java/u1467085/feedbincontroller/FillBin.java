// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: controller.proto

package u1467085.feedbincontroller;

/**
 * Protobuf type {@code feedbins.FillBin}
 */
public  final class FillBin extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:feedbins.FillBin)
    FillBinOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FillBin.newBuilder() to construct.
  private FillBin(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FillBin() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FillBin(
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
            u1467085.feedbincontroller.BinId.Builder subBuilder = null;
            if (id_ != null) {
              subBuilder = id_.toBuilder();
            }
            id_ = input.readMessage(u1467085.feedbincontroller.BinId.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(id_);
              id_ = subBuilder.buildPartial();
            }

            break;
          }
          case 16: {

            amount_ = input.readInt32();
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
    return u1467085.feedbincontroller.FeedBinController.internal_static_feedbins_FillBin_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return u1467085.feedbincontroller.FeedBinController.internal_static_feedbins_FillBin_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            u1467085.feedbincontroller.FillBin.class, u1467085.feedbincontroller.FillBin.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private u1467085.feedbincontroller.BinId id_;
  /**
   * <code>.feedbins.BinId id = 1;</code>
   */
  public boolean hasId() {
    return id_ != null;
  }
  /**
   * <code>.feedbins.BinId id = 1;</code>
   */
  public u1467085.feedbincontroller.BinId getId() {
    return id_ == null ? u1467085.feedbincontroller.BinId.getDefaultInstance() : id_;
  }
  /**
   * <code>.feedbins.BinId id = 1;</code>
   */
  public u1467085.feedbincontroller.BinIdOrBuilder getIdOrBuilder() {
    return getId();
  }

  public static final int AMOUNT_FIELD_NUMBER = 2;
  private int amount_;
  /**
   * <code>int32 amount = 2;</code>
   */
  public int getAmount() {
    return amount_;
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
    if (id_ != null) {
      output.writeMessage(1, getId());
    }
    if (amount_ != 0) {
      output.writeInt32(2, amount_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getId());
    }
    if (amount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, amount_);
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
    if (!(obj instanceof u1467085.feedbincontroller.FillBin)) {
      return super.equals(obj);
    }
    u1467085.feedbincontroller.FillBin other = (u1467085.feedbincontroller.FillBin) obj;

    if (hasId() != other.hasId()) return false;
    if (hasId()) {
      if (!getId()
          .equals(other.getId())) return false;
    }
    if (getAmount()
        != other.getAmount()) return false;
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
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
    }
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getAmount();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static u1467085.feedbincontroller.FillBin parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static u1467085.feedbincontroller.FillBin parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static u1467085.feedbincontroller.FillBin parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static u1467085.feedbincontroller.FillBin parseFrom(
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
  public static Builder newBuilder(u1467085.feedbincontroller.FillBin prototype) {
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
   * Protobuf type {@code feedbins.FillBin}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:feedbins.FillBin)
      u1467085.feedbincontroller.FillBinOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return u1467085.feedbincontroller.FeedBinController.internal_static_feedbins_FillBin_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return u1467085.feedbincontroller.FeedBinController.internal_static_feedbins_FillBin_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              u1467085.feedbincontroller.FillBin.class, u1467085.feedbincontroller.FillBin.Builder.class);
    }

    // Construct using u1467085.feedbincontroller.FillBin.newBuilder()
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
      if (idBuilder_ == null) {
        id_ = null;
      } else {
        id_ = null;
        idBuilder_ = null;
      }
      amount_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return u1467085.feedbincontroller.FeedBinController.internal_static_feedbins_FillBin_descriptor;
    }

    @java.lang.Override
    public u1467085.feedbincontroller.FillBin getDefaultInstanceForType() {
      return u1467085.feedbincontroller.FillBin.getDefaultInstance();
    }

    @java.lang.Override
    public u1467085.feedbincontroller.FillBin build() {
      u1467085.feedbincontroller.FillBin result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public u1467085.feedbincontroller.FillBin buildPartial() {
      u1467085.feedbincontroller.FillBin result = new u1467085.feedbincontroller.FillBin(this);
      if (idBuilder_ == null) {
        result.id_ = id_;
      } else {
        result.id_ = idBuilder_.build();
      }
      result.amount_ = amount_;
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
      if (other instanceof u1467085.feedbincontroller.FillBin) {
        return mergeFrom((u1467085.feedbincontroller.FillBin)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(u1467085.feedbincontroller.FillBin other) {
      if (other == u1467085.feedbincontroller.FillBin.getDefaultInstance()) return this;
      if (other.hasId()) {
        mergeId(other.getId());
      }
      if (other.getAmount() != 0) {
        setAmount(other.getAmount());
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
      u1467085.feedbincontroller.FillBin parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (u1467085.feedbincontroller.FillBin) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private u1467085.feedbincontroller.BinId id_;
    private com.google.protobuf.SingleFieldBuilderV3<
        u1467085.feedbincontroller.BinId, u1467085.feedbincontroller.BinId.Builder, u1467085.feedbincontroller.BinIdOrBuilder> idBuilder_;
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public boolean hasId() {
      return idBuilder_ != null || id_ != null;
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public u1467085.feedbincontroller.BinId getId() {
      if (idBuilder_ == null) {
        return id_ == null ? u1467085.feedbincontroller.BinId.getDefaultInstance() : id_;
      } else {
        return idBuilder_.getMessage();
      }
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public Builder setId(u1467085.feedbincontroller.BinId value) {
      if (idBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        id_ = value;
        onChanged();
      } else {
        idBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public Builder setId(
        u1467085.feedbincontroller.BinId.Builder builderForValue) {
      if (idBuilder_ == null) {
        id_ = builderForValue.build();
        onChanged();
      } else {
        idBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public Builder mergeId(u1467085.feedbincontroller.BinId value) {
      if (idBuilder_ == null) {
        if (id_ != null) {
          id_ =
            u1467085.feedbincontroller.BinId.newBuilder(id_).mergeFrom(value).buildPartial();
        } else {
          id_ = value;
        }
        onChanged();
      } else {
        idBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public Builder clearId() {
      if (idBuilder_ == null) {
        id_ = null;
        onChanged();
      } else {
        id_ = null;
        idBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public u1467085.feedbincontroller.BinId.Builder getIdBuilder() {
      
      onChanged();
      return getIdFieldBuilder().getBuilder();
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    public u1467085.feedbincontroller.BinIdOrBuilder getIdOrBuilder() {
      if (idBuilder_ != null) {
        return idBuilder_.getMessageOrBuilder();
      } else {
        return id_ == null ?
            u1467085.feedbincontroller.BinId.getDefaultInstance() : id_;
      }
    }
    /**
     * <code>.feedbins.BinId id = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        u1467085.feedbincontroller.BinId, u1467085.feedbincontroller.BinId.Builder, u1467085.feedbincontroller.BinIdOrBuilder> 
        getIdFieldBuilder() {
      if (idBuilder_ == null) {
        idBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            u1467085.feedbincontroller.BinId, u1467085.feedbincontroller.BinId.Builder, u1467085.feedbincontroller.BinIdOrBuilder>(
                getId(),
                getParentForChildren(),
                isClean());
        id_ = null;
      }
      return idBuilder_;
    }

    private int amount_ ;
    /**
     * <code>int32 amount = 2;</code>
     */
    public int getAmount() {
      return amount_;
    }
    /**
     * <code>int32 amount = 2;</code>
     */
    public Builder setAmount(int value) {
      
      amount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 amount = 2;</code>
     */
    public Builder clearAmount() {
      
      amount_ = 0;
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


    // @@protoc_insertion_point(builder_scope:feedbins.FillBin)
  }

  // @@protoc_insertion_point(class_scope:feedbins.FillBin)
  private static final u1467085.feedbincontroller.FillBin DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new u1467085.feedbincontroller.FillBin();
  }

  public static u1467085.feedbincontroller.FillBin getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FillBin>
      PARSER = new com.google.protobuf.AbstractParser<FillBin>() {
    @java.lang.Override
    public FillBin parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new FillBin(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FillBin> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FillBin> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public u1467085.feedbincontroller.FillBin getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
