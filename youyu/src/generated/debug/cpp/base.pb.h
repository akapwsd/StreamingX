// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: base.proto

#ifndef PROTOBUF_base_2eproto__INCLUDED
#define PROTOBUF_base_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3001000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3001000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message.h>
#include <google/protobuf/repeated_field.h>
#include <google/protobuf/extension_set.h>
#include <google/protobuf/unknown_field_set.h>
// @@protoc_insertion_point(includes)

namespace batprotobuf {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_base_2eproto();
void protobuf_InitDefaults_base_2eproto();
void protobuf_AssignDesc_base_2eproto();
void protobuf_ShutdownFile_base_2eproto();

class messageFrame;

// ===================================================================

class messageFrame : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:batprotobuf.messageFrame) */ {
 public:
  messageFrame();
  virtual ~messageFrame();

  messageFrame(const messageFrame& from);

  inline messageFrame& operator=(const messageFrame& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const messageFrame& default_instance();

  static const messageFrame* internal_default_instance();

  void Swap(messageFrame* other);

  // implements Message ----------------------------------------------

  inline messageFrame* New() const { return New(NULL); }

  messageFrame* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const messageFrame& from);
  void MergeFrom(const messageFrame& from);
  void Clear();
  bool IsInitialized() const;

  size_t ByteSizeLong() const;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input);
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const;
  ::google::protobuf::uint8* InternalSerializeWithCachedSizesToArray(
      bool deterministic, ::google::protobuf::uint8* output) const;
  ::google::protobuf::uint8* SerializeWithCachedSizesToArray(::google::protobuf::uint8* output) const {
    return InternalSerializeWithCachedSizesToArray(false, output);
  }
  int GetCachedSize() const { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(messageFrame* other);
  void UnsafeMergeFrom(const messageFrame& from);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return _internal_metadata_.arena();
  }
  inline void* MaybeArenaPtr() const {
    return _internal_metadata_.raw_arena_ptr();
  }
  public:

  ::google::protobuf::Metadata GetMetadata() const;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // optional uint32 crc32 = 1;
  void clear_crc32();
  static const int kCrc32FieldNumber = 1;
  ::google::protobuf::uint32 crc32() const;
  void set_crc32(::google::protobuf::uint32 value);

  // optional bytes data = 2;
  void clear_data();
  static const int kDataFieldNumber = 2;
  const ::std::string& data() const;
  void set_data(const ::std::string& value);
  void set_data(const char* value);
  void set_data(const void* value, size_t size);
  ::std::string* mutable_data();
  ::std::string* release_data();
  void set_allocated_data(::std::string* data);

  // @@protoc_insertion_point(class_scope:batprotobuf.messageFrame)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr data_;
  ::google::protobuf::uint32 crc32_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_base_2eproto_impl();
  friend void  protobuf_AddDesc_base_2eproto_impl();
  friend void protobuf_AssignDesc_base_2eproto();
  friend void protobuf_ShutdownFile_base_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<messageFrame> messageFrame_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// messageFrame

// optional uint32 crc32 = 1;
inline void messageFrame::clear_crc32() {
  crc32_ = 0u;
}
inline ::google::protobuf::uint32 messageFrame::crc32() const {
  // @@protoc_insertion_point(field_get:batprotobuf.messageFrame.crc32)
  return crc32_;
}
inline void messageFrame::set_crc32(::google::protobuf::uint32 value) {
  
  crc32_ = value;
  // @@protoc_insertion_point(field_set:batprotobuf.messageFrame.crc32)
}

// optional bytes data = 2;
inline void messageFrame::clear_data() {
  data_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& messageFrame::data() const {
  // @@protoc_insertion_point(field_get:batprotobuf.messageFrame.data)
  return data_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void messageFrame::set_data(const ::std::string& value) {
  
  data_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:batprotobuf.messageFrame.data)
}
inline void messageFrame::set_data(const char* value) {
  
  data_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:batprotobuf.messageFrame.data)
}
inline void messageFrame::set_data(const void* value, size_t size) {
  
  data_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:batprotobuf.messageFrame.data)
}
inline ::std::string* messageFrame::mutable_data() {
  
  // @@protoc_insertion_point(field_mutable:batprotobuf.messageFrame.data)
  return data_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* messageFrame::release_data() {
  // @@protoc_insertion_point(field_release:batprotobuf.messageFrame.data)
  
  return data_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void messageFrame::set_allocated_data(::std::string* data) {
  if (data != NULL) {
    
  } else {
    
  }
  data_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), data);
  // @@protoc_insertion_point(field_set_allocated:batprotobuf.messageFrame.data)
}

inline const messageFrame* messageFrame::internal_default_instance() {
  return &messageFrame_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS

// @@protoc_insertion_point(namespace_scope)

}  // namespace batprotobuf

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_base_2eproto__INCLUDED