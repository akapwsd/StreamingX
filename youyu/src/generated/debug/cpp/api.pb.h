// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: api.proto

#ifndef PROTOBUF_api_2eproto__INCLUDED
#define PROTOBUF_api_2eproto__INCLUDED

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

namespace uyujoy {
namespace com {
namespace api {
namespace gateway {
namespace frontend {

// Internal implementation detail -- do not call these.
void protobuf_AddDesc_api_2eproto();
void protobuf_InitDefaults_api_2eproto();
void protobuf_AssignDesc_api_2eproto();
void protobuf_ShutdownFile_api_2eproto();

class ping;
class pong;

// ===================================================================

class ping : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.com.api.gateway.frontend.ping) */ {
 public:
  ping();
  virtual ~ping();

  ping(const ping& from);

  inline ping& operator=(const ping& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const ping& default_instance();

  static const ping* internal_default_instance();

  void Swap(ping* other);

  // implements Message ----------------------------------------------

  inline ping* New() const { return New(NULL); }

  ping* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const ping& from);
  void MergeFrom(const ping& from);
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
  void InternalSwap(ping* other);
  void UnsafeMergeFrom(const ping& from);
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

  // optional string channelId = 1;
  void clear_channelid();
  static const int kChannelIdFieldNumber = 1;
  const ::std::string& channelid() const;
  void set_channelid(const ::std::string& value);
  void set_channelid(const char* value);
  void set_channelid(const char* value, size_t size);
  ::std::string* mutable_channelid();
  ::std::string* release_channelid();
  void set_allocated_channelid(::std::string* channelid);

  // @@protoc_insertion_point(class_scope:uyujoy.com.api.gateway.frontend.ping)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  ::google::protobuf::internal::ArenaStringPtr channelid_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_api_2eproto_impl();
  friend void  protobuf_AddDesc_api_2eproto_impl();
  friend void protobuf_AssignDesc_api_2eproto();
  friend void protobuf_ShutdownFile_api_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<ping> ping_default_instance_;

// -------------------------------------------------------------------

class pong : public ::google::protobuf::Message /* @@protoc_insertion_point(class_definition:uyujoy.com.api.gateway.frontend.pong) */ {
 public:
  pong();
  virtual ~pong();

  pong(const pong& from);

  inline pong& operator=(const pong& from) {
    CopyFrom(from);
    return *this;
  }

  static const ::google::protobuf::Descriptor* descriptor();
  static const pong& default_instance();

  static const pong* internal_default_instance();

  void Swap(pong* other);

  // implements Message ----------------------------------------------

  inline pong* New() const { return New(NULL); }

  pong* New(::google::protobuf::Arena* arena) const;
  void CopyFrom(const ::google::protobuf::Message& from);
  void MergeFrom(const ::google::protobuf::Message& from);
  void CopyFrom(const pong& from);
  void MergeFrom(const pong& from);
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
  void InternalSwap(pong* other);
  void UnsafeMergeFrom(const pong& from);
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

  // @@protoc_insertion_point(class_scope:uyujoy.com.api.gateway.frontend.pong)
 private:

  ::google::protobuf::internal::InternalMetadataWithArena _internal_metadata_;
  mutable int _cached_size_;
  friend void  protobuf_InitDefaults_api_2eproto_impl();
  friend void  protobuf_AddDesc_api_2eproto_impl();
  friend void protobuf_AssignDesc_api_2eproto();
  friend void protobuf_ShutdownFile_api_2eproto();

  void InitAsDefaultInstance();
};
extern ::google::protobuf::internal::ExplicitlyConstructed<pong> pong_default_instance_;

// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// ping

// optional string channelId = 1;
inline void ping::clear_channelid() {
  channelid_.ClearToEmptyNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline const ::std::string& ping::channelid() const {
  // @@protoc_insertion_point(field_get:uyujoy.com.api.gateway.frontend.ping.channelId)
  return channelid_.GetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void ping::set_channelid(const ::std::string& value) {
  
  channelid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), value);
  // @@protoc_insertion_point(field_set:uyujoy.com.api.gateway.frontend.ping.channelId)
}
inline void ping::set_channelid(const char* value) {
  
  channelid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), ::std::string(value));
  // @@protoc_insertion_point(field_set_char:uyujoy.com.api.gateway.frontend.ping.channelId)
}
inline void ping::set_channelid(const char* value, size_t size) {
  
  channelid_.SetNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(),
      ::std::string(reinterpret_cast<const char*>(value), size));
  // @@protoc_insertion_point(field_set_pointer:uyujoy.com.api.gateway.frontend.ping.channelId)
}
inline ::std::string* ping::mutable_channelid() {
  
  // @@protoc_insertion_point(field_mutable:uyujoy.com.api.gateway.frontend.ping.channelId)
  return channelid_.MutableNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline ::std::string* ping::release_channelid() {
  // @@protoc_insertion_point(field_release:uyujoy.com.api.gateway.frontend.ping.channelId)
  
  return channelid_.ReleaseNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited());
}
inline void ping::set_allocated_channelid(::std::string* channelid) {
  if (channelid != NULL) {
    
  } else {
    
  }
  channelid_.SetAllocatedNoArena(&::google::protobuf::internal::GetEmptyStringAlreadyInited(), channelid);
  // @@protoc_insertion_point(field_set_allocated:uyujoy.com.api.gateway.frontend.ping.channelId)
}

inline const ping* ping::internal_default_instance() {
  return &ping_default_instance_.get();
}
// -------------------------------------------------------------------

// pong

inline const pong* pong::internal_default_instance() {
  return &pong_default_instance_.get();
}
#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)

}  // namespace frontend
}  // namespace gateway
}  // namespace api
}  // namespace com
}  // namespace uyujoy

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_api_2eproto__INCLUDED